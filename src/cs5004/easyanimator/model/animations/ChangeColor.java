package cs5004.easyanimator.model.animations;

import java.awt.Color;

import cs5004.easyanimator.model.shapes.Shape;
import cs5004.easyanimator.model.Utils;

/**
 * This class represents the first animation type -- changing
 * the color of a shape. We use the Java inbuilt color, which encapsulates
 * colors in the default sRGB color space.
 */
public class ChangeColor extends AbstractAnimations {
  private Color originalColor;
  private Color newColor; // color to change to.

  /**
   * Constructs a ChangeColor object, with its given type, start and end times,
   * original color and new color. Calls the AbstractAnimations super-constructor
   * and sets the AnimationType parameter to CHANGECOLOR.
   *
   * @param shape         the shape will be animated, type Shape.
   * @param start         the start time of the animation, an int.
   * @param end           the end time of the animation, an int.
   * @param originalColor the original color of the object, type Color.
   * @param newColor      the color to which the object will be changed to, type Color.
   * @throws IllegalArgumentException if the originalColor or newColor are null, or if the
   *                                  originalColor or newColor are something other than red, green,
   *                                  and blue, or if the newColor is the same as the
   *                                  originalColor.
   */
  public ChangeColor(Shape shape, int start, int end, Color originalColor, Color newColor) {
    super(AnimationType.CHANGECOLOR, shape, start, end);
    if (originalColor == null || newColor == null) {
      throw new IllegalArgumentException("Original color and new cannot be null.");
    }
    if (originalColor == newColor) {
      throw new IllegalArgumentException("New color must be different from original color.");
    }

    this.originalColor = originalColor;
    this.newColor = newColor;
  }

  /**
   * Get the original color of the shape.
   *
   * @return the original color of the shape
   */
  public Color getOriginalColor() { return this.originalColor; }

  /**
   * Get the new color of the shape.
   *
   * @return the new color of the shape
   */
  public Color getNewColor() { return this.newColor; }

  /**
   * Returns the string "moves".
   *
   * @return the animation change as a string
   */
  public String getChange() { return "changes color "; }

  /**
   * Get the starting state of the animation as a string.
   *
   * @return the starting state of the animation as a string
   */
  public String getStartState() { return Utils.colorAsString(this.originalColor); }

  /**
   * Get the end state of the animation as a string.
   *
   * @return the end state of the animation as a string
   */
  public String getEndState() { return Utils.colorAsString(this.newColor); }

  /**
   * Implements the ChangeColor animation on a shape.
   *
   * @param time the current time of the animation
   */
  public void implementAnimation(double time) {
    // getRed() returns the red component in the range 0-255 in the default sRGB space.
    float originalRed = Utils.rgbToFloat(this.originalColor.getRed());
    // getGreen() returns the green component in the range 0-255 in the default sRGB space.
    float originalGreen = Utils.rgbToFloat(this.originalColor.getGreen());
    // getBlue() returns the blue component in the range 0-255 in the default sRGB space.
    float originalBlue = Utils.rgbToFloat(this.originalColor.getBlue());

    float newRed = Utils.rgbToFloat(this.newColor.getRed());
    float newGreen = Utils.rgbToFloat(this.newColor.getGreen());
    float newBlue = Utils.rgbToFloat(this.newColor.getBlue());

    float changeRed = newRed - originalRed;
    float changeGreen = newGreen - originalGreen;
    float changeBlue = newBlue - originalBlue;

    float changeInTime = (float) (time - this.getStartTime())
            / (float) (this.getEndTime() - this.getStartTime());

    if ((time > this.getEndTime()) || (time < this.getStartTime())) {
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
   * Changes the appropriate fields of the shape to match the changes
   * implemented on the shape.
   *
   * @param s a Shape object, whose field will be changed
   */
  public void updateField(Shape s) { s.changeColor(newColor); }
}