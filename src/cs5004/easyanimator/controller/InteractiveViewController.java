package cs5004.easyanimator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.shapes.ShapesVisitorImpl;
import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the Visual Animation view. Implements
 * AnimationController and its associated methods.
 */
public class InteractiveViewController implements AnimationController, ActionListener {
  private AnimationModel model;
  private View view;
  private double speed;
  private boolean looped;
  private String filename;
  private Timer timer;
  private ArrayList<Shapes> newShapesList;
  private int endTime;
  private double elapsedTime;
  private Appendable log;

  /**
   * Create an InteractiveController object with its given model, view, speed, and the name of
   * the file that the controller will write out to.
   *
   * @param model used by the controller
   * @param view used by the controller
   * @param speed at which the animation occurs
   * @param filename that the controller will write out to
   */
  public InteractiveViewController(AnimationModel model, View view, double speed, String filename) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.looped = false;
    this.filename = filename;
    this.elapsedTime = 0;
    this.endTime = model.getEnd();
    this.log = new StringBuffer();
  }

  /**
   * Starts the animation.
   */
  public void start() {
    this.view.setButtonListener(this);

    for (JCheckBox j : view.getCheckBoxList()) {
      j.addItemListener(new HandlerClass());
    }

    // display the view
    this.view.display();
    // set new shape list
    this.setNewShapesList();
    // create new timer
    this.timer = new Timer(0, timerListener);

    for (Animations a : model.getAnimations()) {
      String sName = a.getShape().getName();

      for (Shapes s : newShapesList) {
        Shapes currentS = s;
        if (currentS.getName().equals(sName)) {
          a.resetShape(currentS);
        }
      }
    }
    // output to file
    this.view.write(filename);
  }

  /**
   * Get the log from this controller.
   *
   * @return The log of this controller
   */
  public Appendable getLog() {
    return this.log;
  }

  /**
   * Get the timer from this controller.
   * @return The timer of this controller
   */
  public Timer getTimer() {
    return this.timer;
  }

  /**
   * Updates the model's original list of shapes by creating a new list of shapes, and sets it to
   * be the model's original list of shapes.
   */
  private void setNewShapesList() {
    newShapesList = new ArrayList<Shapes>();

    // iterate through model's shapes and create a
    // new list of shapes
    for (Shapes s : model.getShapes()) {
      Shapes newS = s;
      newShapesList.add(newS);
    }
  }

  // create new action listener
  ActionListener timerListener = new ActionListener() {

    /**
     * Overrides the ActionListener method.
     *
     * @param e the event
     */
    public void actionPerformed(ActionEvent e) {

      elapsedTime += speed / 1000;

      if (looped && (endTime - elapsedTime < 0.000001)) {
        elapsedTime = 0;

        timer.restart();

        newShapesList = new ArrayList<Shapes>();

        for (Shapes s : model.getShapes()) {
          Shapes newS = s.visitShape(new ShapesVisitorImpl());
          newShapesList.add(newS);
        }
      }
      else if (endTime - elapsedTime < 0.000001) {
        timer.stop();
      }

      for (Animations a : model.getAnimations()) {
        Animations currentA = a;

        int start = a.getStartTime();
        int end = a.getEndTime();

        if (start <= elapsedTime && end >= elapsedTime) {
          a.implementAnimation(elapsedTime);
          view.setShapes(newShapesList);
          view.refresh();
        }
      }
      view.display();
    }
  };

  /**
   * Overrides the ActionListener method.
   *
   * @param e the event
   */
  public void actionPerformed(ActionEvent e) {
    // evaluate event
    switch (e.getActionCommand()) {
      case "Play":
        this.appendToLog("You pressed the play button. \n");
        this.timer.start();
        this.displayCheckBoxes(false);
        // offer SVG functionality
        System.out.println(log.toString());
        break;
      case "Pause":
        this.appendToLog("You pressed the pause button. \n");
        this.timer.stop();
        this.displayCheckBoxes(true);
        break;
      case "Restart":
        this.appendToLog("You pressed the restart button. \n");
        this.timer.restart();
        this.displayCheckBoxes(false);
        this.setNewShapesList();
        elapsedTime = 0;
        break;
      case "Loop":
        this.appendToLog("You pressed the loop button.\n");
        looped = !looped;
        view.setIsLoop(looped);
        this.displayCheckBoxes(true);
        break;
      case "Increase Speed":
        this.appendToLog("You pressed the increase speed button.\n");
        this.displayCheckBoxes(false);
        speed += 10;
        elapsedTime -= (speed) / 1000;
        break;
      case "Decrease Speed":
        this.appendToLog("You pressed the decrease speed button.\n");
        this.displayCheckBoxes(false);
        if (speed <= 0) {
          speed = 0;
        }
        else {
          speed -= 10;
        }
        elapsedTime += (speed) / 1000;
        break;
      case "Set file":
        this.appendToLog("You pressed the set file button.\n");
        this.displayCheckBoxes(true);
        filename = view.getFilename();
        break;
      case "Export":
        this.appendToLog("You pressed the export button.\n");
        setFilename(filename);
      default:
        break;
    }
  }

  /**
   * Display or hide checkboxes.
   *
   * @param enable true to display checkboxes, false to hide them
   */
  private void displayCheckBoxes(boolean enable) {
    for (JCheckBox j : view.getCheckBoxList()) {
      j.setEnabled(enable);
    }
  }

  /**
   * A class to handle operations of a checkbox's item listener.
   */
  private class HandlerClass implements ItemListener {

    /**
     * Overrides the ItemListener method.
     *
     * @param e the item event
     */
    public void itemStateChanged(ItemEvent e) {
      for (JCheckBox j : view.getCheckBoxList()) {
        if (!j.isSelected()) {
          changeRenderValue(j.getAccessibleContext().getAccessibleName(), false);
        }
        else {
          changeRenderValue(j.getAccessibleContext().getAccessibleName(), true);
        }
      }
    }

    /**
     * Changes the shape's display value to false.
     *
     * @param name of the shape
     * @param display, the boolean that will be changed
     */
    private void changeRenderValue(String name, boolean display) {
      for (int i = 0; i < model.getShapes().size(); i++) {
        if (name.equals(model.getShapes().get(i).getName())) {
          view.getShapes().get(i).changeDisplayValue(display);
          model.getShapes().get(i).changeDisplayValue(display);
          newShapesList.get(i).changeDisplayValue(display);
        }
      }
    }
  }

  /**
   * Sets the file name to write out to.
   *
   * @param command The new filename that the user determines for the animation
   */
  private void setFilename(String command) {
    try {
      this.view.write(command);

    } catch (Exception e) {
      view.displayErrorMsg("Invalid filename.");
    }
  }

  /**
   * Append an entry into the log.
   *
   * @param entry The string that will be appended to the log.
   */
  private void appendToLog(String entry) {
    try {
      this.log.append(entry);

    } catch (IOException e) {
      //do nothing
    }
  }
}