package cs5004.easyanimator.view;

import java.awt.*;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.ModelItem;

public class AnimatePanel extends JPanel {
  private AnimationModel model;

  public AnimatePanel(AnimationModel model) {
    this.model = model;
    setPreferredSize(new Dimension(400, 400));
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.BLUE);
    g.fillRect(0, 0, getWidth(), getHeight());

    for (ModelItem obj : this.model.getModel()) {
      g.fillRect(0, 0, 34, 34);
    }
  }

//  public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//
//    Rectangle test =  new Rectangle("R", 100, 50, Color.BLUE,
//            new Coordinates(25, 50));
//    int angle = 45;
//    Graphics2D g2d = (Graphics2D)g;
//    g2d.setColor(Color.RED);
//    int sizeX = (int) Math.round(test.getWidth()), sizeY = (int) Math.round(test.getHeight());
//    g2d.rotate(Math.toRadians(45), sizeX /2, sizeY/2);
//    g2d.fillRect(sizeX/4, sizeY/4, sizeX/2, sizeY/2);
//  }
}