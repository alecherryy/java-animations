package cs5004.easyanimator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This is the ModelItemImpl class, which implements all methods listen in
 * the ModelItem Interface. A model item is a composite object; it
 * contains a String value as its name (which common to the shape), a
 * shape object and an array of Animations to be executed on the shape.
 */
public class ModelItemImpl implements ModelItem {
  private String name;
  private Shapes shape;
  private List<Animations> animations;

  /**
   * This is the class constructor. Its only parameter is a shape;
   * once initialized it has a name and an empty array of
   * Animations.
   *
   * @param s the shape
   */
  public ModelItemImpl(Shapes s) {
    this.shape = s;
    this.name = s.getName();
    this.animations = new ArrayList<Animations>();
  }

  /**
   * Returns the name.
   *
   * @return the name in a string
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the shape.
   *
   * @return the shape as an object
   */
  public Shapes getShape() {

    return this.shape;
  }

  /**
   * Add animation to a shape.
   *
   * @param a the animation
   */
  public void addAnimation(Animations a) {
    this.animations.add(a);
  }

  /**
   * Remove animation from a shape.
   *
   * @return a list of animations
   */
  public ArrayList<Animations> getAllAnimations() {

    return (ArrayList<Animations>) this.animations;
  }

  /**
   * Remove animation from a shape.
   *
   * @param index of the animation
   * @throws IndexOutOfBoundsException if animation does exist
   */
  public void removeAnimation(int index) {
    try {
      this.animations.remove(index);
    }
    catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("This animation does not exist.");
    }
  }

  /**
   * Checks if an item has at least one animation and
   * returns true or false accordingly.
   *
   * @return true if the animations array is empty, otherwise returns false
   */
  public boolean hasAnimation() {
    return this.animations.size() != 0;
  }

  /**
   * Returns a summary of the item, including a description of the
   * Shape and all the animations.
   *
   * @return a summary of the item in a string
   */
  public String toString() {
    StringBuilder str = new StringBuilder();

    str.append(this.shape.getDescription());
    str.append("\n\n");
    for (Animations obj : this.animations) {
      str.append(obj.getDescription());
      str.append("\n");
    }
    return str.toString();
  }
}
