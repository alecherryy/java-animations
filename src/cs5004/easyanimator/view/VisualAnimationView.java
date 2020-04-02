package cs5004.easyanimator.view;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents a view for a simple Visual Animation model. It
 * extends the JFrame abstract class and implements all methods listed
 * in the iView interface.
 */
public class VisualAnimationView extends JFrame implements View {
  private AnimateJPanel animationPanel;
  private ArrayList<Shapes> shapes;

  public VisualAnimationView(int speed, ArrayList<Shapes> shapes) {
    super("Java Easy Animator");

    this.shapes = shapes;

    // set preferred size of the frame
    // TODO set setMaximumSize(), setMinimumSize() or setResizable()
    setSize(800, 800);
    setResizable(false);
    setLocation(0, 0);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // create panel
    this.animationPanel = new AnimateJPanel();
    this.animationPanel.setPreferredSize(new Dimension(800, 800));

    // TODO add scrollPanel
    pack();
  }

  /**
   * Set an object's visibility to true.
   */
  public void display() {
    setVisible(true);
  }

  /**
   * Takes a list of Shape objects and sets them
   * within the view.
   *
   * @param shapes a list of shapes
   */
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;
    animationPanel.setShapes(shapes);
  }

  /**
   * Resets the view.
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
}
