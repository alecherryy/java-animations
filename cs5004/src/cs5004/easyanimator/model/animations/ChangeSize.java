package cs5004.easyanimator.model.animations;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents the third animation type -- changing the size of a shape, using its width
 * and height. It extends AbstractAnimation.
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
   * @param shape          the shape will be animated, type Shape.
   * @param start          the start time of the animation, an int.
   * @param end            the end time of the animation, an int.
   * @param originalWidth  the original coordinates of the object, type Pair.
   * @param originalHeight the original height of the object, type float.
   * @param newWidth       the new width of the object, to which the width will be changed.
   * @param newHeight      the new height of the object, to which the height will be changed.
   */
  public ChangeSize(Shapes shape, int start, int end, double originalWidth, double originalHeight,
                    double newWidth, double newHeight) {
    super(AnimationType.CHANGESIZE, shape, start, end);
    this.originalWidth = originalWidth;
    this.originalHeight = originalHeight;
    this.newWidth = newWidth;
    this.newHeight = newHeight;
  }

  @Override
  public double getOriginalWidth() {

    return originalWidth;
  }

  @Override
  public double getOriginalHeight() {

    return originalHeight;
  }

  @Override
  public double getNewWidth() {

    return newWidth;
  }

  @Override
  public double getNewHeight() {
    return newHeight;
  }

  @Override
  public String toSVGTag(double speed) {
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    String svg = "";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
        + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
        + this.getShape().svgD1Tag() + "\" "
        + "from=\"" + this.originalWidth
        + "\" to=\"" + this.newWidth + "\" fill=\"freeze\" /> \n";

    svg += "<animate attributeType=\"xml\" type=\"scale\" "
        + "begin=\"" + begin + "ms\" dur=\"" + dur + "ms\" attributeName=\""
        + this.getShape().svgD2Tag() + "\" "
        + "from=\"" + this.originalHeight
        + "\" to=\"" + this.newHeight + "\" fill=\"freeze\" />\n";

    return svg;
  }


  @Override
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

  @Override
  public String getChange() {

    return "scales";
  }

  @Override
  public String getStartState() {

    return Utils.formatSizeString(this.getShape(), this.originalWidth, this.originalHeight);
  }

  @Override
  public String getEndState() {

    return Utils.formatSizeString(this.getShape(), this.newWidth, this.newHeight);
  }

  @Override
  public void implementAnimation(double time) {
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
      this.getShape().changeWidth(finalWidth);
      this.getShape().changeHeight(finalHeight);
    }
  }

  @Override
  public void updateField(Shapes s) {
    s.changeWidth(newWidth);
    s.changeHeight(newHeight);
  }
}