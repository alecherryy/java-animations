package cs5004.easyanimator.model.shapes;
import java.awt.Color;


/**
 * This class represents a Shape Implementation, it implements all the
 * methods that belong to the Shape Interface and it can be used
 * to create instances of multiple shape types such as: rectangles,
 * circles, squares, ovals, etc.
 */
public class ShapeImpl implements Shape {
  private String name;
  private ShapeType type;
  private double width;
  private double height;
  private Color color;
  private Pair position;

  /**
   * This class represents a Shape Implementation, it implements all the
   * methods that belong to the Shape Interface and it can be used
   * to create instances of multiple shape types such as: rectangles,
   * circles, squares, ovals, etc.
   *
   * @param name the name of the shape
   * @param type the type of shape
   * @throws IllegalArgumentException if the name and color are empty strings or the width and height are less
   * than 1.
   */
  public ShapeImpl(String name, ShapeType type, double width, double height, Color color,
                   Pair position) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("A shape must have a name.");
    }
    this.name = name;
    // if height and width are equal and type equals to rectangle, change type to square
    if (width == height && type == ShapeType.RECTANGLE) {
      this.type = ShapeType.SQUARE;
    }
    // if height and width are equal and type equals to oval, change type to circle
    else if (width == height && type == ShapeType.OVAL) {
      this.type = ShapeType.CIRCLE;
    }
    else {
      this.type = type;
    }

    // a shape of width and height equal to 0 is a point, and not a shape
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("A shape has to have a width and height greater than 0.");
    }
    this.width = width;
    this.height = height;

    // shapes must have a color
    if (color == null || color.equals("")) {
      throw new IllegalArgumentException("A shape must have a color.");
    }
    this.color = color;
    this.position = position;
  }

  /**
   * Returns the name of the shape.
   *
   * @return the name of the shape
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the type of the shape.
   *
   * @return the type of the shape
   */
  public ShapeType getType() {
    return this.type;
  };


  /**
   * Returns the width of the shape.
   *
   * @return the width of the shape
   */
  public double getWidth() {
    return this.width;
  }

  /**
   * Returns the height of the shape.
   *
   * @return the width of the shape
   */
  public double getHeight() {
    return this.height;
  }

  /**
   * Returns the color of the shape.
   *
   * @return the color of the shape
   */
  public Color getColor() {
    return this.color;
  };

  /**
   * Returns the size of the Shape in the form
   * of value pair (width, height).
   *
   * @return the size of the shape
   */
  public Pair getSize() {
    return new Pair(this.width, this.height);
  }

  /**
   * Returns the position of the Shape in the form
   * of value pair coordinates (x, y).
   *
   * @return the position of the shape
   */
  public Pair getPosition() {
    return this.position;
  }

  /**
   * Changes the color of this shape to a new color.
   *
   * @param c the color to which we are changing this shape's color
   */
  public void changeColor(Color c) {
    this.color = c;
  }

  /**
   * Changes the width of this shape to a new width.
   *
   * @param w the width to which we are changing this shape's width
   * @throws IllegalArgumentException if width is smaller than 1
   */
  public void changeWidth(double w) {
    if (w <= 0) {
      throw new IllegalArgumentException("A shape's width has to be greater than 0.");
    }
    this.width = w;
  }

  /**
   * Changes the height of this shape to a new height.
   *
   * @param h the height to which we are changing this shape's height
   * @throws IllegalArgumentException if height is smaller than 1
   */
  public void changeHeight(double h) {
    if (h <= 0) {
      throw new IllegalArgumentException("A shape's width has to be greater than 0.");
    }
    this.height = h;
  }

  /**
   * Changes the position of this shape to a new position.
   *
   * @param pos the position to which we are changing this shape's position
   */
  public void changePosition(Pair pos) {
    this.position = pos;
  }

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed
   * description of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  public String toString() {
    String type = "";

    switch(this.type) {
      case RECTANGLE:
        type = "Rectangle";
        break;
      case SQUARE:
        type = "Square";
        break;
      case OVAL:
        type = "Oval";
        break;
      case CIRCLE:
        type = "Circle";
        break;
      default:
        break;
    }
    String str = colorAsString(this.color)
        + " "
        + type
        + " with corner at " + this.getPosition().toString() + ", "
        + "width " + this.width + " "
        + "and height " + this.height;
    return str;
  }

  /**
   * Private helper method to get the String representation of a shape's color.
   *
   * @param c the color of the shape
   * @return the RGB value of a shape's color as a string
   */
  private String colorAsString(Color c) {
    return "("
            + rgbToFloat(c.getRed())
            + ", "
            + rgbToFloat(c.getGreen())
            + ", "
            + rgbToFloat(c.getBlue()) + ")";
  }

  /**
   * Private helper method to convert RGB values in 0-255 format to values in 0.0f-1.0f format.
   *
   * @param v the RGB value of the color
   * @return a float representing a color
   */

  private float rgbToFloat(int v) {
    return (float) v / (float) 255;
  }
}
