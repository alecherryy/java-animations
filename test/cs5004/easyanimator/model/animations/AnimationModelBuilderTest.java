package cs5004.easyanimator.model.animations;

import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.AnimationModelImpl.AnimationModelBuilder;
import cs5004.easyanimator.model.TweenModelBuilder;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;

import static org.junit.Assert.assertEquals;

/**
 * This is a JUnit suite to test the AnimationModelBuilder class.
 */
public class AnimationModelBuilderTest {
  private TweenModelBuilder<AnimationModel> builder;
  private Shapes rectangle;
  private Shapes oval;

  /**
   * Class set up.
   */
  @Before
  public void setUp() {
    builder = new AnimationModelBuilder();
    AnimationModel model = new AnimationModelImpl();

    Coordinates c1 = new Coordinates(0, 0);
    Coordinates c2 = new Coordinates(115.5, 50.5);
    rectangle = new Rectangle("R", 5, 10, 1, 2,
            Color.BLUE, new Coordinates(25, 50));
    Shapes square = new Rectangle("S", 2, 7, 10, 30,
            Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
            new Coordinates(0, 18.5));
    Shapes oval2 = new Oval("O2", 0, 10, 3, 4, Color.BLACK,
            new Coordinates(0, 0));
    ChangeColor changeColor = new ChangeColor(rectangle, 6, 9, Color.BLUE, Color.RED);
    ChangeColor changeColor2 = new ChangeColor(oval, 10, 15, Color.GREEN, Color.RED);
    ChangeCoordinates changeCoordinates = new ChangeCoordinates(square, 3, 5, new Coordinates(0,
            0), new Coordinates(20, 50));
    ChangeCoordinates changeCoordinates2 = new ChangeCoordinates(rectangle, 6, 9, c1, c2);
    ChangeSize changeSize = new ChangeSize(oval, 11, 15, 35, 18,
            40, 20);
    ChangeSize changeSize2 = new ChangeSize(oval2, 2, 5, 3, 4,
            15.5, 15.5);
  }

  /**
   * Test for addOval() method.
   */
  @Test
  public void testAddOval() {
    builder.addOval(oval.getName(), 0, (float) 18.5, 5, 5, Color.GREEN,
            10, 20);
    assertEquals(2, builder.build().getShapes().size());
  }

  /**
   * Test for addRectangle() method.
   */
  @Test
  public void testAddRectangle() {
    builder.addRectangle(rectangle.getName(), 25, 50, 5, 5, Color.BLUE,
            5, 10);
    assertEquals( 2, builder.build().getShapes().size());
  }

  /**
   * Class set up.
   */
  @Before
  public void exceptionSetUp() {
    builder.build().addShape(rectangle);
    builder.build().addShape(oval);
  }

  /**
   * Test for animations and shape exceptions.
   */
  @Test(expected = IllegalArgumentException.class)
  public void addexceptionShapesAndAnimations() {
    // shape does not exist
    this.builder.addRectangle("R", 2, 7, 10, 30, Color.RED,
            1, 100);
    this.builder.addOval("R", 2, 7, 34, 34, Color.GREEN,
            76, 77);
    this.builder.addOval("L", 2, 7, 34, 34, Color.GREEN,
            76, 31);
    this.builder.addColorChange("O", Color.GREEN, Color.BLUE, 7, 20);
    this.builder.addColorChange("R", Color.GREEN, Color.RED, 14, 20);
    this.builder.addMove("O", 5, 5, 12, 12, 4, 15);
    builder.build();
  }

  /**
   * Test for addMove() method.
   */
  @Test
  public void addMoveTest() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addMove("O", 5, 5, 12, 12, 11, 15);
    assertEquals(1, builder.build().getAnimations().size());
  }

  /**
   * Test for addColorChange() method.
   */
  @Test
  public void addColorChangeTest() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addColorChange("O", Color.GREEN, Color.RED, 10, 13);
    assertEquals(1, builder.build().getAnimations().size());
  }

  /**
   * Test for addSizeChange() method.
   */
  @Test
  public void addSizeChangeTest() {
    rectangle = new Rectangle("R", 5, 10, 1, 2,
            Color.BLUE, new Coordinates(25, 50));
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addSizeChange("R", 1, 2, 5, 6, 6, 8);
    assertEquals(1, builder.build().getAnimations().size());
  }
}