package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.*;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.animations.ChangeCoordinates;
import cs5004.easyanimator.model.animations.ChangeSize;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * A class that represents a view for an interactive view.
 */
public class InteractiveView extends JFrame implements View {
  private float speed;
  private AnimateJPanel animationPanel;
  private ArrayList<Shapes> shapes;
  private ArrayList<Animations> animations;
  private boolean loop;
  private int endTime;
  private JButton play;
  private JButton pause;
  private JButton restart;
  private JButton increaseSpeed;
  private  JButton decreaseSpeed;

  // extra functionality
  // add a new shape
  private JButton btnAddRect;
  private JButton btnAddOval;
  private JTextField sAdd;
  // add a new animation
  private JTextField aStart;
  private JTextField aEnd;
  private JButton btnAddAnimation;
  // remove a shape
  private JButton btnRemove;
  private JTextField sRemove;
  // save
  private JButton btnSave;
  private JCheckBox loopCheck;
  private ArrayList<JRadioButton> format;

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

    this.loop = false;
    this.endTime = endTime;

    this.speed = speed;

    this.shapes = shapes;
    this.animations = animations;

    this.setTitle("Java Easy Animator");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.animationPanel = new AnimateJPanel();
    this.animationPanel.setPreferredSize(new Dimension(1000, 1000));

    this.animationPanel.setShapes(shapes);

    // add scroll bars
    this.add(setScrollBars(this.animationPanel));

    // add edit panel
    this.add(setControlPanel(), BorderLayout.SOUTH);

    // add edit panel
    this.add(setEditPanel(), BorderLayout.EAST);

    // Then on your component(s)

