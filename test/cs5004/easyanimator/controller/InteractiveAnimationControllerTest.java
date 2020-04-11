package cs5004.easyanimator.controller;
import org.junit.Before;
import org.junit.Test;

import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;

import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.view.InteractiveView;
import cs5004.easyanimator.view.View;

import static org.junit.Assert.assertEquals;

/**
 * This is JUnit test suite for the interactive view
 * controller.
 */
public class InteractiveAnimationControllerTest {
  JButton playButton;
  JButton pauseButton;
  JButton restartButton;
  JButton increaseSpeedButton;
  JButton decreaseSpeedButton;
  JButton fileButton;
  JCheckBox loopCheckbox;

  InteractiveAnimationController controller;
  AnimationModelImpl model;
  View view;

  /**
   * Initializes data.
   */
  @Before
  public void init() {
    this.playButton = new JButton("Play");
    this.pauseButton = new JButton("Pause");
    this.restartButton = new JButton("Restart");
    this.increaseSpeedButton = new JButton("Increase Speed");
    this.decreaseSpeedButton = new JButton("DecreaseSpeed");
    this.fileButton = new JButton("File");
    this.loopCheckbox = new JCheckBox("Loop");

    this.model = new AnimationModelImpl();
    this.view = new InteractiveView(50, model.getShapes(), model.getAnimations(),
        10);
    this.controller = new InteractiveAnimationController(this.view, this.model, 50,
        "interactive.svg");
  }

  /**
   * Test for the start() method.
   */
  @Test
  public void testStart() {
    this.controller.start();
    assertEquals(false, this.controller.getTimer().isRunning());
    ActionEvent e = new ActionEvent(playButton, 1, "Play");
    this.controller.actionPerformed(e);
    assertEquals(true, this.controller.getTimer().isRunning());
  }

  /**
   * Test for the actionPerformed() method.
   */
  @Test
  public void testActionPerformed() {
    ActionEvent e = new ActionEvent(playButton, 1, "Play");
    ActionEvent e1 = new ActionEvent(pauseButton, 2, "Pause");
    ActionEvent e2 = new ActionEvent(restartButton, 3, "Restart");
    ActionEvent e3 = new ActionEvent(increaseSpeedButton, 4, "Increase speed");
    ActionEvent e4 = new ActionEvent(decreaseSpeedButton, 5, "Decrease speed");
    ActionEvent e5 = new ActionEvent(fileButton, 6, "Set file");
    ActionEvent e6 = new ActionEvent(loopCheckbox, 7, "Loop");

    this.controller.start();
    assertEquals("", this.controller.getLog().toString());
    this.controller.actionPerformed(e);
    assertEquals(true, this.controller.getTimer().isRunning());
    assertEquals("You pressed the play button.\n", this.controller.getLog().toString());

    this.controller.actionPerformed(e1);
    assertEquals(false, this.controller.getTimer().isRunning());

    this.controller.actionPerformed(e2);
    assertEquals(true, this.controller.getTimer().isRunning());

    this.controller.actionPerformed(e3);
    this.controller.actionPerformed(e4);
    this.controller.actionPerformed(e5);
    this.controller.actionPerformed(e6);
    assertEquals("You pressed the play button.\n"
        + "You pressed the pause button.\n"
        + "You pressed the restart button.\n"
        + "You pressed the set file button.\n"
        + "You pressed the loop button.\n", this.controller.getLog().toString());
  }
}
