package cs5004.EasyAnimator.model.shapes;

import java.awt.Color;

import cs5004.EasyAnimator.model.Utils;

/**
 * This abstract class implements Shape and all of its methods.
 * It contains the code for an abstract shape and represents the
 * behavior shared by all shapes.
 */
public abstract class AbstractShape implements Shapes {
  private String name;
  private ShapeType type;
  private int appearTime;
  private int disappearTime;
  private double d1;
  private double d2;
  private Color color;
  private Coordinates position;
  private boolean displayValue;

  /**
   * Constructs an AbstractShape object with its given name, type, appear time,
   * disappear time, d1, d2, color, and position.
   *
   * @param name the name of the shape
   * @param type the type of shape
   * @param appearTime the time when the shape appears
   * @param disappearTime the time when the shape disappears
   * @param d1 the first dimension of the shape. Width for rectangle, x-radius for oval.
   * @param d2 the second dimension of the shape. Height for rectangle, y-radius for oval
   * @param color the color of the shape
   * @throws IllegalArgumentException if the name and color are empty strings or the d1 and d2 are
   *                                  less than 1 or appear or disappear time are negative, or
   *                                  appear time is after disappear time.
   */
  public AbstractShape(String name, ShapeType type, int appearTime, int disappearTime, double d1,
                       double d2, Color color,
                       Coordinates position) {
    // check for bad inputs
    if (Utils.isNegative(d1) || Utils.isNegative(d2) || Utils.isNegative(appearTime) ||
        Utils.isNegative(disappearTime)) {
      throw new IllegalArgumentException(""
          + "The following values cannot be negative: appear time,"
          + "disappear time, first dimension, and second dimension.");
    }
    // check for bad inputs
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("A shape must have a name.");
    }
    if (disappearTime < appearTime) {
      throw new IllegalArgumentException("Disappear time must be before appear time");
    }

    this.name = name;
    this.type = type;
    this.appearTime = appearTime;
    this.disappearTime = disappearTime;
    this.d1 = d1;
    this.d2 = d2;

    // shapes must have a color
    if (color == null) {
      throw new IllegalArgumentException("A shape must have a color.");
    }
    this.color = color;
    this.position = position;
    this.displayValue = true;
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
   * Returns the first dimension of the shape (width for rectangle, x-radius for Oval)
   *
   * @return the width of the shape
   */
  public double getD1() {
    return this.d1;
  }

  /**
   * Returns the second dimension of the shape (height for rectangle, y-radius for Oval).
   *
   * @return the height of the shape
   */
  public double getD2() {
    return this.d2;
  }

  /**
   * Returns the color of the shape as a string.
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
    return new Coordinates(this.d1, this.d2);
  }

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed description
   * of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  public Coordinates getPosition() {
    return this.position;
  }

  /**
   * Returns time when the shape appears.
   *
   * @return the appear time of the shape as an int
   */
  public int getAppearTime() {
    return this.appearTime;
  }

  /**
   * Returns time when the shape disappears.
   *
   * @return the disappear time of the shape as an int
   */
  public int getDisappearTime() {
    return this.disappearTime;
  }

  /**
   * Changes the color of this shape to a new color.
   *
   * @param c the color to which we are changing this shape to
   */
  public void changeColor(Color c) {
    this.color = c;
  }

  /**
   * Changes the width of this shape to a new width.
   *
   * @param newD1 the width to which we are changing this shape's width
   * @throws IllegalArgumentException if new width is negative
   */
  public void changeD1(double newD1) throws IllegalArgumentException {
    // check for bad input
    if (Utils.isNegative(newD1)) {
      throw new IllegalArgumentException("New d1 cannot be negative.");
    }
    this.d1 = newD1;
  }

  /**
   * Changes the height of this shape to a new height.
   *
   * @param newD2 the height to which we are changing this shape's height
   * @throws IllegalArgumentException if new height is negative
   */
  public void changeD2(double newD2) throws IllegalArgumentException {
    // check for bad input
    if (Utils.isNegative(newD2)) {
      throw new IllegalArgumentException("New d2 cannot be negative.");
    }
    this.d2 = newD2;
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
   * Changes the appear time of this shape to a new appear time.
   *
   * @param appear the appear time to which we are changing this shape's appear time to
   * @throws IllegalArgumentException if appear is negative or if the new appear time is greater
   *                                  than the disappear time
   */
  public void changeAppearTime(int appear) {

    if (appear > this.disappearTime) {
      throw new IllegalArgumentException("Appear time must be before disappear time.");
    }
    this.appearTime = appear;
  }

  /**
   * Changes the disappear time of this shape to a new disappear time.
   *
   * @param disappear the disappear time to which we are changing this shape's disappear time to
   * @throws IllegalArgumentException if disappear time is negative or if the new disappear time is
   *                                  less than the appear time
   */
  public void changeDisappearTime(int disappear) {
    if (disappear < this.appearTime) {
      throw new IllegalArgumentException("Disappear time must be after appear time.");
    }
    this.disappearTime = disappear;
  }

  /**
   * Returns the string representation of the dimensions of the shape.
   * We round to 1 decimal place.
   *
   * @return the dimensions of the shape in a string
   */
  public String getDimensions() {
    return Utils.formatSizeString(this, this.d1, this.d2);
  }

  /**
   * Returns boolean indicate whether or not the shape should be displayed.
   *
   * @return boolean true if shape should be displayed, false if not
   */
  public boolean getDisplayValue() {
    return this.displayValue;
  }

  /**
   * Changes display boolean of this shape to a new value.
   *
   * @param newDisplay render boolean to set this shape to
   */
  public void changeDisplayValue(boolean newDisplay) {
    this.displayValue = newDisplay;
  }

  /**
   * Get the shape type of the shape.
   *
   * @return the ShapeType of the shape
   */
  public ShapeType getShapeType() {
    return this.type;
  }

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed description
   * of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  public String getDescription() {
    return "Name: " + this.name + "\n"
        + "Type: " + this.type.toString() + "\n"
        + this.getLocation() + ", "
        + this.getDimensions() + ", "
        + "Color: " + Utils.colorAsString(this.color) + "\n"
        + "Appears at t=" + this.appearTime + "s\n"
        + "Disappears at t=" + this.disappearTime + "s\n";
  }

  /**
   * Overloads toString() method to accept a single float speed parameter.
   *
   * @param speed of the animation
   * @return a summary in a string
   */
  public String getDescription(float speed) {
    double appearT = (double) this.appearTime / speed;
    double disappearT = (double) this.disappearTime / speed;
    return "Name: " + this.name + "\n"
            + "Type: " + this.type.toString() + "\n"
            + this.getLocation() + ", "
            + this.getDimensions() + ", "
            + "Color: " + Utils.colorAsString(this.color) + "\n"
            + "Appears at t=" + appearT + "s\n"
            + "Disappears at t=" + disappearT + "s\n";
  }
}