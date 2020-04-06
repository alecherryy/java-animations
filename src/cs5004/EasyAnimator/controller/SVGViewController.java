package cs5004.EasyAnimator.controller;
import cs5004.EasyAnimator.view.View;
import javax.swing.*;

public class SVGViewController implements AnimationController {
  private View view;
  private String filename;

  /**
   * Constructs an SVGViewController object with its given view and filename.
   *
   * @param view     the view that the controller will be using to display.
   * @param filename the output file that the controller will write to.
   */
  public SVGViewController(View view, String filename) {
    this.view = view;
    this.filename = filename;
  }

  @Override
  public void start() {
    view.write(filename);

  }

  @Override
  public Appendable getLog() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public Timer getTimer() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
