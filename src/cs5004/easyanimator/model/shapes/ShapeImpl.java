package model.Shape;

import cs5004.easyanimator.model.shapes.Pair;
import cs5004.easyanimator.model.shapes.Shape;
import cs5004.easyanimator.model.shapes.ShapeType;

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
  private String color;
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
   * than 1
   */
  public ShapeImpl(String name, ShapeType type, double width, double height, String color, Pair position) {
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
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("A shape has to have a width and height greater than 1.");
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
  public String getColor() {
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
    String str = this.color
            + " "
            + type
            + " with corner at " + this.getPosition().toString() + ", "
            + "width " + this.width + " "
            + "and height " + this.height;
    return str;
  }
}
