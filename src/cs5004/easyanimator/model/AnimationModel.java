package cs5004.easyanimator.model;

import java.util.ArrayList;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This is the AnimationModel Interface. It includes all methods available to an
 * Animation model; including actions such as adding a shape, removing a shape,
 * adding an animation, removing an animation and get a summary of each shape and
 * animation within the model.
 */
public interface AnimationModel {

  /**
   * Add a new shape to the list.
   *
   * @param s the shape to add
   * @throws IllegalArgumentException if a shape of the same name already exists
   */
  void addShape(Shapes s) throws IllegalArgumentException;


  /**
   * Add a new animation to the list of animations.
   *
   * @param a the animation to add
   * @throws IllegalArgumentException if the shape does not exist
   */
  void addAnimation(Animations a) throws IllegalArgumentException;

  /**
   * Returns a list of shapes.
   *
   * @return a list of shapes
   */
  ArrayList<Shapes> getShapes();

  /**
   * Returns a list of animations.
   *
   * @return a list of animations
   */
  ArrayList<Animations> getAnimations();

  /**
   * Returns a list of settings.
   *
   * @return the model settings
   */
  ArrayList<Integer> getSettings();

  /**
   * Return a summary of each item in the model. For each item,
   * the summary include a description of the shape and a description
   * of each animation associated with the given shape.
   *
   * @return the model in a string
   */
  String getDescription();

  /**
   * Returns the end time from the list of animations.
   *
   * @return end time from the list of animations as an int.
   */
  int getEnd();
}
