package cs5004.EasyAnimator.model;

import java.awt.Color;
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
  private ArrayList<Integer> settings;

  /**
   * This is the class constructor. It initializes a new empty list
   * of objects (i.e., AnimationModelItem).
   */
  public AnimationModelImpl() {
    this.shapes = new ArrayList<Shapes>();
    this.animations = new ArrayList<Animations>();
    this.settings = new ArrayList<Integer>();
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
    this.settings = builder.settings;
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
   * Returns a list of settings.
   *
   * @return the model settings
   */
  public ArrayList<Integer> getSettings() {
    return this.settings;
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
    private ArrayList<Integer> settings;
    private HashMap<String, String> source;
    private HashMap<String, ArrayList<ArrayList<Integer>>> data;

    /**
     * Constructs a SimpleAnimationBuilder object.
     **/
    public AnimationModelBuilder() {
      this.animationsList = new ArrayList<Animations>();
      this.shapesList = new ArrayList<Shapes>();
      this.settings = new ArrayList<Integer>();
      // create a hash map with {name: type}
      this.source = new HashMap<String, String>();
      // create a hash map with {name: [[shape values], [shape values]]}
      this.data = new HashMap<String, ArrayList<ArrayList<Integer>>>();
    }

    /**
     * Add given animation in order in the list of animations if animation is valid.
     *
     * @param x the x coordinates
     * @param y the y coordinates
     * @param width the width coordinates
     * @param height the width coordinates
     * @return the model builder
     */
    public TweenModelBuilder<AnimationModel> setBounds(int x, int y, int width, int height) {
      this.settings.add(x);
      this.settings.add(y);
      this.settings.add(width);
      this.settings.add(height);

      return this;
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

      if (checkShapeDuplicate(name)) {
        shapesList.add(shape);
      }

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

      if (checkShapeDuplicate(name)) {
        shapesList.add(shape);
      }

      return this;
    }

    /**
     * Private helper method to make sure the same shape is not added twice
     * (i.e. no duplicates).
     *
     * @param name of the shape
     * @return true if the shape is unique, otherwise returns false
     */
    private boolean checkShapeDuplicate(String name) {
      if (!(shapesList.isEmpty())) {
        for (Shapes s : this.shapesList) {
          if (name.equals(s.getName())) {
            return false;
          }
        }
      }
      return true;
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

      this.source.forEach((k, v) -> {
        if (name.equals(k)) {
          createIndividualShapes(k, v);
        }
      });

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
     * Collect shapes names and types and add them to a HashMap.
     *
     * @param name of the shape
     * @param type of the shape
     */
    public void addShapeToModel(String name, String type) {
      this.source.put(name, type);
      ArrayList<ArrayList<Integer>> f = new ArrayList<ArrayList<Integer>>();
      this.data.put(name, f);
    }

    /**
     * Collects all shape and animated related data and adds it to a
     * master Hash Map.
     *
     * @param name of the shape
     * @param data a ArrayList of integers (i.e. shape and animations parameters)
     */
    public void addDatatoModel(String name, ArrayList<Integer> data) {
      this.data.get(name).add(data);
    }

    private void createIndividualShapes(String name, String type) {
      HashMap<String, ArrayList<ArrayList<Integer>>> f;

      // lamba expression to filter the data
      // hash map and show only objects matching the shape name
      f = (HashMap<String, ArrayList<ArrayList<Integer>>>) this.data.entrySet()
          .stream()
          .filter(map -> name.equals(map.getKey()))
          .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));

      // variable for hash map key selector
      ArrayList<ArrayList<Integer>> shape = f.get(name);

      // shape parameters
      float x;
      float y;
      float width;
      float height;
      Color color;
      int appear;
      int disappear;

      appear = shape.get(0).get(0);
      x = (float) shape.get(0).get(1);
      y = (float) shape.get(0).get(2);
      width = (float) shape.get(0).get(3);
      height = (float) shape.get(0).get(4);
      color = new Color(
              shape.get(0).get(5),
              shape.get(0).get(6),
              shape.get(0).get(7));
      int size = f.get(name).size();
      disappear = shape.get(size - 1).get(8);

      // check shape type
      if (type.equals("rectangle")) {
        addRectangle(name, x, y, width, height, color, appear, disappear);
      }
      else if (type.equals("ellipse")) {
        addOval(name, x, y, width, height, color, appear, disappear);
      }
    }

    /**
     * This is a private method used to generate and add shapes to our
     * model after the input file has been parsed.
     */
    private void generateShapes() {
      // create a Shape obj for each item in the hash map
      this.source.forEach((k, v) -> {
          createIndividualShapes(k, v);
      });
    }

    /**
     * This is a private method used to generate and add animations to our
     * model after the input file has been parsed.
     */
    private void generateAnimations() {
      // iterate through each item in the data hash map
      this.data.forEach((k, v) -> {
        // create animations for each item in the array of integers
        for (ArrayList<Integer> el : v) {
          int startT = el.get(0);
          int endT = el.get(8);
          int width = el.get(3);
          int height = el.get(4);
          Color color = new Color(el.get(5), el.get(6), el.get(7));
          Color newColor = new Color(el.get(13), el.get(14), el.get(15));

          if (el.get(1) != el.get(9) || el.get(2) != el.get(10)) {
            addMove(k, el.get(1), el.get(2), el.get(9), el.get(10), startT, endT);
          }
          else if (color != newColor) {
            addColorChange(k, color, newColor, startT, endT);
          }
          else if (width != el.get(11) || height != el.get(12)) {
            addSizeChange(k, width, height, el.get(11), el.get(12), startT, endT);
          }
        }
      });
    }

    /**
     * Return the built model.
     *
     * @return the build model
     */
    public AnimationModel build() {
      // add shapes
      generateShapes();

      // add animations
      generateAnimations();

      return new AnimationModelImpl(this);
    }
  }
}