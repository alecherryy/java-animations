package cs5004.easyanimator.controller;

import javax.swing.Timer;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the Textual View. Implements
 * AnimationController and its associated methods.
 */
public class TextualAnimationController implements AnimationController {
  private AnimationModel model;
  private View view;
  private String filename;

  /**
   * Constructs a TextualViewController object with its given model, view and filename.
   *
   * @param view the view that the controller will be displaying on
   * @param model the animation model that the controller will use
   * @param filename the output file the controller will write to
   */
  public TextualAnimationController(View view, AnimationModel model, String filename) {
    this.model = model;
    this.view = view;
    this.filename = filename;
  }

  /**
   * Starts the animation.
   */
  public void start() {

    for (Animations a : model.getAnimations()) {
      Animations currentA = a;
      String shapeN = a.getShape().getName();

      for (Shapes s : model.getShapes()) {
        Shapes currentS = s;

        if (shapeN.equals(s.getName())) {
          currentA.updateField(currentS);
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
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  public Appendable getLog() {
    return null;
  }

  /**
   * Get the timer from this controller.
   * @return The timer of this controller
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  public Timer getTimer() {
    return null;
  }
}
