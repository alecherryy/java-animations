package cs5004.easyanimator.util;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse extends MouseAdapter implements MouseListener {
  protected Graphics g;
  // TODO clean this up
  @Override
  public void mouseClicked(MouseEvent e) {
    int x = e.getX();
    int y = e.getY();
  }

  public void paint(Graphics g, int x, int y) {
    g.setColor(Color.BLUE);
    g.drawOval(x, y, 50, 50);
    g.fillOval(x, y, 50, 50);
  }

}
