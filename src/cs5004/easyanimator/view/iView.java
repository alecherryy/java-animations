package cs5004.easyanimator.view;

import java.util.ArrayList;

import cs5004.easyanimator.model.shapes.Shape;


public interface iView {
  /**
   * Set an object's visibility to true.
   */
  void display();

  /**
   * Takes a list of Shape objects and sets them within
   * the view.
   */
  void setShapes(ArrayList<Shape> shapes);

  /**
   * Resets the view.
   */
  void refresh();

  /**
   * Returns a list of Shape objects.
   *
   * @return a list of Shape objects
   */
  ArrayList<Shape> getShapes();
}