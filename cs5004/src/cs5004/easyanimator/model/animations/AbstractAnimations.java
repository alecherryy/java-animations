package cs5004.easyanimator.model.animations;

import java.awt.Color;

import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This abstract class implements Animation and all of its methods. It contains the code
 * for an abstract animation and represents the behavior shared by all animations.
 */
public abstract class AbstractAnimations implements Animations {
  private AnimationType type;
  private Shape shape;
  private int startTime;
  private int endTime;

  /**
   * This is the class constructor; it takes 4 parameters: a type, a shape, a start time and an end
   * time.
   *
   * @param type  of animation
   * @param shape to be animated
   * @param start of the animation
   * @param end   of the animation
   * @throws IllegalArgumentException if end time is greater than start time or if they're both
   *                                  negative
   */
  public AbstractAnimations(AnimationType type, Shape shape, int start, int end) {
    // check if end is smaller than start
    if (end < start) {
      throw new IllegalArgumentException("Animation end-time must be greater than start-time.");
    }
    // check end and start are not negative
    if (start < 0 || end < 0) {
      throw new IllegalArgumentException("Start and end time must be positive.");
    }
    this.type = type;
    this.shape = shape;
    this.startTime = start;
    this.endTime = end;
  }

  /**
   * Get the shape that will be animated.
   *
   * @return the shape that will receive the animation
   */
  public Shape getShape() {

    return this.shape;
  }

  /**
   * Get the type of animation.
   *
   * @return the type of the animation, an AnimationType object
   */
  public AnimationType getAnimationType() {

    return this.type;
  }

  /**
   * Get the start time of the animation.
   *
   * @return the start time of the animation, an int
   */
  public int getStartTime() {

    return this.startTime;
  }

  /**
   * Get the end time of the animation.
   *
   * @return the end time of the animation, an int
   */
  public int getEndTime() {

    return this.endTime;
  }

  /**
   * Get the string representation of the animation.
   *
   * @return the animation represented as a string.
   */
  public String getDescription() {
    return "Shape "
        + this.shape.getName() + " "
        + this.getChange() + "from "
        + this.getStartState() + " to "
        + this.getEndState() + " from t=" + this.startTime
        + " to t=" + this.endTime;
  }

  /**
   * Resets the shape that the animation is being implemented on to a new shape.
   *
   * @param s a Shape object, which we are changing the shape animation field to
   */
  public void resetShape(Shape s) {

    this.shape = s;
  }

  /**
   * Get the original color of the shape.
   *
   * @return the original color of the shape
   */
  public Color getOriginalColor() {
    return null;
  }

  /**
   * Get the new color of the shape.
   *
   * @return the new color of the shape
   */
  public Color getNewColor() {
    return null;
  }

  /**
   * Get the original coordinates of the shape.
   *
   * @return the original coordinates of the shape
   */
  public Coordinates getOriginalCoordinates() {
    return null;
  }

  /**
   * Get the new coordinates of the shape.
   *
   * @return the new coordinates of the shape
   */
  public Coordinates getNewCoordinates() {
    return null;
  }

  /**
   * Get the original width of the shape.
   *
   * @return the original width of the shape
   */
  public double getOriginalWidth() {
    return -1;
  }

  /**
   * Get the original height of the shape.
   *
   * @return the original height of the shape
   */
  public double getOriginalHeight() {
    return -1;
  }

  /**
   * Get the new width of the shape.
   *
   * @return the new width of the shape
   */
  public double getNewWidth() {
    return -1;
  }

  /**
   * Get the new height of the shape.
   *
   * @return the new height of the shape
   */
  public double getNewHeight() {
    return -1;
  }
}