package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;

/**
 * This class represents an rectangle. It extends the AbstractShape class,
 * and defines methods specific to rectangles. A square is also a rectangle,
 * whose "height" and "width" are equal.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs an Rectangle object, with its given name, appear time,
   * disappear time, width, height, color, and position. Calls the AbstractShape
   * super-constructor and sets the ShapeType to RECTANGLE.
   *
   * @param name the name of the shape
   * @param appearTime the appear time of the shape
   * @param disappearTime the disappear time of the shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @param color the color of the shape
   * @param pos the position of the shape
   */
  public Rectangle(String name,
                   int appearTime,
                   int disappearTime,
                   double width,
                   double height,
                   Color color,
                   Coordinates pos) {
    super(name, ShapeType.RECTANGLE, appearTime, disappearTime, width, height, color, pos);
  }

  /**
   * Returns the location of the rectangle in a string, using its bottom left
   * corner as a reference.
   *
   * @return the location of the rectangle as a string
   */
  public String getLocation() {
    return "Min corner: "
        + Utils.getPositionString(this.getPosition());
  }

  /**
   * Returns the shape (either a rectangle or an oval) with the changes made to it after it has been
   * visited.
   *
   * @param visitor the visitor, type ShapesVisitor, that the function visit() will be called on,
   *                passing an instance of the current class. It will 'visit' this rectangle object.
   * @return the shape, type Shapes, with the changes made to it.
   */
  public Shapes visitShape(ShapesVisitor visitor) {
    return visitor.visit(this);
  }


  /**
   * Get the svg tag of the shape.
   *
   * @return svg tag of the shape as a string
   */
  public String toSVGTag() {
    return  "<rect id=\"" + this.getName() + "\" "
            + "x=\"" + this.getPosition().getX() + "\" "
            + "y=\"" + this.getPosition().getY() + "\" "
            + "width=\"" + this.getD1() + "\" "
            + "height=\"" + this.getD2() + "\" "
            + "fill=\"rgb(" + this.getColor().getRed() + "," + this.getColor().getGreen() + ","
            + this.getColor().getBlue() + ")\" "
            + "visibility=\"visible\">\n";
  }

  /**
   * Get the svg tag x of the shape when it is being animated.
   *
   * @return the svg tag x of the shape when it is being animated as a string
   */
  public String svgTagX() {
    return "x";
  }

  /**
   * Get the svg tag y of the shape when it is being animated.
   *
   * @return the svg tag y of the shape when it is being animated as a string
   */
  public String svgTagY() {
    return "y";
  }

  /**
   * Get the svg end tag of the shape.
   *
   * @return String svg end tag as a string
   */
  public String svgEndTag() {
    return "</rect>\n";
  }

  /**
   * Get the svg dimension 1 tag.
   *
   * @return svg dimension 1 tag as a string
   */
  public String svgD1Tag() {
    return "width";
  }

  /**
   * Get the svg dimension 2 tag.
   *
   * @return svg dimension 2 tag as a string
   */
  public String svgD2Tag() {
    return "height";
  }
}