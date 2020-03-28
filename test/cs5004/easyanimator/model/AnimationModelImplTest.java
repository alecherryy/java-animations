package cs5004.easyanimator.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.animations.ChangeCoordinates;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shape;

/**
 * This is the test class for all AnimationModelImpl methods.
 */
public class AnimationModelImplTest {
  private AnimationModelImpl clara;
  private AnimationModelImpl alessia;

  /**
   * This is a test for class constructor.
   */
  @Test
  public void classConstructor() {
    alessia = new AnimationModelImpl();
    Assert.assertNotNull(alessia);
    Assert.assertEquals("", alessia.getDescription());
  }

  /**
   * This is a test for addShape() method.
   */
  @Test
  public void testAddShape() {
    alessia = new AnimationModelImpl();
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED,
            new Coordinates(0, 0)));
    Assert.assertFalse(alessia.isEmpty());
  }

  /**
   * Set up model so we can test exceptions for addShape() method.
   */
  @Before
  public void setUp() {
    clara = new AnimationModelImpl();
    clara.addShape(new Rectangle("S", 75, 75, Color.RED,
            new Coordinates(0, 0)));
    alessia = new AnimationModelImpl();
    alessia.addShape(new Oval("O", 23.4, 8.88, Color.BLUE,
            new Coordinates(12.3, 76.01)));
  }

  /**
   * Test for addShape() method exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testChangeWidthExceptions() {
    clara.addShape(new Rectangle("S", 12.43, 86.01, Color.GREEN,
            new Coordinates(35, 75.4)));
    clara.addShape(new Oval("s", 12.43, 86.01, Color.RED,
            new Coordinates(35, 75.4)));
  }

  /**
   * Test for addAnimation() method exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testAddAnimationExceptions() {
    Shape sqr = clara.getItem("V").getShape();
    clara.addAnimation("V", new ChangeColor(sqr, 2, 99, Color.RED, Color.BLUE));
    Shape rect = clara.getItem("S").getShape();
    clara.addAnimation("C", new ChangeColor(rect, 10, 20, Color.GREEN, Color.RED));
    Shape oval = clara.getItem("O").getShape();
    alessia.addAnimation("A", new ChangeCoordinates(oval, 2, 100, oval.getPosition(),
            new Coordinates(25, 98)));
  }

  /**
   * This is a test for isEmpty() method.
   */
  @Test
  public void testIsEmpty() {
    alessia = new AnimationModelImpl();
    Assert.assertTrue(alessia.isEmpty());
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED,
            new Coordinates(0, 0)));
    Assert.assertFalse(alessia.isEmpty());
  }

  /**
   * This is a test for removeShape() method.
   */
  @Test
  public void testRemoveShape() {
    clara = new AnimationModelImpl();
    clara.addShape(new Rectangle("R", 100, 50, Color.BLUE,
            new Coordinates(25, 50)));
    Assert.assertFalse(clara.isEmpty());
    clara.addShape(new Rectangle("S", 75, 75, Color.RED,
            new Coordinates(0, 0)));
    clara.removeShape("R");
    Assert.assertFalse(clara.isEmpty());
    clara.removeShape("S");
    Assert.assertTrue(clara.isEmpty());
  }

  /**
   * This is a test for addAnimation() method.
   */
  @Test
  public void testAddAnimation() {
    alessia = new AnimationModelImpl();
    alessia.addShape(new Oval("O", 23.56, 50, Color.GREEN,
            new Coordinates(0, 0)));
    alessia.addAnimation("O", new ChangeColor(alessia.getItem("O").getShape(),
            1, 10, Color.RED, Color.BLUE));
    Assert.assertTrue(alessia.getItem("O").hasAnimation());
    Shape shape = alessia.getItem("O").getShape();
    alessia.addAnimation("O", new ChangeCoordinates(shape, 10, 99,
            shape.getPosition(), new Coordinates(25, 98)));
    Assert.assertTrue(alessia.getItem("O").hasAnimation());
  }

  /**
   * This is a test for getDescription() method.
   */
  @Test
  public void testGetDescription() {
    clara = new AnimationModelImpl();
    clara.addShape(new Rectangle("R", 100, 50, Color.BLUE,
            new Coordinates(25, 50)));
    clara.addShape(new Rectangle("S", 25.1, 25.1, Color.RED,
            new Coordinates(0, 0)));
    Assert.assertEquals(""
                    + "Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (25.0,50.0), Width: 100.0, Height: 50.0, Color: (0.0,0.0,1.0)"
                    + "\n"
                    + "\n"
                    + "Name: S\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 25.1, Height: 25.1, Color: (1.0,0.0,0.0)"
                    + "\n"
                    + "\n",
            clara.getDescription());
    Shape sqr = clara.getItem("S").getShape();
    clara.addAnimation("s", new ChangeColor(sqr, 5, 100, Color.RED, Color.BLUE));
    Assert.assertEquals(""
                    + "Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (25.0,50.0), Width: 100.0, Height: 50.0, Color: (0.0,0.0,1.0)"
                    + "\n"
                    + "\n"
                    + "Name: S\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 25.1, Height: 25.1, Color: (1.0,0.0,0.0)"
                    + "\n"
                    + "\n"
                    + "Shape S changes color from (1.0,0.0,0.0) to (0.0,0.0,1.0) from t=5 to t=100\n",
            clara.getDescription());
    clara.addAnimation("S", new ChangeCoordinates(sqr, 1, 10, sqr.getPosition(),
            new Coordinates(0, 0)));
    Assert.assertEquals(""
                    + "Shapes:\n"
                    + "Name: R\n"
                    + "Type: rectangle\n"
                    + "Min corner: (25.0,50.0), Width: 100.0, Height: 50.0, Color: (0.0,0.0,1.0)"
                    + "\n"
                    + "\n"
                    + "Name: S\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 25.1, Height: 25.1, Color: (1.0,0.0,0.0)"
                    + "\n"
                    + "\n"
                    + "Shape S moves from (0.0,0.0) to (0.0,0.0) from t=1 to t=10\n"
                    + "Shape S changes color from (1.0,0.0,0.0) to (0.0,0.0,1.0) from t=5 to t=100\n",
            clara.getDescription());
    alessia = new AnimationModelImpl();
    alessia.addShape(new Oval("O", 23.56, 50, Color.GREEN,
            new Coordinates(0, 0)));
    Shape oval = alessia.getItem("O").getShape();
    alessia.addAnimation("O", new ChangeColor(oval, 1, 10, Color.GREEN, Color.BLUE));
    alessia.addAnimation("O", new ChangeCoordinates(oval, 10, 99, oval.getPosition(),
            new Coordinates(25, 98)));
    Assert.assertEquals(""
                    + "Shapes:\n"
                    + "Name: O\n"
                    + "Type: oval\n"
                    + "Center: (0.0,0.0), X radius: 23.6, Y radius: 50.0, Color: (0.0,1.0,0.0)"
                    + "\n"
                    + "\n"
                    + "Shape O changes color from (0.0,1.0,0.0) to (0.0,0.0,1.0) from t=1 to t=10\n"
                    + "Shape O moves from (0.0,0.0) to (25.0,98.0) from t=10 to t=99\n",
            alessia.getDescription());
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0)));
    Assert.assertEquals(""
                    + "Shapes:\n"
                    + "Name: O\n"
                    + "Type: oval\n"
                    + "Center: (0.0,0.0), X radius: 23.6, Y radius: 50.0, Color: (0.0,1.0,0.0)"
                    + "\n"
                    + "\n"
                    + "Name: S\n"
                    + "Type: rectangle\n"
                    + "Min corner: (0.0,0.0), Width: 75.0, Height: 75.0, Color: (1.0,0.0,0.0)"
                    + "\n"
                    + "\n"
                    + "Shape O changes color from (0.0,1.0,0.0) to (0.0,0.0,1.0) from t=1 to t=10\n"
                    + "Shape O moves from (0.0,0.0) to (25.0,98.0) from t=10 to t=99\n",
            alessia.getDescription());
    Shape square = alessia.getItem("S").getShape();
    alessia.addAnimation("S", new ChangeColor(square, 2, 99, Color.RED, Color.BLUE));
    Assert.assertEquals(""
            + "Shapes:\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0,0.0), X radius: 23.6, Y radius: 50.0, Color: (0.0,1.0,0.0)"
            + "\n"
            + "\n"
            + "Name: S\n"
            + "Type: rectangle\n"
            + "Min corner: (0.0,0.0), Width: 75.0, Height: 75.0, Color: (1.0,0.0,0.0)"
            + "\n"
            + "\n"
            + "Shape O changes color from (0.0,1.0,0.0) to (0.0,0.0,1.0) from t=1 to t=10\n"
            + "Shape S changes color from (1.0,0.0,0.0) to (0.0,0.0,1.0) from t=2 to t=99\n"
            + "Shape O moves from (0.0,0.0) to (25.0,98.0) from t=10 to t=99\n",
            alessia.getDescription());
  }
}