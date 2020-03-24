package cs5004.easyanimator.model.animations;
import java.awt.Color;


// Change color

import cs5004.easyanimator.model.shapes.Shape;

public class ChangeColor extends AbstractAnimations{
  private Color originalColor;
  private Color newColor; // color to change to

  public ChangeColor(Shape shape, int start, int end, Color originalColor, Color newColor) {
    super(AnimationType.CHANGECOLOR, shape, start, end);
    this.originalColor = originalColor;
    this.newColor = newColor;
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
