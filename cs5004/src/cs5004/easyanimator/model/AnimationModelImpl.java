package cs5004.easyanimator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.animations.AnimationType;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.util.*;
import java.awt.Color;


/**
 * This is the AnimationModel class; it implements all methods available on the
 * AnimationModel Interface.
 */
public class AnimationModelImpl implements AnimationModel {
  private List<ModelItem> model;

  /**
   * This is the class constructor. It initializes a new empty
   * list of objects (i.e., AnimationModelItem).
   */
  public AnimationModelImpl() {
    this.model = new ArrayList<ModelItem>();
  }

  /**
   * Add a new shape to the list.
   *
   * @param s the shape to add
   * @throws IllegalArgumentException if a shape of the same name already exists
   */
  public void addShape(Shape s) {
    String name = s.getName();
    if (helperAddShape(name)) {
      this.model.add(new ModelItemImpl(s));
      // exit method
      return;
    }
    throw new IllegalArgumentException("A shape with this name already exists.");
  }

  /**
   * Helper function to return an item in the model based on the shape's name.
   *
   * @param name of the shape
   * @throws IllegalArgumentException if the shape does not exist
   */
  private boolean helperAddShape(String name) {
    for (ModelItem obj : this.model) {
      if (obj.getName().equalsIgnoreCase(name)) {
        return false;
      }
    }

    return true;
  }

  /**
   * Given a shape name returns the item inside the model matching
   * the given name.
   *
   * @param name the shape name
   */
  public ModelItem getItem(String name) {
    return returnShape(name);
  }

  /**
   * Removes a shape from the list, using its name as an identifier.
   *
   * @param name of the shape to add
   * @throws IllegalArgumentException if the shape does not exist
   */
  public void removeShape(String name) {
    int i = 0;

    for (ModelItem obj : this.model) {
      if (obj.getName().equals(name)) {
        // remove shape at the current index
        this.model.remove(i);
        // exit method
        return;
      }
      i++;
    }
    throw new IllegalArgumentException("This index does not exist.");
  }

  /**
   * Add a new animation to a specific shape, using the shape name as an identifier.
   *
   * @param name of the shape
   * @param a     the animation to add
   * @throws IllegalArgumentException if the shape does not exist
   */
  public void addAnimation(String name, Animations a) {
    // throw an exception if there are no shapes
    if ((this.model.isEmpty())) {
      throw new IllegalArgumentException("You need to add a shape, "
          + "before you can add an animation");
    }

    returnShape(name).addAnimation(a);
  }

  /**
   * Helper function to return an item in the model based on the shape's name.
   *
   * @param name of the shape
   * @throws IllegalArgumentException if the shape does not exist
   */
  private ModelItem returnShape(String name) {
    // look for obj whose name matches the given name
    for (ModelItem obj : this.model) {
      if (obj.getName().equalsIgnoreCase(name)) {
        return obj;
      }
    }
    // if the obj does not exist, throw an exception
    throw new IllegalArgumentException("This shape does not exist.");
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
   * Returns a summary of each item in the model. For each item,
   * the summary include a description of the shape and a description of
   * each animation associated with the given shape. If the
   * model is empty, returns an empty string.
   *
   * @return the model in a string
   */
  public String getDescription() {
    // check if model is empty
    if (!(this.model.isEmpty())) {
      StringBuilder shapes = new StringBuilder();
      StringBuilder animations = animationDescriptions();

      // append title
      shapes.append("Shapes:\n");

      for (ModelItem obj : this.model) {
        // call toString() method on each obj and append it
        // to the StringBuilder
        shapes.append(obj.getShape().getDescription());
        shapes.append("\n");
      }
      // concatenate string
      return shapes.toString() + animations.toString();

    }

    return "";
  }

  /**
   * This is a private helper function to help format the model's getDescription() method.
   * It iterates through the model and groups all animations into a separate array. Then, sorts
   * the animations by their start time in ascending order (i.e. from the smallest to biggest).
   *
   * @return a StringBuilder containing all the animations descriptions
   */
  private ArrayList<Animations> createAnimationsList() {
    ArrayList<Animations> animations = new ArrayList<Animations>();

    // iterate through the model
    for (ModelItem obj : this.model) {
      // if object has animations, it groups them into one array
      if (obj.hasAnimation()) {
        for (Animations a : obj.getAllAnimations()) {
          animations.add(a);
        }
      }
    }

    // sort animations by start time
    animations.sort((Animations a, Animations b) ->
        Integer.compare(a.getStartTime(), b.getStartTime()));

    return animations;
  }

  /**
   * This is a private helper function to help format the model's getDescription() method.
   *
   * @return a StringBuilder containing all the animations descriptions
   */
  private StringBuilder animationDescriptions() {
    // call createAnimationsList() to create an array of animations
    ArrayList<Animations> animations = createAnimationsList();
    StringBuilder str = new StringBuilder();

    // iterate through the array and append each description to the StringBuilder
    for (Animations a : animations) {
      str.append(a.getDescription());
      str.append("\n");
    }

    return str;
  }

  public final class AnimationModelBuilder
      implements TweenModelBuilder<AnimationModel> {

    private List<Animations> animationsList;
    private List<Shape> shapesList;


    public AnimationModelBuilder() {
      this.animationsList = new ArrayList<Animations>();
      this.shapesList = new ArrayList<Shape>();
    }

    @Override
    public TweenModelBuilder<AnimationModel> addOval(
        String name,
        float x, float y,
        float xRadius, float yRadius,
        float red, float green, float blue,
        int appear, int disappear) {
      Coordinates pos = new Coordinates(x, y);
      Color col = new Color(red, green, blue);
      Shape shape = new Oval(name, appear, disappear, xRadius, yRadius, col, pos);
      shapesList.add(shape);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRectangle(
        String name,
        float minX, float minY,
        float width, float height,
        float red, float green, float blue,
        int appear, int disappear) {
      Coordinates pos = new Coordinates(minX, minY);
      Color col = new Color(red, green, blue);
      Shape shape = new Rectangle(name, appear, disappear, width, height, col, pos);
      shapesList.add(shape);
      return this;
    }

    private void addAnimations(Animations a) {
      AnimationType addType = a.getAnimationType();
      Shape addShape = a.getShape();
      int addStart = a.getStartTime();

      for (int i = 0; i < animationsList.size(); i++) {
        Animations current = animationsList.get(i);
        AnimationType type = current.getAnimationType();
        Shape shape = current.getShape();
        int start = current.getStartTime();
        int end = current.getEndTime();

      }
      animationsList.add(a);
    }

    @Override
    // TODO implement code
    public TweenModelBuilder<AnimationModel> addMove(
        String name,
        float fromX, float fromY, float toX, float toY,
        int start, int endT) {
      Coordinates origin = new Coordinates(fromX, fromY);
      Coordinates dest = new Coordinates(toX, toY);
        return this;
    }


    @Override
    // TODO implement code
    public TweenModelBuilder<AnimationModel> addColorChange(
        String name,
        float oldR, float oldG, float oldB, float newR, float newG, float newB,
        int startTime, int endTime) {
      Color oldColor = new Color(oldR, oldG, oldB);
      Color newColor = new Color(newR, newG, newB);

      Shape s = null;

      return this;
    }

    @Override
    // TODO implement code
    public TweenModelBuilder<AnimationModel> addScaleChange(String name, float fromSx, float
        fromSy, float toSx, float toSy, int startTime, int endTime) {

      Shape s = null;
      return this;
    }

    @Override
    // TODO implement code
    public AnimationModel build() {
      return null;
      // return new AnimationModelImpl(this);
    }
  }
  
}

