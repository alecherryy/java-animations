package cs5004.easyanimator.view;

import java.util.ArrayList;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;


public interface View {
  /**
   * TODO Return the description of the view? Model? I am not sure.
   *
   * @return description in a string
   */
  String getDescription();

  /**
   * Writes data to a given file.
   *
   * @param file the name of the file
   */
  void writeOut(String file);

  /**
   * Retrieves and returns the Animation Model.
   *
   * @return the animation model
   */
  AnimationPanel getAnimationPanel();

  /**
   * TODO returns the current tick? Not sure.
   *
   * @return the tick as a double
   */
  double getTick();

  /**
   * Set an object's visibility to true.
   */
  void makeVisible();

  /**
   * Erases all Animations and Shapes from the view.
   */
  void refreshView();

  /**
   * Resets the View, Animations and Shapes to their default values.
   */
  void resetView();

  /**
   * Takes a list of Shape objects and sets them within
   * the view.
   */
  void setShapes(ArrayList<Shape> shapes);

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  ArrayList<Shape> getShapes();

  /**
   * Returns a list of Animations objects.
   *
   * @return a list of Animations objects
   */
  ArrayList<Animations> getAnimations();
}