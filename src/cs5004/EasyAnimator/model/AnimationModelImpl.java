package cs5004.EasyAnimator.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.animations.ChangeColor;
import cs5004.EasyAnimator.model.animations.ChangeCoordinates;
import cs5004.EasyAnimator.model.animations.ChangeSize;
import cs5004.EasyAnimator.model.shapes.Coordinates;
import cs5004.EasyAnimator.model.shapes.Oval;
import cs5004.EasyAnimator.model.shapes.Rectangle;
import cs5004.EasyAnimator.model.shapes.Shapes;

/**
 * This is the AnimationModel class; it implements all methods available
 * on the AnimationModel Interface.
 */
public class AnimationModelImpl implements AnimationModel {
  private ArrayList<Shapes> shapes;
  private ArrayList<Animations> animations;

  /**
   * This is the class constructor. It initializes a new empty list
   * of objects (i.e., AnimationModelItem).
   */
  public AnimationModelImpl() {

    this.shapes = new ArrayList<Shapes>();
    this.animations = new ArrayList<Animations>();
  }

  /**
   * Private constructor, later used in our static class where we implement our
   * model builder. This will allow us to return an AnimationModelImpl object
   * with the appropriate changes made to it.
   *
   * @param builder animation model builder
   */
  private AnimationModelImpl(AnimationModelBuilder builder) {
    this.shapes = builder.shapesList;
    this.animations = builder.animationsList;
  }

  /**
   * Add a new shape to the list.
   *
   * @param s the shape to add
   * @throws IllegalArgumentException if a shape of the same name already exists
   */
  public void addShape(Shapes s) {
    String name = s.getName();

    if (helperDuplicateShape(name)) {
      // exit method
      throw new IllegalArgumentException("A shape with this name already exists.");
    }
    this.shapes.add(s);
  }

