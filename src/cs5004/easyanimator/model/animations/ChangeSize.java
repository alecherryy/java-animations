package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This class represents changing the size of a shape. It extends AbstractAnimation and
 * implements all its methods.
 */
public class ChangeSize extends AbstractAnimations {
  private double originalWidth;
  private double originalHeight;
  private double newWidth;
  private double newHeight;

  /**
   * This is the class constructor; it takes 7 parameters: a shape, a start
   * time, an end time, the original width, the original height, the new width
   * and the new height.
   *
   * @param shape to be animated
   * @param start of the animation
   * @param end of the animation
   * @param originalWidth of the shape
   * @param originalHeight of the shape
   * @param newWidth of the shape
   * @param newHeight of the shape
   */
  public ChangeSize(Shape shape, int start, int end, double originalWidth, double originalHeight,
                    double newWidth, double newHeight) {
    super(AnimationType.CHANGESIZE, shape, start, end);
    this.originalWidth = originalWidth;
    this.originalHeight = originalHeight;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  /**
   * Get the original width of the shape.
   *
   * @return the original width of the shape
   */
  public double getOriginalWidth() {
    return originalWidth;
  }

  /**
   * Get the original height of the shape.
   *
   * @return the original height of the shape
   */
  public double getOriginalHeight() {
    return originalHeight;
  }

  /**
   * Get the new width of the shape.
   *
   * @return the new width of the shape
   */
  public double getNewWidth() {
    return newWidth;
  }

  /**
   * Get the new height of the shape.
   *
   * @return the new height of the shape
   */
  public double getNewHeight() {
    return newHeight;
  }

  /**
   * Get the string representation of what the animation is changing.
   *
   * @return the animation change as a string
   */
  public String getChange() { return "scales "; }

  /**
   * Get the starting state of the animation as a string.
   *
   * @return the starting state of the animation as a string
   */
  public String getStartState() {
    return "Width: " + originalWidth + " Height: " + originalHeight;
  }

  /**
   * Get the end state of the animation as a string.
   *
   * @return the end state of the animation as a string
   */
  public String getEndState() {
    return "Width: " + newWidth + " Height: " + newHeight;
  }

  /**
   * Implements the animation on a shape.
   *
   * @param time the current time of the animation
   */
  public void implementAnimation(double time) {
    double changeWidth = this.newWidth - this.originalWidth;
    double changeHeight = this.newHeight - this.originalHeight;

    double changeInTime = (time - this.getStartTime())
        / (double) (this.getEndTime() - this.getStartTime());

    if ((time > this.getEndTime()) || (time < this.getStartTime())) {
      // do nothing
      return;
    }
    else {
      double finalWidth = this.originalWidth + (changeInTime * changeWidth);
      double finalHeight = this.originalHeight + (changeInTime * changeHeight);
      this.getShape().changeWidth(finalWidth);
      this.getShape().changeHeight(finalHeight);
    }
  }

  /**
   * Changes the appropriate fields of the shape to match the changes
   * implemented on the shape.
   *
   * @param s a Shape object, whose field will be changed
   */
  public void changeField(Shape s) {
    s.changeWidth(newWidth);
    s.changeHeight(newHeight);
  }
}
