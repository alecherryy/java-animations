package cs5004.easyanimator.model.*;

// Move animation
public class Move extends AbstractAnimations{
  public Move(AnimationType type, Shape shape, int start, int end) {
    super(type, shape, start, end);
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
