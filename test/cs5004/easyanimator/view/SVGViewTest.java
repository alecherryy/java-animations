package cs5004.easyanimator.view;

import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

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


public class SVGViewTest {
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

  @Before
  public void setup() {
    model = new AnimationModelImpl();
    c1 = new Coordinates(200, 200);
    c2 = new Coordinates(300, 300);
    c3 = new Coordinates(500, 100);
    c4 = new Coordinates(500, 400);

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

    view = new SVGView(10, model.getShapes(), model.getAnimations());

  }

  @Test
  public void testGetDescription() {
    assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
        "xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255,0," +
        "0)\" visibility=\"visible\">\n" +
        "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" attributeName=\"x\" " +
        "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" attributeName=\"y\" " +
        "from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" attributeName=\"x\" " +
        "from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" attributeName=\"y\" " +
        "from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
        "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /> \n" +
        "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
        "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n" +
        "</rect>\n" +
        "\n" +
        "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,0,255)" +
        "\" visibility=\"visible\">\n" +
        "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" attributeName=\"cx\" " +
        "from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" attributeName=\"cy\" " +
        "from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"3000.0ms\" " +
        "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
        "</ellipse>\n" +
        "</svg>", view.getTextDescription());
  }

  @Test
  public void testWrite() {
    this.view.write("test/outSVG.svg");

    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader("test/outSVG.svg"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      assertEquals("<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n" +
          "xmlns=\"http://www.w3.org/2000/svg\">\n" +
          "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" fill=\"rgb(255," +
          "0,0)\" visibility=\"visible\">\n" +
          "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" attributeName=\"x\"" +
          " from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
          "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" attributeName=\"y\"" +
          " from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n" +
          "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" attributeName=\"x\"" +
          " from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
          "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" attributeName=\"y\"" +
          " from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n" +
          "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
          "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /> \n" +
          "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" " +
          "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n" +
          "</rect>\n" +
          "\n" +
          "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" fill=\"rgb(0,0," +
          "255)\" visibility=\"visible\">\n" +
          "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" " +
          "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n" +
          "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" " +
          "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n" +
          "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"3000.0ms\" " +
          "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" fill=\"freeze\" />\n" +
          "</ellipse>\n" +
          "</svg>", sb.toString());
      br.close();
    } catch (Exception e) {
      // do nothing
    }

  }

}
