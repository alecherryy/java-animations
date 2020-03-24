package cs5004.easyanimator.model.animations;

// Change dimension

import cs5004.easyanimator.model.shapes.Shape;

public class ChangeDimension extends AbstractAnimations{
  private double startD1;
  private double startD2;
  private double destD1;
  private double destD2;
  public ChangeDimension(Shape shape, int start, int end, double startD1, double startD2,
                         double destD1, double destD2) {
    super(AnimationType.CHANGEDIMENSION, shape, start, end);
      this.startD1 = startD1;
      this.startD2 = startD2;
      this.destD1 = destD1;
      this.destD2 = destD2;
    }

  @Override
  public void animate(double currentTime) {

  }

  @Override
  public String getMovement() {
    return null;
  }

  @Override
  public String getStartState() {
    return null;
  }

  @Override
  public String getEndState() {
    return null;
  }

  @Override
  public void changeCoordinates(Shape shape) {

  }
}
