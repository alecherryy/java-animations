package cs5004.easyanimator.model.shapes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class ShapeTest {
  private Shape rectangle;
  private Shape square;
  private Shape oval;
  private Shape circle;

  /**
   * This is a test for ShapeImpl class constructor.
   */
  @Test
  public void classConstructor() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals(ShapeType.RECTANGLE, rectangle.getType());
    Assert.assertEquals("(25.0, 50.0)", rectangle.getPosition().toString());
    Assert.assertEquals(100.0, rectangle.getWidth(), 0.01);
    square = new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0));
    Assert.assertEquals(ShapeType.RECTANGLE, square.getType());
    Assert.assertEquals("(0.0, 0.0)", square.getPosition().toString());
    Assert.assertEquals(75.0, square.getWidth(), 0.01);
    oval = new Oval("O", 36, 18, Color.GREEN, new Coordinates(0, 18.465));
    Assert.assertEquals(ShapeType.OVAL, oval.getType());
    Assert.assertEquals("(0.0, 18.465)", oval.getPosition().toString());
    Assert.assertEquals(36, oval.getWidth(), 0.01);
    circle = new Oval("C", 50, 50, Color.BLUE, new Coordinates(200, 199.24));
    Assert.assertEquals(ShapeType.OVAL, circle.getType());
    Assert.assertEquals("(200.0, 199.24)", circle.getPosition().toString());
    Assert.assertEquals(50, circle.getWidth(), 0.01);
  }

  /**
   * This is a test for ShapeImpl class constructor adjusting the Shape Type
   * based on the given width, height and Shape Type.
   */
  @Test
  public void classConstructorShapeType() {
    square = new Rectangle("S", 50, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals(ShapeType.RECTANGLE, square.getType());
    circle = new Oval("C", 121, 121, Color.RED, new Coordinates(200, 199.24));
    Assert.assertEquals(ShapeType.OVAL, circle.getType());
  }

  /**
   * Test for class constructors exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testExceptions() {
    oval = new Oval("", 36, 18, Color.GREEN, new Coordinates(0, 18.465));
    oval = new Oval(null, 36, 18, Color.GREEN, new Coordinates(0, 18.465));
    oval = new Oval("O", 36, 18, null, new Coordinates(0, 18.465));
    circle = new Oval("C", 121, 121, null, new Coordinates(200, 199.24));
    square = new Rectangle("S", -13, 75, Color.RED, new Coordinates(0, 0));
    rectangle = new Rectangle("S", -0.999, 75, Color.RED, new Coordinates(0, 0));
    rectangle = new Rectangle("S", 24, -928475, Color.BLUE, new Coordinates(0, 0));
  }

  /**
   * Test for getName() method.
   */
  @Test
  public void testGetName() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals("R", rectangle.getName());
    square = new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0));
    Assert.assertEquals("S", square.getName());
    oval = new Oval("O", 36, 18, Color.GREEN, new Coordinates(0, 18.465));
    Assert.assertEquals("O", oval.getName());
    circle = new Oval("C", 50, 50, Color.BLUE, new Coordinates(200, 199.24));
    Assert.assertEquals("C", circle.getName());
  }

  /**
   * Test for getColor() method.
   */
  @Test
  public void testGetColor() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals(Color.BLUE, rectangle.getColor());
    square = new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0));
    Assert.assertEquals(Color.RED, square.getColor());
    oval = new Oval("O", 36, 18, Color.GREEN, new Coordinates(0, 18.465));
    Assert.assertEquals(ShapeType.OVAL, oval.getType());
    circle = new Oval("C", 50, 50, Color.BLUE, new Coordinates(200, 199.24));
    Assert.assertEquals(Color.BLUE, circle.getColor());
  }

  /**
   * Test for getPosition() method.
   */
  @Test
  public void testGetPosition() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals("(25.0, 50.0)", rectangle.getPosition().toString());
    square = new Rectangle("S", 98, 98, Color.RED, new Coordinates(0, 0));
    Assert.assertEquals("(0.0, 0.0)", square.getPosition().toString());
    circle = new Oval("C", 12, 12, Color.GREEN, new Coordinates(-12.35, 28.3475));
    Assert.assertEquals("(-12.35, 28.3475)", circle.getPosition().toString());
  }

  /**
   * Test for getSize() method.
   */
  @Test
  public void testGetSize() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals("(100.0, 50.0)", rectangle.getSize().toString());
    square = new Rectangle("S", 98, 98, Color.RED, new Coordinates(0, 0));
    Assert.assertEquals("(98.0, 98.0)", square.getSize().toString());
    circle = new Oval("C", 12, 12, Color.GREEN, new Coordinates(-12.35, 28.3475));
    Assert.assertEquals("(12.0, 12.0)", circle.getSize().toString());
  }

  /**
   * Test for changeColor() method.
   */
  @Test
  public void testChangeColor() {
    circle = new Oval("C", 12.24, 12.24, Color.RED, new Coordinates(0, 0));
    circle.changeColor(Color.BLUE);
    Assert.assertEquals(Color.BLUE, circle.getColor());
    rectangle = new Rectangle("R", 12.24, 12.876, Color.GREEN, new Coordinates(0, 0));
    rectangle.changeColor(Color.RED);
    Assert.assertEquals(Color.RED, rectangle.getColor());
    square = new Rectangle("S", 53, 53, Color.BLUE, new Coordinates(14, 82.3));
    square.changeColor(Color.GREEN);
    Assert.assertEquals(Color.GREEN, square.getColor());
  }

  /**
   * Test for changeWidth() method.
   */
  @Test
  public void changeWidth() {
    rectangle = new Rectangle("R", 12.24, 12.876, Color.RED, new Coordinates(0, 0));
    rectangle.changeWidth(12.23);
    Assert.assertEquals(12.23, rectangle.getWidth(), 0.001);
    oval = new Oval("O", 123, 56.32, Color.GREEN, new Coordinates(-12.35, 28.3475));
    oval.changeWidth(1);
    Assert.assertEquals(1.0, oval.getWidth(), 0.001);
  }

  /**
   * Set up shapes so we can test exceptions on their methods.
   */
  @Before
  public void setUp() {
    square = new Rectangle("S", 98, 98, Color.RED, new Coordinates(0, 0));
    rectangle = new Rectangle("R", 12.45, 29.03, Color.BLUE, new Coordinates(-1.45, 0));
    circle = new Oval("C", 12, 12, Color.GREEN, new Coordinates(-12.35, 28.3475));
  }

  /**
   * Test for changeWidth() method exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testChangeWidthExceptions() {
    rectangle.changeWidth(-23);
    circle.changeWidth(0);
  }

  /**
   * Test for changeHeight() method exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testChangeHeightExceptions() {
    square.changeHeight(-1.89);
    rectangle.changeHeight(0);
  }

  /**
   * Test for changeHeight() method.
   */
  @Test
  public void testChangeHeight() {
    square = new Rectangle("S", 98, 98, Color.RED, new Coordinates(0, 0));
    square.changeHeight(75.23);
    Assert.assertEquals(75.23, square.getHeight(), 0.001);
    circle = new Oval("C", 12, 12, Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changeHeight(0.987);
    Assert.assertEquals(0.987, circle.getHeight(), 0.001);
  }

  /**
   * Test for changePosition() method.
   */
  @Test
  public void testChangePosition() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    rectangle.changePosition(new Coordinates(0,23.12));
    Assert.assertEquals("(0.0, 23.12)", rectangle.getPosition().toString());
    square = new Rectangle("S", 98, 98, Color.RED, new Coordinates(0, 0));
    square.changePosition(new Coordinates(-148.70,-2.88));
    Assert.assertEquals("(-148.7, -2.88)", square.getPosition().toString());
    circle = new Oval("C", 12, 12, Color.GREEN, new Coordinates(-12.35, 28.3475));
    circle.changePosition(new Coordinates(0,0));
    Assert.assertEquals("(0.0, 0.0)", circle.getPosition().toString());
  }

  /**
   * Test for toString() method.
   */
  @Test
  public void testToString() {
    rectangle = new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals(""
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0, 50.0), "
            + "Width: 100.0, Height: 50.0, Color: (0.0,0.0,1.0)\n", rectangle.getDescription());
    square = new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0));
    Assert.assertEquals(""
            + "Name: S\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0, 0.0), "
            + "Width: 75.0, Height: 75.0, Color: (1.0,0.0,0.0)\n", square.getDescription());
    oval = new Oval("O", 36.123, 18.86, Color.GREEN, new Coordinates(0, 18.465));
    Assert.assertEquals(""
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0, 18.465), X radius: 36.1, Y radius: 18.9, "
            + "Color: (0.0,1.0,0.0)\n", oval.getDescription());
    circle = new Oval("C", 50.1, 50.03, Color.RED, new Coordinates(200, 199.24));
    Assert.assertEquals(""
            + "Name: C\n"
            + "Type: oval\n"
            + "Center: (200.0, 199.24), "
            + "X radius: 50.1, Y radius: 50.0, Color: (1.0,0.0,0.0)\n", circle.getDescription());
  }
}