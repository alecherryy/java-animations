package cs5004.EasyAnimator.model.animations;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs5004.EasyAnimator.model.Utils;
import cs5004.EasyAnimator.model.shapes.Coordinates;
import cs5004.EasyAnimator.model.shapes.Oval;
import cs5004.EasyAnimator.model.shapes.Rectangle;
import cs5004.EasyAnimator.model.shapes.Shapes;

/**
 * A JUnit test class for the classes that extend AbstractAnimations, which includes: ChangeColor,
 * ChangeCoordinates and ChangeSize.
 */
public class AnimationsTest {
  private ChangeColor changeColor;
  private ChangeCoordinates changeCoordinates;
  private ChangeSize changeSize;
  private Shapes rectangle;
  private Shapes square;
  private Shapes oval;
  private Shapes circle;

  @Before
  public void setup() {

    rectangle = new Rectangle("R", 5, 30, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    square = new Rectangle("R", 2, 7, 10, 30,
        Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
        new Coordinates(0, 18.5));
    circle = new Oval("C", 8, 13, 20, 30, Color.RED,
        new Coordinates(100, 200));

    changeColor = new ChangeColor(rectangle, 10, 30, Color.BLUE, Color.RED);
    changeCoordinates = new ChangeCoordinates(square, 3, 7, new Coordinates(25,
        50), new Coordinates(20, 50));
    changeSize = new ChangeSize(oval, 15, 20, 5, 5,
        40, 20);


  }

  /**
   * Constructor test for changeColor() method.
   */
  @Test
  public void constructorTest1() {
    Assert.assertEquals(rectangle, changeColor.getShape());
    Assert.assertEquals(10, changeColor.getStartTime());
    Assert.assertEquals(30, changeColor.getEndTime());
    Assert.assertEquals(Color.BLUE, changeColor.getOriginalColor());
    Assert.assertEquals(Color.RED, changeColor.getNewColor());
  }

  /**
   * Constructor exception test for changeColor() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorExceptionTest1() {
    // end-time is less than start time.
    new ChangeColor(circle, 20, 10, Color.RED, Color.GREEN);
    new ChangeColor(circle, 7, 10, Color.RED, Color.GREEN);
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
   * Constructor test for changeCoordinates() method.
   */
  @Test
  public void constructorTest2() {
    Assert.assertEquals(square, changeCoordinates.getShape());
    Assert.assertEquals(3, changeCoordinates.getStartTime());
    Assert.assertEquals(7, changeCoordinates.getEndTime());
    Assert.assertEquals("(25.0, 50.0)", changeCoordinates.getOriginalCoordinates().toString());
    Assert.assertEquals("(20.0, 50.0)", changeCoordinates.getNewCoordinates().toString());
    Assert.assertEquals(AnimationType.CHANGECOORDINATES, changeCoordinates.getAnimationType());
  }

  /**
   * Constructor exception test for changeCoordinates() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorExceptionTest2() {
    // end-time is less than start time.
    new ChangeSize(rectangle, 19, 17, 10, 20,
        20, 39);
    // start time is negative.
    new ChangeSize(square, -2, 5, 2, 4, 6,
        8);
    // end time is negative.
    new ChangeSize(oval, 5, -2, 4, 8, 6,
        8);
    // both start and end time are negative.
    new ChangeSize(circle, -5, -2, 4, 8, 6,
        8);
  }

  /**
   * Constructor test for ChangeSize() method.
   */
  @Test
  public void constructorTest3() {
    Assert.assertEquals(oval, changeSize.getShape());
    Assert.assertEquals(15, changeSize.getStartTime());
    Assert.assertEquals(20, changeSize.getEndTime());
    Assert.assertEquals(5, changeSize.getOriginalWidth(), 0.1);
    Assert.assertEquals(5, changeSize.getOriginalHeight(), 0.1);
    Assert.assertEquals(40, changeSize.getNewWidth(), 0.1);
    Assert.assertEquals(20, changeSize.getNewHeight(), 0.1);
    Assert.assertEquals(AnimationType.CHANGESIZE, changeSize.getAnimationType());
  }

  /**
   * Constructor exception test for ChangeSize() method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void constructorExceptionTest3() {
    // end-time is less than start time.
    new ChangeSize(rectangle, 10, 0, 20, 50,
        10, 30);
    // start time is negative.
    new ChangeSize(square, -1, 5, 14, 14,
        15, 15);
    // end time is negative.
    new ChangeSize(circle, 2, -3, 19, 20,
        20, 30);
    // both start and end time are negative.
    new ChangeSize(oval, -2, -1, 21, 20,
        20, 30);
  }

  /**
   * Test for getChange() method.
   */
  @Test
  public void getChangeTest() {
    Assert.assertEquals("changes color ", changeColor.getChange());
    Assert.assertEquals("moves ", changeCoordinates.getChange());
    Assert.assertEquals("scales ", changeSize.getChange());
  }

  /**
   * Test for getStartState() method.
   */
  @Test
  public void getStartStateTest() {
    Assert.assertEquals("(0.0,0.0,1.0)", changeColor.getStartState());
    Assert.assertEquals("(25.0, 50.0)", changeCoordinates.getStartState());
    Assert.assertEquals("X radius: 5.0, Y radius: 5.0", changeSize.getStartState());
  }

  /**
   * Test for getEndState() method.
   */
  @Test
  public void getEndStateTest() {
    Assert.assertEquals("(1.0,0.0,0.0)", changeColor.getEndState());
    Assert.assertEquals("(20.0, 50.0)", changeCoordinates.getEndState());
    Assert.assertEquals("X radius: 40.0, Y radius: 20.0", changeSize.getEndState());
  }

  /**
   * Test for getDescription() method.
   */
  @Test
  public void getDescriptionTest() {
    Assert.assertEquals(""
        + "Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=10 to "
        + "t=30", changeColor.getDescription());
    Assert.assertEquals(""
            + "Shape R moves from (25.0, 50.0) to (20.0, 50.0) from t=3 to t=7",
        changeCoordinates.getDescription());
    Assert.assertEquals(""
        + "Shape O scales from X radius: 5.0, Y radius: 5.0 to X radius: "
        + "40.0, Y radius: 20.0 from t=15 to t=20", changeSize.getDescription());
  }

  /**
   * Test for implementAnimation() method for ChangeColor.
   */
  @Test
  public void implementAnimationTest1() {
    Assert.assertEquals("(0.0,0.0,1.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(10);
    Assert.assertEquals("(0.0,0.0,1.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(12);
    Assert.assertEquals("(0.101960786,0.0,0.9019608)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(20);
    Assert.assertEquals("(0.5019608,0.0,0.5019608)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(30);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    Animations changeCol2 = new ChangeColor(circle, 10, 12, Color.RED, Color.GREEN);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeColor.getShape().getColor()));
    changeCol2.implementAnimation(10);
    Assert.assertEquals("(1.0,0.0,0.0)",
        Utils.colorAsString(changeCol2.getShape().getColor()));
    changeCol2.implementAnimation(12);
    Assert.assertEquals("(0.0,1.0,0.0)",
        Utils.colorAsString(changeCol2.getShape().getColor()));
  }

  /**
   * Test that implementAnimation() throws an exception when we try to change the color of an object
   * from a color that does not match its original color. For example, trying to change the color of
   * an object from blue to green when its original color is red.
   */
  @Test(expected = IllegalArgumentException.class)
  public void implementAnimationExceptionTest1() {
    Animations changeCol = new ChangeColor(circle, 9, 12, Color.BLUE, Color.GREEN);
    changeCol.implementAnimation(9);
    Animations changeCol2 = new ChangeColor(rectangle, 10, 15, Color.PINK, Color.BLUE);
    changeCol2.implementAnimation(10);
  }

  /**
   * Test for implementAnimation() method for ChangeCoordinates.
   */
  @Test
  public void implementAnimationTest2() {
    Assert.assertEquals("(0.0, 0.0)",
        Utils.getPositionString(changeCoordinates.getShape().getPosition()));
    changeCoordinates.implementAnimation(3);
    Assert.assertEquals("(25.0, 50.0)",
        Utils.getPositionString(changeCoordinates.getShape().getPosition()));
    changeCoordinates.implementAnimation(7);
    Assert.assertEquals("(20.0, 50.0)",
        Utils.getPositionString(changeCoordinates.getShape().getPosition()));
    Animations change2 = new ChangeCoordinates(oval, 11, 19,
        new Coordinates(0, 18.5), new Coordinates(2, 20));
    change2.implementAnimation(11);
    Assert.assertEquals("(0.0, 18.5)",
        Utils.getPositionString(change2.getShape().getPosition()));
    change2.implementAnimation(19);
    Assert.assertEquals("(2.0, 20.0)",
        Utils.getPositionString(change2.getShape().getPosition()));
  }

  /**
   * Test that implementAnimation() throws an exception when we try to change the coordinates of an
   * object from a coordinate that does not match its original position. For example, trying to
   * change the position of an object from (0.0, 0.0) when its original position is (0.0, 0.2).
   */
  @Test(expected = IllegalArgumentException.class)
  public void implementAnimationExceptionTest2() {
    // TODO make sure this throws an exception
    Animations changeCor = new ChangeCoordinates(rectangle, 5, 10,
        new Coordinates(0, 0), new Coordinates(5, 0));
    changeCor.implementAnimation(5);
    Animations changeCor2 = new ChangeCoordinates(oval, 12, 15,
        new Coordinates(5, 0), new Coordinates(7, 0));
    changeCor2.implementAnimation(12);
  }

  /**
   * Test for implementAnimation() method for ChangeSize.
   */
  @Test
  public void implementAnimationTest3() {
    // TODO make sure this throws an exception
    Assert.assertEquals(""
        + "X radius: 5.0, Y radius: 5.0", changeSize.getShape().getDimensions());
    changeSize.implementAnimation(15);
    Assert.assertEquals(""
        + "X radius: 5.0, Y radius: 5.0", changeSize.getShape().getDimensions());
    changeSize.implementAnimation(20);
    Assert.assertEquals(""
        + "X radius: 40.0, Y radius: 20.0", changeSize.getShape().getDimensions());

    Animations change2 = new ChangeSize(rectangle, 11, 14, 1,
        2, 40, 50);
    Assert.assertEquals(""
        + "Width: 1.0, Height: 2.0", change2.getShape().getDimensions());
    change2.implementAnimation(11);
    Assert.assertEquals(""
        + "Width: 1.0, Height: 2.0", change2.getShape().getDimensions());
    change2.implementAnimation(14);
    Assert.assertEquals(""
        + "Width: 40.0, Height: 50.0", change2.getShape().getDimensions());
  }

  /**
   * Test that implementAnimation() throws an exception when we try to change the size of an object
   * from a size that does not match its original size For example, trying to change the size of an
   * object from width 5, length 5 when its original size is width 2, size 5.
   */
  @Test(expected = IllegalArgumentException.class)
  public void implementAnimationExceptionTest3() {
    Animations change2 = new ChangeSize(rectangle, 5, 10, 100,
        100, 150, 150);
    Animations change3 = new ChangeSize(oval, 10, 20, 500, 300,
        10, 20);

  }


  /**
   * Test for updateField() method.
   */
  @Test
  public void updateFieldTest() {
    Assert.assertEquals(Color.BLUE, changeColor.getOriginalColor());
    Assert.assertEquals(Color.RED, changeColor.getNewColor());
    changeColor.updateField(rectangle);
    Assert.assertEquals(Color.RED, rectangle.getColor());

    Assert.assertEquals(""
        + "(25.0, 50.0)", changeCoordinates.getOriginalCoordinates().toString());
    Assert.assertEquals(""
        + "(20.0, 50.0)", changeCoordinates.getNewCoordinates().toString());
    changeCoordinates.updateField(square);
    Assert.assertEquals(""
        + "(20.0, 50.0)", square.getPosition().toString());

    Assert.assertEquals(5, changeSize.getOriginalWidth(), 0.1);
    Assert.assertEquals(5, changeSize.getOriginalHeight(), 0.1);
    Assert.assertEquals(40, changeSize.getNewWidth(), 0.1);
    Assert.assertEquals(20, changeSize.getNewHeight(), 0.1);
    changeSize.updateField(oval);
    Assert.assertEquals(40, oval.getD1(), 0.1);
    Assert.assertEquals(20, oval.getD2(), 0.1);
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
  }

  /**
   * Test for returning the correct svg tags.
   */
  @Test
  public void testToSVGTag() {
    // test for changing color
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"1000.0ms\" "
            + "dur=\"2000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" "
            + "to=\"rgb(255,0,0)\" "
            + "fill=\"freeze\" />\n",
        changeColor.toSVGTag(10));

    // test for changing dimension
    Assert.assertEquals("<animate attributeType=\"xml\" type=\"scale\" begin=\"1500.0ms\" "
            + "dur=\"500.0ms\" attributeName=\"rx\" from=\"5.0\" to=\"40.0\" "
            + "fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"1500.0ms\" "
            + "dur=\"500.0ms\" "
            + "attributeName=\"ry\" from=\"5.0\" to=\"20.0\" fill=\"freeze\" />\n",
        changeSize.toSVGTag(10));

    // test for moving
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"300.0ms\" dur=\"400.0ms\" "
            + "attributeName=\"x\" from=\"25.0\" to=\"20.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"300.0ms\" dur=\"400.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n",
        changeCoordinates.toSVGTag(10));
  }

  /**
   * Test for returning the correct svg tags with looping.
   */
  @Test
  public void testToSVGTagWithLoop() {
    // test for changing color
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+1000.0ms\" "
            + "dur=\"2000.0ms\" attributeName=\"fill\" from=\"rgb(0,0,255)\" "
            + "to=\"rgb(255,0,0)\" "
            + "fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"base.end\" "
            + "dur=\"1ms\" attributeName=\"fill\" from=\"rgb(255,0,0)\" "
            + "to=\"rgb(0,0,255)\" "
            + "fill=\"freeze\" />\n",
        this.changeColor.toSVGTagWithLoop(10));

    // test for changing dimension
    Assert.assertEquals("<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"base.begin+1500.0ms\" "
            + "dur=\"500.0ms\" attributeName=\"rx\" from=\"5.0\" to=\"40.0\" "
            + "fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.begin+1500.0ms\" "
            + "dur=\"500.0ms\" "
            + "attributeName=\"ry\" from=\"5.0\" to=\"20.0\" fill=\"freeze\" />\n"

            + "<animate attributeType=\"xml\" type=\"scale\" "
            + "begin=\"base.end\" "
            + "dur=\"1ms\" attributeName=\"rx\" from=\"40.0\" to=\"5.0\" "
            + "fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.end\" "
            + "dur=\"1ms\" "
            + "attributeName=\"ry\" from=\"20.0\" to=\"5.0\" fill=\"freeze\" />\n",
        this.changeSize.toSVGTagWithLoop(10));

    // test for moving
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+300.0ms\" "
            + "dur=\"400.0ms\" "
            + "attributeName=\"x\" from=\"25.0\" to=\"20.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"base.begin+300.0ms\" dur=\"400.0ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n"

            + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"x\" from=\"20.0\" to=\"25.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
            + "attributeName=\"y\" from=\"50.0\" to=\"50.0\" fill=\"freeze\" />\n",
        this.changeCoordinates.toSVGTagWithLoop(10));
  }
}