package cs5004.easyanimator.view;

import java.awt.Dimension;

import javax.swing.*;

/**
 * This class represents a Popup Menu. It's triggered by right clicking
 * anywhere on the AnimateJPanel.
 */
public class PopupMenu extends JPopupMenu {
  JLabel position;

  /**
   * Class constructor.
   *
   * @param x coordinates
   * @param y coordinates
   */
  public PopupMenu(int x, int y) {
    Box box = Box.createVerticalBox();
    this.position = new JLabel(""
            + "<html><h4>Mouse Position:</h4>(x: "
            + x + ", y: " + y + ")</html>", SwingConstants.CENTER);
    box.setPreferredSize(new Dimension(200, 65));
    box.add(this.position);
    add(box);
  }
}
