package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;

/**
 * This class represents an Oval. It extends the AbstractShape class, and defines methods specific
 * to ovals. a circle is also an oval.
 */

public class Oval extends AbstractShape {

  /**
   * Constructs an Oval object, with its given name, appear time, disappear time, width, height,
   * color, and position. Calls the AbstractShape super-constructor and sets the ShapeType to OVAL.
   *
   * @param name      the name of the shape
   * @param appear    the appear time of the shape
   * @param disappear the disappear time of the shape
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @param color     the color of the shape
   * @param pos       the position of the shape
   */
  public Oval(String name, int appear, int disappear, double width, double height, Color color,
              Coordinates pos) {
    super(name, ShapeType.OVAL, appear, disappear, width, height, color, pos);

  }

  /**
   * Returns the string representation of the location of the oval,
   * using its center as a reference.
   *
   * @return the location of the rectangle as a string
   */
  public String getLocation() {

    return  "Center: "
        + Utils.getPositionString(this.getPosition());
  }
}

