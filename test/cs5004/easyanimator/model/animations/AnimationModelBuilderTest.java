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

public class AnimationModelBuilderTest {
  AnimationModel model;
  TweenModelBuilder<AnimationModel> builder;
  private Coordinates c1;
  private Coordinates c2;
  private ChangeColor changeColor2;
  private ChangeCoordinates changeCoordinates2;
  private ChangeCoordinates changeCoordinates3;
  private ChangeSize changeSize2;
  private ChangeSize changeSize3;
  private Shapes rectangle1;
  private Shapes rectangle3;
  private Shapes oval1;
  private Shapes oval4;


  @Before
  public void setup() {
    builder = new AnimationModelBuilder();

    this.model = new AnimationModelImpl();

    c1 = new Coordinates(0, 0);
    c2 = new Coordinates(102.112, 50.5);
    oval1 = new Oval("O", 0, 10, 10, 10,
        Color.BLACK, c1);
    oval4 = new Oval("O2", 10, 15, 20, 25.5,
        Color.BLUE, c2);
    rectangle1 = new Rectangle("R", 0,
        10, 10, 10,
        Color.BLACK, c1);
    rectangle3 = new Rectangle("R2", 10,
        15, 20, 25.5,
        Color.BLUE, c2);
    changeColor2 = new ChangeColor(oval1, 5, 10, Color.BLACK, Color.RED);
    changeSize2 = new ChangeSize(oval4, 11, 12, 20, 25.5,
        15.5, 15.5);
    changeSize3 = new ChangeSize(rectangle3, 11, 12, 20.0, 25.5,
        15.5, 15.5);
    changeCoordinates2 = new ChangeCoordinates(rectangle1, 8, 10, c1, c2);
    changeCoordinates3 = new ChangeCoordinates(oval1, 8, 10, c1, c2);
  }
  @Test
  public void addOval() {
    this.builder.addOval(oval1.getName(), 0, 0, 10, 10,
        1, 1, 1, 0, 10);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(this.oval1)), builder.build().getShapes());
  }

  // Test for adding a rectangle to a model builder
  @Test
  public void addRectangle() {
    this.builder.addRectangle(rectangle1.getName(), 0, 0, 10, 10,
        1, 1, 1, 0, 10);
    assertEquals(new ArrayList<Shapes>(Arrays.asList(this.rectangle1)),
        builder.build().getShapes());
  }

  // Test for adding a change dimension animation to a model builder
  @Test
  public void addChangeDimension() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addSizeChange("rectangle 2", (float) 20.0, (float) 25.5, (float) 15.5,
        (float) 15.5, 11, 12);
    assertEquals(1, builder.build().getAnimations().size());
  }

  // Test for adding a change color animation to a model builder
  @Test
  public void addChangeColor() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addColorChange("oval 1", 1, 1, 1, 1, 0,
        0, 5, 10);
    assertEquals(1, builder.build().getAnimations().size());
  }

  // Test for adding a move animation to a model builder
  @Test
  public void addMove() {
    assertEquals(0, builder.build().getAnimations().size());
    this.builder.addMove("oval 1", 0, 0, (float) 102.112,
        (float) 50.5, 8, 10);
    assertEquals(1, builder.build().getAnimations().size());
  }
}
