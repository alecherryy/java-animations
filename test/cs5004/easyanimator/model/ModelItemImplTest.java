package cs5004.easyanimator.model;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.animations.ChangeCoordinates;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;

public class ModelItemImplTest {
  private ModelItemImpl clara;
  private ModelItemImpl alessia;

  /**
   * This is a test for ShapeImpl class constructor.
   */
  @Test
  public void classConstructor() {
    clara = new ModelItemImpl(new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50)));
    Assert.assertNotNull(clara.getShape());
    Assert.assertEquals("R", clara.getShape().getName());
    alessia = new ModelItemImpl(new Oval("C", 50, 50, Color.RED, new Coordinates(200, 199.24)));
    Assert.assertNotNull(alessia.getShape());
    Assert.assertEquals("C", alessia.getShape().getName());
  }

  /**
   * This is a test for addAnimation() method.
   */
  @Test
  public void testAddAnimation() {
    clara = new ModelItemImpl(new Rectangle("R", 100, 50, Color.RED, new Coordinates(25, 50)));
    clara.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.RED, Color.BLUE));
    Assert.assertTrue(clara.hasAnimation());
    alessia = new ModelItemImpl(new Oval("C", 50, 50, Color.BLUE, new Coordinates(200, 199.24)));
    Assert.assertFalse(alessia.hasAnimation());
    alessia.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLUE, Color.GREEN));
    Assert.assertTrue(alessia.hasAnimation());
  }

  /**
   * This is a test for removeAnimation() method.
   */
  @Test
  public void testRemoveAnimation() {
    clara = new ModelItemImpl(new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50)));
    clara.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLUE, Color.RED));
    Assert.assertTrue(clara.hasAnimation());
    Assert.assertEquals(1, clara.getAllAnimations().size());
    clara.removeAnimation(0);
    Assert.assertFalse(clara.hasAnimation());
    alessia = new ModelItemImpl(new Oval("C", 50, 50, Color.RED, new Coordinates(200, 199.24)));
    Assert.assertFalse(alessia.hasAnimation());
    alessia.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.RED, Color.GREEN));
    alessia.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.GREEN, Color.BLUE));
    Assert.assertTrue(alessia.hasAnimation());
    alessia.removeAnimation(0);
    Assert.assertTrue(alessia.hasAnimation());
    alessia.removeAnimation(0);
    Assert.assertFalse(alessia.hasAnimation());
  }

  /**
   * This is a test for toString() method.
   */
  @Test
  public void testToString() {
    clara = new ModelItemImpl(new Rectangle("R", 100, 50, Color.BLUE, new Coordinates(25, 50)));
    clara.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLUE, Color.GREEN));
    Assert.assertEquals(""
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (25.0, 50.0), Width: 100.0, Height: 50.0, "
            + "Color: (0.0,0.0,1.0)\n\n"
            + "\n"
            + "Shape R changing shape color "
            + "from (0.0, 0.0, 1.0) to (0.0, 1.0, 0.0) starting at 1 and ending at 10\n", clara.toString());
    alessia = new ModelItemImpl(new Oval("O", 25, 25, Color.BLUE, new Coordinates(0, 0)));
    alessia.addAnimation(new ChangeColor(alessia.getShape(), 1, 7, Color.RED, Color.GREEN));
    alessia.addAnimation(new ChangeCoordinates(alessia.getShape(), 10, 99, alessia.getShape().getPosition(), new Coordinates(25, 98)));
    Assert.assertEquals(""
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (0.0, 0.0), X radius: 25.0, Y radius: 25.0, Color: (0.0,0.0,1.0)\n\n"
            + "\n"
            + "Shape O changing shape color from (1.0, 0.0, 0.0) to (0.0, 1.0, 0.0) "
            + "starting at 1 and ending at 7\n"
            + "Shape O changing shape coordinates from (0.0, 0.0) to (25.0, 98.0) "
            + "starting at 10 and ending at 99\n", alessia.toString());
    Assert.assertTrue(alessia.hasAnimation());
  }
}