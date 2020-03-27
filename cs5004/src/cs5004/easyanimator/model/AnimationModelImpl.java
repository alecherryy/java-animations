package cs5004.easyanimator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This class represents an animation model. It implements the AnimationModel interface
 * and all of its operations.
 */

public class AnimationModelImpl implements AnimationModel {
  private List<ModelItem> model;

  /**
   * This is the class constructor. It initializes a new empty list of AnimationModelItem.
   */
  public AnimationModelImpl() {
    model = new ArrayList<ModelItem>();
  }

  /**
   * Add a new shape to the list.
   *
   * @param s the shape to add
   */
  public void addShape(Shape s) {
    this.model.add(new ModelItemImpl(s));
  }

  /**
   * Removes a shape from the list, using its index as an identifier.
   *
   * @param index of the shape to add
   * @throws IndexOutOfBoundsException if index does not exist
   */
  public void removeShape(int index) {
    if (index > this.model.size() - 1) {
      throw new IndexOutOfBoundsException("This index does not exist.");
    }

    // remove given shape using index
    this.model.remove(index);
  }

  /**
   * Add a new animation to a specific shape, using the index to retrieve the correct shape.
   *
   * @param index of the shape
   * @param a     the animation to add
   * @throws IndexOutOfBoundsException if index does not exist
   * @throws IllegalStateException     if the model is empty
   */
  public void addAnimation(int index, Animations a) {
    if (!(this.model.isEmpty())) {
      throw new IllegalStateException("You need to add a shape, before you can add an animation");
    }

    if (index > this.model.size() - 1) {
      throw new IndexOutOfBoundsException("This index does not exist.");
    }

    // use index to select item/shape
    this.model.get(index).addAnimation(a);
  }

  /**
   * Returns true if the model is empty, otherwise returns false.
   *
   * @return true if empty, other returns false
   */
  public boolean isEmpty() {
    return this.model.size() == 0;
  }

  /**
   * Return a summary of each item in the model. For each item, the summary include a description of
   * the shape and a description of each animation associated with the given shape.
   *
   * @return the model in a string
   */
  public String getDescription() {
    StringBuilder str = new StringBuilder();

    for (ModelItem obj : this.model) {
      // call toString() method on each obj and append it
      // to the StringBuilder
      str.append(obj.toString());
    }

    // return StringBuilder in a string
    return str.toString();
  }
}
