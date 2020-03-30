package cs5004.easyanimator.model.animations;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.Shape;
import cs5004.easyanimator.model.shapes.Coordinates;

/**
 * This class represents the second animation type -- changing
 * the coordinates of a shape. It extends AbstractAnimation.
 */
public class ChangeCoordinates extends AbstractAnimations {
  private Coordinates originalCoordinates;
  private Coordinates newCoordinates;

  /**
   * Constructs an AbstractAnimation object, with its given type, shape,
   * and start and end times. Calls the AbstractAnimations super-constructor
   * and sets the AnimationType parameter to CHANGECOORDINATES.
   *
   * @param shape     the shape will be animated, type Shape.
   * @param start     the start time of the animation, an int.
   * @param end       the end time of the animation, an int.
   * @param originalC the original coordinates of the object, type Pair.
   * @param newC      the new coordinates of the object, type Pair.
   */
  public ChangeCoordinates(Shape shape, int start, int end,
                           Coordinates originalC, Coordinates newC) {
    super(AnimationType.CHANGECOORDINATES, shape, start, end);
    this.originalCoordinates = originalC;
    this.newCoordinates = newC;
  }

  /**
   * Get the original coordinates of the shape.
   *
   * @return the original coordinates of the shape
   */
  public Coordinates getOriginalCoordinates() {
    return originalCoordinates;
  }

  /**
   * Get the new coordinates of the shape.
   *
   * @return the new coordinates of the shape
   */
  public Coordinates getNewCoordinates() {
    return newCoordinates;
  }

  /**
   * Returns the string "moves".
   *
   * @return the animation change as a string
   */
  public String getChange() {
    return "moves ";
  }

  /**
   * Get the starting state of the animation as a string.
   *
   * @return the starting state of the animation as a string
   */
  public String getStartState() {
    return Utils.getPositionString(originalCoordinates);
  }

  /**
   * Get the end state of the animation as a string.
   *
   * @return the end state of the animation as a string
   */
  public String getEndState() {
    return Utils.getPositionString(newCoordinates);
  }

  /**
   * Implements the ChangeCoordinates animation on a shape.
   *
   * @param time the current time of the animation
   */
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
    } else {
      double finalX = originalX + (changeX * changeInTime);
      double finalY = originalY + (changeY * changeInTime);

      Coordinates newPos = new Coordinates(finalX, finalY);

      this.getShape().changePosition(newPos);
    }
  }

  /**
   * Changes the appropriate fields of the shape to match the changes
   * implemented on the shape (according to whether color, dimension,
   * or coordinates are being changed).
   *
   * @param s a Shape object, whose field will be changed
   */
  public void updateField(Shape s) {
    s.changePosition(newCoordinates);
  }
}