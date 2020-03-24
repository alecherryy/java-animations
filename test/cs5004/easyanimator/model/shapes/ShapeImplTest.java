package cs5004.easyanimator.model.shapes;

import org.junit.Assert;
import org.junit.Test;

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
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50));
    Assert.assertEquals(ShapeType.RECTANGLE, rectangle.getType());
    Assert.assertEquals("(25.0, 50.0)", rectangle.getPosition().toString());
    Assert.assertEquals(100.0, rectangle.getWidth(), 0.01);
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, "Red", new Pair(0, 0));
    Assert.assertEquals(ShapeType.SQUARE, square.getType());
    Assert.assertEquals("(0.0, 0.0)", square.getPosition().toString());
    Assert.assertEquals(75.0, square.getWidth(), 0.01);
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, "Green", new Pair(0, 18.465));
    Assert.assertEquals(ShapeType.OVAL, oval.getType());
    Assert.assertEquals("(0.0, 18.465)", oval.getPosition().toString());
    Assert.assertEquals(36, oval.getWidth(), 0.01);
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, "Red", new Pair(200, 199.24));
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
    square = new ShapeImpl("S", ShapeType.RECTANGLE, 50, 50, "Blue", new Pair(25, 50));
    Assert.assertEquals(ShapeType.SQUARE, square.getType());
    circle = new ShapeImpl("C", ShapeType.OVAL, 121, 121, "Red", new Pair(200, 199.24));
    Assert.assertEquals(ShapeType.CIRCLE, circle.getType());
  }

  /**
   * Test for class constructors exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testExceptions() {
    oval = new ShapeImpl("", ShapeType.OVAL, 36, 18, "Green", new Pair(0, 18.465));
    oval = new ShapeImpl(null, ShapeType.OVAL, 36, 18, "Green", new Pair(0, 18.465));
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, "", new Pair(0, 18.465));
    circle = new ShapeImpl("C", ShapeType.OVAL, 121, 121, null, new Pair(200, 199.24));
    square = new ShapeImpl("S", ShapeType.SQUARE, -13, 75, "Red", new Pair(0, 0));
    square = new ShapeImpl("S", ShapeType.SQUARE, -0.999, 75, "Red", new Pair(0, 0));
    square = new ShapeImpl("S", ShapeType.SQUARE, 24, -928475, "Red", new Pair(0, 0));
  }

  /**
   * Test for getName() method.
   */
  @Test
  public void testGetName() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50));
    Assert.assertEquals("R", rectangle.getName());
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, "Red", new Pair(0, 0));
    Assert.assertEquals("S", square.getName());
    oval = new ShapeImpl("O", ShapeType.OVAL, 36, 18, "Green", new Pair(0, 18.465));
    Assert.assertEquals("O", oval.getName());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, "Red", new Pair(200, 199.24));
    Assert.assertEquals("C", circle.getName());
  }

  /**
   * Test for toString() method.
   */
  @Test
  public void testToString() {
    rectangle = new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50));
    Assert.assertEquals("Blue Rectangle "
            + "with corner at (25.0, 50.0), width 100.0 "
            + "and height 50.0", rectangle.toString());
    square = new ShapeImpl("S", ShapeType.SQUARE, 75, 75, "Red", new Pair(0, 0));
    Assert.assertEquals("Red Square "
            + "with corner at (0.0, 0.0), width 75.0 "
            + "and height 75.0", square.toString());
    oval = new ShapeImpl("O", ShapeType.OVAL, 36.123, 18.86, "Green", new Pair(0, 18.465));
    Assert.assertEquals("Green Oval "
            + "with corner at (0.0, 18.465), width 36.123 "
            + "and height 18.86", oval.toString());
    circle = new ShapeImpl("C", ShapeType.CIRCLE, 50.1, 50.03, "Yellow", new Pair(200, 199.24));
    Assert.assertEquals("Yellow Circle "
            + "with corner at (200.0, 199.24), width 50.1 "
            + "and height 50.03", circle.toString());
  }
}