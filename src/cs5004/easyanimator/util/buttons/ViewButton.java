package cs5004.easyanimator.util.buttons;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ViewButton extends JButton {
  private String label;

  public ViewButton(String label) {
    this.label = label;
    new JButton("Hello");
  }

  public void setListener(ActionListener listener) {
    this.addActionListener(listener);
  }
}
