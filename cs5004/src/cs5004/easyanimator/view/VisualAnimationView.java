package cs5004.easyanimator.view;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;


public class VisualAnimationView extends JFrame implements View {
  private AnimationPanel animatePanel;
  private List<Shape> shapes;

  @Override
  public String getDescription() {
    return null;
  }

  @Override
  public void writeOut(String fileName) {

  }

  @Override
  public AnimationModel getModel() {
    return null;
  }

  @Override
  public double getTick() {
    return 0;
  }

  @Override
  public void makeVisible() {

  }

  @Override
  public void refresh() {

  }

  @Override
  public void setShapes(List<Shape> shapes) {

  }

  @Override
  public List<Shape> getShapes() {
    return null;
  }

  @Override
  public List<Animations> getAnimations() {
    return null;
  }
}
