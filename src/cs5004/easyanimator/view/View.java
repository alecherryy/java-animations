package cs5004.easyanimator.view;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This interface represents a View; it contains all
 * methods common to all views.
 */
public interface View {

  /**
   * Sets the view's visibility to true (i.e. view is visible within the JFrame).
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  void display() throws UnsupportedOperationException;

  /**
   * Takes a list of Shape objects and sets them within the view.
   *
   * @param shapes a list of shapes
   */
  void setShapes(ArrayList<Shapes> shapes);

  /**
   * Repaints the view.
   *
   * @throws UnsupportedOperationException if the view does not support this method
   */
  void refresh() throws UnsupportedOperationException;

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  ArrayList<Shapes> getShapes();

  /**
   * Returns a list of Animations objects.
   *
   * @return a list of Animation objects
   */
  ArrayList<Animations> getAnimations();

  /**
   * Returns the description of the view in a string.
   *
   * @return the view description in a string
   * @throws UnsupportedOperationException if the view does not support this method
   */
  String getTextDescription() throws UnsupportedOperationException;

  /**
   * Writes out the text description of the animation to a file specified in the parameters.
   *
   * @param fileName the file to which we are outputting the string representation of the
   *                 animation.
   * @throws UnsupportedOperationException if the view does not support this method
   */
  void write(String fileName) throws UnsupportedOperationException;

  /**
   * Get the speed at which the animation occurs.
   *
   * @return the speed of the animation
   * @throws UnsupportedOperationException if the view does not support this method
   */
  float getSpeed() throws UnsupportedOperationException;

  /**
   * Display the error message on the screen.
   *
   * @param error the error message as a string.
   * @throws UnsupportedOperationException if the view does not support this method
   **/
  void displayErrorMsg(String error) throws UnsupportedOperationException;

  /**
   * Returns a list of integers that represent the settings.
   *
   * @return a list representing the settings.
   * @throws UnsupportedOperationException if the view does not support this method
   */
  ArrayList<Integer> getSettings() throws UnsupportedOperationException;

  /**
   * Sets the boolean isLoop in the view.
   * @param loop boolean to set isLoop to
   * @throws UnsupportedOperationException if the view does not need the functionality
   */
  void setIsLoop(boolean loop);

  /**
   * Returns the is loop boolean in the view.
   * @return boolean for the isLoop field
   * @throws UnsupportedOperationException if the view does not support this  functionality
   */
  boolean getIsLoop();

  /**
   * Give the view an actionListener for the buttons in the view.
   *
   * @param event The action event for the button
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  void setButtonListener(ActionListener event);

  /**
   * Returns the file name command from the text box.
   *
   * @return file name from user
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  String getFilename();

  /**
   * Returns the checkbox list from this view.
   *
   * @return the view's checkbox list, a list of JCheckBox objects
   * @throws UnsupportedOperationException if the view does not support this functionality
   */
  List<JCheckBox> getCheckBoxList();

}