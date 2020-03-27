package cs5004.easyanimator.model;

import java.util.ArrayList;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

public interface ModelItem {

  /**
   * Returns the shape.
   */
  Shape getShape();

  /**
   * Returns the name.
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
   */
  boolean hasAnimation();

  /**
   * Returns a summary of the item, including a description of the
   * Shape and all the animations.
   *
   * @return a summary in a string
   */
  String toString();
}
