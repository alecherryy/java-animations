package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents a view for a simple Visual Animation model. It
 * extends the JFrame abstract class and implements all methods listed
 * in the iView interface.
 */
public class VisualAnimationView extends JFrame implements View {
  private AnimateJPanel animationPanel;
  private ArrayList<Shapes> shapes;

  public VisualAnimationView(float speed, ArrayList<Shapes> shapes) {
    super();

    this.shapes = shapes;
    this.setTitle("Simple Animation");
    this.setSize(700, 700);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animationPanel = new AnimateJPanel();
    animationPanel.setPreferredSize(new Dimension(700, 700));

    animationPanel.setShapes(shapes);

    JScrollPane scrollPane = new JScrollPane(animationPanel);
    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scrollPane.setBounds(50, 30, 300, 50);

    this.add(scrollPane, BorderLayout.CENTER);

    this.pack();
  }

  @Override
  public void display() {
    setVisible(true);
  }

 @Override
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;
    animationPanel.setShapes(shapes);
  }

  @Override
  public void refresh() {
    repaint();
  }

  @Override
  public ArrayList<Shapes> getShapes() {
    return this.shapes;
  }

  @Override
  public ArrayList<Animations> getAnimations() {
    throw new UnsupportedOperationException("Visual Animation View view does not include this " +
        "functionality.");
  }

  @Override
  public String getTextDescription() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Visual Animation View view does not include this " +
        "functionality.");
  }

  @Override
  public void write(String fileName) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Visual Animation View view does not include this " +
        "functionality.");
  }

  @Override
  public float getSpeed() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Visual Animation View view does not include this " +
        "functionality.");
  }

  @Override
  public void displayErrorMsg(String error) throws UnsupportedOperationException {
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  @Override
  public void displayButton(ActionListener event) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Visual Animation View view does not include this " +
        "functionality.");
  }
}
