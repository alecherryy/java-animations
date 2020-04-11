package cs5004.easyanimator.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.Timer;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.shapes.ShapesVisitorImpl;
import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the Visual Animation view.
 * Implements AnimationController and its associated methods.
 */
public class VisualAnimationController implements AnimationController, ActionListener {
  private AnimationModel model;
  private View view;
  private double speed;
  private boolean started;
  private Appendable log;

  /**
   * Constructs a VisualAnimationViewController object with
   * its given model, view, and speed.
   *
   * @param model that will be used by the controller
   * @param view that will be used by the controller
   * @param speed at which the animation occurs
   */
  public VisualAnimationController(AnimationModel model, View view, double speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.started = false;
    this.log = new StringBuffer();
  }

  /**
   * Starts the animation.
   */
  public void start() {
    this.started = true;
    long startTime = System.currentTimeMillis();
    long timeElapsed = 0;

    double secondsElapsed = 0;
    double unitsElapsed = 0;

    ArrayList<Shapes> newShapesList = new ArrayList<Shapes>();

    for (Shapes s : model.getShapes()) {
      Shapes newS = s.visitShape(new ShapesVisitorImpl());
      newShapesList.add(newS);
    }

    this.view.setButtonListener(this);

    while (this.started) {
      timeElapsed = System.currentTimeMillis() - startTime;
      secondsElapsed = timeElapsed / 1000.0;
      unitsElapsed = secondsElapsed * speed;

      for (Animations a : model.getAnimations()) {
        Animations currentA = a;
        String shapeName = currentA.getShape().getName();

        for (Shapes s : model.getShapes()) {
          Shapes currentS = s;

          if (s.getName().equals(shapeName)) {
            currentA.changeShape(currentS);
          }
        }

        int start = currentA.getStartTime();
        int end = currentA.getEndTime();

        if (start <= unitsElapsed && end >= unitsElapsed) {
          currentA.implementAnimation(unitsElapsed);
          this.view.setShapes(newShapesList);
          this.view.refresh();
        }
      }
      this.view.display();
    }
  }

  /**
   * Overrides the ActionListener method.
   *
   * @param e the event
   */
  public void actionPerformed(ActionEvent e) {
    // evaluate event
    switch (e.getActionCommand()) {
      case "Remove":
        this.appendToLog("You pressed the Remove Shape button.\n");
        String name = view.getTextFieldValue();
        removeShape(name);
        break;
      case "Add":
        this.appendToLog("You pressed the Add Shape button.\n");
      case "Save":
        this.appendToLog("You pressed the Save button.\n");
        this.view.write("test-save.svg");
      default:
        break;
    }
  }


  /**
   * Get the log from this controller.
   *
   * @return The log of this controller
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  public Appendable getLog() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  /**
   * Get the timer from this controller.
   * @return The timer of this controller
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  public Timer getTimer() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  /**
   * Given a shape name, remove it from the list and remove all animations
   * associated with it.
   *
   * @param name The name of the shape
   */
  private void removeShape(String name) {
    this.model.removeShape(name);
    this.view.refresh();
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
