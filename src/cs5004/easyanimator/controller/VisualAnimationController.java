package cs5004.easyanimator.controller;

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
public class VisualAnimationController implements AnimationController {
  private AnimationModel model;
  private View view;
  private double speed;
  private boolean started;

  /**
   * Constructs a VisualAnimationViewController object with
   * its given model, view, and speed.
   *
   * @param view that will be used by the controller
   * @param model that will be used by the controller
   * @param speed at which the animation occurs
   */
  public VisualAnimationController(View view, AnimationModel model, double speed) {
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

    ArrayList<Shapes> newShapesList = new ArrayList<Shapes>();

    for (Shapes s : model.getShapes()) {
      Shapes newS = s.visitShape(new ShapesVisitorImpl());
      newShapesList.add(newS);
    }

    while (this.started) {
      timeElapsed = System.currentTimeMillis() - startTime;
      secondsElapsed = timeElapsed / 1000.0;
      unitsElapsed = secondsElapsed * speed;

      for (Animations a : model.getAnimations()) {
        Animations currentA = a;
        Shapes animationS = currentA.getShape();
        for (Shapes s : newShapesList) {
          Shapes currentS = s;
          if (currentS.getName().equals(animationS.getName())) {
            currentA.changeShape(currentS);
          }
        }

        int start = a.getStartTime();
        int end = a.getEndTime();

        if (start <= unitsElapsed && end >= unitsElapsed) {
          a.implementAnimation(unitsElapsed);
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

  /**
   * Given a shape name, remove it from the list and remove all animations
   * associated with it.
   *
   * @param name The name of the shape
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  private void removeShape(String name) {
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
