package cs5004.EasyAnimator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.shapes.Coordinates;
import cs5004.EasyAnimator.model.shapes.Shapes;

/**
 * This class represents a JPanel used to implement the VisualAnimation View. It
 * extends the Java JPanel built-in class.
 */
public class AnimateJPanel extends JPanel {
  private final ArrayList<Animations> animations;
  private ArrayList<Shapes> shapes;

  /**
   * This is the class constructor.
   */
  public AnimateJPanel() {
    super();
    this.shapes = new ArrayList<Shapes>();
    this.animations = new ArrayList<Animations>();
    this.setBackground(Color.WHITE);

    // add scroll bars
    JScrollPane scrollPane = new JScrollPane();

    // set vertical and horizontal scrolling as needed
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

    // add scroll pane
    this.add(scrollPane);
  }

  /**
   * Overrides the paintComponent methods from the JPanel
   * abstract class and iterates through the shape to draw
   * new objects onto the canvas.
   *
   * @param g a Graphics object
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2d = (Graphics2D) g;

    g2d.setColor(Color.BLACK);

    AffineTransform originalTransform = g2d.getTransform();

    // iterate through shapes and paint on JPanel
    for (Shapes s : this.shapes) {
      // shape's coordinates
      Coordinates pos = s.getPosition();
      int x = (int) pos.getX();
      int y = (int) pos.getY();

      // shape's width and height
      int w = (int) s.getD1();
      int h = (int) s.getD2();

      Color c = s.getColor();

      // check shape and create graphics accordingly
      switch(s.getType()) {
        case RECTANGLE:
          g2d.setColor(c);
          g2d.fillRect(x, y, w, h);
          g2d.drawRect(x, y, w, h);
          break;
        case OVAL:
          g2d.setColor(c);
          g2d.fillOval(x, y, w, h);
          g2d.drawOval(x, y, w, h);
          break;
        default:
          break;
      }
    }
//    this.
    g2d.setTransform(originalTransform);
  }

  /**
   * Set a list shapes inside the JPanel.
   *
   * @param shapes the list of the shapes
   */
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;
  }
}