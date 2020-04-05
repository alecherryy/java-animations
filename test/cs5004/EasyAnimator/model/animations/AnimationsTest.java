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
 * A JUnit test class for the classes that extend AbstractAnimations,
 * which includes: ChangeColor, ChangeCoordinates and ChangeSize.
 */
public class AnimationsTest {
  private Coordinates c1;
  private Coordinates c2;
  private ChangeColor changeColor;
  private ChangeColor changeColor2;
  private ChangeColor changeColor3;
  private ChangeCoordinates changeCoordinates;
  private ChangeCoordinates changeCoordinates2;
  private ChangeCoordinates changeCoordinates3;
  private ChangeSize changeSize;
  private ChangeSize changeSize2;
  private ChangeSize changeSize3;
  private Shapes rectangle;
  private Shapes rectangle2;
  private Shapes rectangle3;
  private Shapes square;
  private Shapes oval;
  private Shapes oval2;
  private Shapes oval3;
  private Shapes oval4;
  private Shapes circle;

  @Before
  public void setup() {

    c1 = new Coordinates(0, 0);
    c2 = new Coordinates(115.5, 50.5);
    oval4 = new Oval("O2", 10, 15, 20, 25.5,
            Color.BLUE, c2);
    rectangle = new Rectangle("R", 5, 10, 1, 2, Color.BLUE, new Coordinates(25, 50));
    rectangle2 = new Rectangle("R", 0, 10, 10, 10, Color.BLACK, c1);
    rectangle3 = new Rectangle("R2", 10, 15, 20, 25.5, Color.BLUE, c2);
    square = new Rectangle("R", 2, 7, 10, 30, Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
            new Coordinates(0, 18.5));
    oval2 = new Oval("O", 0, 0, 3, 4, Color.BLACK,
            new Coordinates(0, 0));
    oval3 = new Oval("O", 0, 10, 10, 10, Color.BLACK,
            c1);
    oval4 = new Oval("O2", 10, 15, 20, 25.5,
            Color.BLUE, c2);
    circle = new Oval("C", 8, 13, 20, 30, Color.RED,
            new Coordinates(100, 200));
    changeColor = new ChangeColor(rectangle, 6, 9, Color.BLUE, Color.RED);
    changeColor2 = new ChangeColor(oval3, 5, 10, Color.BLACK, Color.RED);
    changeColor3 = new ChangeColor(rectangle2, 5, 10, Color.BLACK, Color.PINK);
    changeCoordinates = new ChangeCoordinates(square, 3, 7, new Coordinates(25,
            50), new Coordinates(20, 50));
    changeCoordinates2 = new ChangeCoordinates(rectangle2, 8, 10, c1, c2);
    changeCoordinates3 = new ChangeCoordinates(oval, 11, 17, c1, c2);
    changeSize = new ChangeSize(oval, 15, 20, 35, 18,
            40, 20);
    changeSize2 = new ChangeSize(oval4, 11, 12, 20, 25.5,
            15.5, 15.5);
    changeSize3 = new ChangeSize(rectangle3, 11, 12, 20.0, 25.5,
            15.5, 15.5);
  }

