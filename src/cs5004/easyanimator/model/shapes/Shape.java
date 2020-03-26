package cs5004.easyanimator.model.shapes;
import java.awt.Color;

/**
 * This is a Shape interface. It includes all methods available to
 * a shape.
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
   * Returns the size of the Shape in the form
   * of value pair (width, height).
   *
   * @return the size of the shape
   */
  Pair getSize();

  /**
   * Returns the position of the Shape in the form
   * of value pair coordinates (x, y).
   *
   * @return the position of the shape
   */
  Pair getPosition();

  /**
   * Returns the color of the shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Changes the color of this shape to a new color.
   *
   * @param c the color to which we are changing this shape's colo
   */
  void changeColor(Color c);

  /**
   * Changes the width of this shape to a new width.
   *
   * @param w the width to which we are changing this shape's width
   */
  void changeWidth(double w);

  /**
   * Changes the height of this shape to a new height.
   *
   * @param h the height to which we are changing this shape's height
   */
  void changeHeight(double h);

  /**
   * Changes the position of this shape to a new position.
   *
   * @param pos the position to which we are changing this shape's position
   */
  void changePosition(Pair pos);

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed
   * description of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  String toString();

}
