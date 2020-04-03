package cs5004.easyanimator.view;

import java.util.ArrayList;
// to handle all action events.
import java.awt.event.ActionListener;

import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.model.animations.Animations;


/**
 * This interface represents a View; it contains all
 * methods common to all views.
 */
public interface View {

  /**
   * Set an object's visibility to true.
   *
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   */
  void display() throws UnsupportedOperationException;

  /**
   * Takes a list of Shape objects and sets them
   * within the view.
   *
   * @param shapes a list of shapes
   */
  void setShapes(ArrayList<Shapes> shapes);

  /**
   * Tell the view to draw itself.
   *
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   */
  void refresh() throws UnsupportedOperationException;

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  ArrayList<Shapes> getShapes();

  /**
   * Returns a list of animation objects.
   *
   * @return a list of animation objects
   */
  ArrayList<Animations> getAnimations();

  /**
   * Gets the text view of the animation as a string.
   *
   * @return text view of the animation as a string.
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   */
  String getTextDescription() throws UnsupportedOperationException;

  /**
   * Writes out the text description of the animation to a file specified in the parameters.
   *
   * @param fileName the file to which we are outputting the string representation of the
   *                 animation.
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   */
  void write(String fileName) throws UnsupportedOperationException;

  /**
   * Get the speed at which the animation occurs
   *
   * @return the speed of the animation as a float
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   */
  float getSpeed() throws UnsupportedOperationException;

  /**
   * Display the error message on the screen.
   *
   * @param error the error message as a string.
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   **/
  void displayErrorMsg(String error) throws UnsupportedOperationException;

  /**
   * We use the listener interface for receiving action events  for the buttons in the view.
   *
   * @param event The event for the button
   * @throws UnsupportedOperationException if the view is not supposed to have this functionality.
   */
  void displayButton(ActionListener event) throws UnsupportedOperationException;

}