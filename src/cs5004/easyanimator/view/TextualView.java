package cs5004.easyanimator.view;

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
 * This class implements all the methods in View and
 * represents the view as a text object.
 */
public class TextualView implements View {
  private float speed;
  private ArrayList<Shapes> shapes;
  private ArrayList<Animations> animations;

  /**
   * This is the class constructor for the TextualView with its given speed,
   * shapes list, and animations list.
   *
   * @param speed the speed at which the animation happens as a double
   * @param shapes the lists of the shapes present in the  model
   * @param animations the list of the animations present in model
   */
  public TextualView(float speed, ArrayList<Shapes> shapes, ArrayList<Animations> animations) {
    this.speed = speed;
    this.shapes = shapes;
    this.animations = animations;
  }

  /**
   * Sets the view's visibility to true (i.e. view is visible within the JFrame).
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void display() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Textual view does not include this functionality.");
  }

  /**
   * Takes a list of Shape objects and sets them within the view.
   *
   * @param shapes a list of shapes
   */
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;
  }

  /**
   * Repaints the view.
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public void refresh() {
    throw new UnsupportedOperationException(""
            + "Textual view does not include this functionality.");
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
   * Returns the description of the view in a string.
   *
   * @return the view description in a string
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public String getTextDescription() throws UnsupportedOperationException {
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
   * Writes out the text description of the animation to a file specified in the parameters.
   *
   * @param fileName the file to which we are outputting the
   *                 string representation of the animation.
   * @throws UnsupportedOperationException if the view does not support this method
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
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public float getSpeed() throws UnsupportedOperationException {
    return this.speed;
  }

  /**
   * Display the error message on the screen.
   *
   * @param error the error message as a string.
   * @throws UnsupportedOperationException if the view does not support this method
   **/
  public void displayErrorMsg(String error) throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
            + "Textual view does not include this functionality.");
  }

  /**
   * Returns a list of integers that represent the settings.
   *
   * @return a list representing the settings.
   * @throws UnsupportedOperationException because TextualView does not support this functionality
   */

  public ArrayList<Integer> getSettings() throws UnsupportedOperationException {
    throw new UnsupportedOperationException(""
        + "Textual view does not include this functionality.");
  }

  /**
   * Sets the boolean isLoop in the view.
   * @param loop boolean to set isLoop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public void setIsLoop(boolean loop) {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Returns the is loop boolean in the view.
   * @return boolean for the isLoop field
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  public boolean getIsLoop() {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Give the view an actionListener for the buttons in the view.
   *
   * @param event The action event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public void setButtonListener(ActionListener event) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  public String getFilename() throws UnsupportedOperationException{
    throw new UnsupportedOperationException("View does not support this method");
  }

  @Override
  public List<JCheckBox> getCheckBoxList() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("View does not support this method");
  }
}

