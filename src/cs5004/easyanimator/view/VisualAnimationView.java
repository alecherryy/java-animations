package cs5004.easyanimator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;

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
  private ArrayList<Integer> settings;
  private ArrayList<Animations> animations;
  private float speed;

  /**
   * This is the class constructor. It takes two parameters: the speed
   * and a list of shapes.
   *
   * @param speed the speed
   * @param shapes the list of shapes
   */
  public VisualAnimationView(float speed, ArrayList<Integer> settings, ArrayList<Shapes> shapes,
                             ArrayList<Animations> animations) {

    this.shapes = shapes;
    this.settings = settings;
    this.speed = speed;
    this.animations = animations;
    this.setTitle("Simple Animation");
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
   * Initialize the view, add both shapes and animations and draws
   * the shapes onto the JPanel.
   */
  public void start() {
    boolean started = true;
    long startTime = System.currentTimeMillis();

    ArrayList<Animations> animations = this.getAnimations();
    ArrayList<Shapes> shapes = this.getShapes();

    ArrayList<Shapes> newShapesList = new ArrayList<Shapes>(shapes);

    // display the view
    this.display();

    while (started) {
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
          this.setShapes(newShapesList);
          this.refresh();
        }
      }
    }
  }
}