  /**
   * Constructor test for changeColor() method.
   */
  @Test
  public void constructorTest1() {
    Assert.assertEquals(rectangle, changeColor.getShape());
    Assert.assertEquals(6, changeColor.getStartTime());
    Assert.assertEquals(9, changeColor.getEndTime());
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
    Assert.assertEquals(35, changeSize.getOriginalWidth(), 0.1);
    Assert.assertEquals(18, changeSize.getOriginalHeight(), 0.1);
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
    Assert.assertEquals("X radius: 35.0, Y radius: 18.0", changeSize.getStartState());
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
            + "Shape R changes color from (0.0,0.0,1.0) to (1.0,0.0,0.0) from t=6 to "
            + "t=9", changeColor.getDescription());
    Assert.assertEquals(""
                    + "Shape R moves from (25.0, 50.0) to (20.0, 50.0) from t=3 to t=7",
            changeCoordinates.getDescription());
    Assert.assertEquals(""
            + "Shape O scales from X radius: 35.0, Y radius: 18.0 to X radius: "
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
    Assert.assertEquals("(0.0,0.0,1.0)",
            Utils.colorAsString(changeColor.getShape().getColor()));
    changeColor.implementAnimation(20);
//    Assert.assertEquals("(1.0,0.0,0.0)",
//            Utils.colorAsString(changeColor.getShape().getColor()));
//    Animations changeCol2 = new ChangeColor(circle, 0, 10, Color.GREEN, Color.RED);
//    Assert.assertEquals("(1.0,0.0,0.0)",
//            Utils.colorAsString(changeColor.getShape().getColor()));
//    changeCol2.implementAnimation(4);
//    Assert.assertEquals("(0.4,0.6,0.0)",
//            Utils.colorAsString(changeCol2.getShape().getColor()));
//    changeCol2.implementAnimation(20);
//    Assert.assertEquals("(0.4,0.6,0.0)",
//            Utils.colorAsString(changeCol2.getShape().getColor()));
//    Animations changeCol3 = new ChangeColor(oval2, 5, 10, Color.BLACK, Color.RED);
//    Assert.assertEquals("(0.0,0.0,0.0)",
//            Utils.colorAsString(changeCol3.getShape().getColor()));
//    changeCol3.implementAnimation(4);
//    Assert.assertEquals("(0.0,0.0,0.0)",
//            Utils.colorAsString(changeCol3.getShape().getColor()));
//    changeCol3.implementAnimation(7);
//    Assert.assertEquals("(0.4,0.0,0.0)",
//            Utils.colorAsString(changeCol3.getShape().getColor()));
//    changeCol3.implementAnimation(10);
//    Assert.assertEquals("(1.0,0.0,0.0)",
//            Utils.colorAsString(changeCol3.getShape().getColor()));
//    changeCol3.implementAnimation(12);
//    Assert.assertEquals("(1.0,0.0,0.0)",
//            Utils.colorAsString(changeCol3.getShape().getColor()));
  }

  /**
   * Test for implementAnimation() method for changeCoordinates().
   */
  @Test
  public void implementAnimationTest2() {
//    Assert.assertEquals("(0.0, 0.0)",
//            Utils.getPositionString(changeCoordinates.getShape().getPosition()));
//    changeCoordinates.implementAnimation(10);
//    Assert.assertEquals("(0.0, 0.0)",
//            Utils.getPositionString(changeCoordinates.getShape().getPosition()));
//
//    Animations change2 = new ChangeCoordinates(oval2, 8, 10,
//            new Coordinates(0.0, 0.0), new Coordinates(100, 50));
//    Assert.assertEquals("(0.0, 0.0)",
//            Utils.getPositionString(change2.getShape().getPosition()));
//    change2.implementAnimation(7);
//    Assert.assertEquals("(0.0, 0.0)",
//            Utils.getPositionString(change2.getShape().getPosition()));
//    change2.implementAnimation(9);
//    Assert.assertEquals("(50.0, 25.0)",
//            Utils.getPositionString(change2.getShape().getPosition()));
//    change2.implementAnimation(10);
//    Assert.assertEquals("(100.0, 50.0)",
//            Utils.getPositionString(change2.getShape().getPosition()));
//
//    Animations change3 = new ChangeCoordinates(rectangle, 0, 3,
//            new Coordinates(25.0, 50.0), new Coordinates(60.0, 50.0));
//    Assert.assertEquals("(25.0, 50.0)",
//            Utils.getPositionString(change3.getShape().getPosition()));
//    change3.implementAnimation(3);
//    Assert.assertEquals("(60.0, 50.0)",
//            Utils.getPositionString(change3.getShape().getPosition()));
  }


