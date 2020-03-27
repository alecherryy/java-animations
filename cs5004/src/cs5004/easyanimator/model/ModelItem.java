package cs5004.easyanimator.model;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This interface represents a model item, and it establishes the relationship between a shape
 * and an animation. Its primary purpose is to add and remove an animation to/from a shape.
 */
public interface ModelItem {

  /**
   * Returns the shape.
   */
  Shape getShape();

  /**
   * Add animation to a shape.
   *
   * @param a the animation
   */
  void addAnimation(Animations a);

  /**
   * Returns the animation.
   *
   * @param index of the animation
   */
  Animations getAnimation(int index);

  /**
   * Remove animation from a shape.
   *
   * @param index of the animation
   */
  void removeAnimation(int index);

  /**
   * Checks if an item has at least one animation and returns true or false accordingly.
   */
  boolean hasAnimation();

  /**
   * Returns a summary of the item, including a description of the Shape and all the animations.
   *
   * @return a summary in a string
   */
  String toString();
}
