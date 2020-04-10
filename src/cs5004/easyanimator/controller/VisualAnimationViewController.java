package cs5004.easyanimator.controller;

import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.shapes.ShapesVisitorImpl;
import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the Visual Animation view.
 * Implements AnimationController and its associated methods.
 */
public class VisualAnimationViewController implements AnimationController{
  private AnimationModel model;
  private View view;
  private double speed;
  private boolean started;

  /**
   * Constructs a VisualAnimationViewController object with its given model, view, and speed.
   *
   * @param model    The model that will be used by the controller
   * @param view     The view that will be used by the controller
   * @param speed    The speed at which the animation occurs
   */
  public VisualAnimationViewController(AnimationModel model, View view, double speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.started = false;
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

    ArrayList<Animations> animations = model.getAnimations();
    ArrayList<Shapes> shapes = model.getShapes();


    ArrayList<Shapes> newShapesList = new ArrayList<Shapes>();

    for (Shapes s : shapes) {
      Shapes newS = s.visitShape(new ShapesVisitorImpl());
      newShapesList.add(newS);
    }

    while (this.started) {
      timeElapsed = System.currentTimeMillis() - startTime;
      secondsElapsed = timeElapsed / 1000.0;
      unitsElapsed = secondsElapsed * speed;

      for (Animations a : animations) {
        Animations currentA = a;
        String shapeName = currentA.getShape().getName();

        for (Shapes s : shapes) {
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
}
