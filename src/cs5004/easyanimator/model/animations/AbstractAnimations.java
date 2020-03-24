package cs5004.easyanimator.model.animations;
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

  public AbstractAnimations(AnimationType type, Shape shape, int start, int end) {
    if (end < start) {
      throw new IllegalArgumentException("Animation end-time must be greater than start-time.");
    }
    if (start < 0 || end < 0) {
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
    return "Shape name: " + this.shape.getName() + " " + this.getChange() + " from "
        + this.getStartState() + " to " + this.getEndState() + " starting at " + this.startTime
        + " and ending at " + this.endTime;
  }

  @Override
  public void resetShape(Shape s) {
    this.shape = s;
  }

}
  @Override
  public void resetShape(Shape s) {
    this.shape = s;
  }

}

