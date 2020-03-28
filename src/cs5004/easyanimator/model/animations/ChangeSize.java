package cs5004.easyanimator.model.animations;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This class represents the third animation type -- changing the size of a shape,
 * using its width and height. It extends AbstractAnimation.
 */
public class ChangeSize extends AbstractAnimations {
  private double originalWidth;
  private double originalHeight;
  private double newWidth;
  private double newHeight;

  /**
   * Constructs a Change Size object, with its given shape, start time,
   * end time, original width, original height, new width, and new height.
   * Calls the AbstractAnimations super-constructor and sets the
   * AnimationType parameter to CHANGESIZE.
   *
   * @param shape          the shape will be animated, type Shape.
   * @param start          the start time of the animation, an int.
   * @param end            the end time of the animation, an int.
   * @param originalWidth  the original coordinates of the object, type Pair.
   * @param originalHeight the original height of the object, type double.
   * @param newWidth       the new width of the object, to which the width will be changed.
   * @param newHeight      the new height of the object, to which the height will be changed.
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
  public double getOriginalWidth() { return originalWidth; }

  /**
   * Get the original height of the shape.
   *
   * @return the original height of the shape
   */
  public double getOriginalHeight() { return originalHeight; }

  /**
   * Get the new width of the shape.
   *
   * @return the new width of the shape
   */
  public double getNewWidth() { return newWidth; }

  /**
   * Get the new height of the shape.
   *
   * @return the new height of the shape
   */
  public double getNewHeight() { return newHeight; }

  /**
   * Returns the string "moves".
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
    return Utils.formatSizeString(this.getShape(), this.originalWidth, this.originalHeight);
  }

  /**
   * Get the end state of the animation as a string.
   *
   * @return the end state of the animation as a string
   */
  public String getEndState() {
    return Utils.formatSizeString(this.getShape(), this.newWidth, this.newHeight);
  }

  /**
   * Implements the ChangeSize animation on a shape.
   *
   * @param time the current time of the animation
   */
  public void implementAnimation(double time) {
    double changeWidth = this.newWidth - this.originalWidth;
    double changeHeight = this.newHeight - this.originalHeight;

    double changeInTime = (time - this.getStartTime())
            / (double) (this.getEndTime() - this.getStartTime());

    if ((time > this.getEndTime()) || (time < this.getStartTime())) {
      // do nothing.
      return;
    } else {
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
  public void updateField(Shape s) {
    s.changeWidth(newWidth);
    s.changeHeight(newHeight);
  }
}