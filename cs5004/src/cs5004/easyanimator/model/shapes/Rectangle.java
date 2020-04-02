package cs5004.easyanimator.model.shapes;

import java.awt.*;

import cs5004.easyanimator.model.Utils;


/**
 * This class represents an rectangle. It extends the AbstractShape class, and defines methods
 * specific to rectangles. A square is also a rectangle, whose "height" and "width" are equal.
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructs an Rectangle object, with its given name, appear time, disappear time, width,
   * height, color, and position. Calls the AbstractShape super-constructor and sets the ShapeType
   * to RECTANGLE.
   *
   * @param name          the name of the shape
   * @param appearTime    the appear time of the shape
   * @param disappearTime the disappear time of the shape
   * @param width         the width of the shape
   * @param height        the height of the shape
   * @param color         the color of the shape
   * @param pos           the position of the shape
   */
  public Rectangle(String name, int appearTime, int disappearTime, double width, double height,
                   Color color,
                   Coordinates pos) {
    super(name, ShapeType.RECTANGLE, appearTime, disappearTime, width, height, color, pos);
  }

  /**
   * Returns the string representation of the location of the rectangle, using its bottom left
   * corner as a reference.
   *
   * @return the location of the rectangle as a string
   */
  public String getLocation() {
    return "Min corner: "
        + Utils.getPositionString(this.getPosition());
  }

  @Override
  public Shapes visitShape(ShapesVisitor visitor) {
    return visitor.visit(this);
  }

  @Override
  public String toSVGTag() {
    return "<rect id=\"" + this.getName() + "\" x=\"" + this.getPosition().getX() + "\" y=\""
        + this.getPosition().getY() + "\" width=\"" + this.getD1() + "\" height=\"" +
        this.getD2() + "\" fill=\"rgb(" + this.getColor().getRed() + "," +
        this.getColor().getGreen() + ","
        + this.getColor().getBlue() + ")\" visibility=\"visible\">\n";
  }

  @Override
  public String svgTagX() {
    return "x";
  }

  @Override
  public String svgTagY() {
    return "y";
  }

  @Override
  public String svgEndTag() {
    return "</rect>\n";
  }

  @Override
  public String svgD1Tag() {
    return "width";
  }

  @Override
  public String svgD2Tag() {
    return "height";
  }
}