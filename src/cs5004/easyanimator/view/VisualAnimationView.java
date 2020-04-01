package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;


public class VisualAnimationView extends JFrame implements View {
  private AnimationPanel panel;
//  private ActionListener listener;
//  private ArrayList<Shape> shapes;

  public VisualAnimationView(String title) {
    super(title);
    this.panel = new AnimationPanel();
//    this.listener = null;

    // JFrame SETTINGS
    // set preferred size of the frame
    // TODO set setMaximumSize(), setMinimumSize() or setResizable()
    setSize(800, 800);
    // place frame in the top left corner
    setLocation(0, 0);
    // exit the program when hit "close"
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

//    this.label = new JLabel("Java Easy Animator - "
//            + "Welcome to our Easy Animator Application. "
//            + "To get started, add shape to the model.");
//    this.add(this.label);
    this.add(panel, BorderLayout.CENTER);
  }

  /**
   * Set view listener to be given parameter.
   *
   * @param listener of the view
   */
  public void setListener(ActionListener listener) {
    this.listener = listener;
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
  public AnimationPanel getAnimationPanel() {
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
