package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.Shape;
import cs5004.easyanimator.model.shapes.Pair;

/**
 * This class represents the second animation type -- changing the coordinates of a shape. It
 * extends AbstractAnimation.
 */

public class ChangeCoordinates extends AbstractAnimations {
  private Pair originalCoordinates;
  private Pair newCoordinates;

  /**
   * Constructs an AbstractAnimation object, with its given type, shape, and start and end times.
   * Calls the AbstractAnimations super-constructor and sets the AnimationType parameter to
   * CHANGE COORDINATES.
   *
   * @param shape the shape will be animated, type Shape.
   * @param start the start time of the animation, an int.
   * @param end   the end time of the animation, an int.
   * @param originalC the original coordinates of the object, type Pair.
   */
  public ChangeCoordinates(Shape shape, int start, int end, Pair originalC, Pair newC) {
    super(AnimationType.CHANGECOORDINATES, shape, start, end);
    this.originalCoordinates = originalC;
    this.newCoordinates = newC;
  }

  public Pair getOriginalCoordinates() {
    return originalCoordinates;
  }

  public Pair getNewCoordinates() {
    return newCoordinates;
  }

  @Override
  public String getChange() {
    // TODO returning null pointer error
    return "moves";
  }

  @Override
  public String getStartState() {
    // TODO returning null pointer error
    return Utils.getPositionString(originalCoordinates);
  }

  @Override
  public String getEndState() {
    // TODO returning null pointer error
    return Utils.getPositionString(newCoordinates);
  }

  @Override
  public void implementAnimation(double time) {
    double originalX = this.originalCoordinates.getX();
    double originalY = this.originalCoordinates.getY();

    double changeX = this.newCoordinates.getX() - this.originalCoordinates.getX();
    double changeY = this.newCoordinates.getY() - this.originalCoordinates.getY();

    double changeInTime = (time - this.getStartTime())
        / (double) (this.getEndTime() - this.getStartTime());

    if ((time > this.getEndTime()) || (time < this.getStartTime())) {
      // do nothing.
      return;
    }
    else {
      double finalX = originalX + (changeX * changeInTime);
      double finalY = originalY + (changeY * changeInTime);

      Pair newPos = new Pair(finalX, finalY);

      this.getShape().changePosition(newPos);
    }
  }

  @Override
  public void changeField(Shape s) {
    s.changePosition(newCoordinates);
  }
}

