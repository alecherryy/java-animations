package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This class represents the third animation type -- changing the size of a shape, using its
 * width and height. It extends AbstractAnimation.
 */

public class ChangeSize extends AbstractAnimations {
  private double originalWidth;
  private double originalHeight;
  private double newWidth;
  private double newHeight;

  /**
   * Constructs a Change Size object, with its given shape, start time, end time, original width,
   * original height, new width, and new height. Calls the AbstractAnimations super-constructor
   * and sets the AnimationType parameter to CHANGESIZE.
   *
   * @param shape the shape will be animated, type Shape.
   * @param start the start time of the animation, an int.
   * @param end   the end time of the animation, an int.
   * @param originalWidth the original coordinates of the object, type Pair.
   * @param originalHeight the original height of the object, type double.
   * @param newWidth the new width of the object, to which the width will be changed.
   * @param newHeight the new height of the object, to which the height will be changed.
   */

  public ChangeSize(Shape shape, int start, int end, double originalWidth, double originalHeight,
                    double newWidth, double newHeight) {
    super(AnimationType.CHANGESIZE, shape, start, end);
    this.originalWidth = originalWidth;
    this.originalHeight = originalHeight;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  public double getOriginalWidth() {
    return originalWidth;
  }

  public double getOriginalHeight() {
    return originalHeight;
  }

  public double getNewWidth() {
    return newWidth;
  }

  public double getNewHeight() {
    return newHeight;
  }

  @Override
  public String getChange() {
    // TODO returning null pointer error
    return "Changing shape size ";
  }

  @Override
  public String getStartState() {
    // TODO returning null pointer error
    return "width: " + originalWidth + " height: " + originalHeight;
  }

  @Override
  public String getEndState() {
    // TODO returning null pointer error
    return "width: " + newWidth + " height: " + newHeight;
  }

  @Override
  public void implementAnimation(double time) {
    double changeWidth = this.newWidth - this.originalWidth;
    double changeHeight = this.newHeight - this.originalHeight;

    double changeInTime = (time - this.getStartTime())
        / (double) (this.getEndTime() - this.getStartTime());

    if ((time > this.getEndTime()) || (time < this.getStartTime())) {
      // do nothing.
      return;
    }
    else {
      double finalWidth = this.originalWidth + (changeInTime * changeWidth);
      double finalHeight = this.originalHeight + (changeInTime * changeHeight);
      this.getShape().changeWidth(finalWidth);
      this.getShape().changeHeight(finalHeight);
    }
  }

  @Override
  public void changeField(Shape s) {
    s.changeWidth(newWidth);
    s.changeHeight(newHeight);

  }
}

