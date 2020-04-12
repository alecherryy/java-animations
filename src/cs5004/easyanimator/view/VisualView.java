package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents a view for a simple Visual Animation model. It
 * extends the JFrame abstract class and implements all methods listed
 * in the iView interface.
 */
public class VisualView extends JFrame implements View {
  private AnimateJPanel animationPanel;
  private ArrayList<Shapes> shapes;
  private ArrayList<Integer> settings;
  private ArrayList<Animations> animations;
  private float speed;

  /**
   * This is the class constructor. It takes two parameters: the speed
   * and a list of shapes.
   *
   * @param speed the speed
   * @param settings the panel settings
   * @param shapes the list of shapes
   * @param animations the list of animations
   */
  public VisualView(float speed, ArrayList<Integer> settings, ArrayList<Shapes> shapes,
                    ArrayList<Animations> animations) {
    this.shapes = shapes;
    this.settings = settings;
    this.speed = speed;
    this.animations = animations;
    this.setTitle("Java Easy Animator");
    this.setSize(this.settings.get(0), this.settings.get(1));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animationPanel = new AnimateJPanel();
    animationPanel.setPreferredSize(new Dimension(this.settings.get(2), this.settings.get(3)));

    animationPanel.setShapes(shapes);

    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  /**
   * Sets the view's visibility to true (i.e. view is visible within the JFrame).
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void display() {
    setVisible(true);
  }

  /**
   * Takes a list of Shape objects and sets them within the view.
   *
   * @param shapes a list of shapes
   */
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;
    animationPanel.setShapes(shapes);
  }

  /**
   * Repaints the view.
   */
  public void refresh() {
    repaint();
  }

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  public ArrayList<Shapes> getShapes() {
    return this.shapes;
  }

  /**
   * Returns a list of Animations objects.
   *
   * @return a list of Animation objects
   * @throws UnsupportedOperationException if the view does not support this operation.
   */
  public ArrayList<Animations> getAnimations() {
    return animations;
  }

  /**
   * Returns a list of integers that represent the settings.
   *
   * @return a list representing the settings.
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public ArrayList<Integer> getSettings() throws UnsupportedOperationException {
    return this.settings;
  }

  /**
   * Returns the description of the view in a string.
   *
   * @return the view description in a string
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public String getTextDescription() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
  }

  /**
   * Writes out the text description of the animation to a file specified in the parameters.
   *
   * @param fileName the file to which we are outputting the string representation of the
   *                 animation.
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void write(String fileName) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
  }

  /**
   * Get the speed at which the animation occurs.
   *
   * @return the speed of the animation
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public float getSpeed() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
  }

  /**
   * Display the error message on the screen.
   *
   * @param error the error message as a string.
   * @throws UnsupportedOperationException if the view does not support this method
   **/
  public void displayErrorMsg(String error) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
  }

  /**
   * Sets the boolean isLoop in the view.
   * @param loop boolean to set isLoop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public void setIsLoop(boolean loop) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Returns the is loop boolean in the view.
   * @return boolean for the isLoop field
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public boolean getIsLoop() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Give the view an actionListener for the buttons in the view.
   *
   * @param e The action event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public void setButtonListener(ActionListener e) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public String getTextFieldValue() {
    return null;
  }

  /**
   * Returns the checkbox list from this view.
   *
   * @return the view's checkbox list, a list of JCheckBox objects
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public List<JCheckBox> getCheckBoxList() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Parse a textfield content and returns a new shape.
   *
   * @param type of shape
   * @return a new shape
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public Shapes getNewShape(String type) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Parse a textfield content and returns a new animation.
   *
   * @return a new animation
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public Animations getNewAnimation() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }
}
