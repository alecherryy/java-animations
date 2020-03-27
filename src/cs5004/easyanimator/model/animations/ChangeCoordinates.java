package cs5004.easyanimator.model.animations;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This class represents changing the coordinates of a shape. It extends
 * AbstractAnimation and implements all its methods.
 */
public class ChangeCoordinates extends AbstractAnimations {
  private Coordinates originalCoordinates;
  private Coordinates newCoordinates;

  /**
   * This is the class constructor; it takes 5 parameters: a shape, a start
   * time, an end time, an original pair of coordinates and a new pair of coordinates.
   *  @param shape to be animated
   * @param start of the animation
   * @param end of the animation
   * @param originalC of the shape at the beginning of the animation
   * @param newC of the shape
   */
  public ChangeCoordinates(Shape shape, int start, int end,
                           Coordinates originalC, Coordinates newC) {
    super(AnimationType.CHANGECOORDINATES, shape, start, end);
    originalCoordinates = originalC;
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
   * Return a string.
   *
   * @return a string
   */
  public String getChange() {
    return "moves ";
  }

  /**
   * Private helper method to get the shape's coordinates as a string.
   *
   * @param pos the position of the shape (i.e. its coordinates)
   * @return the string representation of the shape's position
   */
  private String getPositionAsString(Coordinates pos)
  {
    return "(" + pos.getX() + "," + pos.getY() + ")";
  }

  /**
   * Get the start time of the animation.
   *
   * @return the start time of the animation, an int
   */
  public String getStartState() {
    return getPositionAsString(originalCoordinates);
  }

  /**
   * Get the end time of the animation.
   *
   * @return the end time of the animation, an int
   */
  public String getEndState() {
    return getPositionAsString(newCoordinates);
  }

  /**
   * Implements the animation on a shape.
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
    }
    else {
      double finalX = originalX + (changeX * changeInTime);
      double finalY = originalY + (changeY * changeInTime);

      Coordinates newPos = new Coordinates(finalX, finalY);

      this.getShape().changePosition(newPos);
    }
  }

  /**
   * Changes the appropriate fields of the shape to match the changes
   * implemented on the shape.
   *
   * @param s a Shape object, whose field will be changed.
   */
  public void changeField(Shape s) {
    s.changePosition(newCoordinates);
  }
}
