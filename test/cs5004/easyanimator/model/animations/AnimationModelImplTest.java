package cs5004.easyanimator.model.animations;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;

import static org.junit.Assert.assertEquals;

/**
 * This is a JUnit suite to test the AnimationModelImpl class.
 */
public class AnimationModelImplTest {
  private Coordinates c1;
  private Coordinates c2;
  private ChangeColor changeColor;
  private ChangeColor changeColor2;
  private ChangeCoordinates changeCoordinates;
  private ChangeCoordinates changeCoordinates2;
  private ChangeSize changeSize;
  private ChangeSize changeSize2;
  private Shapes rectangle;
  private Shapes square;
  private Shapes oval;
  private Shapes oval2;
  private AnimationModel model;

  /**
   * Class set up.
   */
  @Before
  public void setup() {
    model = new AnimationModelImpl();
    c1 = new Coordinates(0, 0);
    c2 = new Coordinates(115.5, 50.5);
    rectangle = new Rectangle("R", 5, 10, 1, 2,
            Color.BLUE, new Coordinates(25, 50));
    square = new Rectangle("S", 2, 7, 10, 30,
            Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
            new Coordinates(0, 18.5));
    oval2 = new Oval("O2", 0, 10, 3, 4, Color.BLUE,
            new Coordinates(0, 0));
    changeColor = new ChangeColor(rectangle, 6, 9, Color.BLUE, Color.RED);
    changeColor2 = new ChangeColor(oval, 10, 15, Color.GREEN, Color.RED);
    changeCoordinates = new ChangeCoordinates(square, 3, 7, new Coordinates(0,
            0), new Coordinates(20, 50));
    changeCoordinates2 = new ChangeCoordinates(rectangle, 6, 9, c1, c2);
    changeSize = new ChangeSize(oval, 10, 15, 35, 18,
            40, 20);
    changeSize2 = new ChangeSize(oval2, 2, 5, 3, 4,
            15.5, 15.5);
  }

  /**
   * Test for addShape() method.
   */
  @Test
  public void addShapeTest() {
    this.model.addShape(rectangle);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0, 50.0), Width: 1.0, Height: 2.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=5s\n"
            + "Disappears at t=10s\n\n", model.getDescription());
    this.model.addShape(oval);
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0, 50.0), Width: 1.0, Height: 2.0, Color: (0.0,0.0,1.0)\n"
            + "Appears at t=5s\n"
            + "Disappears at t=10s\n"
            + "\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0, 18.5), X radius: 5.0, Y radius: 5.0, Color: (0.0,1.0,0.0)\n"
            + "Appears at t=10s\n"
            + "Disappears at t=20s\n\n", model.getDescription());
  }

  /**
   * Test for addAnimations() method.
   */
  @Test
  public void addAnimationsTest() {
    model.addAnimation(changeColor);
    assertEquals("Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) "
            + "from t=6 to t=9\n", model.getDescription());
    model.addAnimation(changeCoordinates);
    assertEquals("Shape S moves from (0.0, 0.0) to (20.0, 50.0) from t=3 to t=7\n"
                    + "Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=6 to t=9\n",
            model.getDescription());
  }

  /**
   * Test adding animations in different order.
   */
  @Test
  public void addAnimationsTest2() {
    model.addAnimation(changeCoordinates);
    assertEquals("Shape S moves from (0.0, 0.0) to (20.0, 50.0) from t=3 to t=7\n",
            model.getDescription());
    model.addAnimation(changeColor);
    assertEquals("Shape S moves from (0.0, 0.0) to (20.0, 50.0) from t=3 to t=7\n"
                    + "Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=6 to t=9\n",
            model.getDescription());

  }
  // TODO test that addAnimation() throws an exception when adding an invalid
  //  animation (incompatible move for the same shape during overlapping time intervals).
  // TODO test that addShape() throws an IllegalArgumentException for duplicate shapes.
  // TODO test that addAnimation() throws an IllegalArgumentException for incompatible changes.
  //  For example, having a shape object whose color is green, then trying to animate it to
  //  change its color from blue to red. Same with incompatible coordinates and incompatible size.
  // TODO test that addAnimation() throws an exception if the model is empty (there are no shapes
  //  to animate).
  // TODO test that addAnimation() throws an exception if it is trying to animate a shape that
  //  does not exist.
  // TODO test that addAnimation() throws an exception if we try to animate the shape after it
  //  has disappeared, or before it has appeared.


  /**
   * Test for getDescription() method.
   */
  @Test
  public void getDescriptionTest() {
    model.addShape(rectangle);
    model.addShape(oval);
    model.addShape(square);
    model.addAnimation(changeColor);
    model.addAnimation(changeColor2);
    model.addAnimation(changeCoordinates);
    assertEquals("Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (25.0, 50.0), Width: 1.0, Height: 2.0, Color: (0.0,0.0,1.0)\n"
                    + "Appears at t=5s\n"
                    + "Disappears at t=10s\n"
                    + "\n"
                    + "Name: O\n"
                    + "Type: oval\n"
                    + "Center: (0.0, 18.5), X radius: 5.0, Y radius: 5.0, Color: (0.0,1.0,0.0)\n"
                    + "Appears at t=10s\n"
                    + "Disappears at t=20s\n"
                    + "\n"
                    + "Name: S\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0, 0.0), Width: 10.0, Height: 30.0, Color: (1.0,0.0,0.0)\n"
                    + "Appears at t=2s\n"
                    + "Disappears at t=7s\n"
                    + "\n"
                    + "Shape S moves from (0.0, 0.0) to (20.0, 50.0) from t=3 to t=7\n"
                    + "Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=6 to t=9\n"
                    + "Shape O changes color from (0.0,1.0,0.0) to (1.0,0.0,0.0) from t=10 to t=15\n",
            model.getDescription());
  }

  /**
   * Test for getAnimations() method.
   */
  @Test
  public void getAnimationsTest() {
    model.addShape(rectangle);
    model.addShape(oval);
    model.addAnimation(changeColor);
    model.addAnimation(changeSize);
    assertEquals(new ArrayList<Animations>(Arrays.asList(changeColor,
            changeSize)),
            model.getAnimations());
  }

  /**
   * Test for getShapes() method.
   */
  @Test
  public void getShapesTest() {
    model.addShape(rectangle);
    model.addShape(oval);
    model.addAnimation(changeColor);
    model.addAnimation(changeSize);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(rectangle, oval)), model.getShapes());
  }
}