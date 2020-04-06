package cs5004.EasyAnimator.view;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import cs5004.EasyAnimator.model.AnimationModel;
import cs5004.EasyAnimator.model.AnimationModelImpl;
import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.animations.ChangeColor;
import cs5004.EasyAnimator.model.animations.ChangeCoordinates;
import cs5004.EasyAnimator.model.animations.ChangeSize;
import cs5004.EasyAnimator.model.shapes.Coordinates;
import cs5004.EasyAnimator.model.shapes.Oval;
import cs5004.EasyAnimator.model.shapes.Rectangle;
import cs5004.EasyAnimator.model.shapes.Shapes;

import static org.junit.Assert.assertEquals;


public class VisualAnimationViewTest {
  AnimationModel model;
  Shapes rectangle;
  Shapes oval;
  Coordinates c1;
  Coordinates c2;
  Coordinates c3;
  Coordinates c4;
  Animations changeCoordinates1;
  Animations changeCoordinates2;
  Animations changeCoordinates3;
  Animations changeColor1;
  Animations changeSize1;
  View view;
  ArrayList<Shapes> shapesList;

  @Before
  public void setup() {
    model = new AnimationModelImpl();
    c1 = new Coordinates(200, 200);
    c2 = new Coordinates(300, 300);
    c3 = new Coordinates(500, 100);
    c4 = new Coordinates(500, 400);

    rectangle = new Rectangle("R", 1, 100, 50, 100,
        Color.RED, c1);
    oval = new Oval("O", 6, 100, 60, 30, Color.BLUE,
        c3);
    changeCoordinates1 = new ChangeCoordinates(rectangle, 10, 50, c1, c2);
    changeCoordinates2 = new ChangeCoordinates(oval, 20, 70, c3, c4);
    changeCoordinates3 = new ChangeCoordinates(rectangle, 70, 100, c2, c1);
    changeColor1 = new ChangeColor(oval, 50, 80, Color.BLUE, Color.GREEN);
    changeSize1 = new ChangeSize(rectangle, 51, 70, 50, 100,
        25, 100);
    model.addShape(rectangle);
    model.addShape(oval);
    model.addAnimation(changeCoordinates1);
    model.addAnimation(changeCoordinates2);
    model.addAnimation(changeColor1);
    model.addAnimation(changeCoordinates3);
    model.addAnimation(changeSize1);

    shapesList = model.getShapes();

    view = new VisualAnimationView(10, model.getShapes());

  }

  /**
   * Test for display() method.
   */
  @Test
  public void testMakeVisible() {
    assertEquals(false, ((VisualAnimationView) this.view).isVisible());
    this.view.display();
    assertEquals(true, ((VisualAnimationView) this.view).isVisible());
  }

  /**
   * Test for setShapes() method.
   */
  @Test
  public void testSetShapes() {
    assertEquals(shapesList, view.getShapes());
    view.setShapes(new ArrayList<Shapes>());
    assertEquals(new ArrayList<Shapes>(), view.getShapes());
  }

  /**
   * Test for getShapes() method.
   */
  @Test
  public void testGetShapes() {
    assertEquals(shapesList, view.getShapes());
  }

  /**
   * Test that getAnimations() throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testGetAnimations() {
    view.getAnimations();
  }

  /**
   * Test that getTextDescription() throws an exception.
   */

  @Test(expected = UnsupportedOperationException.class)
  public void testGetTextDescription() {
    view.getTextDescription();
  }

  /**
   * Test that write() throws an exception.
   */

  @Test(expected = UnsupportedOperationException.class)
  public void testWrite() {
    this.view.write("output.txt");
  }

  /**
   * Test that getSpeed() throws an exception.
   */

  @Test(expected = UnsupportedOperationException.class)
  public void testGetSpeed() {
    view.getSpeed();
  }

//  @Test
//  public void testShowErrorMessage() {
//    assertEquals(view.displayErrorMsg("error"), "error");
//  }

}