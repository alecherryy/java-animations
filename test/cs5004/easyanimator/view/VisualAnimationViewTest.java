package cs5004.easyanimator.view;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VisualAnimationViewTest {
  private View clara;

  @Before
  public void setUp() {
    clara = new VisualAnimationView("Clara");

  }

  /**
   * This is a test for ShapeImpl class constructor.
   */
  @Test
  public void classConstructor() {
    assertNotNull(clara);
    assertNull(clara.getShapes());
    assertNotNull(clara.getAnimationPanel());
  }
}