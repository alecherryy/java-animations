package cs5004.easyanimator.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents a view for a simple Visual Animation model. It
 * extends the JFrame abstract class and implements all methods listed
 * in the iView interface.
 */
public class VisualAnimationView extends JFrame implements View {
  private AnimateJPanel animationPanel;
  private ArrayList<Shapes> shapes;

  /**
   * This is the class constructor. It takes two parameters: the speed
   * and a list of shapes.
   *
   * @param speed the speed
   * @param shapes the list of shapes
   */
  public VisualAnimationView(float speed, ArrayList<Shapes> shapes) {
    super();

    this.shapes = shapes;
    this.setTitle("Simple Animation");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animationPanel = new AnimateJPanel();
    animationPanel.setPreferredSize(new Dimension(700, 700));

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
   *
   * @throws UnsupportedOperationException if the view does not support this method
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
   */
  public ArrayList<Animations> getAnimations() {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
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
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }
}
