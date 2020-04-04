package cs5004.easyanimator.model.shapes;

import java.awt.Color;

/**
 * This is a Shape interface. It includes all methods available to a Shape object.
 */
public interface Shapes {

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
   * Returns the first dimension of the shape (width for rectangle, x-radius for Oval).
   *
   * @return the width of the shape
   */
  double getD1();

  /**
   * Returns the second dimension of the shape (height for rectangle, y-radius for Oval).
   *
   * @return the height of the shape
   */
  double getD2();

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
   * @return the color of the shape as a string
   */
  Color getColor();

  /**
   * Returns the shape (either a rectangle or an oval) with the changes
   * made to it after it has been visited.
   *
   * @param visitor the visitor, type ShapesVisitor, that the function visit()
   *                will be called on, passing an instance of the current class
   * @return the shape, type Shapes, with the changes made to it
   */
  Shapes visitShape(ShapesVisitor visitor);

  /**
   * Returns time when the shape appears.
   *
   * @return the appear time of the shape as an int
   */
  int getAppearTime();

  /**
   * Returns time when the shape disappears.
   *
   * @return the disappear time of the shape as an int
   */
  int getDisappearTime();

  /**
   * Changes the color of this shape to a new color.
   *
   * @param c the color to which we are changing this shape to
   */
  void changeColor(Color c);

  /**
   * Changes the first dimension of this shape to a d1.
   *
   * @param newD1 the new D1 to which we are changing this shape's D1
   * @throws IllegalArgumentException if new D1 is negative
   */
  void changeD1(double newD1) throws IllegalArgumentException;

  /**
   * Changes the D2 of this shape to a new D2.
   *
   * @param newD2 the D2 to which we are changing this shape's D2
   * @throws IllegalArgumentException if new height is negative
   */
  void changeD2(double newD2) throws IllegalArgumentException;

  /**
   * Changes the position of this shape to a new position.
   *
   * @param pos the position to which we are changing this shape's position
   */
  void changePosition(Coordinates pos);

  /**
   * Changes the appear time of this shape to a new appear time.
   *
   * @param appear the appear time to which we are changing this shape's appear time to
   * @throws IllegalArgumentException if appear is negative or if the new appear
   *                                  time is greater than the disappear time
   */
  void changeAppearTime(int appear) throws IllegalArgumentException;

  /**
   * Changes the disappear time of this shape to a new disappear time.
   *
   * @param disappear the disappear time to which we are changing this shape's disappear time to
   * @throws IllegalArgumentException if disappear is negative or if the new disappear
   *                                  time is less than the appear time
   */
  void changeDisappearTime(int disappear) throws IllegalArgumentException;

  /**
   * Returns the string representation of the location of the shape.
   *
   * @return the location of the shape as a string
   */
  String getLocation();

  /**
   * Returns the string representation of the dimensions of the shape.
   * We round to 1 decimal place.
   *
   * @return the dimensions of the shape in a string
   */
  String getDimensions();

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed description
   * of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  String getDescription();

  /**
   * Overloads toString() method to accept a single float speed parameter.
   *
   * @param speed of the animation
   * @return a summary in a string
   */
  String getDescription(float speed);

  /**
   * Returns boolean indicate whether or not the shape should be displayed.
   *
   * @return boolean true if shape should be displayed, false if not
   */
  boolean getDisplayValue();

  /**
   * Changes display boolean of this shape to a new value.
   *
   * @param newDisplay render boolean to set this shape to
   */
  void changeDisplayValue(boolean newDisplay);

  /**
   * Get the shape type of the shape.
   *
   * @return the ShapeType of the shape
   */
  ShapeType getShapeType();

  /**
   * Get the svg tag of the shape.
   *
   * @return svg tag of the shape as a string
   */
  String toSVGTag();

  /**
   * Get the svg tag x of the shape when it is being animated.
   *
   * @return the svg tag x of the shape when it is being animated as a string
   */
  String svgTagX();

  /**
   * Get the svg tag y of the shape when it is being animated.
   *
   * @return the svg tag y of the shape when it is being animated as a string
   */
  String svgTagY();

  /**
   * Get the svg end tag of the shape.
   *
   * @return String svg end tag as a string
   */
  String svgEndTag();

  /**
   * Get the svg dimension 1 tag.
   *
   * @return svg dimension 1 tag as a string
   */
  String svgD1Tag();

  /**
   * Get the svg dimension 2 tag.
   *
   * @return svg dimension 2 tag as a string
   */
  String svgD2Tag();
}
