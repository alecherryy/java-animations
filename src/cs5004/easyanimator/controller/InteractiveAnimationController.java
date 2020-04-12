package cs5004.easyanimator.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class InteractiveAnimationController implements AnimationController, ActionListener {
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
  public InteractiveAnimationController(View view, AnimationModel model, double speed,
                                        String filename) {
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
   * Create an InteractiveController object with its given model, view, speed, and the name of
   * the file that the controller will write out to.
   *
   * @param model used by the controller
   * @param view used by the controller
   * @param speed at which the animation occurs
   */
  public InteractiveAnimationController(View view, AnimationModel model, double speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.looped = false;
    this.elapsedTime = 0;
    this.endTime = model.getEnd();
    this.log = new StringBuffer();
  }

  /**
   * Starts the animation.
   */
  public void start() {
    this.view.setButtonListener(this);

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
        this.appendToLog("You pressed the play button.\n");
        this.timer.start();
        break;
      case "Pause":
        this.appendToLog("You pressed the pause button.\n");
        this.timer.stop();
        break;
      case "Restart":
        this.appendToLog("You pressed the restart button.\n");
        reset();
        break;
      case "Loop":
        this.appendToLog("You pressed the loop button.\n");
        looped = !looped;
        view.setIsLoop(looped);
        break;
      case "Increase":
        this.appendToLog("You pressed the increase speed button.\n");
        showMessagDialog("Speed increased by 10.");
        speed += 10;
        elapsedTime -= (speed) / 1000;
        break;
      case "Decrease":
        this.appendToLog("You pressed the decrease speed button.\n");
        showMessagDialog("Speed decreased by 10.");
        // if speed is negative, set it to 0
        if (speed <= 0) {
          speed = 0;
        }
        else {
          speed -= 10;
        }
        elapsedTime += (speed) / 1000;
        break;
      case "Save":
        this.appendToLog("You pressed the export button.\n");
        this.view.write("");
      case "Remove":
        this.appendToLog("You pressed the Remove Shape button.\n");
        this.model.removeShape(this.view.getTextFieldValue());
        reset();
        break;
      case "Add":
        this.appendToLog("You pressed the Add Shape button.\n");
        reset();
        break;
      default:
        break;
    }
  }

  /**
   * Private method to reset the model and view after making changes
   * to the model or hittin "Restart" in the UI.
   */
  private void reset() {
    this.timer.restart();
    this.setNewShapesList();
    elapsedTime = 0;
  }

  /**
   * Private helper method to display Message dialog when pressing
   * a button.
   *
   * @param message of the dialog window
   */
  private void showMessagDialog(String message) {
    JOptionPane.showMessageDialog((Component) this.view, message);
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