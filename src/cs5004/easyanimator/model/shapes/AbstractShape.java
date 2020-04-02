package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;

/**
 * This abstract class implements Shape and all of its methods. It contains the code for
 * an abstract shape and represents the behavior shared by all shapes.
 */
public abstract class AbstractShape implements Shapes {
  private String name;
  private ShapeType type;
  private double width;
  private double height;
  private Color color;
  private Coordinates position;

  /**
   * Constructs an AbstractShape object with its given name, type, appear time,
   * disappear time, width, height, color, and position.
   *
   * @param name      the name of the shape
   * @param type      the type of shape
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @param color     the color of the shape
   * @throws IllegalArgumentException if the name and color are empty strings or the width and
   *                                  height are less than 1 or appear or disappear time are
   *                                  negative, or appear time is after disappear time.
   */
  public AbstractShape(String name, ShapeType type, double width, double height, Color color,
                       Coordinates position) {
    // check for bad inputs
    if (Utils.isNegative(width) || Utils.isNegative(height)) {
      throw new IllegalArgumentException(""
              + "The following values cannot be negative: appear time,"
              + "disappear time, width, and height.");
    }
    // check for bad inputs
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("A shape must have a name.");
    }
    this.name = name;
    this.type = type;
    this.width = width;
    this.height = height;

    // shapes must have a color
    if (color == null) {
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
  }

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
   * @return the color of the shape as a string
   */
  public Color getColor() {
    return this.color;
  }

  /**
   * Returns the size of the Shape in the form of value pair (width, height).
   *
   * @return the size of the shape
   */
  public Coordinates getSize() {
    return new Coordinates(this.width, this.height);
  }

  /**
   * Returns the position of the Shape in the form of value pair coordinates (x, y).
   *
   * @return the position of the shape
   */
  public Coordinates getPosition() {
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
   * @throws IllegalArgumentException if new width is negative
   */
  public void changeWidth(double w) throws IllegalArgumentException {
    // check for bad input
    if (Utils.isNegative(w)) {
      throw new IllegalArgumentException("New width cannot be negative.");
    }
    this.width = w;
  }

  /**
   * Changes the height of this shape to a new height.
   *
   * @param h the height to which we are changing this shape's height
   * @throws IllegalArgumentException if new height is negative
   */
  public void changeHeight(double h) throws IllegalArgumentException {
    // check for bad input
    if (Utils.isNegative(h)) {
      throw new IllegalArgumentException("New height cannot be negative.");
    }
    this.height = h;
  }

  /**
   * Changes the position of this shape to a new position.
   *
   * @param pos the position to which we are changing this shape's position
   */
  public void changePosition(Coordinates pos) {
    this.position = pos;
  }

  /**
   * Returns the string representation of the dimensions of the shape.
   * We round to 1 decimal place.
   *
   * @return the dimensions of the shape as a string
   */
  public String getDimensions() {
    return Utils.formatSizeString(this, this.width, this.height);
  }

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed
   * description of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  public String getDescription() {
    return  "Name: " + this.name + "\n"
            + "Type: " + this.type.toString() + "\n"
            + this.getLocation() + ", "
            + this.getDimensions() + ", "
            + "Color: " + Utils.colorAsString(this.color) + "\n";
  }
}