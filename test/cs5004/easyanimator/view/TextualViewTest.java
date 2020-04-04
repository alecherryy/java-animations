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
  View textualView;
  ArrayList<Shapes> shapesList;

  @Before
  public void setup() {
    model = new AnimationModelImpl();
    c1 = new Coordinates(250, 270);
    c2 = new Coordinates(390, 390);
    c3 = new Coordinates(510, 115);
    c4 = new Coordinates(5, 40);
    rectangle = new Rectangle("R", 1, 100, 50, 100,
        Color.RED, c1);
    oval = new Oval("O", 6, 100, 60, 30, Color.BLUE,
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
    textualView = new TextualView(20, model.getShapes(), model.getAnimations());
    shapesList = textualView.getShapes();
  }

  /**
   * Test that display() throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testDisplay() {
    textualView.display();
  }

  /**
   * Test for setShapes() method.
   */
  @Test
  public void testSetShapes() {
    assertEquals(shapesList, textualView.getShapes());
    textualView.setShapes(new ArrayList<Shapes>());
    assertEquals(new ArrayList<Shapes>(), textualView.getShapes());
  }

  /**
   * Test that refresh() throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testRefresh() {
    textualView.refresh();
  }

  /**
   * Test for getShapes() method.
   */
  @Test
  public void testGetShapes() {
    assertEquals(shapesList, textualView.getShapes());
  }

  /**
   * Test for getAnimations() method.
   */
  @Test
  public void testGetAnimations() {
    assertEquals(model.getAnimations(), textualView.getAnimations());
  }

  /**
   * Test for getTextDescription() method.
   */
  @Test
  public void testGetTextDescription() {
    assertEquals("Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (250.0, 270.0), Width: 50.0, Height: 100.0, "
            + "Color: (1.0,0.0,0.0)\n"
            + "Appears at t=0.05s\n"
            + "Disappears at t=5.0s\n"
            + "\n"
            + "Name: O\n" + "Type: oval\n"
            + "Center: (510.0, 115.0), X radius: 60.0, Y radius: 30.0, "
            + "Color: (0.0,0.0,1.0)\n"
            + "Appears at t=0.3s\n"
            + "Disappears at t=5.0s\n"
            + "\n"
            + "Shape R moves from (250.0, 270.0) to (390.0, 390.0) from t=0.5s to t=2.5s\n"
            + "Shape O moves from (510.0, 115.0) to (5.0, 40.0) from t=1.0s to t=3.5s\n"
            + "Shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=2.5s to t=4.0s\n"
            + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from "
            + "t=2.55s to t=3.5s\n"
            + "Shape R moves from (390.0, 390.0) to (250.0, 270.0) from t=3.5s to t=5.0s\n",
        this.textualView.getTextDescription());

    TextualView empty = new TextualView(10, new ArrayList<Shapes>(),
        new ArrayList<Animations>());

    assertEquals("", empty.getTextDescription());
  }

  /**
   * Test for write() method.
   */
  @Test
  public void testWrite() {
    this.textualView.write("test/output.txt");

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
              + "Min corner: (250.0, 270.0), Width: 50.0, Height: 100.0, "
              + "Color: (1.0,0.0,0.0)\n"
              + "Appears at t=0.05s\n" + "Disappears at t=5.0s\n" + "\n"
              + "Name: O\n" + "Type: oval\n"
              + "Center: (510.0, 115.0), X radius: 60.0, Y radius: 30.0, Color: (0.0,0.0,1.0)\n"
              + "Appears at t=0.3s\n" + "Disappears at t=5.0s\n" + "\n"
              + "Shape R moves from (250.0, 270.0) to (390.0, 390.0) from t=0.5s to t=2.5s\n"
              + "Shape O moves from (510.0, 115.0) to (5.0, 40.0) from t=1.0s to t=3.5s\n"
              + "Shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=2.5s to t=4.0s\n"
              + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 from "
              + "t=2.55s to t=3.5s\n"
              + "Shape R moves from (390.0, 390.0) to (250.0, 270.0) from t=3.5s to t=5.0s\n",
          sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }
  }

  /**
   * Test for write() method that doesn't output anything.
   */
  @Test
  public void testWriteEmptyView() {
    TextualView emptyView = new TextualView(10, new ArrayList<Shapes>(),
        new ArrayList<Animations>());

    emptyView.write("test/emptyOutput.txt");

    BufferedReader br = null;
    try {
      br = new BufferedReader(new FileReader("test/emptyOutput.txt"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }
  }


  /**
   * Test for getSpeed() method.
   */
  @Test
  public void testGetSpeed() {
    assertEquals(20, textualView.getSpeed(), 0.1);
  }

  /**
   * Test that displayErrorMsg() throws an exception.
   */
  @Test(expected = UnsupportedOperationException.class)
  public void testDisplayErrorMsg() {
    textualView.displayErrorMsg("Error.");
  }

}
