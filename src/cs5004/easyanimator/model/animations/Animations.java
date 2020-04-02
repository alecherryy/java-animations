package cs5004.easyanimator.model.animations;

import java.awt.*;

import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This interface represents the operations offered by the animation model. It supports all types of
 * animations. One Animation object represents one animation that will be implemented on a shape.
 */

public interface Animations {

  /**
   * Get the shape that will be animated.
   *
   * @return the shape that will receive the animation, a Shapes object.
   */
  Shapes getShape();

  /**
   * Get the type of animation.
   *
   * @return the type of the animation, an AnimationType obkect.
   */
  AnimationType getAnimationType();

  /**
   * Get the start time of the animation.
   *
   * @return the start time of the animation, an int.
   */
  int getStartTime();

  /**
   * Get the end time of the animation.
   *
   * @return the end time of the animation, an int.
   */
  int getEndTime();

  /**
   * Get the string representation of what the animation is changing.
   * If the color is being changed, this method will output "changes color, ";
   * if the size is being changed, this method will output "scales,"
   * and if the coordinates of the shape are being changed, this
   * method will output "moves".
   *
   * @return the animation change as a string
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
   * Implements the animation on a shape (whether it is changing color,
   * changing size, or changing coordinates).
   *
   * @param time the current time of the animation
   */
  void implementAnimation(double time);

  /**
   * Changes the appropriate fields of the shape to match the changes
   * implemented on the shape (according to whether color, dimension,
   * or coordinates are being changed).
   *
   * @param s a Shape object, whose field will be changed
   */
  void updateField(Shapes s);

  /**
   * Resets the shape that the animation is being implemented on to a new shape.
   *
   * @param s a Shape object, which we are changing the shape animation field to
   */
  void resetShape(Shapes s);

  /**
   * Get the original color of the shape.
   *
   * @return the original color of the shape
   */
  Color getOriginalColor();

  /**
   * Get the new color of the shape.
   *
   * @return the new color of the shape
   */
  Color getNewColor();

  /**
   * Get the original coordinates of the shape.
   *
   * @return the original coordinates of the shape
   */
  Coordinates getOriginalCoordinates();

  /**
   * Get the new coordinates of the shape.
   *
   * @return the new coordinates of the shape
   */
  Coordinates getNewCoordinates();

  /**
   * Get the original width of the shape.
   *
   * @return the original width of the shape
   */
  double getOriginalWidth();

  /**
   * Get the original height of the shape.
   *
   * @return the original height of the shape
   */
  double getOriginalHeight();

  /**
   * Get the new width of the shape.
   *
   * @return the new width of the shape
   */
  double getNewWidth();

  /**
   * Get the new height of the shape.
   *
   * @return the new height of the shape
   */
  double getNewHeight();

  /**
   * Returns the svg tag of the animation
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation
   */
  String toSVGTag(double speed);

  /**
   * Returns the svg tag of the animation
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation when there's a loop
   */
  String toSVGTagWithLoop(double speed);

}