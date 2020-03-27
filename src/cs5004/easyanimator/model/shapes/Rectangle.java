package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;


/**
 * This class represents an rectangle. It extends the AbstractShape class, and defines methods
 * specific to rectangles. A square is also a rectangle, whose "height" and
 * "width" are equal.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs an Rectangle object, with its given name, appear time, disappear time, width,
   * height, color, and position. Calls the AbstractShape super-constructor and sets
   * the ShapeType to RECTANGLE.
   *
   * @param name      the name of the shape
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @param color     the color of the shape
   * @param pos       the position of the shape
   */
  public Rectangle(String name, double width, double height, Color color, Coordinates pos) {
    super(name, ShapeType.RECTANGLE, width, height, color, pos);
  }

  /**
   * Returns the string representation of the location of the rectangle,
   * using its bottom left corner as a reference.
   *
   * @return the location of the rectangle as a string
   */
  public String getLocation() {
    return  "Min corner: "
            + Utils.getPositionString(this.getPosition());
  }
}

