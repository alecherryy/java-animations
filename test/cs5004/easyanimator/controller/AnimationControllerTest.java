package cs5004.easyanimator.controller;

import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.animations.ChangeColor;
import cs5004.easyanimator.model.animations.ChangeCoordinates;
import cs5004.easyanimator.model.animations.ChangeSize;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;
import cs5004.easyanimator.view.SVGView;
import cs5004.easyanimator.view.TextualView;
import cs5004.easyanimator.view.View;

public class AnimationControllerTest {
  ArrayList<Integer> settings;
  SVGAnimationController svg;
  TextualAnimationController textual;
  AnimationModelImpl model;
  View view;

  @Test
  public void testStart() throws FileNotFoundException {
    settings = new ArrayList<Integer>();
    settings.add(0);
    settings.add(0);
    settings.add(1000);
    settings.add(1000);
    Shapes r = new Rectangle("R", 1, 100, 50, 100,
            Color.RED, new Coordinates(200, 200));
    Shapes o = new Oval("O", 20, 100, 120, 60,
            Color.BLUE, new Coordinates(440, 70));
    model = new AnimationModelImpl();
    model.addShape(r);
    model.addShape(o);
    Animations a1 = new ChangeCoordinates(r, 10, 50, new Coordinates(200, 200), new Coordinates(300, 300));
    Animations a2 = new ChangeCoordinates(o, 20, 70, new Coordinates(500, 100), new Coordinates(500, 400));
    Animations a3 = new ChangeCoordinates(r, 70, 100, new Coordinates(300, 300), new Coordinates(200, 200));
    Animations a4 = new ChangeColor(o, 50, 80, Color.BLUE, Color.GREEN);
    Animations a5 = new ChangeSize(r, 51, 70, 50, 100,
            25, 100);
    model.addAnimation(a1);
    model.addAnimation(a2);
    model.addAnimation(a3);
    model.addAnimation(a4);
    model.addAnimation(a5);
    view = new SVGView(25, settings, model.getShapes(), model.getAnimations());
    svg = new SVGAnimationController(view, "test/test-toh-8.svg");
    svg.start();
    Assert.assertEquals(""
            + "<svg width=\"1000\" height=\"1000\" version=\"1.1\" "
            + "overflow=\"auto\" xmlns=\"http://www.w3.org/2000/svg\">\n"
            + "<rect id=\"R\" x=\"200.0\" y=\"200.0\" width=\"50.0\" height=\"100.0\" "
            + "fill=\"rgb(255,0,0)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"1600.0ms\" "
            + "attributeName=\"x\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"400.0ms\" dur=\"1600.0ms\" "
            + "attributeName=\"y\" from=\"200.0\" to=\"300.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2800.0ms\" dur=\"1200.0ms\" "
            + "attributeName=\"x\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2800.0ms\" dur=\"1200.0ms\" "
            + "attributeName=\"y\" from=\"300.0\" to=\"200.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"2040.0ms\" dur=\"760.0ms\" "
            + "attributeName=\"width\" from=\"50.0\" to=\"25.0\" fill=\"freeze\" /> \n"
            + "<animate attributeType=\"xml\" type=\"scale\" begin=\"2040.0ms\" dur=\"760.0ms\" "
            + "attributeName=\"height\" from=\"100.0\" to=\"100.0\" fill=\"freeze\" />\n"
            + "</rect>\n"
            + "<ellipse id=\"O\" cx=\"440.0\" cy=\"70.0\" rx=\"120.0\" ry=\"60.0\" "
            + "fill=\"rgb(0,0,255)\" visibility=\"visible\">\n"
            + "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"cx\" from=\"500.0\" to=\"500.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"800.0ms\" dur=\"2000.0ms\" "
            + "attributeName=\"cy\" from=\"100.0\" to=\"400.0\" fill=\"freeze\" />\n"
            + "<animate attributeType=\"xml\" begin=\"2000.0ms\" dur=\"1200.0ms\" "
            + "attributeName=\"fill\" from=\"rgb(0,0,255)\" to=\"rgb(0,255,0)\" "
            + "fill=\"freeze\" />\n"
            + "</ellipse>\n"
            + "</svg>\n", readFile(new FileReader("test/test-toh-8.svg")));
    view = new TextualView(10, model.getShapes(), model.getAnimations());
    textual = new TextualAnimationController(view, model, "test/test-toh-8.text");
    textual.start();
    Assert.assertEquals(""
            + "Shapes:\n"
            + "Name: R\n"
            + "Type: rectangle\n"
            + "Min corner: (200.0, 200.0), Width: 25.0, Height: 100.0, Color: (1.0,0.0,0.0)\n"
            + "Appears at t=0.1s\n"
            + "Disappears at t=10.0s\n"
            + "\n"
            + "Name: O\n"
            + "Type: oval\n"
            + "Center: (500.0, 400.0), X radius: 120.0, Y radius: 60.0, Color: (0.0,1.0,0.0)\n"
            + "Appears at t=2.0s\n"
            + "Disappears at t=10.0s\n"
            + "\n"
            + "Shape R moves from (200.0, 200.0) to (300.0, 300.0) from t=1.0s to t=5.0s\n"
            + "Shape O moves from (500.0, 100.0) to (500.0, 400.0) from t=2.0s to t=7.0s\n"
            + "Shape O changes color from (0.0,0.0,1.0) to (0.0,1.0,0.0) from t=5.0s to t=8.0s\n"
            + "Shape R scales from Width: 50.0, Height: 100.0 to Width: 25.0, Height: 100.0 "
            + "from t=5.1s to t=7.0s\n"
            + "Shape R moves from (300.0, 300.0) to (200.0, 200.0) "
            + "from t=7.0s to t=10.0s\n", readFile(new FileReader("test/test-toh-8.text")));
    }

  private String readFile(FileReader file) {
    StringBuilder sb = null;
    try {
      BufferedReader br = new BufferedReader(file);
      sb = new StringBuilder();
      String line = br.readLine();

      while (line != null) {
        sb.append(line);
        sb.append("\n");
        line = br.readLine();
      }
      br.close();
    } catch (Exception e) {
      // do nothing
    }
    return sb.toString();
  }
}