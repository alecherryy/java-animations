package cs5004.easyanimator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This class implements ModelItem and all of its operations. Animations are stored as a list.
 */
public class ModelItemImpl implements ModelItem {
  private Shape shape;
  private List<Animations> animations;

  public ModelItemImpl(Shape s) {
    this.shape = s;
    this.animations = new ArrayList<Animations>();
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
  public void addAnimation(Animations a) {
    this.animations.add(a);
  }

  /**
   * Returns the animation.
   *
   * @param index of the animation
   * @throw IndexOutOfBoundsException if index does not exist
   */
  public Animations getAnimation(int index) {
    if (index > this.animations.size() - 1) {
      throw new IndexOutOfBoundsException("This index does not exist.");
    }
    return this.animations.get(index);
  }

  /**
   * Remove animation from a shape.
   *
   * @param index of the animation
   */
  public void removeAnimation(int index) {
    this.animations.remove(index);
  }

  /**
   * Checks if an item has at least one animation and returns true or false accordingly.
   */
  public boolean hasAnimation() {
    return this.animations.size() != 0;
  }


  /**
   * Returns a summary of the item, including a description of the Shape and all the animations.
   *
   * @return a summary in a string
   */
  public String toString() {
    StringBuilder str = new StringBuilder();

    str.append(this.shape.toString());
    str.append("\n");
    for (Animations obj : this.animations) {
      str.append(obj.getDescription());
    }
    str.append("\n\n");

    return str.toString();
  }
}
