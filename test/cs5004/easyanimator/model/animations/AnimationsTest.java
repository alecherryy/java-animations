package cs5004.easyanimator.model.animations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * A JUnit test class for the classes that extend AbstractAnimations, which include: ChangeColor,
 * ChangeCoordinates, and ChangeSize.
 */

public class AnimationsTest {
  private ChangeColor changeColor;
  private ChangeCoordinates changeCoordinates;
  private ChangeSize changeSize;
  private Shape rectangle;
  private Shape square;
  private Shape oval;
  private Shape oval2;
  private Shape circle;

  @Before
  public void setup() {
    rectangle = new Rectangle("R", 5, 10, Color.BLUE, new Coordinates(25, 50));
    square = new Rectangle("R", 2, 7, Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, Color.GREEN, new Coordinates(0, 18.5));
    oval2 = new Oval("O", 0, 0, Color.BLACK, new Coordinates(0, 0));
    circle = new Oval("C", 8, 13, Color.RED, new Coordinates(100, 200));
    changeColor = new ChangeColor(rectangle, 10, 20, Color.BLUE, Color.RED);
    changeCoordinates = new ChangeCoordinates(square, 0, 5, new Coordinates(25, 50),
        new Coordinates(20, 50));
    changeSize = new ChangeSize(oval, 0, 10, 35, 18, 40, 20);
  }

  /**
   * Constructor test for ChangeColor.
   */
  @Test
  public void constructorTest1() {
    Assert.assertEquals(rectangle, changeColor.getShape());
    Assert.assertEquals(10, changeColor.getStartTime());
    Assert.assertEquals(20, changeColor.getEndTime());
    Assert.assertEquals(Color.BLUE, changeColor.getOriginalColor());
    Assert.assertEquals(Color.RED, changeColor.getNewColor());
    Assert.assertEquals(AnimationType.CHANGECOLOR, changeColor.getAnimationType());

  }

  /**
   * Constructor exception test for ChangeColor.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorExceptionTest1() {
    // end-time is less than start time.
    new ChangeColor(circle, 20, 10, Color.RED, Color.GREEN);
    // start time is negative.
    new ChangeColor(rectangle, -1, 5, Color.RED, Color.GREEN);
    // end time is negative.
    new ChangeColor(square, 5, -1, Color.RED, Color.GREEN);
    // both start and end time are negative.
    new ChangeColor(square, -5, -1, Color.RED, Color.GREEN);
    // original color is null.
    new ChangeColor(square, 2, 3, null, Color.GREEN);
    // new color is null.
    new ChangeColor(square, 2, 3, Color.RED, null);
    // new color is the same as original color.
    new ChangeColor(circle, 3, 5, Color.RED, Color.RED);
  }

  /**
   * Constructor test for ChangeCoordinates.
   */
  @Test
  public void constructorTest2() {
    Assert.assertEquals(square, changeCoordinates.getShape());
    Assert.assertEquals(0, changeCoordinates.getStartTime());
    Assert.assertEquals(5, changeCoordinates.getEndTime());
    Assert.assertEquals("(25.0, 50.0)", changeCoordinates.getOriginalCoordinates().toString());
    Assert.assertEquals("(20.0, 50.0)", changeCoordinates.getNewCoordinates().toString());
    Assert.assertEquals(AnimationType.CHANGECOORDINATES, changeCoordinates.getAnimationType());

  }

  /**
   * Constructor exception test for ChangeCoordinates.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorExceptionTest2() {
    // end-time is less than start time.
    new ChangeSize(rectangle, 19, 17, 10, 20, 20, 39);
    // start time is negative.
    new ChangeSize(square, -2, 5, 2, 4, 6, 8);
    // end time is negative.
    new ChangeSize(oval, 5, -2, 4, 8, 6, 8);
    // both start and end time are negative.
    new ChangeSize(circle, -5, -2, 4, 8, 6, 8);
  }

  /**
   * Constructor test for ChangeSize.
   */
  @Test
  public void constructorTest3() {
    Assert.assertEquals(oval, changeSize.getShape());
    Assert.assertEquals(0, changeSize.getStartTime());
    Assert.assertEquals(10, changeSize.getEndTime());
    Assert.assertEquals(35, changeSize.getOriginalWidth(), 0.1);
    Assert.assertEquals(18, changeSize.getOriginalHeight(), 0.1);
    Assert.assertEquals(40, changeSize.getNewWidth(), 0.1);
    Assert.assertEquals(20, changeSize.getNewHeight(), 0.1);
    Assert.assertEquals(AnimationType.CHANGESIZE, changeSize.getAnimationType());
  }

