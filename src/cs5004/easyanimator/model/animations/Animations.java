package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This interface represents the operations offered by the animation model. It supports all
 * types of animations. One Animation object represents one animation that will be implemented
 * on a shape.
 */

public interface Animations {

  /**
   * Get the shape that will be animated.
   *
   * @return the shape that will receive the animation
   */
  Shape getShape();

  /**
   * Get the type of animation.
   *
   * @return the type of the animation, an AnimationType object
   */
  AnimationType getAnimationType();

  /**
   * Get the start time of the animation.
   *
   * @return the start time of the animation, an int
   */
  int getStartTime();

  /**
   * Get the end time of the animation.
   *
   * @return the end time of the animation, an int
   */
  int getEndTime();

  /**
   * Get the string representation of what the animation is changing. If the color is being
   * changed, this method will output "Change in color," if the size is being changed,
   * this method will output "Change in size," and if the coordinates of the shape are
   * being changed, this method will output "Change in coordinates."
   *
   * @return the animation change as a string.
   */
  String getChange();

  /**
   * Get the starting state of the animation as a string.
   *
   * @return the starting state of the animation as a string
   */
  String getStartState();

  /**
   * Get the end state of the animation as a string.
   *
   * @return the end state of the animation as a string
   */
  String getEndState();

  /**
   * Get the string representation of the animation.
   *
   * @return the animation represented as a string
   */
  String getDescription();

  /**
   * Implements the animation on a shape (whether it is changing color, changing size, or
   * changing coordinates).
   *
   * @param time the current time of the animation
   */
  void implementAnimation(double time);

  /**
   * Changes the appropriate fields of the shape to match the changes implemented on the
   * shape (according to whether color, dimension, or coordinates are being changed).
   *
   * @param s a Shape object, whose field will be changed.
   */
  void changeField(Shape s);

  /**
   * Resets the shape that the animation is being implemented on to a new shape.
   *
   * @param s a Shape object, which will now be animated
   */
  void resetShape(Shape s);
}

