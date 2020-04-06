package cs5004.EasyAnimator.model.animations;

import cs5004.EasyAnimator.model.Utils;
import cs5004.EasyAnimator.model.shapes.Shapes;

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
   * Constructs a Change Size object, with its given shape, start time, end time, original width,
   * original height, new width, and new height. Calls the AbstractAnimations super-constructor and
   * sets the AnimationType parameter to CHANGESIZE.
   *
   * @param shape the shape will be animated, type Shape.
   * @param start  the start time of the animation, an int.
   * @param end the end time of the animation, an int.
   * @param originalWidth the original coordinates of the object, type Pair.
   * @param originalHeight the original height of the object, type float.
   * @param newWidth the new width of the object, to which the width will be changed.
   * @param newHeight the new height of the object, to which the height will be changed.
   */
  public ChangeSize(
          Shapes shape,
          int start,
          int end,
          double originalWidth,
          double originalHeight,
          double newWidth,
          double newHeight) {
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
   * Returns the svg tag of the animation.
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation
   */
  public String toSVGTag(double speed) {
    StringBuilder svg = new StringBuilder();
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    // initial state
    svg.append("<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.getShape().svgD1Tag() + "\" "
            + "from=\"" + this.originalWidth
            + "\" to=\"" + this.newWidth + "\" fill=\"freeze\" /> \n");

    // post-animation state
    svg.append("<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
            + this.getShape().svgD2Tag() + "\" "
            + "from=\"" + this.originalHeight
            + "\" to=\"" + this.newHeight + "\" fill=\"freeze\" />\n");

    return svg.toString();
  }

  /**
   * Returns the svg tag of the animation.
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation when there's a loop
   */
  public String toSVGTagWithLoop(double speed) {
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    String svg = "";

    // actual move tags
    svg += "<animate attributeType=\"xml\" type=\"scale\" "
        + "begin=\"base.begin+" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
        + this.getShape().svgD1Tag() + "\" "
        + "from=\"" + this.originalWidth
        + "\" to=\"" + this.newWidth + "\" fill=\"freeze\" /> \n";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
        + "begin=\"base.begin+" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
        + this.getShape().svgD2Tag() + "\" "
        + "from=\"" + this.originalHeight
        + "\" to=\"" + this.newHeight + "\" fill=\"freeze\" />\n";

    // tag for looping back
    svg += "<animate attributeType=\"xml\" type=\"scale\" "
        + "begin=\"base.end\" dur=\"1ms\"" + " attributeName=\""
        + this.getShape().svgD1Tag() + "\" "
        + "from=\"" + this.newWidth
        + "\" to=\"" + this.originalWidth + "\" fill=\"freeze\" /> \n";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
        + "begin=\"base.end\" dur=\"1ms\"" + " attributeName=\""
        + this.getShape().svgD2Tag() + "\" "
        + "from=\"" + this.newHeight
        + "\" to=\"" + this.originalHeight + "\" fill=\"freeze\" />\n";

    return svg;
  }

  /**
   * Returns the string "moves".
   *
   * @return the animation change as a string
   */
  public String getChange() {

    return "scales ";
  }

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
   * Implements the animation on a shape.
   *
   * @param time the current time of the animation
   * @throws IllegalArgumentException if the size from which we are changing this shape's size
   * does not match the shape's original size.
   */
  public void implementAnimation(double time) {
    if (this.getShape().getD1() != this.getOriginalWidth()
        || this.getShape().getD2() != this.getOriginalHeight()) {
      throw new IllegalArgumentException("Size from which we are changing this shape's size must"
         + "match the shape's original size");

    }
    double changeWidth = this.newWidth - this.originalWidth;
    double changeHeight = this.newHeight - this.originalHeight;

    double changeInTime = (time - this.getStartTime())
        / (float) (this.getEndTime() - this.getStartTime());

    if ((time > this.getEndTime()) || (time < this.getStartTime())) {
      // do nothing.
      return;
    } else {
      double finalWidth = this.originalWidth + (changeInTime * changeWidth);
      double finalHeight = this.originalHeight + (changeInTime * changeHeight);
      this.getShape().changeD1(finalWidth);
      this.getShape().changeD2(finalHeight);
    }
  }

  /**
   * Changes the appropriate fields of the shape.
   *
   * @param s a Shape object, whose field will be changed
   */
  public void updateField(Shapes s) {
    s.changeD1(newWidth);
    s.changeD2(newHeight);
  }
}