  /**
   * Helper function to return an item in the model based on the shape's name.
   *
   * @param name of the shape
   * @throws IllegalArgumentException if the shape does not exist
   */
  private boolean helperDuplicateShape(String name) {
    for (Shapes s : this.shapes) {
      if (s.getName().equalsIgnoreCase(name)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Add a new animation to a specific shape, using the shape name as an identifier.
   *
   * @param a the animation to add
   * @throws IllegalArgumentException if the shape does not exist
   */
  public void addAnimation(Animations a) {
    this.animations.add(a);
  }

  /**
   * Returns a list of shapes.
   *
   * @return a list of shapes
   */
  public ArrayList<Shapes> getShapes() {
    return this.shapes;
  }

  /**
   * Returns a list of animations.
   *
   * @return a list of animations
   */
  public ArrayList<Animations> getAnimations() {
    return this.animations;
  }

  /**
   * Returns a description of all shapes and animations in a string.
   *
   * @return the model in a string
   */
  public String getDescription() {
    // check if model is empty
    StringBuilder str = new StringBuilder();

    if (this.shapes.size() != 0) {
      // append title
      str.append("Shapes:\n");

      for (Shapes s : this.shapes) {
        // call toString() method on each obj and append it
        // to the StringBuilder
        str.append(s.getDescription());
        str.append("\n");
      }

    }

    if (this.animations.size() != 0) {
      Utils.sortAnimations(this.animations);

      for (Animations a : this.animations) {
        str.append(a.getDescription());
        str.append("\n");
      }
    }

    return str.toString();
  }

  /**
   * This is a static class that implements our Tween Model Builder, and will
   * add shapes and animations to our model.
   */
  public static final class AnimationModelBuilder implements TweenModelBuilder<AnimationModel> {
    private ArrayList<Animations> animationsList;
    private ArrayList<Shapes> shapesList;
    private HashMap<String, String> shapesSource;
    private HashMap<String, ArrayList<Integer>> shapesInfo;

    /**
     * Constructs a SimpleAnimationBuilder object.
     **/
    public AnimationModelBuilder() {
      this.animationsList = new ArrayList<Animations>();
      this.shapesList = new ArrayList<Shapes>();
      this.shapesSource = new HashMap<String, String>();
      this.shapesInfo = new HashMap<String, ArrayList<Integer>>();
    }

    /**
     * Add a new oval to the model.
     *
     * @param name the name of the oval
     * @param x the x-coordinate of the center of the oval
     * @param y the y-coordinate of the center of the oval
     * @param xRadius the x-radius of the oval
     * @param yRadius the y-radius of the oval
     * @param color the color of the oval as a float
     * @param appear the appear time of the oval
     * @param disappear the disappear time of the oval.
     * @return the builder object
     */
    public TweenModelBuilder<AnimationModel> addOval(
            String name,
            float x, float y,
            float xRadius, float yRadius,
            Color color,
            int appear, int disappear) {
      Coordinates pos = new Coordinates(x, y);
      Shapes shape = new Oval(name, appear, disappear, xRadius, yRadius, color, pos);
      shapesList.add(shape);
      return this;
    }

    /**
     * Add a new rectangle to the model.
     *
     * @param name the name of the oval
     * @param x the x-coordinate of the lower left corner of the rectangle
     * @param y the y-coordinate of the lower left corner of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @param appear the appear time of the rectangle
     * @param disappear the disappear time of the rectangle
     * @return the builder object
     */
    public TweenModelBuilder<AnimationModel> addRectangle(
            String name,
            float x, float y,
            float width, float height,
            Color color,
            int appear, int disappear) {
      Coordinates pos = new Coordinates(x, y);
      Shapes shape = new Rectangle(name, appear, disappear, width, height, color, pos);
      shapesList.add(shape);
      return this;
    }

    /**
     * Add given animation in order in the list of animations if animation is valid.
     *
     * @param a animation to add to list of animations
     */
    private void addAnimations(Animations a) {
      for (Animations obj : this.animationsList) {
        if (a.getAnimationType() == obj.getAnimationType()) {
          if (invalidAnimation(a, obj)) {
            throw new IllegalArgumentException(
                    "Incompatible move for the same shape "
                            + "during overlapping time intervals");
          }
        }
      }
      animationsList.add(a);
    }

    /**
     * Helper private method to ensure the animation being added does not conflict
     * with an existing one.
     *
     * @param a one animation
     * @param b another animation
     * @return true if animation is invalid, otherwise return false
     */
    private boolean invalidAnimation(Animations a, Animations b) {
      // since shape names are unique, check those first
      if (a.getShape().getName().equals(b.getShape().getName())) {
        if (a.getAnimationType() == b.getAnimationType()) {
          if (a.getStartTime() >= b.getStartTime() && a.getEndTime() <= b.getEndTime()) {
            return true;
          }
        }
      }
      return false;
    }

    /**
     * Move the shape that is passed in to the given position that is passed in
     * during the time interval that is passed in.
     *
     * @param name the name of the shape that will be moved
     * @param fromX the x-coordinate from which the shape will be moved
     * @param fromY the y-coordinate to which the shape will be moved
     * @param toX the x-coordinate to which the shape will be moved
     * @param toY the y-coordinate to which the shape will be moved
     * @param start the time when the move starts
     * @param end the time when the move ends
     */
    public TweenModelBuilder<AnimationModel> addMove(
            String name,
            float fromX, float fromY,
            float toX, float toY,
            int start, int end) {
      Shapes s = null;

      for (Shapes obj : this.shapesList) {
        if (obj.getName().equals(name)) {
          s = obj;
          s.changePosition(new Coordinates(fromX, fromY));
        }
      }

      Animations a = new ChangeCoordinates(s, start, end, new Coordinates(fromX, fromY),
              new Coordinates(toX, toY));

      this.addAnimations(a);
      return this;
    }

    /**
     * Change the color of the shape that is passed in to the given position
     * that is passed in during the time interval that is passed in.
     *
     * @param name the name of the shape whose color will be changed
     * @param color the shape's original color
     * @param newColor the shape's new color
     * @param start the time when the color change starts
     * @param end the time when the color change ends
     * @return the animation builder model
     */
    public TweenModelBuilder<AnimationModel> addColorChange(
            String name, Color color, Color newColor,
            int start, int end) {

      Shapes s = null;

      for (Shapes obj : this.shapesList) {
        if (obj.getName().equals(name)) {
          s = obj;
          s.changeColor(color);
        }
      }

      Animations a = new ChangeColor(s, start, end, color, newColor);
      this.addAnimations(a);

      return this;
    }

    /**
     * Change the width and height of the shape that is passed in to
     * the width and height that are passed in during the time interval that is passed in.
     *
     * @param name the name of the shape whose size will be changed
     * @param w the original width of the shape
     * @param h the original height of the shape
     * @param newW the new width of the shape, to which its width will be changed
     * @param newH the new width of the shape, to which its height will be changed
     * @param start the time when the scale change starts
     * @param end the time when the scale change ends
     * @return the animation builder model
     */
    public TweenModelBuilder<AnimationModel> addSizeChange(
            String name,
            float w, float h,
            float newW, float newH,
            int start, int end) {

      Shapes s = null;

      for (Shapes obj : this.shapesList) {
        if (obj.getName().equals(name)) {
          s = obj;
          s.changeD1(w);
          s.changeD2(h);
        }
      }

      Animations a = new ChangeSize(s, start, end, w, h, newW, newH);
      this.addAnimations(a);

      return this;
    }

    /**
     * Generate a dictionary of shapes.
     */
    public void addShapeMap(String name, String type) {
      this.shapesSource.put(name, type);
    }

    /**
     * Generate a dictionary of shapes.
     */
    public void addShapInfoeMap(String name, ArrayList<Integer> info) {

      this.shapesInfo.put(name, info);
    }

    /**
     * Generate a dictionary of shapes.
     */
    public void generateShapes() {
      ArrayList<Animations> f;


      String name;
      float x;
      float y;
      float width;
      float height;
      Color color;
      int appear;
      int disappear;

      for (HashMap.Entry<String, String> s : this.shapesSource.entrySet()) {
        f = (ArrayList<Animations>) this.animationsList.stream().filter(
                a -> a.getShape().getName().equals(s.getKey())).collect(Collectors.toList());
        Utils.sortAnimations(f);

        name = s.getKey();
        x = (float) this.shapesInfo.get(s.getKey()).get(0);
        y = (float) this.shapesInfo.get(s.getKey()).get(1);
        width = (float) this.shapesInfo.get(s.getKey()).get(2);
        height = (float) this.shapesInfo.get(s.getKey()).get(3);
        color = new Color(
                this.shapesInfo.get(s.getKey()).get(4),
                this.shapesInfo.get(s.getKey()).get(5),
                this.shapesInfo.get(s.getKey()).get(6));
        appear = f.get(0).getStartTime();
        f.sort((Animations a, Animations b) ->
                Integer.compare(a.getEndTime(), b.getEndTime()));
        disappear = f.get(0).getEndTime();

        if (s.getValue().equals("rectangle")) {
          addRectangle(s.getKey(), x, y, width, height, color, appear, disappear);
        }
        else {
          addOval(s.getKey(), x, y, width, height, color, appear, disappear);
        }

      }
    }

    /**
     * Return the built model.
     *
     * @return the build model
     */
    public AnimationModel build() {
      return new AnimationModelImpl(this);
    }
  }
}
