package cs5004.EasyAnimator.controller;

import java.util.ArrayList;
import javax.swing.*;

import cs5004.EasyAnimator.model.AnimationModel;
import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.shapes.Shapes;
import cs5004.EasyAnimator.view.View;

public class VisualViewController implements AnimationController{
  private AnimationModel model;
  private View view;
  private double speed;
  private boolean started;

  /**
   * Constructs a VisualController object.
   *
   * @param speed the speed at which the animation will occur
   * @param model the model that the controller will be using
   * @param view  the view that the controller will be using to display
   */
  public VisualViewController(AnimationModel model, View view, double speed) {
    this.model = model;
    this.view = view;
    this.speed = speed;
    this.started = false;
  }

  @Override
  public void start() {
    started = true;
    long startTime = System.currentTimeMillis();

    ArrayList<Animations> animations = model.getAnimations();
    ArrayList<Shapes> shapes = model.getShapes();

    ArrayList<Shapes> newShapesList = new ArrayList<Shapes>();
    while (this.started) {
      long timeElapsed = System.currentTimeMillis() - startTime;
      double secondsElapsed = timeElapsed / 1000.0;
      double unitsElapsed = secondsElapsed * speed;

      for (int i = 0; i < animations.size(); i++) {
        Animations currentAnimation = animations.get(i);
        Shapes animationShape = currentAnimation.getShape();
        for (int j = 0; j < newShapesList.size(); j++) {
          Shapes currentShape = newShapesList.get(j);
          if (currentShape.getName().equals(animationShape.getName())) {
            currentAnimation.resetShape(currentShape);
          }
        }
      }

      for (int i = 0; i < animations.size(); i++) {
        Animations current = animations.get(i);
        int start = current.getStartTime();
        int end = current.getEndTime();

        if (start <= unitsElapsed && end >= unitsElapsed) {
          current.implementAnimation(unitsElapsed);
          this.view.setShapes(newShapesList);
          this.view.refresh();
        }
      }
      this.view.display();
    }
  }

  @Override
  public Appendable getLog() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }

  @Override
  public Timer getTimer() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Controller does not support this functionality");
  }
}
