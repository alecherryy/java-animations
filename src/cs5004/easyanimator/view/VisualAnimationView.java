package cs5004.easyanimator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;


public class VisualAnimationView extends JFrame implements iView {
  private AnimationModel model;
  private AnimateJPanel panel;

  public VisualAnimationView(String title) {
    super(title);

    this.model = new AnimationModelImpl();

    // set preferred size of the frame
    // TODO set setMaximumSize(), setMinimumSize() or setResizable()
    setSize(800, 800);
    setResizable(false);

    // place frame in the top left corner
    setLocation(0, 0);
    // exit the program when hit "close"
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create panel
    this.panel = new AnimateJPanel(this.model);

    // add animation panel to the view
    add(this.panel);
  }

  /**
   * Animate the panel.
   */
  public void animate() {
    this.refreshView();
  }

  /**
   * Set view listener to be given parameter.
   *
   * @param listener of the view
   */
  public void setListener(ActionListener listener) {
    return;
  }

  /**
   * Set view's visibility to true (i.e. show the JFrame)
   */
  public void display() {
    setVisible(true);
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
  public AnimateJPanel getAnimationPanel() {
    return this.panel;
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
   * Erases all Animations and Shapes from the view.
   */
  public void refreshView() {
    // remove everything from the panel
    this.panel.removeAll();
    revalidate();
    repaint();
  }

  /**
   * Resets the view to its default state.
   */
  public void resetView() {
    // start over
    this.repaint();
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
