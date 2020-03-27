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
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0)));
    Assert.assertFalse(alessia.isEmpty());
  }

  /**
   * Set up model so we can test exceptions for addShape() method.
   */
  @Before
  public void setUp() {
    clara = new AnimationModelImpl();
    clara.addShape(new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0)));
  }

  /**
   * Test for addShape() method exceptions.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testChangeWidthExceptions() {
    clara.addShape(new Rectangle("S", 12.43, 86.01, Color.GREEN, new Coordinates(35, 75.4)));
    clara.addShape(new Oval("s", 12.43, 86.01, Color.RED, new Coordinates(35, 75.4)));
  }

  /**
   * This is a test for isEmpty() method.
   */
  @Test
  public void testIsEmpty() {
    alessia = new AnimationModelImpl();
    Assert.assertTrue(alessia.isEmpty());
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0)));
    Assert.assertFalse(alessia.isEmpty());
  }

  /**
   * This is a test for removeShape() method.
   */
  @Test
  public void testRemoveShape() {
    clara = new AnimationModelImpl();
    clara.addShape(new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50)));
    Assert.assertFalse(clara.isEmpty());
    clara.addShape(new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0)));
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
    alessia.addShape(new Oval("O", 23.56, 50, Color.GREEN, new Coordinates(0, 0)));
    alessia.addAnimation("O", new ChangeColor(alessia.getItem("O").getShape(), 1, 10, Color.RED, Color.BLUE));
    Assert.assertTrue(alessia.getItem("O").hasAnimation());
    Shape shape = alessia.getItem("O").getShape();
    alessia.addAnimation("O", new ChangeCoordinates(shape, 10, 99, shape.getPosition(), new Coordinates(25, 98)));
    Assert.assertTrue(alessia.getItem("O").hasAnimation());
  }

  /**
   * This is a test for getDescription() method.
   */
  @Test
  public void testGetDescription() {
//    clara = new AnimationModelImpl();
//    clara.addShape(new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50)));
//    Assert.assertEquals("Shapes:\n"
//            + "Name: R\n"
//            + "Type: rectangle\n"
//            + "Min corner: (25.0, 50.0), Width: 100.0, Height: 50.0, Color: (0.0,0.0,1.0)\n"
//            + "\n", clara.getDescription());
    alessia = new AnimationModelImpl();
    alessia.addShape(new Oval("O", 23.56, 50, Color.GREEN, new Coordinates(0, 0)));
    Shape oval = alessia.getItem("O").getShape();
    alessia.addAnimation("O", new ChangeColor(oval, 1, 10, Color.GREEN, Color.BLUE));
    alessia.addAnimation("O", new ChangeCoordinates(oval, 10, 99, oval.getPosition(), new Coordinates(25, 98)));
    alessia.addShape(new Rectangle("S", 75, 75, Color.RED, new Coordinates(0, 0)));
    Shape square = alessia.getItem("S").getShape();
    alessia.addAnimation("S", new ChangeColor(square, 2, 99, Color.RED, Color.BLUE));
    System.out.println(alessia.getDescription());
  }
}