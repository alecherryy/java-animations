package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.shapes.Shape;

// Move animation
public class Move extends AbstractAnimations {
  public Move(AnimationType type, Shape shape, int start, int end) {
    super(type, shape, start, end);
  }

  public void animate(double currentTime) {

  }

  public String getMovement() {
    return null;
  }

  public String getStartState() {
    return null;
  }

  public String getEndState() {
    return null;
  }

  public void changeCoordinates(Shape shape) {

  }
}