  /**
   * Constructor exception test for ChangeSize.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorExceptionTest3() {
    // end-time is less than start time.
    new ChangeSize(rectangle, 10, 0, 20, 50, 10, 30);
    // start time is negative.
    new ChangeSize(square, -1, 5, 14, 14, 15, 15);
    // end time is negative.
    new ChangeSize(circle, 2, -3, 19, 20, 20, 30);
    // both start and end time are negative.
    new ChangeSize(oval, -2, -1, 21, 20, 20, 30);
  }

  /**
   * TESTING getChange() method.
   */
  @Test
  public void getChangeTest() {
    Assert.assertEquals("changes color ", changeColor.getChange());
    Assert.assertEquals("moves ", changeCoordinates.getChange());
    Assert.assertEquals("scales ", changeSize.getChange());
  }

  /**
   * TESTING getStartState() method.
   */
  @Test
  public void getStartStateTest() {
    Assert.assertEquals("(0.0,0.0,1.0)", changeColor.getStartState());
    Assert.assertEquals("(25.0,50.0)", changeCoordinates.getStartState());
    Assert.assertEquals("X radius: 35.0, Y radius: 18.0", changeSize.getStartState());
  }

  /**
   * TESTING getEndState() method.
   */
  @Test
  public void getEndStateTest() {
    Assert.assertEquals("(1.0,0.0,0.0)", changeColor.getEndState());
    Assert.assertEquals("(20.0,50.0)", changeCoordinates.getEndState());
    Assert.assertEquals("X radius: 40.0, Y radius: 20.0", changeSize.getEndState());
  }

  /**
   * TESTING getDescription() method.
   */
  @Test
  public void getDescriptionTest() {
    Assert.assertEquals("Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=10 to " +
        "t=20", changeColor.getDescription());
    Assert.assertEquals("Shape R moves from (25.0, 50.0) to (20.0, 50.0) from t=0 to t=5",
        changeCoordinates.getDescription());
    Assert.assertEquals("Shape O scales from X radius: 35.0, Y radius: 18.0 to X radius: " +
        "40.0, Y radius: 20.0 from t=0 to t=10", changeSize.getDescription());
  }

