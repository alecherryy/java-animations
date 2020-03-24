package cs5004.easyanimator.model;

import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.shapes.Shape;
import javafx.animation.Animation;

public class AnimationModelImpl implements AnimationModel {

  private List<Shape> shapes;
  private List<Animation> animations;

  public AnimationModelImpl() {
    shapes = new ArrayList<Shape>();
    animations = new ArrayList<Animation>();
  }

  @Override
  public void addShape(Shape s) {
    this.shapes.add(s);
  }

  @Override
  public void addAnimations(Animation a) {
    this.animations.add(a);
  }

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public List<Shape> getShapes() {
    return shapes;
  }

  @Override
  public List<Animation> getAnimations() {
    return animations;
  }

  @Override
  public int getLastEndTime() {
    return 0;
  }
}
