package cs5004.EasyAnimator.model.shapes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

/**
 * A JUnit test class for the Rectangle Class and the methods that are specific to it.
 */
public class RectangleTest {
  private Shapes rectangle;
  private Shapes square;

  /**
   * Class set up.
   */
  @Before
  public void setup() {
    rectangle = new Rectangle("R", 5, 10, 1, 2,
        Color.BLUE, new Coordinates(25, 50));
    square = new Rectangle("S", 2, 7, 10, 30,
        Color.RED, new Coordinates(0, 0));
  }

  /**
   * Test for getLocation() method.
   */
  @Test
  public void getLocationTest() {
    Assert.assertEquals("Min corner: (25.0, 50.0)", rectangle.getLocation());
    Assert.assertEquals("Min corner: (0.0, 0.0)", square.getLocation());
  }

  /**
   * Test for toSVGTag() method.
   */
  @Test
  public void toSVGTagTest() {
    Assert.assertEquals("<rect id=\"R\" x=\"25.0\" y=\"50.0\" width=\"1.0\" height=\"2.0\""
        + " fill=\"rgb(0,0,255)\" visibility=\"visible\">\n", rectangle.toSVGTag());
    Assert.assertEquals("<rect id=\"S\" x=\"0.0\" y=\"0.0\" width=\"10.0\" "
        + "height=\"30.0\" fill=\"rgb(255,0,0)\" visibility=\"visible\">\n", square.toSVGTag());
  }

  /**
   * Test for svgTagX() method.
   */
  @Test
  public void SVGTagXTest() {
    Assert.assertEquals("x", rectangle.svgTagX());
    Assert.assertEquals("x", square.svgTagX());
  }

  /**
   * Test for svgTagY() method.
   */
  @Test
  public void SVGTagYTest() {
    Assert.assertEquals("y", rectangle.svgTagY());
    Assert.assertEquals("y", square.svgTagY());
  }

  /**
   * Test for svgEndTag() method.
   */
  @Test
  public void SVGEndTagTest() {
    Assert.assertEquals("</rect>\n", rectangle.svgEndTag());
    Assert.assertEquals("</rect>\n", square.svgEndTag());
  }

  /**
   * Test for svgD1Tag() method.
   */
  @Test
  public void SVGD1TagTest() {
    Assert.assertEquals("width", rectangle.svgD1Tag());
    Assert.assertEquals("width", square.svgD1Tag());
  }

  /**
   * Test for svgD2Tag() method.
   */
  @Test
  public void SVGD2TagTest() {
    Assert.assertEquals("height", rectangle.svgD2Tag());
    Assert.assertEquals("height", square.svgD2Tag());
  }
}

