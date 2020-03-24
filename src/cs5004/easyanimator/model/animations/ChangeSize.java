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
   * @return the original width of the shape.
   */
  public double getOriginalWidth() {
    return originalWidth;
  }

  /**
   * Get the original height of the shape.
   *
   * @return the original height of the shape.
   */
  public double getOriginalHeight() {
    return originalHeight;
  }

  /**
   * Get the new width of the shape.
   *
   * @return the new width of the shape.
   */
  public double getNewWidth() {
    return newWidth;
  }

  /**
   * Get the new height of the shape.
   *
   * @return the new height of the shape.
   */
  public double getNewHeight() {
    return newHeight;
  }

  @Override
  public String getChange() {
    return "Changing shape size ";
  }

  @Override
  public String getStartState() {
    return "width: " + originalWidth + " height: " + originalHeight;
  }

  @Override
  public String getEndState() {
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
