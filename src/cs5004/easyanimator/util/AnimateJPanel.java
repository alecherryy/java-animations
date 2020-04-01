package cs5004.easyanimator.util;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.ModelItem;
import cs5004.easyanimator.model.shapes.Shape;

public class AnimateJPanel extends JPanel implements ActionListener {
  private AnimationModel model;
  Timer t = new Timer(5, this);
  private int start;
  private int vel = 2;

  public AnimateJPanel(AnimationModel model) {
    this.model = model;
    setPreferredSize(new Dimension(400, 400));
  }

  /**
   * Takes a Graphics object as a parameter and feeds each shape's data from the
   * model into the Graphics. It then draws each shape into the view.
   *
   * @param g a Graphics object
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    for (ModelItem obj : this.model.getModel()) {
      // create shape variable for each obj
      Shape s = obj.getShape();

      start = s.getPosition().getX();
      // check shape and create graphics accordingly
      switch(s.getType()) {

        case RECTANGLE:
          g.setColor(s.getColor());
          g.fillRect(start, s.getPosition().getY(), s.getWidth(), s.getHeight());
          break;
        case OVAL:
          g.setColor(s.getColor());
          g.fillOval(start, s.getPosition().getY(), s.getWidth(), s.getHeight());
          break;
        default:
          break;
      }
      t.start();
    }
  }

  public void actionPerformed(ActionEvent e) {
    for (ModelItem obj : this.model.getModel()) {
      start = start + vel;
      System.out.println(start);
      repaint();
    }

  }
}