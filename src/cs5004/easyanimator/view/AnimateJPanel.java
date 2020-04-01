package cs5004.easyanimator.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

import javax.swing.JPanel;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.ModelItem;
import cs5004.easyanimator.model.shapes.Shape;

public class AnimateJPanel extends JPanel {
  private AnimationModel model;

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

    g.setColor(Color.BLUE);

    int i = 0;
    for (ModelItem obj : this.model.getModel()) {
      // create shape variable for each obj
      Shape s = obj.getShape();

      int R = (int) (Math.random() * 256);
      int G = (int) (Math.random() * 256);
      int B = (int) (Math.random() * 256);
      Color c = new Color(R, G, B);

      g.setColor(c);
      // check shape and create graphics accordingly
      switch(s.getType()) {
        case RECTANGLE:
          g.fillRect(10 + i, 10 + i, 50 + i, 50 + i);
          break;
        case OVAL:
          g.fillOval(10 + i, 10 + i, 50 + i, 50 + i);
          break;
        default:
          break;
      }
      i = 10;
    }
  }
}