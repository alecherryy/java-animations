package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.shapes.Shape;


public abstract class AbstractAnimations implements Animations {
  private AnimationType type;
  private Shape shape;
  private int startTime;
  private int endTime;

  public AbstractAnimations(AnimationType type, Shape shape, int start, int end) {
    if (end < start) {
      throw new IllegalArgumentException("");
    }
    this.type = type;
    this.shape = shape;
    this.startTime = start;
    this.endTime = end;
  }

  @Override
  public String getDescription() {
    //TODO IMPLEMENT CODE
    return null;
  }

  @Override
  public Shape getShape() {
    return this.shape;
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
  public AnimationType getAnimationType() {
    return this.type;
  }

  @Override
  public void setNewShape(Shape s) {
    this.shape = s;
  }

}
