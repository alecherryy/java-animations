package cs5004.easyanimator.model;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This interface represents an animation model. It contains methods to add shapes and
 * animations to the model, check whether there are shapes or animations in the model, removing
 * shapes from the model, and get a summary of each item in the model.
 */

public interface AnimationModel {

  /**
   * Add a new shape to the list.
   *
   * @param s the shape to add
   */
  void addShape(Shape s);

  /**
   * Removes a shape from the list, using its index as an identifier.
   *
   * @param index the shape to add
   */
  void removeShape(int index);

  /**
   * Add a new animation to a specific shape, using the index to retrieve the correct shape.
   *
   * @param a the shape to add
   * @param a the shape to add
   */
  void addAnimation(int index, Animations a);

  /**
   * Returns true if the model is empty, otherwise returns false.
   *
   * @return true if empty, other returns false
   */
  boolean isEmpty();

  /**
   * Return a summary of each item in the model. For each item, the summary include a description of
   * the shape and a description of each animation associated with the given shape.
   *
   * @return the model in a string
   */
  String getDescription();

}
