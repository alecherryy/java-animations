package cs5004.easyanimator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;
import javax.swing.JCheckBox;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.shapes.ShapesVisitorImpl;
import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the Visual Animation view. Implements AnimationController and its
 * associated methods.
 */

public class InteractiveViewController implements AnimationController, ActionListener {
  private AnimationModel model;
  private View view;
  private double speed;
  private boolean isLoop;
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
   * @param model    The model that will be used by the controller
   * @param view     The view that will be used by the controller
   * @param speed    The speed at which the animation occurs
   * @param filename The filename that the controller will write out to
   */
  public InteractiveViewController(AnimationModel model, View view, double speed, String filename) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.isLoop = false;
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

    HandlerClass handler = new HandlerClass();

    for (int i = 0; i < view.getCheckBoxList().size(); i++) {
      JCheckBox current = view.getCheckBoxList().get(i);
      current.addItemListener(handler);
    }

    this.view.display();

    this.setNewShapesList();

    this.timer = new Timer(0, timerListener);


    List<Animations> animations = model.getAnimations();

    for (int i = 0; i < animations.size(); i++) {
      Animations currentAnimation = animations.get(i);
      Shapes animationShape = currentAnimation.getShape();
      for (int j = 0; j < newShapesList.size(); j++) {
        Shapes currentShape = newShapesList.get(j);
        if (currentShape.getName().equals(animationShape.getName())) {
          currentAnimation.resetShape(currentShape);
        }
      }
    }

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
    List<Shapes> shapes = model.getShapes();

    newShapesList = new ArrayList<Shapes>();

    for (int i = 0; i < shapes.size(); i++) {
      Shapes newShape = shapes.get(i).visitShape(new ShapesVisitorImpl());
      newShapesList.add(newShape);
    }
  }

  ActionListener timerListener = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {

      elapsedTime += speed / 1000;

      if (isLoop && (endTime - elapsedTime < 0.000001)) {
        elapsedTime = 0;

        timer.restart();

        List<Shapes> shapes = model.getShapes();

        newShapesList = new ArrayList<Shapes>();

        for (int i = 0; i < shapes.size(); i++) {
          Shapes newShape = shapes.get(i).visitShape(new ShapesVisitorImpl());
          newShapesList.add(newShape);
        }

      } else if (endTime - elapsedTime < 0.000001) {
        timer.stop();
      }

      List<Animations> animations = model.getAnimations();

      for (int i = 0; i < animations.size(); i++) {
        Animations current = animations.get(i);
        int start = current.getStartTime();
        int end = current.getEndTime();

        if (start <= elapsedTime && end >= elapsedTime) {
          current.implementAnimation(elapsedTime);
          view.setShapes(newShapesList);
          view.refresh();
        }
      }
      view.display();
    }
  };

  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Play":
        this.appendToLog("You pressed the play button. \n");
        this.timer.start();
        this.displayCheckBoxes(false);
        //Offer SVG functionality
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
        this.appendToLog("You pressed the loop button. \n");
        isLoop = !isLoop;
        view.setIsLoop(isLoop);
        this.displayCheckBoxes(true);
        break;
      case "Increase speed":
        this.appendToLog("You pressed the increase speed button. \n");
        this.displayCheckBoxes(false);
        speed += 5;
        elapsedTime -= (speed) / 1000;
        break;
      case "Decrease speed":
        this.appendToLog("You pressed the decrease speed button. \n");
        this.displayCheckBoxes(false);
        if (speed <= 0) {
          speed = 0;
        } else {
          speed -= 5;
        }
        elapsedTime += (speed) / 1000;
        break;
      case "Set file":
        this.appendToLog("You pressed the set file button. \n");
        this.displayCheckBoxes(true);
        filename = view.getFilename();
        break;
      case "Export":
        this.appendToLog("You pressed the export button. \n");
        setFilename(filename);
      default:
        // do nothing
    }
  }

  /**
   * Either displays or does not display the checkboxes.
   *
   * @param enable boolean for if you want to display or not display the check boxes
   */
  private void displayCheckBoxes(boolean enable) {
    for (int i = 0; i < view.getCheckBoxList().size(); i++) {
      JCheckBox current = view.getCheckBoxList().get(i);
      current.setEnabled(enable);
    }
  }

  /**
   * A class to handle operations of a checkbox's item listener.
   */
  private class HandlerClass implements ItemListener {

    @Override
    public void itemStateChanged(ItemEvent e) {
      for (int i = 0; i < view.getCheckBoxList().size(); i++) {
        if (!view.getCheckBoxList().get(i).isSelected()) {
          changeRenderValue(view.getCheckBoxList().get(i).getAccessibleContext().getAccessibleName()
              , false);
        } else {
          changeRenderValue(view.getCheckBoxList().get(i).getAccessibleContext().getAccessibleName()
              , true);
        }
      }
    }

    /**
     * Changes the shape's display value to false.
     *
     * @param shapeName the name of the shape
     * @param displayValue, the boolean that will be changed
     */
    private void changeRenderValue(String shapeName, boolean displayValue) {

      for (int i = 0; i < model.getShapes().size(); i++) {
        if (shapeName.equals(model.getShapes().get(i).getName())) {
          view.getShapes().get(i).changeDisplayValue(displayValue);
          model.getShapes().get(i).changeDisplayValue(displayValue);
          newShapesList.get(i).changeDisplayValue(displayValue);
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