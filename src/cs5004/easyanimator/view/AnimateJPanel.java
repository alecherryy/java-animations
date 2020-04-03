package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Shapes;

public class AnimateJPanel extends JPanel {
  private ArrayList<Shapes> shapes;

  public AnimateJPanel() {
    super();
    this.shapes = new ArrayList<Shapes>();
    this.setBackground(Color.WHITE);
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