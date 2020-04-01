package cs5004.easyanimator.view;

import javax.swing.*;

public class AnimationPanel extends JPanel {
  private JPopupMenu popup;

  public AnimationPanel() {

    this.popup = new JPopupMenu();
    this.add(new JLabel("Create a new animation"));
  }

  protected void makePopupMenu() {
    JMenuItem menuItem;
    // clear all
    menuItem = new JMenuItem("Clear All");
    popup.add(menuItem);
  }

}
