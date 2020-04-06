package cs5004.EasyAnimator.model.animations;

import cs5004.EasyAnimator.model.Utils;
import cs5004.EasyAnimator.model.shapes.Shapes;
import cs5004.EasyAnimator.model.shapes.Coordinates;

/**
 * This class represents the second animation type -- changing the coordinates of a shape.
 * It extends the AbstractAnimation class.
 */
public class ChangeCoordinates extends AbstractAnimations {
  private Coordinates originalCoordinates;
  private Coordinates newCoordinates;

  /**
   * Constructs an AbstractAnimation object, with its given type,
   * shape, and start and end times. Calls the AbstractAnimations super-constructor
   * and sets the AnimationType parameter to CHANGECOORDINATES.
   *
   * @param shape the shape will be animated, type Shape.
   * @param start the start time of the animation, an int.
   * @param end the end time of the animation, an int.
   * @param originalC the original coordinates of the object, type Pair.
   * @param newC the new coordinates of the object, type Pair.
   */
  public ChangeCoordinates(Shapes shape, int start, int end,
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
   * Returns the svg tag of the animation.
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation
   */
  public String toSVGTag(double speed) {
    StringBuilder svg = new StringBuilder();
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    svg.append("<animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + this.getShape().svgTagX() + "\" "
        + "from=\"" + this.originalCoordinates.getX() + "\" to=\""
        + this.newCoordinates.getX() + "\" fill=\"freeze\" />\n");

    svg.append("<animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + this.getShape().svgTagY() + "\" "
        + "from=\"" + this.originalCoordinates.getY() + "\" to=\""
        + this.newCoordinates.getY() + "\" fill=\"freeze\" />\n");

    return svg.toString();
  }

  /**
   * Returns the svg tag of the animation.
   *
   * @param speed the speed at which the animation occurs
   * @return svg tag string representation of the animation when there's a loop
   */
  public String toSVGTagWithLoop(double speed) {
    String svg = "";
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    // tag for moving animation
    svg += "<animate attributeType=\"xml\" begin=\"base.begin+" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + this.getShape().svgTagX() + "\" "
        + "from=\"" + this.originalCoordinates.getX() + "\" to=\""
        + this.newCoordinates.getX() + "\" fill=\"freeze\" />\n";

    svg += "<animate attributeType=\"xml\" begin=\"base.begin+" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + this.getShape().svgTagY() + "\" "
        + "from=\"" + this.originalCoordinates.getY() + "\" to=\""
        + this.newCoordinates.getY() + "\" fill=\"freeze\" />\n";

    // tag for looping
    svg += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
        + "attributeName=\"" + this.getShape().svgTagX() + "\" "
        + "from=\"" + this.newCoordinates.getX() + "\" to=\""
        + this.originalCoordinates.getX() + "\" fill=\"freeze\" />\n";

    svg += "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
        + "attributeName=\"" + this.getShape().svgTagY() + "\" "
        + "from=\"" + this.newCoordinates.getY() + "\" to=\""
        + this.originalCoordinates.getY() + "\" fill=\"freeze\" />\n";
    return svg;
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
    } else {
      double finalX = originalX + (changeX * changeInTime);
      double finalY = originalY + (changeY * changeInTime);

      Coordinates newPos = new Coordinates(finalX, finalY);

      this.getShape().changePosition(newPos);
    }
  }

  /**
   * Changes the appropriate fields of the shape.
   *
   * @param s a Shape object, whose field will be changed
   */
  public void updateField(Shapes s) {
    s.changePosition(newCoordinates);
  }
}