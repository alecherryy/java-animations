package cs5004.easyanimator.model.shapes;

import java.awt.Color;

import cs5004.easyanimator.model.Utils;


/**
 * This abstract class implements Shape and all of its methods. It contains the code for an abstract
 * shape and represents the behavior shared by all shapes.
 */
public abstract class AbstractShape implements Shape {
  private String name;
  private ShapeType type;
  private int appear;
  private int disappear;
  private double width;
  private double height;
  private Color color;
  private Coordinates position;

  /**
   * Constructs an AbstractShape object with its given name, type, appear time, disappear time,
   * width, height, color, and position.
   *
   * @param name      the name of the shape
   * @param type      the type of shape
   * @param appear    the time when the shape appears
   * @param disappear the time when the shape disappears
   * @param width     the width of the shape
   * @param height    the height of the shape
   * @param color     the color of the shape
   * @throws IllegalArgumentException if the name and color are empty strings or the width and
   *                                  height are less than 1 or appear or disappear time are
   *                                  negative, or appear time is after disappear time.
   */
  public AbstractShape(String name, ShapeType type, int appear, int disappear, double width,
                       double height, Color color,
                       Coordinates position) {
    if (Utils.isNegative(appear) || Utils.isNegative(disappear) || Utils.isNegative(width) ||
        Utils.isNegative(height)) {
      throw new IllegalArgumentException("The following values cannot be negative: appear time," +
          "disappear time, width, and height.");
    }
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("A shape must have a name.");
    }
    this.name = name;
    this.type = type;

    if (appear > disappear) {
      throw new IllegalArgumentException("Disappear time must be greater than appear time.");
    }

    this.appear = appear;
    this.disappear = disappear;
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
   * @return the color of the shape as a string.
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

  @Override
  public int getAppear() {
    return this.appear;
  }

  @Override
  public int getDisappear() {
    return this.disappear;
  }

  @Override
  public void changeColor(Color newColor) {
    this.color = newColor;
  }

  @Override
  public void changeWidth(double w) throws IllegalArgumentException {
    if (Utils.isNegative(w)) {
      throw new IllegalArgumentException("New width cannot be negative");
    }
    this.width = w;
  }

  @Override
  public void changeHeight(double h) throws IllegalArgumentException {
    if (Utils.isNegative(h)) {
      throw new IllegalArgumentException("New height cannot be negative");
    }
    this.height = h;
  }

  @Override
  public void changePosition(Coordinates pos) {
    this.position = pos;
  }

  @Override
  public void changeAppear(int newAppear) throws IllegalArgumentException {
    if (newAppear > this.disappear) {
      throw new IllegalArgumentException("New appear time can not be after disappear " +
          "time");
    }
    this.appear = newAppear;
  }

  @Override
  public void changeDisappear(int newDisappear) throws IllegalArgumentException {
    if (newDisappear < this.appear) {
      throw new IllegalArgumentException("New disappear time can not be before appear time");
    }
    this.appear = newDisappear;
  }

  @Override
  public String getDimensions() {
    return this.widthString() + String.format("%.1f", this.width) + ", " + this.heightString() +
        String.format("%.1f", this.height);
  }

  @Override
  public String getDescription() {
    return "Name: " + this.name + "\n" + "Type: " + this.type.toString() + "\n"
        + this.getLocation() + ", "
        + this.getDimensions() + ", Color: "
        + Utils.colorAsString(this.color) + "\n"
        + "Appears at t=" + appear + "\n" + "Disappears at t=" + disappear + "\n";
  }

  @Override
  public String getTimeDescription() {
    return this.name + " appears at time t=" + appear + " and disappears at time t=" + disappear;
  }
}

