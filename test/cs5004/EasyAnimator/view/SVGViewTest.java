package cs5004.EasyAnimator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import cs5004.EasyAnimator.model.AnimationModel;
import cs5004.EasyAnimator.model.AnimationModelImpl;
import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.animations.ChangeColor;
import cs5004.EasyAnimator.model.animations.ChangeCoordinates;
import cs5004.EasyAnimator.model.animations.ChangeSize;
import cs5004.EasyAnimator.model.shapes.Coordinates;
import cs5004.EasyAnimator.model.shapes.Oval;
import cs5004.EasyAnimator.model.shapes.Rectangle;
import cs5004.EasyAnimator.model.shapes.Shapes;

/**
 * This is a JUnit suite to test the SVGView class.
 */
public class SVGViewTest {
  AnimationModel clara;
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
  ArrayList<Integer> settings;

  /**
   * Set up objects for testing.
   */
  @Before
  public void setUp() {
    clara = new AnimationModelImpl();
    c1 = new Coordinates(200, 200);
    c2 = new Coordinates(300, 300);
    c3 = new Coordinates(500, 100);
    c4 = new Coordinates(500, 400);
    rectangle = new Rectangle("R", 1, 100, 50, 100,
            Color.RED, c1);
    oval = new Oval("O", 6, 100, 60, 30,
            Color.BLUE, c3);
    changeCoordinates1 = new ChangeCoordinates(rectangle, 10, 50, c1, c2);
    changeCoordinates2 = new ChangeCoordinates(oval, 20, 70, c3, c4);
    changeCoordinates3 = new ChangeCoordinates(rectangle, 70, 100, c2, c1);
    changeColor1 = new ChangeColor(oval, 50, 80, Color.BLUE, Color.GREEN);
    changeSize1 = new ChangeSize(rectangle, 51, 70, 50, 100,
            25, 100);
    clara.addShape(rectangle);
    settings = new ArrayList<Integer>();
    settings = new ArrayList<>();
    settings.add(145);
    settings.add(50);
    settings.add(410);
    settings.add(220);

    view = new SVGView(10, settings, clara.getShapes(), clara.getAnimations());

  }

  /**
   * Test for getDescription() method.
   */
  @Test
  public void testGetDescription() {
    Assert.assertEquals(""
            + "<svg width=\"410\" height=\"220\" version=\"1.1\" overflow=\"auto\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
            + "</rect>\n"
            + "</svg>", view.getTextDescription());
    clara.addShape(oval);
    Assert.assertEquals(""
            + "<svg width=\"410\" height=\"220\" version=\"1.1\" overflow=\"auto\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
            + "</rect>\n"
            + "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
            + "</ellipse>\n"
            + "</svg>", view.getTextDescription());
    clara.addAnimation(changeCoordinates1);
    Assert.assertEquals(""
            + "<svg width=\"410\" height=\"220\" version=\"1.1\" overflow=\"auto\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
            + "</ellipse>\n"
            + "</svg>", view.getTextDescription());
    clara.addAnimation(changeCoordinates2);
    clara.addAnimation(changeColor1);
    clara.addAnimation(changeCoordinates3);
    clara.addAnimation(changeSize1);
    Assert.assertEquals(""
            + "<svg width=\"410\" height=\"220\" version=\"1.1\" overflow=\"auto\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
            + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" "
            + "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
            + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
            + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"3000.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>", view.getTextDescription());
  }

  /**
   * Test suit for write() method.
   */
  @Test
  public void testWrite() {
    clara.addShape(oval);
    clara.addAnimation(changeCoordinates2);
    clara.addAnimation(changeColor1);
    clara.addAnimation(changeCoordinates3);
    clara.addAnimation(changeSize1);
    clara.addAnimation(changeCoordinates1);
    this.view.write("test/outputSVG.svg");

    try {
      BufferedReader br = new BufferedReader(new FileReader("test/outputSVG.svg"));
      StringBuilder sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      Assert.assertEquals(""
              + "<svg width=\"410\" height=\"220\" version=\"1.1\" overflow=\"auto\" "
              + "xmlns=\"http://www.w3.org/2000/svg\">\n"
              + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
              + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
              + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
              + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"7000.0ms\" dur=\"3000.0ms\" "
              + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" "
              + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /> \n"
              + "<animate attributeType=\"xml\" type=\"scale\" begin=\"5100.0ms\" dur=\"1900.0ms\" "
              + "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
              + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"1000.0ms\" dur=\"4000.0ms\" "
              + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
              + "</rect>\n"
              + "<ellipse id=\"O\" cx=\"500.0\" cy=\"100.0\" rx=\"60.0\" ry=\"30.0\" "
              + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
              + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
              + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"5000.0ms\" "
              + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
              + "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"3000.0ms\" "
              + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" "
              + "fill=\"freeze\" />\n"
              + "</ellipse>\n"
              + "</svg>\n", sb.toString());
      br.close();
    } catch (Exception e) {
      return;
    }
  }
}