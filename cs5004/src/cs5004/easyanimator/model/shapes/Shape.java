package cs5004.easyanimator.model.shapes;

import java.awt.Color;

/**
 * This is a Shape interface. It includes all methods available to a shape.
 */
public interface Shape {

  /**
   * Returns the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Returns the type of the shape.
   *
   * @return the type of the shape
   */
  ShapeType getType();

  /**
   * Returns the width of the shape.
   *
   * @return the width of the shape
   */
  double getWidth();

  /**
   * Returns the height of the shape.
   *
   * @return the width of the shape
   */
  double getHeight();

  /**
   * Returns the size of the Shape in the form of value pair (width, height).
   *
   * @return the size of the shape
   */
  Coordinates getSize();

  /**
   * Returns the position of the Shape in the form of value pair coordinates (x, y).
   *
   * @return the position of the shape
   */
  Coordinates getPosition();

  /**
   * Returns the color of the shape as a string.
   *
   * @return the color of the shape as a string.
   */
  Color getColor();

  /**
   * Returns the appear time of the shape.
   *
   * @return the appear time of the shape.
   */
  int getAppear();

  /**
   * Returns the disappear time of the shape.
   *
   * @return the disappear time of the shape.
   */

  int getDisappear();

  /**
   * Changes the color of this shape to a new color.
   *
   * @param c the color to which we are changing this shape's color.
   */
  void changeColor(Color c);

  /**
   * Changes the width of this shape to a new width.
   *
   * @param w the width to which we are changing this shape's width.
   * @throws IllegalArgumentException if new width is negative.
   */
  void changeWidth(double w) throws IllegalArgumentException;

  /**
   * Changes the height of this shape to a new height.
   *
   * @param h the height to which we are changing this shape's height.
   * @throws IllegalArgumentException if new height is negative.
   */
  void changeHeight(double h) throws IllegalArgumentException;

  /**
   * Changes the position of this shape to a new position.
   *
   * @param pos the position to which we are changing this shape's position.
   */
  void changePosition(Coordinates pos);

  /**
   * Changes the appear time of this shape to a new appear time.
   *
   * @param newAppear the new appear time to which we are changing this shape's appear time.
   * @throws IllegalArgumentException if new appear time is greater than disappear time.
   */
  void changeAppear(int newAppear) throws IllegalArgumentException;

  /**
   * Changes the disappear time of this shape to a new disappear time.
   *
   * @param newDisappear the new disappear time to which we are changing this shape's disappear
   *                     time.
   * @throws IllegalArgumentException if new disappear time is less than appear time.
   *                                  <p>
   *                                  *
   */
  void changeDisappear(int newDisappear);

  /**
   * Returns the string representation of the location of the shape.
   *
   * @return the location of the shape as a string.
   */
  String getLocation();

  /**
   * Returns the string representation of the dimensions of the shape. We round to 1 decimal
   * place.
   *
   * @return the dimensions of the shape as a string.
   */
  String getDimensions();

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed description of the shape
   * information and its expected behavior.
   *
   * @return a summary in a string
   */
  String getDescription();

  /**
   * Returns width as string.
   *
   * @return the width of this shape as a string.
   */
  String widthString();

  /**
   * Returns height as string.
   *
   * @return the height of this shape as a string.
   */
  String heightString();

  /**
   * Returns a String of the following format: [shape name] appears at time t=[appear time] and
   * disappears at time t=[disappear time].
   *
   * @return a String with a description of when a shape appears and when it disappears.
   */
  String getTimeDescription();

}
