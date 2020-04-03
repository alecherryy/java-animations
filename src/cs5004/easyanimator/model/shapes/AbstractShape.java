package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;

/**
 * This abstract class implements Shape and all of its methods. It contains the code for an abstract
 * shape and represents the behavior shared by all shapes.
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
   * Constructs an AbstractShape object with its given name, type, appear time, disappear time, d1,
   * d2, color, and position.
   *
   * @param name          the name of the shape
   * @param type          the type of shape
   * @param appearTime    the time when the shape appears
   * @param disappearTime the time when the shape disappears
   * @param d1            the first dimension of the shape. Width for rectangle, x-radius for oval.
   * @param d2            the second dimension of the shape. Height for rectangle, y-radius for
   *                      oval
   * @param color         the color of the shape
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

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ShapeType getType() {
    return this.type;
  }

  @Override
  public double getD1() {
    return this.d1;
  }

  @Override
  public double getD2() {
    return this.d2;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public Coordinates getSize() {
    return new Coordinates(this.d1, this.d2);
  }

  @Override
  public Coordinates getPosition() {
    return this.position;
  }

  @Override
  public int getAppearTime() {
    return this.appearTime;
  }

  @Override
  public int getDisappearTime() {
    return this.disappearTime;
  }

  @Override
  public void changeColor(Color c) {
    this.color = c;
  }

  @Override
  public void changeWidth(double w) throws IllegalArgumentException {
    // check for bad input
    if (Utils.isNegative(w)) {
      throw new IllegalArgumentException("New d1 cannot be negative.");
    }
    this.d1 = w;
  }

  @Override
  public void changeHeight(double h) throws IllegalArgumentException {
    // check for bad input
    if (Utils.isNegative(h)) {
      throw new IllegalArgumentException("New d2 cannot be negative.");
    }
    this.d2 = h;
  }

  @Override
  public void changePosition(Coordinates pos) {
    this.position = pos;
  }

  @Override
  public void changeAppearTime(int appear) {

    if (appear > this.disappearTime) {
      throw new IllegalArgumentException("Appear time must be before disappear time.");
    }
    this.appearTime = appear;
  }

  @Override
  public void changeDisappearTime(int newDisappear) {
    if (newDisappear < this.appearTime) {
      throw new IllegalArgumentException("Disappear time must be after appear time.");
    }
    this.disappearTime = newDisappear;
  }

  @Override
  public String getDimensions() {
    return Utils.formatSizeString(this, this.d1, this.d2);
  }

  @Override
  public boolean getDisplayValue() {
    return this.displayValue;
  }

  @Override
  public void changeDisplayValue(boolean newDisplay) {
    this.displayValue = newDisplay;
  }

  @Override
  public ShapeType getShapeType() {
    return this.type;
  }

  @Override
  public String getDescription() {
    return "Name: " + this.name + "\n"
        + "Type: " + this.type.toString() + "\n"
        + this.getLocation() + ", "
        + this.getDimensions() + ", "
        + "Color: " + Utils.colorAsString(this.color) + "\n";
  }
}