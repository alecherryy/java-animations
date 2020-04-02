package cs5004.easyanimator.util;//package cs5004.easyanimator.util;

public class UtilJFrame {

  public static void main(String[] args) throws InterruptedException {
    VisualAnimationView view = new VisualAnimationView("Java Easy Animator");

    view.getModel().addShape(new Rectangle("S", 75, 75, Color.GREEN, new Coordinates(100, 200)));
    view.getModel().addShape(new Rectangle("R", 75, 75, Color.RED, new Coordinates(50, 100)));
    view.getModel().addShape(new Oval("O", 100, 100, Color.BLUE, new Coordinates(0, 0)));
    view.display();
    while (true) {
      Thread.sleep(1000);
      SwingUtilities.invokeLater(()->view.animate());
    }
  }
}