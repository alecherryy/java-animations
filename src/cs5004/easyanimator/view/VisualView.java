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

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents a view for a simple Visual Animation model. It
 * extends the JFrame abstract class and implements all methods listed
 * in the iView interface.
 */
public class VisualView extends JFrame implements View {
  private AnimateJPanel animationPanel;
  private ArrayList<Shapes> shapes;
  private ArrayList<Integer> settings;
  private ArrayList<Animations> animations;
  private float speed;
//  private JPanel cmdPanel;
  private JTextField shapeName;
  private JButton btnAdd;
  private JButton btnRemove;
  private JButton btnSave;
  private ArrayList<JRadioButton> format;

  /**
   * This is the class constructor. It takes two parameters: the speed
   * and a list of shapes.
   *
   * @param speed the speed
   * @param shapes the list of shapes
   */
  public VisualView(float speed, ArrayList<Integer> settings, ArrayList<Shapes> shapes,
                    ArrayList<Animations> animations) {
    this.shapes = shapes;
    this.settings = settings;
    this.speed = speed;
    this.animations = animations;
    this.setTitle("Java Easy Animator");
    this.setSize(this.settings.get(0), this.settings.get(1));
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    animationPanel = new AnimateJPanel();
    animationPanel.setPreferredSize(new Dimension(this.settings.get(2), this.settings.get(3)));

    animationPanel.setShapes(shapes);

    this.add(setUpScrollBars(this.animationPanel), BorderLayout.CENTER);

    // create command panel
    JPanel panel = setUpCommandPanel();
    addCommandsToPanel(panel);
    
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

    return scroll;
  }

  /**
   * Private method to set up the command panel.
   */
  private JPanel setUpCommandPanel() {
    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.LEFT));
    panel.setPreferredSize(new Dimension(300, this.settings.get(3)));
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
    removeBox.add(removeShapeName);
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
   * Give the view an actionListener for the buttons in the view.
   *
   * @param e the event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public void setButtonListener(ActionListener e) {
    this.btnAdd.addActionListener(e);
    this.btnRemove.addActionListener(e);
//    this.shapeName.addActionListener(e);
//    this.btnSave.addActionListener(e);

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
    return this.shapeName.getText();
  }

  /**
   * Sets the view's visibility to true (i.e. view is visible within the JFrame).
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void display() {
    setVisible(true);
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
   * Repaints the view.
   */
  public void refresh() {
    repaint();
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
   * @throws UnsupportedOperationException if the view does not support this operation.
   */
  public ArrayList<Animations> getAnimations() {
    return animations;
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
   * Writes out the text description of the animation to a file specified in the parameters.
   *
   * @param fileName the file to which we are outputting the
   *                 string representation of the animation.
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void write(String fileName) {
    String format = selectedFileFormat();
    String description = "";

    if (format != null) {
      if (format.equals("SVG")) {
        description = this.getSvgFile();
      }
      else {
        description = this.getTextFile();
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
   * Returns the animation in an svg file.
   *
   * @return the animation in an svg file
   * @throws UnsupportedOperationException if the view does not support this method
   */
  private String getSvgFile() {
    StringBuilder svg = new StringBuilder();

    // create svg mark up
    // set overflow to "auto" to enable scrolling
    svg.append("<svg width=\"" + this.settings.get(2) + "\" height=\"" + this.settings.get(3)
            + "\" version=\"1.1\" overflow=\"auto\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (Shapes s : this.getShapes()) {
      svg.append(s.toSVGTag());
      for (Animations a : this.getAnimations()) {
        if (s.getName().equals(a.getShape().getName())) {
          svg.append(a.toSVGTag(this.getSpeed()));
        }
      }
      svg.append(s.svgEndTag());
    }
    svg.append("</svg>");

    return svg.toString();
  }

  /**
   * Returns the animation in an svg file.
   *
   * @return the animation in an svg file
   * @throws UnsupportedOperationException if the view does not support this method
   */
  private String getTextFile() {
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
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public float getSpeed() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Visual Animation View view does not include this "
            + "functionality.");
  }

  /**
   * Display the error message on the screen.
   *
   * @param error the error message as a string.
   * @throws UnsupportedOperationException if the view does not support this method
   **/
  public void displayErrorMsg(String error) throws UnsupportedOperationException {
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
    return this.settings;
  }

  /**
   * Sets the boolean isLoop in the view.
   * @param loop boolean to set isLoop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public void setIsLoop(boolean loop) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Returns the is loop boolean in the view.
   * @return boolean for the isLoop field
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public boolean getIsLoop() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Returns the checkbox list from this view.
   *
   * @return the view's checkbox list, a list of JCheckBox objects
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public List<JCheckBox> getCheckBoxList() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }
}
