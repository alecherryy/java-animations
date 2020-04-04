package cs5004.easyanimator.model.animations;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.AnimationModelImpl.AnimationModelBuilder;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.util.TweenModelBuilder;

import static org.junit.Assert.assertEquals;

/**
 * This is a JUnit suite to test the AnimationModelBuilder class.
 */
public class AnimationModelBuilderTest {
  private AnimationModel model;
  private TweenModelBuilder<AnimationModel> builder;
  private Coordinates c1;
  private Coordinates c2;
  private ChangeColor changeColor;
  private ChangeColor changeColor2;
  private ChangeCoordinates changeCoordinates;
  private ChangeCoordinates changeCoordinates2;
  private ChangeSize changeSize;
  private ChangeSize changeSize2;
  private Shapes rectangle;
  private Shapes square;
  private Shapes oval;
  private Shapes oval2;

  /**
   * Class set up.
   */
  @Before
  public void setUp() {
    builder = new AnimationModelBuilder();
    this.model = new AnimationModelImpl();

    c1 = new Coordinates(0, 0);
    c2 = new Coordinates(115.5, 50.5);
    rectangle = new Rectangle("R", 5, 10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    square = new Rectangle("S", 2, 7, 10, 30,
        Color.RED, new Coordinates(0, 0));
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
        new Coordinates(0, 18.5));
    oval2 = new Oval("O2", 0, 10, 3, 4, Color.BLACK,
        new Coordinates(0, 0));
    changeColor = new ChangeColor(rectangle, 6, 9, Color.BLUE, Color.RED);
    changeColor2 = new ChangeColor(oval, 10, 15, Color.GREEN, Color.RED);
    changeCoordinates = new ChangeCoordinates(square, 3, 5, new Coordinates(0,
        0), new Coordinates(20, 50));
    changeCoordinates2 = new ChangeCoordinates(rectangle, 6, 9, c1, c2);
    changeSize = new ChangeSize(oval, 11, 15, 35, 18,
        40, 20);
    changeSize2 = new ChangeSize(oval2, 2, 5, 3, 4,
        15.5, 15.5);
  }

  /**
   * Test for addOval() method.
   */

  @Test
  public void addOvalTest() {
    builder.addOval(oval.getName(), 0, 18.5, 5, 5, 0, 1, 0,
            10, 20);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(oval)), builder.build().getShapes());
  }

  /**
   * Test for addRectangle() method.
   */

  @Test
  public void addRectangleTest() {
    builder.addRectangle(rectangle.getName(), 25, 50, 5, 5, 0, 0,
            1, 5, 10);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(rectangle)), builder.build().getShapes());

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
   * Test for addMove() method.
   */
  @Test
  public void addMoveTest() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addMove("O", 5, 5, 12,
        12, 11, 15);
    assertEquals(1, builder.build().getAnimations().size());
  }

  /**
   * Test for addColorChange() method.
   */
  @Test
  public void addColorChangeTest() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addColorChange("O", 0, 1, 0, 1, 0,
        0, 10, 13);
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
    this.builder.addSizeChange("R", 1, 2, 5,
        6, 6, 8);
    assertEquals(1, builder.build().getAnimations().size());
  }
}
