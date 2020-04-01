package cs5004.easyanimator.view;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;


public class VisualAnimationView extends JFrame implements View {
  private AnimationPanel animatePanel;
  private ArrayList<Shape> shapes;

  public VisualAnimationView() {
    this.animatePanel = new AnimationPanel();
    this.shapes = new ArrayList<Shape>();
  }

  /**
   * TODO Return the description of the view? Model? I am not sure.
   *
   * @return description in a string
   */
  public String getDescription() {
    return null;
  }

  /**
   * Writes data to a given file.
   *
   * @param file the name of the file
   */
  public void writeOut(String file) {
    return;
  }

  /**
   * Retrieves and returns the Animation Model.
   *
   * @return the animation model
   */
  public AnimationPanel getModel() {
    return this.animatePanel;
  }

  /**
   * TODO returns the current tick? Not sure.
   *
   * @return the tick as a double
   */
  public double getTick() {
    return 0;
  }

  /**
   * Set an object's visibility to true.
   */
  public void makeVisible() {
    return;
  }

  /**
   * Resets the view to its default state.
   */
  public void resetView() {
    return;
  }

  /**
   * Takes a list of Shape objects and sets them within
   * the view.
   */
  public void setShapes(ArrayList<Shape> shapes) {
    return;
  }

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  public ArrayList<Shape> getShapes() {
    return null;
  }

  /**
   * Returns a list of Animations objects.
   *
   * @return a list of Animations objects
   */
  public ArrayList<Animations> getAnimations() {
    return null;
  }
}
