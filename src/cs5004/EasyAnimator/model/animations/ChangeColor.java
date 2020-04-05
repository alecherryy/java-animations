package cs5004.EasyAnimator.model.animations;

import java.awt.Color;

import cs5004.EasyAnimator.model.shapes.Shapes;
import cs5004.EasyAnimator.model.Utils;

/**
 * This class represents the first animation type -- changing
 * the color of a shape. We use the Java inbuilt Color, which encapsulates
 * colors in the default sRGB color space.
 */
public class ChangeColor extends AbstractAnimations {
  private Color color;
  private Color newColor; // color to change to.

  /**
   * Constructs a ChangeColor object, with its given type, start and end times,
   * original color and new color. Calls the AbstractAnimations super-constructor
   * and sets the AnimationType parameter to CHANGECOLOR.
   *
   * @param shape the shape will be animated, type Shape.
   * @param start the start time of the animation, an int.
   * @param end the end time of the animation, an int.
   * @param color the original color of the object, type Color.
   * @param newColor the color to which the object will be changed to, type Color.
   * @throws IllegalArgumentException if the originalColor or newColor are null, or if the
   *                                  originalColor or newColor are something other than red, green,
   *                                  and blue, or if the newColor is the same as the
   *                                  originalColor.
   */
  public ChangeColor(Shapes shape, int start, int end, Color color, Color newColor) {
    super(AnimationType.CHANGECOLOR, shape, start, end);
    if (checkColors(shape, color, newColor)) {
      throw new IllegalArgumentException("Original color and new cannot be null.");
    }

    this.color = color;
    this.newColor = newColor;
  }

  /**
   * Private helper method to check for invalid color parameters.
   *
   * @return true if parameters are invalid, otherwise returns false
   */
  private boolean checkColors(Shapes s, Color color, Color newColor) {
    return color == null || newColor == null || color == newColor || color != s.getColor();
  }

  /**
   * Get the original color of the shape.
   *
   * @return the original color of the shape
   */
  public Color getOriginalColor() {
    return this.color;
  }

  /**
   * Get the new color of the shape.
   *
   * @return the new color of the shape
   */
  public Color getNewColor() {

    return this.newColor;
  }

  /**
   * Returns the string "changes color ".
   *
   * @return the animation change as a string
   */
  public String getChange() {

    return "changes color ";
  }

  /**
   * Get the starting state of the animation as a string.
   *
   * @return the starting state of the animation as a string
   */
  public String getStartState() {
    return Utils.colorAsString(this.color);
  }

  /**
   * Get the end state of the animation as a string.
   *
   * @return the end state of the animation as a string
   */
  public String getEndState() {
    return Utils.colorAsString(this.newColor);
  }

  /**
   * Returns the svg tag of the animation.
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation
   */
  public String toSVGTag(double speed) {
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    return  "<animate attributeType=\"xml\" begin=\""
            + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\"fill\" "
            + "from=\"rgb" + Utils.getRGBColorString(this.color)
            + "\" to=\"rgb" + Utils.getRGBColorString(this.newColor)
            + "\" fill=\"freeze\" />\n";
  }

  /**
   * Implements the animation on a shape (whether it is changing color,
   * changing size, or changing coordinates).
   *
   * @param time the current time of the animation
   */
  public void implementAnimation(double time) {
    // getRed() returns the red component in the range 0-255 in the default sRGB space.
    float originalRed = Utils.rgbToFloat(this.color.getRed());
    // getGreen() returns the green component in the range 0-255 in the default sRGB space.
    float originalGreen = Utils.rgbToFloat(this.color.getGreen());
    // getBlue() returns the blue component in the range 0-255 in the default sRGB space.
    float originalBlue = Utils.rgbToFloat(this.color.getBlue());

    float newRed = Utils.rgbToFloat(this.newColor.getRed());
    float newGreen = Utils.rgbToFloat(this.newColor.getGreen());
    float newBlue = Utils.rgbToFloat(this.newColor.getBlue());

    float changeRed = newRed - originalRed;
    float changeGreen = newGreen - originalGreen;
    float changeBlue = newBlue - originalBlue;

    float changeInTime = (float) (time - this.getStartTime())
            / (float) (this.getEndTime() - this.getStartTime());

    if (time > this.getEndTime() || time < this.getStartTime()) {
      // do nothing.
      return;
    }
    else {
      float finalRed = originalRed + (changeRed * changeInTime);
      float finalGreen = originalGreen + (changeGreen * changeInTime);
      float finalBlue = originalBlue + (changeBlue * changeInTime);

      Color newColor = new Color(finalRed, finalGreen, finalBlue);
      this.getShape().changeColor(newColor);
    }
  }

  /**
   * Changes the appropriate fields of the shape.
   *
   * @param s a Shape object, whose field will be changed
   */
  public void updateField(Shapes s) {
    s.changeColor(newColor);
  }

  /**
   * Returns the svg tag of the animation.
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation when there's a loop
   */
  public String toSVGTagWithLoop(double speed) {
    String tag = "";
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    tag += "<animate attributeType=\"xml\" begin=\"base.begin+" + begin + "ms\" dur=\""
            + dur + "ms\" attributeName=\"fill\" from=\"rgb"
            + Utils.getRGBColorString(this.color) + "\" to=\"rgb"
            + Utils.getRGBColorString(this.newColor) + "\" fill=\"freeze\" />\n";

    tag += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\""
            + " attributeName=\"fill\" from=\"rgb"
            + Utils.getRGBColorString(this.newColor) + "\" to=\"rgb"
            + Utils.getRGBColorString(this.color) + "\" fill=\"freeze\" />\n";

    return tag;
  }
}