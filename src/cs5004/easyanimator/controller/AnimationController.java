package cs5004.easyanimator.controller;

import javax.swing.Timer;

/**
 * This interface represents the AnimationController. Its primary purpose is
 * to start the animation. Classes that implement the interface only need
 * the start() method to begin the animation.
 */
public interface AnimationController {

  /**
   * Starts the animation.
   */
  void start();

  /**
   * Get the log from this controller.
   *
   * @return The log of this controller
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  Appendable getLog();

  /**
   * Get the timer from this controller.
   * @return The timer of this controller
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  Timer getTimer();
}