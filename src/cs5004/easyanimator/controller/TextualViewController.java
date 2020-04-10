package cs5004.easyanimator.controller;

import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.view.View;

/**
 * Represents the controller for the Textual View. Implements AnimationController and its
 * associated methods.
 */

public class TextualViewController implements AnimationController {
  private AnimationModel model;
  private View view;
  private String filename;

  /**
   * Constructs a TextualViewController object with its given model, view and filename.
   *
   * @param model    the animation model that the controller will use
   * @param view     the view that the controller will be displaying on
   * @param filename the output file the controller will write to
   */
  public TextualViewController(AnimationModel model, View view, String filename) {
    this.model = model;
    this.view = view;
    this.filename = filename;
  }


  /**
   * Starts the animation.
   */
  public void start() {
    ArrayList<Animations> animations = model.getAnimations();
    ArrayList<Shapes> shapes = model.getShapes();

    for (int i = 0; i < animations.size(); i++) {
      Animations currAnimation = animations.get(i);
      Shapes currAnimationShape = currAnimation.getShape();
      String currAnimationShapeName = currAnimationShape.getName();

      for (int j = 0; j < shapes.size(); j++) {
        Shapes shape = shapes.get(j);
        if (currAnimationShapeName.equals(shape.getName())) {
          currAnimation.updateField(shape);
        }
      }
    }
    this.view.write(filename);
  }

  @Override
  public Appendable getLog() {
    return null;
  }

  @Override
  public Timer getTimer() {
    return null;
  }
}
