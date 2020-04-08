package cs5004.easyanimator.model;

import org.junit.Assert;
import org.junit.Test;

import java.awt.Color;

import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * A JUnit test class for the Utils class.
 */
public class UtilsTest {

  /**
   * Test for isNegative() method.
   */
  @Test
  public void isNegativeTest() {
    Assert.assertEquals(false, Utils.isNegative(0.0));
    Assert.assertEquals(true, Utils.isNegative(-7.0));
    Assert.assertEquals(false, Utils.isNegative(7.0));
  }

  /**
   * Test for rgbToFloat() method.
   */
  @Test
  public void rgbToFloatTest() {
    Assert.assertEquals(1.0, Utils.rgbToFloat(255), 0.1);
    Assert.assertEquals(0.5882352941, Utils.rgbToFloat(150), 0.1);
    Assert.assertEquals(0.3921568691, Utils.rgbToFloat(100), 0.1);
  }

  /**
   * Test for colorAsString() method.
   */
  @Test
  public void colorAsStringTest() {
    Assert.assertEquals("(0.0,0.0,1.0)", Utils.colorAsString(Color.BLUE));
    Assert.assertEquals("(1.0,0.0,0.0)", Utils.colorAsString(Color.RED));
    Assert.assertEquals("(0.0,1.0,0.0)", Utils.colorAsString(Color.GREEN));
  }

  /**
   * Test for formatSizeString() method.
   */
  @Test
  public void formatSizeStringTest() {
    Shapes rectangle = new Rectangle("R", 2, 5, 5, 8, Color.BLUE, new Coordinates(25, 50));
    Assert.assertEquals("Width: 2.0, Height: 5.0", Utils.formatSizeString(rectangle, 2.0, 5.0));
    Shapes oval = new Oval("O", 7, 8, 4, 8, Color.GREEN, new Coordinates(0, 18.5));
    Assert.assertEquals("X radius: 7.0, Y radius: 8.0", Utils.formatSizeString(oval, 7.0, 8.0));
  }

  /**
   * Test for getRGBColorString() method.
   */
  @Test
  public void getRGBColorStringTest() {
    Assert.assertEquals("(0,0,255)", Utils.getRGBColorString(Color.BLUE));
    Assert.assertEquals("(0,255,0)", Utils.getRGBColorString(Color.GREEN));
    Assert.assertEquals("(255,0,0)", Utils.getRGBColorString(Color.RED));
  }
}
