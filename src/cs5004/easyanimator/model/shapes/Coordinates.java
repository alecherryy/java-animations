package cs5004.easyanimator.model.shapes;

import static cs5004.easyanimator.model.Utils.getPositionString;

/**
 * This class represents the position of a shape.
 */
public class Coordinates {
  public final double x;
  public final double y;

  /**
   * Constructs a Coordinates object with its given x-coordinate and y-coordinate.
   *
   * @param x coordinate of the shape
   * @param y coordinate of the shape
   */
  public Coordinates(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x value of the object.
   *
   * @return x value
   */
  public double getX() {
    return this.x;
  }

  /**
   * Returns the y value of the object.
   *
   * @return y value
   */
  public double getY() {
    return this.y;
  }

  /**
   * Returns coordinates in a string.
   *
   * @return string representation of coordinates
   */
  public String toString() {
    return getPositionString(this);
  }
}