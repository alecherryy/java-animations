package cs5004.easyanimator.model;

import java.util.ArrayList;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This is the ModelItem Interface. It includes all methods available
 * to model item.
 */
public interface ModelItem {

  /**
   * Returns the shape.
   */
  Shape getShape();

  /**
   * Returns the name.
   *
   * @return the name in a string
   */
  String getName();

  /**
   * Add animation to a shape.
   *
   * @param a the animation
   */
  void addAnimation(Animations a);

  /**
   * Remove animation from a shape.
   *
   * @return a list of animations
   */
  ArrayList<Animations> getAllAnimations();

  /**
   * Remove animation from a shape.
   *
   * @param index of the animation
   * @throws IndexOutOfBoundsException if animation does exist
   */
  void removeAnimation(int index);

  /**
   * Checks if an item has at least one animation and
   * returns true or false accordingly.
   *
   * @return true if the animations array is empty, otherwise returns false
   */
  boolean hasAnimation();

  /**
   * Returns a summary of the item, including a description of the
   * Shape and all its animations.
   *
   * @return a summary of the item in a string
   */
  String toString();
}
