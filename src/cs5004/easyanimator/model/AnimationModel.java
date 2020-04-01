package cs5004.easyanimator.model;

import java.util.ArrayList;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This is the AnimationModel Interface. It includes all methods available to an
 * Animation model; including actions such as adding a shape, removing a shape,
 * adding an animation, removing an animation and get a summary of each shape and
 * animation within the model.
 */
public interface AnimationModel {

  /**
   * Returns the model.
   *
   * @return the model
   */
  ArrayList<ModelItem> getModel();

  /**
   * Add a new shape to the list.
   *
   * @param s the shape to add
   * @throws IllegalArgumentException if a shape of the same name already exists
   */
  void addShape(Shape s) throws IllegalArgumentException;

  /**
   * Given a shape name returns the item inside the model matching
   * the given name.
   *
   * @param name the shape name
   */
  ModelItem getItem(String name);

  /**
   * Removes a shape from the list, using its name as an identifier.
   *
   * @param name of the shape to add
   * @throws IllegalArgumentException if the shape does not exist
   */
  void removeShape(String name) throws IllegalArgumentException;

  /**
   * Add a new animation to a specific shape, using the shape name as an identifier.
   *
   * @param name of the shape
   * @param a     the animation to add
   * @throws IllegalArgumentException if the shape does not exist
   */
  void addAnimation(String name, Animations a) throws IllegalArgumentException;

  /**
   * Returns true if the model is empty, otherwise returns false.
   *
   * @return true if empty, other returns false
   */
  boolean isEmpty();

  /**
   * Return a summary of each item in the model. For each item,
   * the summary include a description of the shape and a description
   * of each animation associated with the given shape.
   *
   * @return the model in a string
   */
  String getDescription();
}
