package cs5004.easyanimator.controller;

import javax.swing.*;

import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the SVG view. Implements AnimationController and its
 * associated methods.
 */

public class SVGViewController implements AnimationController {

  private View view;
  private String filename;

  /**
   * Constructs an SVGViewController object with its given view and filename.
   *
   * @param view     the view that the controller will be displaying on
   * @param filename the output file the controller will write to
   */
  public SVGViewController(View view, String filename) {
    this.view = view;
    this.filename = filename;
  }

  /**
   * Starts the animation.
   */
  public void start() {
    this.view.write(filename);

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