  /**
   * Test for implementAnimation() method for changeSize().
   */
  @Test
  public void implementAnimationTest3() {
//    Assert.assertEquals(""
//            + "X radius: 5.0, Y radius: 5.0", changeSize.getShape().getDimensions());
//    changeSize.implementAnimation(5);
//    Assert.assertEquals(""
//            + "X radius: 37.5, Y radius: 19.0", changeSize.getShape().getDimensions());
//    changeSize.implementAnimation(9);
//    Assert.assertEquals(""
//            + "X radius: 39.5, Y radius: 19.8", changeSize.getShape().getDimensions());
//    changeSize.implementAnimation(10);
//    Assert.assertEquals(""
//            + "X radius: 40.0, Y radius: 20.0", changeSize.getShape().getDimensions());
//
//    Animations change2 = new ChangeSize(oval2, 0, 10, 0,
//            0, 10, 10);
//    Assert.assertEquals(""
//            + "X radius: 3.0, Y radius: 4.0", change2.getShape().getDimensions());
//    change2.implementAnimation(5);
//    Assert.assertEquals(""
//            + "X radius: 5.0, Y radius: 5.0", change2.getShape().getDimensions());
//    change2.implementAnimation(10);
//    Assert.assertEquals(""
//            + "X radius: 10.0, Y radius: 10.0", change2.getShape().getDimensions());
//
//    Animations change3 = new ChangeSize(circle, 0, 5,
//            8, 13, 64, 100);
//    Assert.assertEquals(""
//            + "X radius: 20.0, Y radius: 30.0", change3.getShape().getDimensions());
//    change3.implementAnimation(3);
//    Assert.assertEquals(""
//            + "X radius: 41.6, Y radius: 65.2", change3.getShape().getDimensions());
//    change3.implementAnimation(5);
//    Assert.assertEquals(""
//            + "X radius: 64.0, Y radius: 100.0", change3.getShape().getDimensions());

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

    Assert.assertEquals(35, changeSize.getOriginalWidth(), 0.1);
    Assert.assertEquals(18, changeSize.getOriginalHeight(), 0.1);
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

    Assert.assertEquals(oval, changeSize.getShape());
    changeSize.resetShape(oval2);
    Assert.assertEquals(oval2, changeSize.getShape());
  }