    this.pack();
  }

  /**
   * Private method to set up scroll bars.
   *
   * @param panel to add scroll bars to
   * @return the scroll panel
   */
  private JScrollPane setScrollBars(JPanel panel) {
    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroll.setBounds(50, 30, 300, 500);
    scroll.setPreferredSize(new Dimension(1000, 800));

    return scroll;
  }

  /**
   * Private method to set up the edit panel.
   *
   * @return the edit panel
   */
  private JPanel setEditPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setPreferredSize(new Dimension(350, 300));
    panel.setBackground(Color.WHITE);

    // add edit controls to panel
    addEditsToPanel(panel);

    // TODO add vertical scroll bar

    return panel;
  }

  /**
   * Private method to set up the control panel.
   *
   * @return the control panel
   */
  private JPanel setControlPanel() {
    JPanel control = new JPanel();
    control.setLayout(new FlowLayout());
    control.setPreferredSize(new Dimension(300, 50));
    control.setBackground(Color.WHITE);

    // play, pause or restart animation
    this.play = new JButton("Play");
    this.pause = new JButton("Pause");
    this.restart = new JButton("Restart");
    control.add(this.play);
    control.add(this.pause);
    control.add(this.restart);

    // increase or decrease animation speed
    this.increaseSpeed = new JButton("Increase");
    this.decreaseSpeed = new JButton("Decrease");
    control.add(this.increaseSpeed);
    control.add(this.decreaseSpeed);

    // loop animation
    this.loopCheck = new JCheckBox("Loop");
    control.add(this.loopCheck);

    return control;
  }

  /**
   * Add commands to the edit panel.
   *
   * @param panel to add content to
   */
  private void addEditsToPanel(JPanel panel) {
    // panel title
    Box titleBox = Box.createVerticalBox();
    JLabel title = new JLabel("<html><h2>Edit</h2></html>");
    titleBox.add(title);
    panel.add(titleBox);

    // add a new shape
    Box addBox = Box.createVerticalBox();
    JLabel btnAddLabel = new JLabel(""
            + "<html><h4>"
            + "Add a new shape"
            + "</h4></></html>", SwingConstants.LEFT);
    addBox.add(btnAddLabel);
    JLabel addSummary = new JLabel(""
            + "<html><p>Add a new shape to the list of<br />"
            + "current shapes.</p>"
            + "<h4>Format: </h4>"
            + "<p>name appear x-coordinate y-coordinate width height red blue green disappear</p>"
            + "<p>Example: clara 1 190 161 20 11 113 87 151 10</p><br /></html>");
    addBox.add(addSummary);
    this.sAdd = new JTextField(5);
    addBox.add(this.sAdd);
    this.btnAddRect = new JButton("Add rectangle");
    addBox.add(this.btnAddRect);
    this.btnAddOval = new JButton("Add oval");
    addBox.add(this.btnAddOval);
    panel.add(addBox);

    // add a new animation
    // TODO new animations
    Box addAnimationBox = Box.createVerticalBox();
    JLabel btnAddAnimationLabel = new JLabel(""
            + "<html><h4>"
            + "Add a new animation"
            + "</h4></></html>", SwingConstants.LEFT);
    addAnimationBox.add(btnAddAnimationLabel);
    JLabel addAnimationSummary = new JLabel(""
            + "<html><p>Add a new animation to one of the<br />"
            + "current shapes.</p>"
            + "<h4>Initial State Format: </h4>"
            + "<p>name start x-coordinate y-coordinate width height red blue green</p>"
            + "<p>Example: C 1 190 161 20 11 113 87 151</p><br /></html>"
            + "<h4>Final State Format: </h4>"
            + "<p>name start x-coordinate y-coordinate width height red blue green</p>"
            + "<p>Example: C 10 190 161 80 160 113 87 151</p><br /></html>");
    addAnimationBox.add(addAnimationSummary);
    this.aStart = new JTextField(5);
    this.aEnd = new JTextField(5);
    addAnimationBox.add(this.aStart);
    addAnimationBox.add(this.aEnd);
    this.btnAddAnimation = new JButton("Add animation");
    addAnimationBox.add(this.btnAddAnimation);
    panel.add(addAnimationBox);

    // remove a shape
    Box removeBox = Box.createVerticalBox();
    JLabel btnRemoveLabel = new JLabel(""
            + "<html><h4>"
            + "Remove a shape from the Animator"
            + "</h4></></html>", SwingConstants.LEFT);
    removeBox.add(btnRemoveLabel);
    JLabel removeSummary = new JLabel(""
            + "<html><p>Remove a shape from the current<br />"
            + "list of shapes. Removing a shape will<br />"
            + "automatically remove all animations<br />"
            + "associated with it.</p><br /></html>");
    removeBox.add(removeSummary);
    this.sRemove = new JTextField(5);
    removeBox.add(this.sRemove);
    this.btnRemove = new JButton("Remove");
    removeBox.add(this.btnRemove);
    panel.add(removeBox);

    // save to svg file
    Box saveBox = Box.createVerticalBox();
    JLabel btnSaveLabel = new JLabel(""
            + "<html><h4>"
            + "Save animation as an SVG or text file"
            + "</h4></></html>", SwingConstants.LEFT);
    saveBox.add(btnSaveLabel);
    JLabel saveSummary = new JLabel(""
            + "<html><p>Save the current shapes and animations <br />"
            + "as an SVG or text. The new file will be <br />"
            + "automatically saved in the \"resources\" folder <br />"
            + "and will be name with a time stamp.</p><br /></html>");
    saveBox.add(saveSummary);
    this.format = new ArrayList<JRadioButton>();
    JRadioButton saveSVG = new JRadioButton(".svg");
    JRadioButton saveText = new JRadioButton(".txt");
    this.format.add(saveSVG);
    this.format.add(saveText);
    saveBox.add(saveSVG);
    saveBox.add(saveText);
    this.btnSave = new JButton("Save");
    saveBox.add(this.btnSave);
    panel.add(saveBox);

    // TODO add functionality to upload a file
  }

  /**
   * Parse a textfield content and return a new shape. Text field returns
   * content in the following format: name appear X Y width height R G B disappear.
   *
   * @param type of shape
   * @return a new shape
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public Shapes getNewShape(String type) {
    if (!this.sAdd.equals("")) {
      String[] data = this.sAdd.getText().split(" ");
      // Z 1 190 161 120 120 113 87 151 10
      int appear = Integer.parseInt(data[1]);
      int disappear = Integer.parseInt(data[9]);
      int width = Integer.parseInt(data[4]);
      int height = Integer.parseInt(data[5]);
      Color color = new Color(
              Integer.parseInt(data[6]),
              Integer.parseInt(data[7]),
              Integer.parseInt(data[8]));
      Coordinates pos = new Coordinates(Integer.parseInt(data[2]), Integer.parseInt(data[3]));
      if (type.equals("rectangle")) {
        return new Rectangle(data[0], appear, disappear, width, height, color, pos);
      }
      return new Oval(data[0], appear, disappear, width, height, color, pos);
    }
    return null;
  }

  /**
   * Parse a textfield content and return a new shape. Text field returns
   * content in the following format:
   * <ul>
   *   <li>Initial state: name start X Y width height R G B</li>
   *   <li>Final state: name end X Y width height R G B</li>
   * </ul>
   * The method evaluates the string and generates a new animation accordingly.
   *
   * @return a new animation
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public Animations getNewAnimation() {
    String[] start = this.aStart.getText().split(" ");
    String[] end = this.aEnd.getText().split(" ");
    Shapes shape = null;

    // find shape
    for (Shapes s : this.shapes) {
      if (s.getName().equals(start[0])) {
        shape = s;
      }
    }

    // Initial state:   Z 1  190 161 20  11  113 87 151
    // Final state:     Z 25 190 161 200 200 113 87 151
    int startT = Integer.parseInt(start[1]);
    int endT = Integer.parseInt(end[1]);
    int startW = Integer.parseInt(start[4]);
    int endW = Integer.parseInt(end[4]);
    int startH = Integer.parseInt(start[5]);
    int endH = Integer.parseInt(end[5]);
    Coordinates startPos = new Coordinates(Integer.parseInt(start[2]), Integer.parseInt(start[3]));
    Coordinates endPos = new Coordinates(Integer.parseInt(end[2]), Integer.parseInt(end[3]));
    Color startC = new Color(
            Integer.parseInt(start[6]),
            Integer.parseInt(start[7]),
            Integer.parseInt(start[8]));

    Color endC = new Color(
            Integer.parseInt(end[6]),
            Integer.parseInt(end[7]),
            Integer.parseInt(end[8]));
    if (startW != endW || startH != endH) {
      return new ChangeSize(shape, startT, endT, startW, endH, endW, endH);
    }
    if (startC != endC) {
      new ChangeColor(shape, startT, endT, startC, endC);
    }
    if (startPos != endPos) {
      return new ChangeCoordinates(shape, startT, endT, startPos, endPos);
    }
    return null;
  }

  /**
   * Give the view an actionListener for the buttons in the view.
   *
   * @param e the event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public void setButtonListener(ActionListener e) {
    this.play.addActionListener(e);
    this.pause.addActionListener(e);
    this.restart.addActionListener(e);
    this.increaseSpeed.addActionListener(e);
    this.decreaseSpeed.addActionListener(e);
    this.loopCheck.addActionListener(e);
    this.btnAddRect.addActionListener(e);
    this.btnAddOval.addActionListener(e);
    this.btnAddAnimation.addActionListener(e);
    this.btnRemove.addActionListener(e);
    this.btnSave.addActionListener(e);

    for (JRadioButton r : this.format) {
      r.addActionListener(e);
    }
  }

  /**
   * Private helper method to check if a radio button
   * is selected
   */
  private String selectedFileFormat() {
    for (JRadioButton r : this.format) {
      if (r.isSelected()) {
        return r.getText();
      }
    }
    return null;
  }

  /**
   * Sets the view's visibility to true (i.e. view is visible
   * within the JFrame).
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void display() {
    this.setVisible(true);
  }

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public String getTextFieldValue() {
    return this.sRemove.getText();
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
    animationPanel.setShapes(shapes);
  }

  /**
   * Writes out the text description of the shapes and animations to a file
   * in the "resources/" folder, using the current date time stamp as
   * the file name.
   *
   * @param fileName the file to which we are outputting the string
   *                 representation of the animation.
   */
  public void write(String fileName) {
    String format = selectedFileFormat();
    String description = "";

    if (format != null) {
      if (format.equals(".svg")) {
        // output an svg file
        description = this.outputSVG();
      }
      else {
        // output a txt file
        description = this.outputText();
      }

      try {
        BufferedWriter output;
        if (fileName.equals("System.out")) {
          output = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
          File file = new File(""
                  + "src/cs5004/easyanimator/resources/"
                  // save file using the current date time stamp
                  + LocalDate.now().toString().toString() + format);
          output = new BufferedWriter(new FileWriter(file));
        }
        output.write(description);
        output.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    else {
      throw new IllegalStateException("You need to select a file format before saving.");
    }
  }

  /**
   * Private method to render the model in an SVG.
   *
   * @return the model description
   */
  private String outputSVG() {

    String markup = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
            + "xmlns=\"http://www.w3.org/2000/svg\">\n";

    double endTime = (this.endTime / speed) * 1000;
    markup += "<rect>\n"
            + "<animate id=\"base\" begin=\"0;base.end\" dur=\"" + endTime + "ms\" "
            + "attributeName=\"visibility\" from=\"hide\" to=\"hide\"/>\n"
            + "</rect>\n";

    for (Shapes s : this.shapes) {
      Shapes shape = s;

      if (shape.getDisplayValue()) {
        markup += shape.toSVGTag();
      }

      for (Animations a : this.animations) {
        Animations currentA = a;
        Shapes currentS = currentA.getShape();

        // detect loop
        if (shape.getName().equals((currentS.getName())) && !loop) {
          markup += currentA.toSVGTag((this.getSpeed()));
        }
        else if (shape.getName().equals(currentS) && loop) {
          markup += currentA.toSVGTagWithLoop(this.getSpeed());
        }
      }
      markup += shape.svgEndTag() + "\n";
    }
    markup += "</svg>";

    return markup;
  }

  /**
   * Private method to render the model in a text file.
   *
   * @return the model description in a string
   */
  private String outputText() {
    StringBuilder str = new StringBuilder();

    if (shapes.size() != 0) {
      str.append("Shapes:\n");

      for (Shapes s : this.shapes) {
        // call shape description
        str.append(s.getDescription(speed));
        str.append("\n");
      }
    }

    if (this.animations.size() != 0) {
      // sort animations by start time
      Utils.sortAnimations(this.animations);

      for (Animations a : this.animations) {
        // call animation description
        str.append(a.getDescription(this.speed));
        str.append("\n");
      }
    }

    return str.toString();
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
   * Sets the boolean loop in the view.
   *
   * @param loop boolean to set loop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public void setIsLoop(boolean loop) {
    this.loop = loop;
  }

  /**
   * Returns the is loop boolean in the view.
   *
   * @return boolean for the loop field
   * @throws UnsupportedOperationException if the view does not support this  functionality
   */
  public boolean getIsLoop() {
    return this.loop;
  }

  /**
   * Returns the description of the view in a string.
   *
   * @return the view description in a string
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public String getTextDescription() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
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
}