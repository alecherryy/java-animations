import org.junit.Assert;
import org.junit.Test;

import model.Shape.*;

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
}