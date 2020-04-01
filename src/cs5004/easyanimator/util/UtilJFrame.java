package cs5004.easyanimator.util;//package cs5004.easyanimator.util;

import java.awt.*;

import javax.swing.*;

import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Oval;
import cs5004.easyanimator.model.shapes.Rectangle;
import cs5004.easyanimator.view.VisualAnimationView;

public class UtilJFrame {

  public static void main(String[] args) throws InterruptedException {
    VisualAnimationView view = new VisualAnimationView("Java Easy Animator");

    view.getModel().addShape(new Rectangle("S", 75, 75, Color.GREEN, new Coordinates(0, 0)));
    view.getModel().addShape(new Rectangle("R", 75, 75, Color.RED, new Coordinates(0, 0)));
    view.getModel().addShape(new Oval("O", 75, 50, Color.BLUE, new Coordinates(0, 0)));
    view.display();
    while (true) {
      Thread.sleep(1000);
      SwingUtilities.invokeLater(()->view.animate());
    }
  }
}