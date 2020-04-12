package cs5004.easyanimator.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

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
  private boolean loop;
  private int endTime;
  private JButton play, pause, restart, increaseSpeed, decreaseSpeed;
  private JPanel buttonPanel;

  // extra functionality
  private JButton btnAdd;
  private JButton btnRemove;
  private JTextField sRemove;
  private JButton btnSave;
  private JTextField out;
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

    increaseSpeed = new JButton("Increase Speed");
    buttonPanel.add(increaseSpeed);

    decreaseSpeed = new JButton("Decrease Speed");
    buttonPanel.add(decreaseSpeed);

<<<<<<< Updated upstream
=======
    // add command panel
    this.add(setUpCommandPanel(), BorderLayout.EAST);

>>>>>>> Stashed changes
    this.pack();
  }

  /**
   * Private method to set up the Scroll Pane.
   *
   * @param panel to add scroll bars to
   */
  private JScrollPane setUpScrollBars(JPanel panel) {
    JScrollPane scroll = new JScrollPane(panel);
    scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    scroll.setBounds(50, 30, 300, 50);

    this.add(scroll, BorderLayout.CENTER);

    return scroll;
  }

  /**
   * Private method to set up the command panel.
   */
  private JPanel setUpCommandPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel.setPreferredSize(new Dimension(300, 1000));
    this.add(panel, BorderLayout.EAST);

    return panel;
  }

  /**
   * Add commands to the command panel.
   *
   * @param panel to add content to
   */
  private void addCommandsToPanel(JPanel panel) {
    // add a new shape
    Box addBox = Box.createVerticalBox();
    JLabel btnAddLabel = new JLabel(""
            + "<html><h4>"
            + "Add a shape to the Animator"
            + "</h4></></html>", SwingConstants.LEFT);
    addBox.add(btnAddLabel);
    JTextField addShapeName = new JTextField(5);
    addBox.add(addShapeName);
    this.btnAdd = new JButton("Add");
    addBox.add(btnAdd);
    panel.add(addBox);

    // remove a shape
    Box removeBox = Box.createVerticalBox();
    JLabel btnRemoveLabel = new JLabel(""
            + "<html><h4>"
            + "Remove a shape from the Animator"
            + "</h4></></html>", SwingConstants.LEFT);
    removeBox.add(btnRemoveLabel);
    JTextField removeShapeName = new JTextField(5);
    this.sRemove = new JTextField(5);
    removeBox.add(this.sRemove);
    this.btnRemove = new JButton("Remove");
    removeBox.add(this.btnRemove);
    panel.add(removeBox);

    // save to svg file
    Box saveBox = Box.createVerticalBox();
    JLabel btnSaveLabel = new JLabel(""
            + "<html><h4>"
            + "Save animation"
            + "</h4></></html>", SwingConstants.LEFT);
    saveBox.add(btnSaveLabel);
    this.out = new JTextField("Name of the file",5);
    this.out.setForeground(Color.GRAY);
    saveBox.add(this.out);
    this.format = new ArrayList<JRadioButton>();
    JRadioButton saveSVG = new JRadioButton("SVG");
    JRadioButton saveText = new JRadioButton("Text");
    this.format.add(saveSVG);
    this.format.add(saveText);
    saveBox.add(saveSVG);
    saveBox.add(saveText);
    this.btnSave = new JButton("Save");
    saveBox.add(this.btnSave);
    panel.add(saveBox);
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
   * Returns the description of the view in a string.
   *
   * @return the view description in a string
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public String getTextDescription() {

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
   * Sets the view's visibility to true (i.e. view is visible
   * within the JFrame).
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void display() {
    this.setVisible(true);
  }

  /**
   * Give the view an actionListener for the buttons in the view.
   *
   * @param e the event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public void setButtonListener(ActionListener e) {
    play.addActionListener(e);
    pause.addActionListener(e);
    restart.addActionListener(e);
    increaseSpeed.addActionListener(e);
    decreaseSpeed.addActionListener(e);
//    export.addActionListener(e);
//
//    this.btnAdd.addActionListener(e);
//    this.btnRemove.addActionListener(e);
//    this.btnSave.addActionListener(e);
//
//    for (JRadioButton r : this.format) {
//      r.addActionListener(e);
//    }
  }

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public String getTextFieldValue() {
    return this.out.getText();
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
   * Writes out the text description of the animation to a file
   * specified in the parameters.
   *
   * @param fileName the file to which we are outputting the string
   *                 representation of the animation.
   */
  public void write(String fileName) {
    String format = selectedFileFormat();
    String description = "";

    if (format != null) {
      if (format.equals("SVG")) {
        description = this.getTextDescription();
      }
      else {
        description = this.getTextDescription();
      }

      try {
        BufferedWriter output;
        if (fileName.equals("System.out")) {
          output = new BufferedWriter(new OutputStreamWriter(System.out));
        } else {
          File file = new File("test-out." + selectedFileFormat());
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
}