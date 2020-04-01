package cs5004.easyanimator.util.inputfields;

import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewRadioField extends JRadioButton {
  private ActionListener listener;
  private String label;
  private String command;

  public ViewRadioField(ActionListener listener, String label, String command) {
    this.command = command;
    this.listener = listener;
    this.label = label;
    new JRadioButton(this.label);
  }

  public void setListener() {
    this.addActionListener(this.listener);
  }

  public void setAction() {
    this.setActionCommand(this.command);
  }
}
