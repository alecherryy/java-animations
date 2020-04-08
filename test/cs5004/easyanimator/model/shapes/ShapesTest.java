package cs5004.easyanimator.model.shapes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

/**
 * A JUnit test class for the AbstractShape class and the classes that extend it (Oval and
 * Rectangle). Tests are separated according to which method they refer to, and whether they are
 * tested on an Oval or Rectangle object.
 */
public class ShapesTest {
  private Shapes rectangle;
  private Shapes square;
  private Shapes oval;
  private Shapes circle;

  /**
   * Class set up.
   */
  @Before
  public void setup() {
    rectangle = new Rectangle("R", 5, 10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    square = new Rectangle("S", 2, 7, 10, 30,
        Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
        new Coordinates(0, 18.5));
    circle = new Oval("C", 8, 13, 20, 30, Color.RED,
        new Coordinates(100, 200));
  }

  /**
   * These are tests for the AbstractShape constructor, which is called in
   * the Rectangle and Oval sub-classes.
   */
  @Test
  public void rectangleConstructorTest() {
    // for shape type rectangle.
    Assert.assertEquals("R", rectangle.getName());
    Assert.assertEquals(ShapeType.RECTANGLE, rectangle.getType());
    Assert.assertEquals(1.0, rectangle.getD1(), 0.01);
    Assert.assertEquals(2.0, rectangle.getD2(), 0.01);
    Assert.assertEquals(Color.BLUE, rectangle.getColor());
    Assert.assertEquals("(1.0, 2.0)", rectangle.getSize().toString());
    Assert.assertEquals("(25.0, 50.0)", rectangle.getPosition().toString());
    Assert.assertEquals(5, rectangle.getAppearTime());
    Assert.assertEquals(10, rectangle.getDisappearTime());
  }

  /**
   * Test that the Oval class creates a correct Oval object when the
   * proper parameters are passed to it.
   */
  @Test
  public void ovalConstructorTest() {
    Assert.assertEquals("O", oval.getName());
    Assert.assertEquals(ShapeType.OVAL, oval.getType());
    Assert.assertEquals(5.0, oval.getD1(), 0.01);
    Assert.assertEquals(5.0, oval.getD2(), 0.01);
    Assert.assertEquals(Color.GREEN, oval.getColor());
    Assert.assertEquals("(5.0, 5.0)", oval.getSize().toString());
    Assert.assertEquals("(0.0, 18.5)", oval.getPosition().toString());
    Assert.assertEquals(10, oval.getAppearTime());
    Assert.assertEquals(20, oval.getDisappearTime());
  }

  /**
   * These are exception tests for the AbstractShape constructor, which is called
   * in the Rectangle and Oval sub-classes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void rectangleConstructorExceptionTest() {
    // negative width.
    square = new Rectangle("S", 17, 18, -13, 75,
        Color.RED, new Coordinates(0, 0));
    // negative height.
    rectangle = new Rectangle("S", 29, 39, 24,
        -928475, Color.BLUE,
        new Coordinates(0, 0));
    // negative appear time.
    rectangle = new Rectangle("R", -5, 10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    // negative disappear time.
    rectangle = new Rectangle("R", 5, -10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    // null name.
    rectangle = new Rectangle(null, 5, 10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    // empty name string.
    rectangle = new Rectangle("", 5, 10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    // disappear time less than appear time.
    rectangle = new Rectangle("S", 29, 10, 24,
        26, Color.BLUE,
        new Coordinates(0, 0));
    // null color.
    rectangle = new Rectangle("R", 5, 10, 1, 2,
        null, new Coordinates(25, 50));
  }

  /**
   * Test that the constructor in the Oval class throws the correct exceptions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void ovalConstructorExceptionTest() {
    // negative x-radius.
    circle = new Oval("C", 8, 13, -20, 30,
        Color.RED, new Coordinates(100, 200));
    // negative y-radius.
    circle = new Oval("C", 8, 13, 20, -30,
        Color.RED, new Coordinates(100, 200));
    // empty name string.
    oval = new Oval("", 0, 5, 36, 18, Color.GREEN,
        new Coordinates(0, 18.465));
    // null name string.
    oval = new Oval(null, 3, 10, 36, 18,
        Color.GREEN, new Coordinates(0, 18.465));
    // negative appear time.
    oval = new Oval("O", -3, 10, 36, 18,
        Color.GREEN, new Coordinates(0, 18.465));
    // negative disappear time.
    oval = new Oval("O", 3, -10, 36, 18,
        Color.GREEN, new Coordinates(0, 18.465));
    // null color.
    oval = new Oval("O", 20, 35, 36, 18,
        null, new Coordinates(0, 18.465));
    // disappear time less than appear time.
    oval = new Oval("O", 5, 1, 36, 18,
        Color.GREEN, new Coordinates(0, 18.465));

  }

  /**
   * Test for changeColor() method.
   */

  @Test
  public void testChangeColor() {
    circle = new Oval("C", 10, 20, 12.24, 12.24,
        Color.RED, new Coordinates(0, 0));
    circle.changeColor(Color.BLUE);
    Assert.assertEquals(Color.BLUE, circle.getColor());
    rectangle = new Rectangle("R", 0, 10, 12.24,
        12.876, Color.GREEN,
        new Coordinates(0, 0));
    rectangle.changeColor(Color.RED);
    Assert.assertEquals(Color.RED, rectangle.getColor());
    square = new Rectangle("S", 20, 50, 53, 53,
        Color.BLUE, new Coordinates(14, 82.3));
    square.changeColor(Color.GREEN);
    Assert.assertEquals(Color.GREEN, square.getColor());
  }

  /**
   * Test for changeD1() method.
   */
  @Test
  public void testChangeD1() {
    // rectangle shape.
    rectangle = new Rectangle("R", 0, 10, 12.24,
        12.876, Color.RED,
        new Coordinates(0, 0));
    rectangle.changeD1(12.23);
    Assert.assertEquals(12.23, rectangle.getD1(), 0.001);
    // oval shape.
    oval = new Oval("O", 20, 30, 123, 56.32,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    oval.changeD1(1);
    Assert.assertEquals(1.0, oval.getD1(), 0.001);
  }

  /**
   * Test that changeD1() method throws an exception if the new D1 is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeD1Exception() {
    // rectangle shape.
    rectangle = new Rectangle("R", 0, 10, 12.24,
        12.876, Color.RED,
        new Coordinates(0, 0));
    rectangle.changeD1(-5);
    // oval shape.
    oval = new Oval("O", 20, 30, 123, 56.32,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    oval.changeD1(-5);
  }

  /**
   * Test for changeD2() method.
   */
  @Test
  public void testChangeD2() {
    // rectangle shape.
    square = new Rectangle("S", 10, 20, 98, 98,
        Color.RED, new Coordinates(0, 0));
    square.changeD2(75.23);
    Assert.assertEquals(75.23, square.getD2(), 0.001);
    // oval shape.
    circle = new Oval("C", 30, 40, 12, 12,
        Color.GREEN,
        new Coordinates(-12.35, 28.3475));
    circle.changeD2(0.987);
    Assert.assertEquals(0.987, circle.getD2(), 0.001);
  }

  /**
   * Test that changeD2() method throws an exception if the new D2 is negative.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testChangeD2Exception() {
    // rectangle shape.
    square = new Rectangle("S", 10, 20, 98, 98,
        Color.RED, new Coordinates(0, 0));
    square.changeD2(-1);
    // oval shape.
    circle = new Oval("C", 30, 40, 12, 12,
        Color.GREEN,
        new Coordinates(-12.35, 28.3475));
    circle.changeD2(-2);
  }

  /**
   * Test for changePosition() method.
   */
  @Test
  public void testChangePosition() {
    // rectangle shape.
    rectangle = new Rectangle("R", 0, 3, 100, 50,
        Color.BLUE, new Coordinates(25, 50));
    rectangle.changePosition(new Coordinates(0, 23.12));
    Assert.assertEquals("(0.0, 23.12)", rectangle.getPosition().toString());
    square = new Rectangle("S", 10, 20, 98, 98,
        Color.RED, new Coordinates(0, 0));
    square.changePosition(new Coordinates(-148.70, -2.88));
    Assert.assertEquals("(-148.7, -2.88)", square.getPosition().toString());
    // oval shape.
    circle = new Oval("C", 15, 37, 12, 12,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changePosition(new Coordinates(0, 0));
    Assert.assertEquals("(0.0, 0.0)", circle.getPosition().toString());
  }

  /**
   * Test for changeAppearTime() method.
   */
  @Test
  public void testChangeAppearTime() {
    // rectangle shape.
    rectangle = new Rectangle("R", 0, 3, 100, 50,
        Color.BLUE, new Coordinates(25, 50));
    rectangle.changeAppearTime(1);
    Assert.assertEquals(1, rectangle.getAppearTime());
    // oval shape.
    circle = new Oval("C", 15, 37, 12, 12,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changeAppearTime(20);
    Assert.assertEquals(20, circle.getAppearTime());
  }

  /**
   * Test that changeAppearTime() method throws an exception if appear is
   * negative or if the new appear time is greater than the disappear time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeAppearExceptionTest() {
    // rectangle shape; negative appear time.
    rectangle = new Rectangle("R", 0, 3, 100, 50,
        Color.BLUE, new Coordinates(25, 50));
    rectangle.changeAppearTime(-1);
    // rectangle shape; appear time is greater than disappear time.
    square = new Rectangle("S", 2, 7, 10, 30,
        Color.RED, new Coordinates(0, 0));
    square.changeAppearTime(8);
    // oval shape; negative appear time.
    circle = new Oval("C", 15, 37, 12, 12,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changeAppearTime(-20);
    // oval shape; appear time is greater than disappear time.
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
        new Coordinates(0, 18.5));
    oval.changeAppearTime(31);
  }

  /**
   * Test for changeDisappearTime() method.
   */
  @Test
  public void testChangeDisappearTime() {
    // rectangle shape.
    rectangle = new Rectangle("R", 0, 3, 100, 50,
        Color.BLUE, new Coordinates(25, 50));
    rectangle.changeDisappearTime(5);
    Assert.assertEquals(5, rectangle.getDisappearTime());
    // oval shape.
    circle = new Oval("C", 15, 37, 12, 12,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changeDisappearTime(40);
    Assert.assertEquals(40, circle.getDisappearTime());
  }

  /**
   * Test that changeDisappearTime() method throws an exception if disappear is negative or if the
   * new disappear time is less than the appear time.
   */
  @Test(expected = IllegalArgumentException.class)
  public void changeDisappearExceptionTest() {
    // rectangle shape; negative disappear time.
    rectangle = new Rectangle("R", 0, 3, 100, 50,
        Color.BLUE, new Coordinates(25, 50));
    rectangle.changeDisappearTime(-5);
    // rectangle shape; disappear time is less than the appear time.
    square = new Rectangle("S", 2, 7, 10, 30,
        Color.RED, new Coordinates(0, 0));
    square.changeDisappearTime(1);
    // oval shape; negative disappear time.
    circle = new Oval("C", 15, 37, 12, 12,
        Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changeDisappearTime(-20);
    // oval shape; disappear time is less than the appear time.
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
        new Coordinates(0, 18.5));
    oval.changeAppearTime(5);
  }

  /**
   * Test for getDimensions().
   */
  @Test
  public void testGetDimensions() {
    // oval shape.
    Assert.assertEquals("X radius: 20.0, Y radius: 30.0", circle.getDimensions());
    Assert.assertEquals("X radius: 5.0, Y radius: 5.0", oval.getDimensions());
    // rectangle shape.
    Assert.assertEquals("Width: 1.0, Height: 2.0", rectangle.getDimensions());
    Assert.assertEquals("Width: 10.0, Height: 30.0", square.getDimensions());
  }

  /**
   * Test for getDisplayValue().
   */
  @Test
  public void testGetDisplayValue() {
    // oval shape.
    Assert.assertEquals(true, circle.getDisplayValue());
    Assert.assertEquals(true, oval.getDisplayValue());
    // rectangle shape.
    Assert.assertEquals(true, rectangle.getDisplayValue());
    Assert.assertEquals(true, square.getDisplayValue());
  }

  /**
   * Test for changeDisplayValue().
   */
  @Test
  public void testChangeDisplayValue() {
    // oval shape.
    circle.changeDisplayValue(false);
    Assert.assertEquals(false, circle.getDisplayValue());
    oval.changeDisplayValue(false);
    Assert.assertEquals(false, oval.getDisplayValue());
    // rectangle shape.
    rectangle.changeDisplayValue(false);
    Assert.assertEquals(false, rectangle.getDisplayValue());
    square.changeDisplayValue(false);
    Assert.assertEquals(false, square.getDisplayValue());
  }

  /**
   * Test for getDescription() method.
   */
  @Test
  public void testGetDescription() {
    // oval shape.
    Assert.assertEquals("Name: O\n"
        + "Type: oval\n"
        + "Center: (0.0, 18.5), X radius: 5.0, Y radius: 5.0, Color: (0.0,1.0,0.0)\n"
        + "Appears at t=10s\n"
        + "Disappears at t=20s\n", oval.getDescription());
    Assert.assertEquals("Name: C\n"
        + "Type: oval\n"
        + "Center: (100.0, 200.0), X radius: 20.0, Y radius: 30.0, Color: (1.0,0.0,0.0)\n"
        + "Appears at t=8s\n"
        + "Disappears at t=13s\n", circle.getDescription());
    // rectangle shape.
    Assert.assertEquals("Name: R\n"
        + "Type: rectangle\n"
        + "Min corner: (25.0, 50.0), Width: 1.0, Height: 2.0, Color: (0.0,0.0,1.0)\n"
        + "Appears at t=5s\n"
        + "Disappears at t=10s\n", rectangle.getDescription());
    Assert.assertEquals("Name: S\n"
        + "Type: rectangle\n"
        + "Min corner: (0.0, 0.0), Width: 10.0, Height: 30.0, Color: (1.0,0.0,0.0)\n"
        + "Appears at t=2s\n"
        + "Disappears at t=7s\n", square.getDescription());
  }
}