package cs5004.easyanimator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

public class ModelItemImpl implements ModelItem {
  private String name;
  private Shape shape;
  private List<Animations> animations;

  public ModelItemImpl(Shape s) {
    this.shape = s;
    this.name = s.getName();
    this.animations = new ArrayList<Animations>();
  }

  /**
   * Returns the name.
   */
  public String getName() {
    return this.name;
  }

  /**
   * Returns the shape.
   */
  public Shape getShape() {
    return this.shape;
  }

  /**
   * Add animation to a shape.
   *
   * @param a the animation
   */
  public void addAnimation(Animations a) { this.animations.add(a); }

  /**
   * Remove animation from a shape.
   *
   * @return a list of animations
   */
  public ArrayList<Animations> getAllAnimations() {

    try {
      if (this.hasAnimation()) {
        return (ArrayList<Animations>) this.animations;
      }
    }
    catch (NullPointerException n) {
      System.out.println("This shape does not have any animations yet.");
    }

    return null;
  }

  /**
   * Remove animation from a shape.
   *
   * @param index of the animation
   */
  public void removeAnimation(int index) { this.animations.remove(index); }

  /**
   * Checks if an item has at least one animation and
   * returns true or false accordingly.
   */
  public boolean hasAnimation() {
    return this.animations.size() != 0;
  }

  /**
   * Returns a summary of the item, including a description of the
   * Shape and all the animations.
   *
   * @return a summary in a string
   */
  public String toString() {
    StringBuilder str = new StringBuilder();

    str.append(this.shape.getDescription());
    str.append("\n\n");
    for (Animations obj : this.animations) {
      str.append(obj.getDescription());
    }
    return str.toString();
  }
}
