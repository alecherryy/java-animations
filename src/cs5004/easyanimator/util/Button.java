package cs5004.easyanimator.util;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class Button extends JButton {

  public Button(String text) {
    super(text);

    this.setForeground(Color.BLACK);
    this.setBackground(new Color(113,199,236));
    Border line = new LineBorder(new Color(56,90,124), 2);
    Border margin = new EmptyBorder(10, 30, 10, 30);
    this.setOpaque(true);
    Border compound = new CompoundBorder(line, margin);
    this.setBorder(compound);
  }
}
