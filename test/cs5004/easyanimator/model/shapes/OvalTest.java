package cs5004.easyanimator.model.shapes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.Color;

/**
 * A JUnit test class for the Oval Class and the methods that are specific to it.
 */
public class OvalTest {
  private Shapes oval;
  private Shapes circle;

  @Before
  public void setUp() {
    oval = new Oval("O", 10, 20, 5, 5, Color.GREEN,
        new Coordinates(0, 18.5));
    circle = new Oval("C", 8, 13, 20, 30, Color.RED,
        new Coordinates(100, 200));
  }

  /**
   * Test for getLocation() method.
   */
  @Test
  public void getLocationTest() {
    Assert.assertEquals("Center: (0.0, 18.5)", oval.getLocation());
    Assert.assertEquals("Center: (100.0, 200.0)", circle.getLocation());
  }

  /**
   * Test for toSVGTag() method.
   */
  @Test
  public void toSVGTagTest() {
    Assert.assertEquals("<ellipse id=\"O\" cx=\"0.0\" cy=\"18.5\" rx=\"5.0\" ry=\"5.0\" "
        + "fill=\"rgb(0,255,0)\" visibility=\"visible\">\n", oval.toSVGTag());
    Assert.assertEquals("<ellipse id=\"C\" cx=\"100.0\" cy=\"200.0\" rx=\"20.0\" "
        + "ry=\"30.0\" fill=\"rgb(255,0,0)\" visibility=\"visible\">\n", circle.toSVGTag());
  }

  /**
   * Test for svgTagX() method.
   */
  @Test
  public void SVGTagXTest() {
    Assert.assertEquals("cx", oval.svgTagX());
    Assert.assertEquals("cx", circle.svgTagX());

  }

  /**
   * Test for svgTagY() method.
   */
  @Test
  public void SVGTagYTest() {
    Assert.assertEquals("cy", oval.svgTagY());
    Assert.assertEquals("cy", circle.svgTagY());

  }

  /**
   * Test for svgEndTag() method.
   */
  @Test
  public void SVGEndTagTest() {
    Assert.assertEquals("</ellipse>\n", oval.svgEndTag());
    Assert.assertEquals("</ellipse>\n", circle.svgEndTag());
  }

  /**
   * Test for svgD1Tag() method.
   */
  @Test
  public void SVGD1TagTest() {
    Assert.assertEquals("rx", oval.svgD1Tag());
    Assert.assertEquals("rx", circle.svgD1Tag());
  }

  /**
   * Test for svgD2Tag() method.
   */
  @Test
  public void SVGD2TagTest() {
    Assert.assertEquals("ry", oval.svgD2Tag());
    Assert.assertEquals("ry", circle.svgD2Tag());
  }
}


