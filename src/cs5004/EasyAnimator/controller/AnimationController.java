package cs5004.EasyAnimator.controller;
import javax.swing.Timer;

/**
 * This is the interface of the Animation controller, which is implemented by the SVGView,
 * TextualView, and VisualView Controller classes. They all use the start() method to begin the
 * animation.
 */

public interface AnimationController {
  /**
   * Starts the animation using the provided model.
   */
  void start();

  /**
   * Retrieve this controller's log.
   *
   * @return this controller's log as an Appendable.
   * @throws UnsupportedOperationException if the controller does not support this
   *                                       functionality
   */
  Appendable getLog();

  /**
   * Retrieve this controller's timer.
   * @return  this controller's timer.
   * @throws UnsupportedOperationException if the controller does not support the
   *                                       functionality
   */
  Timer getTimer();
}
