/**
 * This is a Shape interface. It includes all methods available to
 * a shape.
 */
public interface Shape {

  /**
   * Returns the width of the shape.
   *
   * @return the width of the shape
   */
  int getWidth();

  /**
   * Returns the height of the shape.
   *
   * @return the width of the shape
   */
  int getHeight();

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
   * Returns a summery of the Shape in a string; meaning, a detailed
   * description of the shape information and its expected behavior.
   *
   * @return a summary in a string
   */
  String toString();
}
