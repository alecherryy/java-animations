package cs5004.easyanimator.model;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;

import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.shapes.Pair;
import cs5004.easyanimator.model.shapes.ShapeImpl;
import cs5004.easyanimator.model.shapes.ShapeType;

public class ModelItemImplTest {
  private ModelItemImpl clara;
  private ModelItemImpl alessia;

  /**
   * This is a test for ShapeImpl class constructor.
   */
  @Test
  public void classConstructor() {
    clara = new ModelItemImpl(new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50)));
    Assert.assertNotNull(clara.getShape());
    Assert.assertEquals("R", clara.getShape().getName());
    alessia = new ModelItemImpl(new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, "Red", new Pair(200, 199.24)));
    Assert.assertNotNull(alessia.getShape());
    Assert.assertEquals("C", alessia.getShape().getName());
  }

  /**
   * This is a test for addAnimation() method.
   */
  @Test
  public void testAddAnimation() {
    clara = new ModelItemImpl(new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50)));
    clara.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLACK, Color.BLUE));
    Assert.assertTrue(clara.hasAnimation());
    alessia = new ModelItemImpl(new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, "Red", new Pair(200, 199.24)));
    Assert.assertFalse(alessia.hasAnimation());
    alessia.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLUE, Color.WHITE));
    Assert.assertTrue(alessia.hasAnimation());
  }

    /**
     * This is a test for removeAnimation() method.
     */
    @Test
    public void testRemoveAnimation() {
      clara = new ModelItemImpl(new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50)));
      clara.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLACK, Color.BLUE));
      Assert.assertTrue(clara.hasAnimation());
      clara.removeAnimation(0);
      Assert.assertFalse(clara.hasAnimation());
      alessia = new ModelItemImpl(new ShapeImpl("C", ShapeType.CIRCLE, 50, 50, "Red", new Pair(200, 199.24)));
      Assert.assertFalse(alessia.hasAnimation());
      alessia.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLACK, Color.BLUE));
      alessia.addAnimation(new ChangeColor(clara.getShape(), 1, 10, Color.BLUE, Color.WHITE));
      Assert.assertTrue(alessia.hasAnimation());
      alessia.removeAnimation(0);
      Assert.assertTrue(alessia.hasAnimation());
      alessia.removeAnimation(0);
      Assert.assertFalse(alessia.hasAnimation());
    }
  }