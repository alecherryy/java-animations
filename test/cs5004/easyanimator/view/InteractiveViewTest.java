package cs5004.easyanimator.view;

import org.junit.Before;
import org.junit.Test;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;

import static org.junit.Assert.*;

public class InteractiveViewTest {
  AnimationModel model;

  @Before
  public void setup() {
    model = new AnimationModelImpl();
  }

  @Test
  public void testJLabel() {
    JCheckBox alessia = new JCheckBox("Select Shapes When Paused");
    assertEquals("Select Shapes When Paused", alessia.getText());
  }
}