import java.text.DecimalFormat;

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
  String getColor();

  /**
   * Returns a summary of the Shape in a string; meaning, a detailed
   * description of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  String toString();
}
