package cs5004.easyanimator.model;

import java.awt.*;
import java.util.ArrayList;

import cs5004.easyanimator.model.animations.AnimationType;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.animations.ChangeCoordinates;
import cs5004.easyanimator.model.animations.ChangeSize;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.shapes.ShapesVisitorImpl;
import cs5004.easyanimator.util.TweenModelBuilder;


/**
 * This is the AnimationModel class; it implements all methods available on the AnimationModel
 * Interface.
 */
public class AnimationModelImpl implements AnimationModel {
  private ArrayList<Shapes> shapes;
  private ArrayList<Animations> animations;

  /**
   * This is the class constructor. It initializes a new empty list of objects (i.e.,
   * AnimationModelItem).
   */
  public AnimationModelImpl() {

    this.shapes = new ArrayList<Shapes>();
    this.animations = new ArrayList<Animations>();
  }

  /**
   * Private constructor, later used in our static class where we implement our model builder.
   * This will allow us to return an AnimationModelImpl object with the appropriate changes
   * made to it.
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

    if (helperAddShape(name)) {
      this.shapes.add(s);
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
    for (Shapes s : this.shapes) {
      if (s.getName().equalsIgnoreCase(name)) {
        return false;
      }
    }

    return true;
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
   * Returns a description of all shapes and animations in the following format:
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
      this.animations.sort((Animations a, Animations b) ->
          Integer.compare(a.getStartTime(), b.getStartTime()));

      for (Animations a : this.animations) {
        str.append(a.getDescription());
        str.append("\n");
      }
    }

    return str.toString();
  }

  /**
   * This is a static class that implements our Tween Model Builder, and will add shapes and
   * animations to our model.
   */

  public static final class AnimationModelBuilder
      implements TweenModelBuilder<AnimationModel> {

    private ArrayList<Animations> animationsList;
    private ArrayList<Shapes> shapesList;


    /**
     * Constructs a {@code SimpleAnimationBuilder} object.
     **/
    public AnimationModelBuilder() {
      this.animationsList = new ArrayList<Animations>();
      this.shapesList = new ArrayList<Shapes>();
    }

    @Override
    public TweenModelBuilder<AnimationModel> addOval(
        String name,
        float x, float y,
        float xRadius, float yRadius,
        float red, float green, float blue,
        int start, int end) {
      Coordinates pos = new Coordinates(x, y);
      Color c = new Color(red, green, blue);
      Shapes shape = new Oval(name, start, end, xRadius, yRadius, c, pos);
      shapesList.add(shape);
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addRectangle(
        String name,
        float x, float y,
        float width, float height,
        float red, float green, float blue,
        int start, int end) {
      Coordinates pos = new Coordinates(x, y);
      Color c = new Color(red, green, blue);
      Shapes shape = new Rectangle(name, start, end, width, height, c, pos);
      shapesList.add(shape);
      return this;
    }

    /**
     * Add given animation in order in the list of animations if animation is valid.
     *
     * @param a animation to add to list of animations
     */
    private void addAnimations(Animations a) {
      AnimationType addType = a.getAnimationType();
      Shapes addShape = a.getShape();
      int addStart = a.getStartTime();

      for (int i = 0; i < animationsList.size(); i++) {
        Animations current = animationsList.get(i);
        AnimationType type = current.getAnimationType();
        Shapes shape = current.getShape();
        int start = current.getStartTime();
        int end = current.getEndTime();

        if (addType == type) {
          if (addShape.getName().equals(shape.getName())) {
            if ((addStart >= start) && (addStart <= end)) {
              throw new IllegalArgumentException("Incompatible move for the same shape "
                  + "during overlapping time intervals");
            }
          }
        }
      }
      animationsList.add(a);
    }

    @Override
    public TweenModelBuilder<AnimationModel> addMove(
        String name,
        float moveFromX, float moveFromY, float moveToX, float moveToY,
        int start, int end) {
      Coordinates originalPos = new Coordinates(moveFromX, moveFromY);
      Coordinates newPos = new Coordinates(moveToX, moveToY);
      Shapes s = null;
      for (int i = 0; i < shapesList.size(); i++) {
        Shapes current = shapesList.get(i);
        if (current.getName().equals(name)) {
          s = current.visitShape(new ShapesVisitorImpl());
          s.changePosition(originalPos);
        }
      }

      try {
        Animations animation = new ChangeCoordinates(s, start, end, originalPos, newPos);
        this.addAnimations(animation);
      } catch (Exception e) {
        // do nothing
      }
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addColorChange(
        String name,
        float oldR, float oldG, float oldB, float newR, float newG, float newB,
        int start, int end) {
      Color oldColor = new Color(oldR, oldG, oldB);
      Color newColor = new Color(newR, newG, newB);

      Shapes s = null;
      for (int i = 0; i < shapesList.size(); i++) {
        Shapes current = shapesList.get(i);
        if (current.getName().equals(name)) {
          s = current.visitShape(new ShapesVisitorImpl());
          s.changeColor(oldColor);
        }
      }
      try {
        Animations animation = new ChangeColor(s, start, end, oldColor, newColor);
        this.addAnimations(animation);
      } catch (Exception e) {
        // do nothing
      }
      return this;
    }

    @Override
    public TweenModelBuilder<AnimationModel> addSizeChange(String name, float orgWidth, float
        orgHeight, float newWidth, float newHeight, int start, int end) {

      Shapes s = null;
      for (int i = 0; i < shapesList.size(); i++) {
        Shapes current = shapesList.get(i);
        if (current.getName().equals(name)) {
          s = current.visitShape(new ShapesVisitorImpl());
          s.changeWidth(orgWidth);
          s.changeHeight(orgHeight);
        }
      }
      try {
        Animations animation = new ChangeSize(s, start, end, orgWidth,
            orgHeight, newWidth, newHeight);
        this.addAnimations(animation);
      } catch (Exception e) {
        // do nothing
      }
      return this;
    }

    @Override
    public AnimationModel build() {
      return new AnimationModelImpl(this);
    }
  }
}
