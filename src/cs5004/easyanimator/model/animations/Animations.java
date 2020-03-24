package cs5004.easyanimator.model.animations;

import cs5004.easyanimator.model.shapes.*;

public interface Animations {

  // Change fields of the shape that needs to be animated.
  void animate(double currentTime);

  // Get string representation of the animation
  String getDescription();

  // Get string representation of the animation movement
  String getMovement();

  // Get string representation of the start state of the animation
  String getStartState();

  // Get string representation of the end state of the animation
  String getEndState();

  // Get the shape that will be animated
  Shape getShape();

  // get start time of the animation
  int getStartTime();

  // get end time of the animation.
  int getEndTime();

  // get type of animation
  AnimationType getAnimationType();

  // Changes the fields of the shape to match the destination of the animation.
  void changeCoordinates(Shape shape);

  // set the shape of the animation to a new shape.
  void setNewShape(Shape shape);

}
