package cs5004.easyanimator.model.shapes;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

public class ShapeImplTest {
  private Shape rectangle;
  private Shape square;
  private Shape oval;
  private Shape circle;

  /**
   * This is a test for ShapeImpl class constructor.
   */
  @Test
  public void classConstructor() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, Color.BLUE, new Pair(25, 50));
    Assert.assertEquals(ShapeType.RECTANGLE, rectangle.getType());
    Assert.assertEquals("(25.0, 50.0)", rectangle.getPosition().toString());
    Assert.assertEquals(100.0, rectangle.getWidth(), 0.01);
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, Color.RED, new Pair(0, 0));
    Assert.assertEquals(ShapeType.SQUARE, square.getType());
    Assert.assertEquals("(0.0, 0.0)", square.getPosition().toString());
    Assert.assertEquals(75.0, square.getWidth(), 0.01);
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, Color.GREEN, new Pair(0, 18.465));
    Assert.assertEquals(ShapeType.OVAL, oval.getType());
    Assert.assertEquals("(0.0, 18.465)", oval.getPosition().toString());
    Assert.assertEquals(36, oval.getWidth(), 0.01);
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, Color.BLUE, new Pair(200, 199.24));
    Assert.assertEquals(ShapeType.CIRCLE, circle.getType());
    Assert.assertEquals("(200.0, 199.24)", circle.getPosition().toString());
    Assert.assertEquals(50, circle.getWidth(), 0.01);
  }

  /**
   * This is a test for ShapeImpl class constructor adjusting the Shape Type
   * based on the given width, height and Shape Type.
   */
  @Test
  public void classConstructorShapeType() {
    square = new ShapeImpl("S", ShapeType.RECTANGLE, 50, 50, Color.BLUE, new Pair(25, 50));
    Assert.assertEquals(ShapeType.SQUARE, square.getType());
    circle = new ShapeImpl("C", ShapeType.OVAL, 121, 121, Color.RED, new Pair(200, 199.24));
    Assert.assertEquals(ShapeType.CIRCLE, circle.getType());
  }

  /**
   * Test for class constructors exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testExceptions() {
    oval = new ShapeImpl("", ShapeType.OVAL, 36, 18, Color.GREEN, new Pair(0, 18.465));
    oval = new ShapeImpl(null, ShapeType.OVAL, 36, 18, Color.GREEN, new Pair(0, 18.465));
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, null, new Pair(0, 18.465));
    circle = new ShapeImpl("C", ShapeType.OVAL, 121, 121, null, new Pair(200, 199.24));
    square = new ShapeImpl("S", ShapeType.SQUARE, -13, 75, Color.RED, new Pair(0, 0));
    square = new ShapeImpl("S", ShapeType.SQUARE, -0.999, 75, Color.RED, new Pair(0, 0));
    square = new ShapeImpl("S", ShapeType.SQUARE, 24, -928475, Color.BLUE, new Pair(0, 0));
  }

  /**
   * Test for getName() method.
   */
  @Test
  public void testGetName() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, Color.BLUE, new Pair(25, 50));
    Assert.assertEquals("R", rectangle.getName());
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, Color.RED, new Pair(0, 0));
    Assert.assertEquals("S", square.getName());
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, Color.GREEN, new Pair(0, 18.465));
    Assert.assertEquals("O", oval.getName());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, Color.BLUE, new Pair(200, 199.24));
    Assert.assertEquals("C", circle.getName());
  }

  /**
   * Test for getColor() method.
   */
  @Test
  public void testGetColor() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, Color.BLUE, new Pair(25, 50));
    Assert.assertEquals(Color.BLUE, rectangle.getColor());
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, Color.RED, new Pair(0, 0));
    Assert.assertEquals(Color.RED, square.getColor());
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, Color.GREEN, new Pair(0, 18.465));
    Assert.assertEquals(ShapeType.OVAL, oval.getType());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, Color.BLUE, new Pair(200, 199.24));
    Assert.assertEquals(Color.BLUE, circle.getColor());
  }

  /**
   * Test for getPosition() method.
   */
  @Test
  public void testGetPosition() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, Color.BLUE, new Pair(25, 50));
    Assert.assertEquals("(25.0, 50.0)", rectangle.getPosition().toString());
    square = new ShapeImpl("S", ShapeType.SQUARE, 98, 98, Color.RED, new Pair(0, 0));
    Assert.assertEquals("(0.0, 0.0)", square.getPosition().toString());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 12, 12, Color.GREEN, new Pair(-12.35, 28.3475));
    Assert.assertEquals("(-12.35, 28.3475)", circle.getPosition().toString());
  }

  /**
   * Test for changeColor() method.
   */
  @Test
  public void testChangeColor() {
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 12.24, 12.24, Color.RED, new Pair(0, 0));
    circle.changeColor(Color.BLUE);
    Assert.assertEquals(Color.BLUE, circle.getColor());
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 12.24, 12.876, Color.GREEN, new Pair(0, 0));
    rectangle.changeColor(Color.RED);
    Assert.assertEquals(Color.RED, rectangle.getColor());
    square = new ShapeImpl("S", ShapeType.SQUARE, 53, 53, Color.BLUE, new Pair(14, 82.3));
    square.changeColor(Color.GREEN);
    Assert.assertEquals(Color.GREEN, square.getColor());
  }

  /**
   * Test for changeWidth() method.
   */
  @Test
  public void changeWidth() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 12.24, 12.876, Color.RED, new Pair(0, 0));
    rectangle.changeWidth(12.23);
    Assert.assertEquals(12.23, rectangle.getWidth(), 0.001);
    oval = new ShapeImpl("O", ShapeType.OVAL, 123, 56.32, Color.GREEN, new Pair(-12.35, 28.3475));
    oval.changeWidth(1);
    Assert.assertEquals(1.0, oval.getWidth(), 0.001);
  }

  /**
   * Test for changeHeight() method.
   */
  @Test
  public void testChangeHeight() {
    square = new ShapeImpl("S", ShapeType.SQUARE, 98, 98, Color.RED, new Pair(0, 0));
    square.changeHeight(75.23);
    Assert.assertEquals(75.23, square.getHeight(), 0.001);
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 12, 12, Color.GREEN, new Pair(-12.35, 28.3475));
    circle.changeHeight(0.987);
    Assert.assertEquals(0.987, circle.getHeight(), 0.001);
  }

  /**
   * Test for changePosition() method.
   */
  @Test
  public void testChangePosition() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, Color.BLUE, new Pair(25, 50));
    rectangle.changePosition(new Pair(0,23.12));
    Assert.assertEquals("(0.0, 23.12)", rectangle.getPosition().toString());
    square = new ShapeImpl("S", ShapeType.SQUARE, 98, 98, Color.RED, new Pair(0, 0));
    square.changePosition(new Pair(-148.70,-2.88));
    Assert.assertEquals("(-148.7, -2.88)", square.getPosition().toString());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 12, 12, Color.GREEN, new Pair(-12.35, 28.3475));
    circle.changePosition(new Pair(0,0));
    Assert.assertEquals("(0.0, 0.0)", circle.getPosition().toString());
  }

  /**
   * Test for toString() method.
   */
  @Test
  public void testToString() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, Color.BLUE, new Pair(25, 50));
    Assert.assertEquals("(0.0, 0.0, 1.0) Rectangle "
            + "with corner at (25.0, 50.0), width 100.0 "
            + "and height 50.0", rectangle.toString());
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, Color.RED, new Pair(0, 0));
    Assert.assertEquals("(1.0, 0.0, 0.0) Square "
            + "with corner at (0.0, 0.0), width 75.0 "
            + "and height 75.0", square.toString());
    oval = new ShapeImpl("O", ShapeType.OVAL, 36.123, 18.86, Color.GREEN, new Pair(0, 18.465));
    Assert.assertEquals("(0.0, 1.0, 0.0) Oval "
            + "with corner at (0.0, 18.465), width 36.123 "
            + "and height 18.86", oval.toString());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50.1, 50.03, Color.RED, new Pair(200, 199.24));
    Assert.assertEquals("(1.0, 0.0, 0.0) Circle "
            + "with corner at (200.0, 199.24), width 50.1 "
            + "and height 50.03", circle.toString());
  }
}