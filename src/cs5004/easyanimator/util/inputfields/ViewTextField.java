package cs5004.easyanimator.util.inputfields;

import java.awt.event.ActionListener;

import javax.swing.*;

public class ViewTextField extends JTextField {
  private ActionListener listener;
  private String command;

  public ViewTextField(ActionListener listener, String label, String command) {
    this.command = command;
    this.listener = listener;
    new JLabel(label);
    new JTextField(10);
    this.addActionListener(this.listener);
    this.setActionCommand(this.command);
  }

  public String getInputText() {
    return this.getText();
  }

  public void setAction() {
    this.setActionCommand(this.command);
  }
}
