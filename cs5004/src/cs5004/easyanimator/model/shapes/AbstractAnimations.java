package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.*;
import java.awt.Color;

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
   * Constructs an AbstractAnimation object, with its given type, shape, and start and end times.
   *
   * @param type  the animation type, as defined by the AnimationType enum.
   * @param shape the shape will be animated, type Shape.
   * @param start the start time of the animation, an int.
   * @param end   the end time of the animation, an int.
   * @throws IllegalArgumentException if the start time is after the end time, or if either the
   * start or end times are negative.
   */
  public AbstractAnimations(AnimationType type, Shape shape, int start, int end) {
    if (end < start) {
      throw new IllegalArgumentException("Animation end-time must be greater than start-time.");
    }
    if (Utils.isNegative(start) || Utils.isNegative(end)) {
      throw new IllegalArgumentException("Start and end time must be positive.");
    }
    this.type = type;
    this.shape = shape;
    this.startTime = start;
    this.endTime = end;
  }

  @Override
  public Shape getShape() {
    return this.shape;
  }

  @Override
  public AnimationType getAnimationType() {
    return this.type;
  }

  @Override
  public int getStartTime() {
    return this.startTime;
  }

  @Override
  public int getEndTime() {
    return this.endTime;
  }

  @Override
  public String getDescription() {
    return "shape " + this.shape.getName() + " " + this.getChange() + " from "
        + this.getStartState() + " to " + this.getEndState() + " from t=" + this.startTime
        + " to t=" + this.endTime;
  }

  @Override
  public void resetShape(Shape s) {
    this.shape = s;
  }

  @Override
  public Color getOriginalColor() {
    return null;
  }

  @Override
  public Color getNewColor() {
    return null;
  }

  @Override
  public Pair getOriginalCoordinates() {
    return null;
  }

  @Override
  public Pair getNewCoordinates() {
    return null;
  }

  @Override
  public double getOriginalWidth() {
    return -1;
  }

  @Override
  public double getOriginalHeight() {
    return -1;
  }

  @Override
  public double getNewWidth() {
    return -1;
  }

  @Override
  public double getNewHeight() {
    return -1;
  }


}

