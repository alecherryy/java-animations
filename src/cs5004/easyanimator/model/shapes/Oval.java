package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;

/**
 * This class represents an Oval. It extends the AbstractShape class,
 * and defines methods specific to ovals. A circle is also an oval,
 * whose x and y radii  are equal.
 */
public class Oval extends AbstractShape {

  /**
   * Constructs an Oval object, with its given name, appear time,
   * disappear time, xRadius, yRadius, color, and position. Calls the AbstractShape
   * super-constructor and sets the ShapeType to OVAL.
   *
   * @param name the name of the shape
   * @param appearTime the appear time of the shape
   * @param disappearTime the disappear time of the shape
   * @param xRadius the x-radius of the shape
   * @param yRadius the y-adius of the shape
   * @param color the color of the shape
   * @param pos the position of the shape
   */
  public Oval(String name, int appearTime, int disappearTime, double xRadius, double yRadius,
              Color color,
              Coordinates pos) {
    super(name, ShapeType.OVAL, appearTime, disappearTime, xRadius, yRadius, color, pos);
  }

  /**
   * Returns the string representation of the location of the oval,
   * using its center as a reference.
   *
   * @return the location of the rectangle as a string
   */
  public String getLocation() {

    return "Center: " + Utils.getPositionString(this.getPosition());
  }

  /**
   * Get the svg tag of the shape.
   *
   * @return svg tag of the shape as a string
   */
  public String toSVGTag() {
    return  "<ellipse id=\"" + this.getName()
            + "\" cx=\"" + this.getPosition().getX() + "\" "
            + "cy=\"" + this.getPosition().getY() + "\" "
            + "rx=\"" + this.getD1() + "\" ry=\"" + this.getD2()
            + "\" fill=\"rgb" + Utils.getRGBColorString(this.getColor())
            + "\" visibility=\"visible\">\n";
  }

  /**
   * Get the svg tag x of the shape when it is being animated.
   *
   * @return the svg tag x of the shape when it is being animated as a string
   */
  public String svgTagX() {
    return "cx";
  }

  /**
   * Get the svg tag y of the shape when it is being animated.
   *
   * @return the svg tag y of the shape when it is being animated as a string
   */
  public String svgTagY() {
    return "cy";
  }

  /**
   * Get the svg end tag of the shape.
   *
   * @return String svg end tag as a string
   */
  public String svgEndTag() {
    return "</ellipse>\n";
  }

  /**
   * Get the svg dimension 1 tag.
   *
   * @return svg dimension 1 tag as a string
   */
  public String svgD1Tag() {
    return "rx";
  }

  /**
   * Get the svg dimension 2 tag.
   *
   * @return svg dimension 2 tag as a string
   */
  public String svgD2Tag() {
    return "ry";
  }
}