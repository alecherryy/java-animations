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
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * A JUnit test class for the AnimationModelImpl class.
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
    Assert.assertNotNull(alessia.getShapes());
  }

  /**
   * This is a test for addShape() method.
   */
  @Test
  public void testAddShape() {
    alessia = new AnimationModelImpl();
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED,
            new Coordinates(0, 0)));
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
   * This is a test for addAnimation() method.
   */
  @Test
  public void testAddAnimation() {
    alessia = new AnimationModelImpl();
    alessia.addShape(new Oval("O", 23.56, 50, Color.GREEN,
            new Coordinates(0, 0)));
    Shapes oval = alessia.getShapes().get(0);
    alessia.addAnimation(new ChangeColor(oval,
            1, 10, Color.RED, Color.BLUE));
    Assert.assertFalse(alessia.getAnimations().size() == 0);
  }

  /**
   * This is a test for getDescription() method.
   */
  @Test
  public void testGetDescription() {
    clara = new AnimationModelImpl();
    clara.addShape(new Oval("O", 23.56, 50, Color.GREEN,
            new Coordinates(0, 0)));
    Assert.assertEquals(""
            + "Shapes:\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0,0.0), X radius: 23.6, Y radius: 50.0, "
            + "Color: (0.0,1.0,0.0)\n\n", clara.getDescription());
    clara.addShape(new Rectangle("R", 75, 70.01, Color.BLUE,
            new Coordinates(100, 25)));
    Assert.assertEquals(""
            + "Shapes:\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0,0.0), X radius: 23.6, Y radius: 50.0, "
            + "Color: (0.0,1.0,0.0)\n"
            + "\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,25.0), Width: 75.0, Height: 70.0, "
            + "Color: (0.0,0.0,1.0)\n\n", clara.getDescription());
    Shapes rect = clara.getShapes().get(0);
    clara.addAnimation(new ChangeColor(rect,
            1, 10, Color.GREEN, Color.RED));
    Assert.assertTrue(clara.getAnimations().size() == 1);
    Assert.assertEquals(""
            + "Shapes:\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0,0.0), X radius: 23.6, Y radius: 50.0, "
            + "Color: (0.0,1.0,0.0)\n"
            + "\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (100.0,25.0), Width: 75.0, Height: 70.0, "
            + "Color: (0.0,0.0,1.0)\n\n"
            + "Shape O changes color "
            + "from (0.0,1.0,0.0) to (1.0,0.0,0.0) "
            + "from t=1 to t=10\n", clara.getDescription());
    AnimationModel alessia = new AnimationModelImpl();
    alessia.addShape(new Oval("O", 23.56, 50, Color.GREEN,
            new Coordinates(0, 0)));
    Shapes oval = alessia.getShapes().get(0);
    alessia.addAnimation(new ChangeColor(oval, 1, 10, Color.GREEN, Color.BLUE));
    alessia.addAnimation(new ChangeCoordinates(oval, 10, 99, oval.getPosition(),
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
    Shapes square = alessia.getShapes().get(1);
    alessia.addAnimation(new ChangeColor(square, 2, 99, Color.RED, Color.BLUE));
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