  /**
   * TESTING implementAnimation() method for ChangeColor.
   */
  @Test
  public void implementAnimationTest1() {
    Assert.assertEquals("(0.0,0.0,1.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(10);
    Assert.assertEquals("(0.0,0.0,1.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(12);
    Assert.assertEquals("(0.2,0.0,0.8)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(20);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));

    Animations changeCol2 = new ChangeColor(circle, 0, 10, Color.GREEN, Color.RED);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeCol2.implementAnimation(4);
    Assert.assertEquals("(0.4,0.6,0.0)",
        Utils.colorAsString(changeCol2.getShape().getColor()));
    changeCol2.implementAnimation(20);
    Assert.assertEquals("(0.4,0.6,0.0)",
        Utils.colorAsString(changeCol2.getShape().getColor()));

    Animations changeCol3 = new ChangeColor(oval2, 5, 10, Color.BLACK, Color.RED);
    Assert.assertEquals("(0.0,0.0,0.0)",
        Utils.colorAsString(changeCol3.getShape().getColor()));
    changeCol3.implementAnimation(4);
    Assert.assertEquals("(0.0,0.0,0.0)",
        Utils.colorAsString(changeCol3.getShape().getColor()));
    changeCol3.implementAnimation(7);
    Assert.assertEquals("(0.4,0.0,0.0)",
        Utils.colorAsString(changeCol3.getShape().getColor()));
    changeCol3.implementAnimation(10);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeCol3.getShape().getColor()));
    changeCol3.implementAnimation(12);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeCol3.getShape().getColor()));
  }

  /**
   * Test for implementAnimation() method for ChangeCoordinates.
   */
  @Test
  public void implementAnimationTest2() {
    Assert.assertEquals("(0.0, 0.0)",
        Utils.getPositionString(changeCoordinates.getShape().getPosition()));
    changeCoordinates.implementAnimation(10);
    Assert.assertEquals("(0.0, 0.0)",
        Utils.getPositionString(changeCoordinates.getShape().getPosition()));

    Animations change2 = new ChangeCoordinates(oval2, 8, 10, new Coordinates(0.0, 0.0),
        new Coordinates(100, 50));
    Assert.assertEquals("(0.0, 0.0)",
        Utils.getPositionString(change2.getShape().getPosition()));
    change2.implementAnimation(7);
    Assert.assertEquals("(0.0, 0.0)",
        Utils.getPositionString(change2.getShape().getPosition()));
    change2.implementAnimation(9);
    Assert.assertEquals("(50.0, 25.0)",
        Utils.getPositionString(change2.getShape().getPosition()));
    change2.implementAnimation(10);
    Assert.assertEquals("(100.0, 50.0)",
        Utils.getPositionString(change2.getShape().getPosition()));

    Animations change3 = new ChangeCoordinates(rectangle, 0, 3, new Coordinates(25.0, 50.0),
        new Coordinates(60.0, 50.0));
    Assert.assertEquals("(25.0, 50.0)",
        Utils.getPositionString(change3.getShape().getPosition()));
    change3.implementAnimation(3);
    Assert.assertEquals("(60.0, 50.0)",
        Utils.getPositionString(change3.getShape().getPosition()));
  }


  /**
   * Test for implementAnimation() method for ChangeSize.
   */
  @Test
  public void implementAnimationTest3() {
    Assert.assertEquals("X radius: 10.0, Y radius: 20.0", changeSize.getShape().getDimensions());
    changeSize.implementAnimation(5);
    Assert.assertEquals("X radius: 37.5, Y radius: 19.0", changeSize.getShape().getDimensions());
    changeSize.implementAnimation(9);
    Assert.assertEquals("X radius: 39.5, Y radius: 19.8", changeSize.getShape().getDimensions());
    changeSize.implementAnimation(10);
    Assert.assertEquals("X radius: 40.0, Y radius: 20.0", changeSize.getShape().getDimensions());

    Animations change2 = new ChangeSize(oval2, 0, 10, 0, 0, 10, 10);
    Assert.assertEquals("X radius: 0.0, Y radius: 0.0", change2.getShape().getDimensions());
    change2.implementAnimation(5);
    Assert.assertEquals("X radius: 5.0, Y radius: 5.0", change2.getShape().getDimensions());
    change2.implementAnimation(10);
    Assert.assertEquals("X radius: 10.0, Y radius: 10.0", change2.getShape().getDimensions());

    Animations change3 = new ChangeSize(circle, 0, 5, 8, 13, 64, 100);
    Assert.assertEquals("X radius: 8.0, Y radius: 13.0", change3.getShape().getDimensions());
    change3.implementAnimation(3);
    Assert.assertEquals("X radius: 41.6, Y radius: 65.2", change3.getShape().getDimensions());
    change3.implementAnimation(5);
    Assert.assertEquals("X radius: 64.0, Y radius: 100.0", change3.getShape().getDimensions());

  }

  /**
   * Test for changeField() method.
   */
  @Test
  public void changeFieldTest() {
    Assert.assertEquals(Color.BLUE, changeColor.getOriginalColor());
    Assert.assertEquals(Color.RED, changeColor.getNewColor());
    changeColor.changeField(rectangle);
    Assert.assertEquals(Color.RED, rectangle.getColor());

    Assert.assertEquals("(25.0, 50.0)", changeCoordinates.getOriginalCoordinates().toString());
    Assert.assertEquals("(20.0, 50.0)", changeCoordinates.getNewCoordinates().toString());
    changeCoordinates.changeField(square);
    Assert.assertEquals("(20.0, 50.0)", square.getPosition().toString());

    Assert.assertEquals(35, changeSize.getOriginalWidth(), 0.1);
    Assert.assertEquals(18, changeSize.getOriginalHeight(), 0.1);
    Assert.assertEquals(40, changeSize.getNewWidth(), 0.1);
    Assert.assertEquals(20, changeSize.getNewHeight(), 0.1);
    changeSize.changeField(oval);
    Assert.assertEquals(40, oval.getWidth(), 0.1);
    Assert.assertEquals(20, oval.getHeight(), 0.1);
  }

  /**
   * Test for resetShape() method.
   */
  @Test
  public void resetShapeTest() {
    Assert.assertEquals(rectangle, changeColor.getShape());
    changeColor.resetShape(circle);
    Assert.assertEquals(circle, changeColor.getShape());

    Assert.assertEquals(square, changeCoordinates.getShape());
    changeCoordinates.resetShape(oval);
    Assert.assertEquals(oval, changeCoordinates.getShape());

    Assert.assertEquals(oval, changeSize.getShape());
    changeSize.resetShape(oval2);
    Assert.assertEquals(oval2, changeSize.getShape());
  }
}
