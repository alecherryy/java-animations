package cs5004.easyanimator.model.animations;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.shapes.Coordinates;

/**
 * This class represents the second animation type -- changing the coordinates of a shape. It
 * extends AbstractAnimation.
 */
public class ChangeCoordinates extends AbstractAnimations {
  private Coordinates originalCoordinates;
  private Coordinates newCoordinates;

  /**
   * Constructs an AbstractAnimation object, with its given type, shape, and start and end times.
   * Calls the AbstractAnimations super-constructor and sets the AnimationType parameter to
   * CHANGECOORDINATES.
   *
   * @param shape     the shape will be animated, type Shape.
   * @param start     the start time of the animation, an int.
   * @param end       the end time of the animation, an int.
   * @param originalC the original coordinates of the object, type Pair.
   * @param newC      the new coordinates of the object, type Pair.
   */
  public ChangeCoordinates(Shapes shape, int start, int end,
                           Coordinates originalC, Coordinates newC) {
    super(AnimationType.CHANGECOORDINATES, shape, start, end);
    this.originalCoordinates = originalC;
    this.newCoordinates = newC;
  }

  @Override
  public Coordinates getOriginalCoordinates() {
    return originalCoordinates;
  }

  @Override
  public Coordinates getNewCoordinates() {
    return newCoordinates;
  }

  @Override
  public String toSVGTag(double speed) {
    String svg = "";
    double begin = (this.getStartTime() / speed) * 1000;
    double end = (this.getEndTime() / speed) * 1000;
    double dur = end - begin;

    svg += "<animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + this.getShape().svgTagX() + "\" "
        + "from=\"" + this.originalCoordinates.getX() + "\" to=\""
        + this.newCoordinates.getX() + "\" fill=\"freeze\" />\n";

    svg += "<animate attributeType=\"xml\" begin=\"" + begin + "ms\" dur=\""
        + dur + "ms\" attributeName=\"" + this.getShape().svgTagY() + "\" "
        + "from=\"" + this.originalCoordinates.getY() + "\" to=\""
        + this.newCoordinates.getY() + "\" fill=\"freeze\" />\n";

    return svg;
  }

  @Override
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

  @Override
  public String getChange() {
    return "moves";
  }

  @Override
  public String getStartState() {
    return Utils.getPositionString(originalCoordinates);
  }

  @Override
  public String getEndState() {
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
    } else {
      double finalX = originalX + (changeX * changeInTime);
      double finalY = originalY + (changeY * changeInTime);

      Coordinates newPos = new Coordinates(finalX, finalY);

      this.getShape().changePosition(newPos);
    }
  }

  @Override
  public void updateField(Shapes s) {
    s.changePosition(newCoordinates);
  }
}