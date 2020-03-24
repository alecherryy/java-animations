package cs5004.easyanimator.model;

import org.junit.Assert;
import org.junit.Test;

import cs5004.easyanimator.model.shapes.Pair;
import cs5004.easyanimator.model.shapes.ShapeImpl;
import cs5004.easyanimator.model.shapes.ShapeType;

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
    alessia.addShape(new ShapeImpl("S", ShapeType.SQUARE, 75, 75, "Red", new Pair(0, 0)));
    Assert.assertFalse(alessia.isEmpty());
  }

  /**
   * This is a test for removeShape() method.
   */
  @Test
  public void testRemoveShape() {
    clara = new AnimationModelImpl();
    clara.addShape(new ShapeImpl("R", ShapeType.RECTANGLE, 100, 50, "Blue", new Pair(25, 50)));
    Assert.assertFalse(clara.isEmpty());
    clara.removeShape(0);
    Assert.assertTrue(clara.isEmpty());
  }
}