package cs5004.easyanimator.view;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.animations.ChangeCoordinates;
import cs5004.easyanimator.model.animations.ChangeSize;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;

import static org.junit.Assert.assertEquals;


/**
 * A JUnit test class for the TextualView class.
 */

public class TextualViewTest {
  AnimationModel model;
  Shapes rectangle;
  Shapes oval;
  Coordinates c1;
  Coordinates c2;
  Coordinates c3;
  Coordinates c4;
  Animations changeCoordinates1;
  Animations changeCoordinates2;
  Animations changeCoordinates3;
  Animations changeColor1;
  Animations changeSize1;
  View view;
  ArrayList<Shapes> shapesList;

  @Before
  public void setup() {
    model = new AnimationModelImpl();
    c1 = new Coordinates(200, 200);
    c2 = new Coordinates(300, 300);
    c3 = new Coordinates(500, 100);
    c4 = new Coordinates(500, 400);

    rectangle = new Rectangle("R", 1, 100, 50, 100,
        Color.RED, c1);
    oval = new Oval("O",6, 100, 60, 30, Color.BLUE,
        c3);
    changeCoordinates1 = new ChangeCoordinates(rectangle, 10, 50, c1, c2);
    changeCoordinates2 = new ChangeCoordinates(oval, 20, 70, c3, c4);
    changeCoordinates3 = new ChangeCoordinates(rectangle, 70, 100, c2, c1);
    changeColor1 = new ChangeColor(oval, 50, 80, Color.BLUE, Color.GREEN);
    changeSize1 = new ChangeSize(rectangle, 51, 70, 50, 100,
        25, 100);
    model.addShape(rectangle);
    model.addShape(oval);
    model.addAnimation(changeCoordinates1);
    model.addAnimation(changeCoordinates2);
    model.addAnimation(changeColor1);
    model.addAnimation(changeCoordinates3);
    model.addAnimation(changeSize1);

    view = new TextualView(10, model.getShapes(), model.getAnimations());
    shapesList = view.getShapes();
  }

  @Test
  public void testGetDescription() {
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, "
            + "Color: (1.0,0.0,0.0)\n" + "Appears at t=0.1s\n"
            + "Disappears at t=10.0s\n" + "\n" + "Name: O\n" + "Type: oval\n"
            + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, "
            + "Color: (0.0,0.0,1.0)\n"
            + "Appears at t=0.6s\n" + "Disappears at t=10.0s\n" + "\n"
            + "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1.0s to t=5.0s\n"
            + "shape O moves from (500.0, 100.0) to (500.0, 400.0) from t=2.0s to t=7.0s\n"
            + "shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) "
            + "from t=5.0s to t=8.0s\n"
            + "shape R moves from (300.0, 300.0) to (200.0, 200.0) "
            + "from t=7.0s to t=10.0s\n"
            + "shape R scales from Width: 50.0, "
            + "Height: 100.0 to Width: 25.0, Height: 100.0 "
            + "from t=5.1s to t=7.0s\n",
        this.view.getTextDescription());

    TextualView empty = new TextualView(10, new ArrayList<Shapes>(),
        new ArrayList<Animations>());

    assertEquals("", empty.getTextDescription());
  }

  @Test
  public void testWrite() {
    this.view.write("test/output.txt");

    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("test/output.txt"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("Shapes:\n"
          + "Name: R\n"
          + "Type: rectangle\n"
          + "Min corner: (200.0, 200.0), Width: 50.0, Height: 100.0, "
          + "Color: (1.0,0.0,0.0)\n"
          + "Appears at t=0.1s\n" + "Disappears at t=10.0s\n" + "\n"
          + "Name: O\n" + "Type: oval\n"
          + "Center: (500.0, 100.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
          + "Appears at t=0.6s\n" + "Disappears at t=10.0s\n" + "\n"
          + "shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1.0s to t=5.0s\n"
          + "shape O moves from (500.0, 100.0) to (500.0, 400.0) from t=2.0s to t=7.0s\n"
          + "shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=5.0s to t=8.0s\n"
          + "shape R moves from (300.0, 300.0) to (200.0, 200.0) "
          + "from t=7.0s to t=10.0s\nshape R scales from Width: 50.0, Height: 100.0 to Width: "
          + "25.0, Height: 100.0 from t=5.1s to t=7.0s\n", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }

  }


}
