package cs5004.EasyAnimator.controller;
import java.util.List;

import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.shapes.Shapes;
import cs5004.EasyAnimator.view.View;
import cs5004.EasyAnimator.model.AnimationModel;

import javax.swing.*;

public class TextualViewController implements AnimationController {
  private AnimationModel model;
  private View view;
  private String filename;

  /**
   * Constructs a TextualViewController object with its given model view, and filename.
   *
   * @param model    the model that the controller will be using
   * @param view     the view that the controller will be using to display
   * @param filename the output file that the controller will write to
   */
  public TextualViewController(AnimationModel model, View view, String filename) {
    this.model = model;
    this.view = view;
    this.filename = filename;
  }
  @Override
  public void start() {
    List<Animations> animations = model.getAnimations();
    List<Shapes> shapes = model.getShapes();

    for (int i = 0; i < animations.size(); i++) {
      Animations a = animations.get(i);
      Shapes shape = a.getShape();
      String name = shape.getName();

      for (int j = 0; j < shapes.size(); j++) {
        Shapes current = shapes.get(j);
        if (name.equals(current.getName())) {
          a.updateField(current);
        }
      }
    }
    this.view.write(filename);
  }

  @Override
  public Appendable getLog() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Controller does not support this functionality");  }

  @Override
  public Timer getTimer() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("Controller does not support this functionality");  }
}
