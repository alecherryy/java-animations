package cs5004.easyanimator.view;

import java.util.ArrayList;

import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This interface represents a View; it contains all
 * methods common to all views.
 */
public interface View {

  /**
   * Set an object's visibility to true.
   */
  void display();

  /**
   * Takes a list of Shape objects and sets them
   * within the view.
   *
   * @param shapes a list of shapes
   */
  void setShapes(ArrayList<Shapes> shapes);

  /**
   * Resets the view.
   */
  void refresh();

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  ArrayList<Shapes> getShapes();
}