  /**
   * Test for returning the correct svg tags.
   */
  @Test
  public void testToSVGTag() {
    // test for changing color
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"500.0ms\" "
                    + "dur=\"500.0ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" "
                    + "to=\"rgb(255,0,0)\" "
                    + "fill=\"freeze\" />\n",
            changeColor2.toSVGTag(10));
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"500.0ms\" dur=\"500.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,0,0)\" "
                    + "to=\"rgb(255,175,175)\" fill=\"freeze\" />\n",
            changeColor3.toSVGTag(10));

    // test for changing dimension
    Assert.assertEquals("<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\" attributeName=\"rx\" from=\"20.0\" to=\"15.5\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\" "
                    + "attributeName=\"ry\" from=\"25.5\" to=\"15.5\" fill=\"freeze\" />\n",
            changeSize2.toSVGTag(10));
    Assert.assertEquals("<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\" attributeName=\"width\" from=\"20.0\" to=\"15.5\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"1100.0ms\" "
                    + "dur=\"100.0ms\""
                    + " attributeName=\"height\" from=\"25.5\" to=\"15.5\" fill=\"freeze\" />\n",
            changeSize3.toSVGTag(10));

    // test for moving
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"x\" from=\"0.0\" to=\"115.5\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"y\" from=\"0.0\" to=\"50.5\" fill=\"freeze\" />\n",
            changeCoordinates2.toSVGTag(10));
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"1100.0ms\" dur=\"600.0ms\" "
                    + "attributeName=\"cx\" from=\"0.0\" to=\"115.5\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"1100.0ms\" dur=\"600.0ms\" "
                    + "attributeName=\"cy\" from=\"0.0\" to=\"50.5\" fill=\"freeze\" />\n",
            changeCoordinates3.toSVGTag(10));
  }

  /**
   * Test for returning the correct svg tags with looping.
   */
  @Test
  public void testToSVGTagWithLoop() {
    // test for changing color
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+500.0ms\" "
                    + "dur=\"500.0ms\" attributeName=\"fill\" from=\"rgb(0,0,0)\" "
                    + "to=\"rgb(255,0,0)\" "
                    + "fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"base.end\" "
                    + "dur=\"1ms\" attributeName=\"fill\" from=\"rgb(255,0,0)\" "
                    + "to=\"rgb(0,0,0)\" "
                    + "fill=\"freeze\" />\n",
            this.changeColor2.toSVGTagWithLoop(10));
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+500.0ms\" "
                    + "dur=\"500.0ms\" "
                    + "attributeName=\"fill\" from=\"rgb(0,0,0)\" "
                    + "to=\"rgb(255,175,175)\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
                    + "attributeName=\"fill\" from=\"rgb(255,175,175)\" "
                    + "to=\"rgb(0,0,0)\" fill=\"freeze\" />\n",
            this.changeColor3.toSVGTagWithLoop(10));
    // test for changing dimension
    Assert.assertEquals("<animate attributeType=\"xml\" type=\"scale\" "
                    + "begin=\"base.begin+1100.0ms\" "
                    + "dur=\"100.0ms\" attributeName=\"rx\" from=\"20.0\" to=\"15.5\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.begin+1100.0ms\" "
                    + "dur=\"100.0ms\" "
                    + "attributeName=\"ry\" from=\"25.5\" to=\"15.5\" fill=\"freeze\" />\n"

                    + "<animate attributeType=\"xml\" type=\"scale\" "
                    + "begin=\"base.end\" "
                    + "dur=\"1ms\" attributeName=\"rx\" from=\"15.5\" to=\"20.0\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.end\" "
                    + "dur=\"1ms\" "
                    + "attributeName=\"ry\" from=\"15.5\" to=\"25.5\" fill=\"freeze\" />\n",
            this.changeSize2.toSVGTagWithLoop(10));
    Assert.assertEquals("<animate attributeType=\"xml\" type=\"scale\" "
                    + "begin=\"base.begin+1100.0ms\" "
                    + "dur=\"100.0ms\" attributeName=\"width\" from=\"20.0\" to=\"15.5\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.begin+1100.0ms\" "
                    + "dur=\"100.0ms\""
                    + " attributeName=\"height\" from=\"25.5\" to=\"15.5\" fill=\"freeze\" />\n"

                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.end\" "
                    + "dur=\"1ms\" attributeName=\"width\" from=\"15.5\" to=\"20.0\" "
                    + "fill=\"freeze\" /> \n"
                    + "<animate attributeType=\"xml\" type=\"scale\" begin=\"base.end\" "
                    + "dur=\"1ms\""
                    + " attributeName=\"height\" from=\"15.5\" to=\"25.5\" fill=\"freeze\" />\n",
            this.changeSize3.toSVGTagWithLoop(10));

    // test for moving
    Assert.assertEquals("<animate attributeType=\"xml\" begin=\"base.begin+800.0ms\" "
                    + "dur=\"200.0ms\" "
                    + "attributeName=\"x\" from=\"0.0\" to=\"115.5\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"base.begin+800.0ms\" dur=\"200.0ms\" "
                    + "attributeName=\"y\" from=\"0.0\" to=\"50.5\" fill=\"freeze\" />\n"

                    + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
                    + "attributeName=\"x\" from=\"115.5\" to=\"0.0\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
                    + "attributeName=\"y\" from=\"50.5\" to=\"0.0\" fill=\"freeze\" />\n",
            this.changeCoordinates2.toSVGTagWithLoop(10));
    Assert.assertEquals(""
                    + "<animate attributeType=\"xml\" begin=\"base.begin+1100.0ms\" dur=\"600.0ms\""
                    + " attributeName=\"cx\" from=\"0.0\" to=\"115.5\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"base.begin+1100.0ms\" dur=\"600.0ms\""
                    + " attributeName=\"cy\" from=\"0.0\" to=\"50.5\" fill=\"freeze\" />\n"

                    + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
                    + "attributeName=\"cx\" from=\"115.5\" to=\"0.0\" fill=\"freeze\" />\n"
                    + "<animate attributeType=\"xml\" begin=\"base.end\" dur=\"1ms\" "
                    + "attributeName=\"cy\" from=\"50.5\" to=\"0.0\" fill=\"freeze\" />\n",
            this.changeCoordinates3.toSVGTagWithLoop(10));
  }
}