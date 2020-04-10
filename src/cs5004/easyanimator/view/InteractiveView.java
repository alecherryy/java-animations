package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * A class that represents a view for an interactive view.
 */
public class InteractiveView extends JFrame implements View {

  private float speed;
  private AnimateJPanel animatePanel;
  private ArrayList<Shapes> shapes;
  private ArrayList<Animations> animations;
  private boolean isLoop;
  private int endTime;
  private JButton play, pause, restart, increaseSpeed, decreaseSpeed,
      file, export;
  private JCheckBox loopCheckbox;
  private JPanel buttonPanel;
  private JTextField fileInput;
  private JLabel checkboxPanelLabel;
  private JPanel checkboxPanel;
  private List<JCheckBox> checkBoxList;

  /**
   * Constructs an InteractiveView object, with its given speed, list of shapes, list of animations,
   * and endTime.
   *
   * @param speed  represents the speed at which the animation occurs
   * @param shapes list of shapes that view will use
   */
  public InteractiveView(float speed, ArrayList<Shapes> shapes, ArrayList<Animations> animations,
                         int endTime) {
    super();

    this.isLoop = false;
    this.endTime = endTime;

    this.speed = speed;
    this.checkBoxList = new ArrayList<JCheckBox>();

    this.shapes = shapes;
    this.animations = animations;

    this.setTitle("Simple Animation");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animatePanel = new AnimateJPanel();
    animatePanel.setPreferredSize(new Dimension(1000, 1000));

    animatePanel.setShapes(shapes);

    JScrollPane animationScrollPane = new JScrollPane(animatePanel);
    animationScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    animationScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    animationScrollPane.setBounds(50, 30, 300, 500);
    animationScrollPane.setPreferredSize(new Dimension(1000, 1000));


    this.add(animationScrollPane);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());
    this.add(buttonPanel, BorderLayout.SOUTH);

    play = new JButton("Play");
    buttonPanel.add(play);

    pause = new JButton("Pause");
    buttonPanel.add(pause);
    restart = new JButton("Restart");
    buttonPanel.add(restart);

    loopCheckbox = new JCheckBox("Loop");
    buttonPanel.add(loopCheckbox);

    increaseSpeed = new JButton("Increase Speed");
    buttonPanel.add(increaseSpeed);

    decreaseSpeed = new JButton("Decrease Speed");
    buttonPanel.add(decreaseSpeed);

    fileInput = new JTextField(10);
    buttonPanel.add(fileInput);
    file = new JButton("Set file");
    buttonPanel.add(file);

    export = new JButton("Export");
    buttonPanel.add(export);

    // for the checkBox functionality
    checkboxPanel = new JPanel();
    checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
    checkboxPanelLabel = new JLabel(" Select Shapes When Paused ");
    checkboxPanel.add(checkboxPanelLabel);

    // check boxes
    for (int i = 0; i < shapes.size(); i++) {
      Shapes current = shapes.get(i);
      JCheckBox currentBox = new JCheckBox(current.getName());

      currentBox.setSelected(true);

      this.checkBoxList.add(currentBox);
      checkboxPanel.add(currentBox);
    }

    JScrollPane checkboxScrollPane = new JScrollPane(checkboxPanel);
    checkboxScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    checkboxScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    checkboxScrollPane.setBounds(50, 30, 50, 500);
    checkboxScrollPane.setPreferredSize(new Dimension(200, 1000));

    this.add(checkboxScrollPane, BorderLayout.EAST);

    this.pack();
  }

  @Override
  public String getTextDescription() {

    String state = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n";

    double endTime = (this.endTime / speed) * 1000;
    state += "<rect>\n"
        + "<animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime + "ms\" "
        + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
        + "</rect>\n";

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      if (currentShape.getDisplayValue()) {
        state += currentShape.toSVGTag();
        for (int j = 0; j < animations.size(); j++) {
          Animations currentA = animations.get(j);
          Shapes currentS = currentA.getShape();
          if (currentShape.getName().equals(currentS.getName()) && !isLoop) {
            state += currentA.toSVGTag(this.getSpeed());
          } else if (currentShape.getName().equals(currentS.getName()) && isLoop) {
            state += currentA.toSVGTagWithLoop(this.getSpeed());
          }
        }
        state += currentShape.svgEndTag() + "\n";
      }
    }
    state += "</svg>";

    return state;
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void setButtonListener(ActionListener actionEvent) {
    play.addActionListener(actionEvent);
    pause.addActionListener(actionEvent);
    restart.addActionListener(actionEvent);
    increaseSpeed.addActionListener(actionEvent);
    decreaseSpeed.addActionListener(actionEvent);
    file.addActionListener(actionEvent);
    loopCheckbox.addActionListener(actionEvent);
    export.addActionListener(actionEvent);
  }

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   */
  public String getFilename() {
    return fileInput.getText();
  }

  /**
   * Display the error message on the screen.
   *
   * @param error the error message as a string.
   **/
  public void displayErrorMsg(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
        JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Returns a list of integers that represent the settings.
   *
   * @return a list representing the settings.
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public ArrayList<Integer> getSettings() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this functionality.");
  }

  /**
   * Repaints the view.
   */
  public void refresh() {
    this.repaint();
  }

  /**
   * Takes a list of Shape objects and sets them within the view.
   *
   * @param shapes a list of shapes
   */
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;
    animatePanel.setShapes(shapes);
  }


  /**
   * Writes out the text description of the animation to a file specified in the parameters.
   *
   * @param fileName the file to which we are outputting the string representation of the
   *                 animation.
   */
  public void write(String fileName) {
    String description = this.getTextDescription();
    try {
      BufferedWriter output;
      if (fileName.equals("System.out")) {
        output = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File file = new File(fileName);
        output = new BufferedWriter(new FileWriter(file));
      }
      output.write(description);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Get the speed at which the animation occurs.
   *
   * @return the speed of the animation
   */
  public float getSpeed() {
    return this.speed;
  }

  /**
   * Returns the checkbox list from this view.
   *
   * @return the view's checkbox list, a list of JCheckBox objects
   */
  public List<JCheckBox> getCheckBoxList() {
    return this.checkBoxList;
  }

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  public ArrayList<Shapes> getShapes() {
    return this.shapes;
  }

  /**
   * Returns a list of Animations objects.
   *
   * @return a list of Animation objects
   */
  public ArrayList<Animations> getAnimations() {
    return this.animations;
  }

  /**
   * Sets the boolean isLoop in the view.
   *
   * @param loop boolean to set isLoop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public void setIsLoop(boolean loop) {
    this.isLoop = loop;
  }

  /**
   * Returns the is loop boolean in the view.
   *
   * @return boolean for the isLoop field
   * @throws UnsupportedOperationException if the view does not support this  functionality
   */
  public boolean getIsLoop() {
    return this.isLoop;
  }


}