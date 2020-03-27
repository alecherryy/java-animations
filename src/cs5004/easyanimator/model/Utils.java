package cs5004.easyanimator.model;

import java.awt.Color;

import cs5004.easyanimator.model.shapes.*;


/**
 * This class contains static methods that are common to a few classes. They are used for testing.
 */
public class Utils {

  /**
   * Check if a number is negative or not. This will be used for throwing exceptions inside
   * constructors.
   *
   * @param n The number we are checking
   * @return true if number is negative, false if it isn't
   */
  public static boolean isNegative(double n) {
    return n < 0.0;
  }

  /**
   * Convert RGB values in 0-255 format to values in 0.0f-1.0f format.
   *
   * @param v the RGB value of the color
   * @return a float representing a color
   */

  public static float rgbToFloat(int v) {
    return (float) v / (float) 255;
  }

  /**
   * Get the String representation of a shape's color in RGB.
   *
   * @param color the color of the shape
   * @return the RGB value of a shape's color as a string
   */

  public static String colorAsString(Color color) {
    return "(" + Utils.rgbToFloat(color.getRed()) + "," + Utils.rgbToFloat(color.getGreen()) + ","
        + Utils.rgbToFloat(color.getBlue()) + ")";
  }

  /**
   * Returns the position of the shape in string representation.
   *
   * @param pos the position of the shape, a Pair object
   * @return the string representation of the position of the shape
   */
  public static String getPositionString(Coordinates pos) {
    return "(" + pos.getX() + "," + pos.getY() + ")";
  }

  /**
   * Returns the string representation of a color with its RGB value (not as a float).
   *
   * @param color the color
   */
  public static String getRGBColorString(Color color) {
    return "(" + color.getRed() + "," + color.getGreen() + "," + color.getBlue() + ")";
  }
}

