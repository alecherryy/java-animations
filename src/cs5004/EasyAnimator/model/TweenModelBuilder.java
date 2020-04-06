package cs5004.EasyAnimator.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * This interface contains all the methods inside the
 * AnimationFileReader interface. It will read a file that contains
 * the animation, and builds a model according to the specifications.
 */
public interface TweenModelBuilder<T> {

  /**
   * Add a new oval to the model.
   *
   * @param name the name of the oval
   * @param x the x-coordinate of the center of the oval
   * @param y the y-coordinate of the center of the oval
   * @param xRadius the x-radius of the oval
   * @param yRadius the y-radius of the oval
   * @param color the color of the oval as a float
   * @param appear the appear time of the oval
   * @param disappear the disappear time of the oval.
   * @return the builder object
   */
  TweenModelBuilder<T> addOval(String name, float x, float y, float xRadius, float yRadius,
                               Color color, int appear, int disappear);

  /**
   * Add a new rectangle to the model.
   *
   * @param name the name of the oval
   * @param x the x-coordinate of the lower left corner of the rectangle
   * @param y the y-coordinate of the lower left corner of the rectangle
   * @param width the width of the rectangle
   * @param height the height of the rectangle
   * @param color the color of the rectangle
   * @param appear the appear time of the rectangle
   * @param disappear the disappear time of the rectangle
   * @return the builder object
   */
  TweenModelBuilder<T> addRectangle(String name, float x, float y, float width, float height,
                                    Color color, int appear, int disappear);

  /**
   * Move the shape that is passed in to the given position that is passed in
   * during the time interval that is passed in.
   *
   * @param name the name of the shape that will be moved
   * @param fromX the x-coordinate from which the shape will be moved
   * @param fromY the y-coordinate to which the shape will be moved
   * @param toX the x-coordinate to which the shape will be moved
   * @param toY the y-coordinate to which the shape will be moved
   * @param start the time when the move starts
   * @param end the time when the move ends
   */
  TweenModelBuilder<T> addMove(String name, float fromX, float fromY, float toX, float toY,
                               int start, int end);

  /**
   * Change the color of the shape that is passed in to the given position
   * that is passed in during the time interval that is passed in.
   *
   * @param name the name of the shape whose color will be changed
   * @param color the shape's original color
   * @param newColor the shape's new color
   * @param start the time when the color change starts
   * @param end the time when the color change ends
   * @return the animation builder model
   */
  TweenModelBuilder<T> addColorChange(String name, Color color,
                                      Color newColor, int start, int end);

  /**
   * Change the width and height of the shape that is passed in to
   * the width and height that are passed in during the time interval that is passed in.
   *
   * @param name the name of the shape whose size will be changed
   * @param w the original width of the shape
   * @param h the original height of the shape
   * @param newW the new width of the shape, to which its width will be changed
   * @param newH the new width of the shape, to which its height will be changed
   * @param start the time when the scale change starts
   * @param end the time when the scale change ends
   * @return the animation builder model
   */
  TweenModelBuilder<T> addSizeChange(String name, float w, float h,
                                     float newW, float newH, int start, int end);

  /**
   * Return the built model.
   *
   * @return the build model
   */
  T build();

  void addShapeMap(String name, String type);

  void addShapInfoeMap(String name, ArrayList<Integer> test);
}
