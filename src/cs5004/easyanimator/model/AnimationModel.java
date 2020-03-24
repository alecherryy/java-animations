package cs5004.easyanimator.model;

import java.util.List;

import cs5004.easyanimator.model.shapes.Shape;
import javafx.animation.Animation;

public interface AnimationModel {

  // Add shape to list of shapes
  void addShape(Shape s);

  // Add animation to list of animations
  void addAnimations(Animation a);

  // Get String representation of present state of the animation
  String getDescription();

  // Get the list of shapes
  List<Shape> getShapes();

  // Get a list of animation
  List<Animation> getAnimations();

  int getLastEndTime();

}
