package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;


/**
 * This class represents an rectangle. It extends the AbstractShape class, and defines methods
 * specific to rectangles. A square is also a rectangle.
 */

public class Rectangle extends AbstractShape {

  /**
   * Constructs an Rectangle object, with its given name, appear time, disappear time, width,
   * height, color, and position. Calls the AbstractShape super-constructor and sets the ShapeType
   * to RECTANGLE.
   *
   * @param name      the name of the shape
   * @param appear    the appear time of the shape
   * @param disappear the disappear time of the shape
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @param color     the color of the shape
   * @param pos       the position of the shape
   */
  public Rectangle(String name, int appear, int disappear, double width, double height, Color color,
                   Coordinates pos) {
    super(name, ShapeType.RECTANGLE, appear, disappear, width, height, color, pos);

  }

  @Override
  public String getLocation() {
    return "Min corner: " + Utils.getPositionString(this.getPosition());
  }

  @Override
  public String widthString() {
    return "Width: ";
  }

  @Override
  public String heightString() {
    return "Height: ";
  }
}

