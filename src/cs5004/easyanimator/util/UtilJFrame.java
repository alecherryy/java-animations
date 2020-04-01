package cs5004.easyanimator.util;//package cs5004.easyanimator.util;
//
//import java.awt.*;
//
//import cs5004.easyanimator.view.VisualAnimationView;
//
//public class UtilJFrame {
//  public static void main(String[] args) {
//    VisualAnimationView view = new VisualAnimationView("Java Easy Animator");
//    view.display();
//
//    EventQueue.invokeLater(() -> {
//      SwingTimerEx ex = new SwingTimerEx();
//      ex.setVisible(true);
//    });
//  }
//}

import java.awt.*;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.ModelItem;
import cs5004.easyanimator.model.shapes.Coordinates;
import cs5004.easyanimator.model.shapes.Rectangle;

public class UtilJFrame {

  private JFrame frame;

  public UtilJFrame(Model model) {

    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    AnimationModel items = new AnimationModelImpl();
    items.addShape(new Rectangle("S", 75, 75, Color.RED,
            new Coordinates(0, 0)));
    items.addShape(new Rectangle("R", 75, 75, Color.RED,
            new Coordinates(0, 0)));
    JPanel panel = new MyJPanel(items);
    panel.setDoubleBuffered(true);

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }

  void refresh() {
    frame.repaint();
  }

  public static void main(String[] args) throws InterruptedException {
    Controller controller = new Controller(400, 400);
    while (true) {
      Thread.sleep(100);
      SwingUtilities.invokeLater(()->controller.animate());
    }
  }
}

//"wires" gui and model
class Controller {

  private Model model;
  private UtilJFrame view;

  Controller(int sizeX, int sizeY){

    model = new Model(sizeX, sizeY);
    view = new UtilJFrame(model);
  }

  void animate() {
    int newAngle = (model.getAngle() < 360 ) ? model.getAngle() + 1 : 0 ;
    model.setAngle(newAngle);
    view.refresh();
  }
}

//represents the inforamtion the GUI needs
class Model {

  int sizeX, sizeY, angle = 0;

  public Model(int sizeX, int sizeY) {
    this.sizeX = sizeX;
    this.sizeY = sizeY;
  }

  int getSizeX() { return sizeX; }

  int getSizeY() {return sizeY;}

  int getAngle() {return angle;}

  //degrees
  void setAngle(int angle) {  this.angle = angle; }
}

//a JPanel with custom paint component
class MyJPanel extends JPanel {

  private AnimationModel model;

  public MyJPanel(AnimationModel model) {
    this.model = model;
    setPreferredSize(new Dimension(400, 400));
  }

  @Override
//  public void paintComponent(Graphics g) {
//    super.paintComponent(g);
//    Graphics2D g2d = (Graphics2D)g;
//    Graphics2D g3d = (Graphics2D)g;
//    g2d.setColor(Color.RED);
//    g3d.setColor(Color.YELLOW);
//    int sizeX = model.getSizeX(), sizeY = model.getSizeY();
//    g3d.rotate(Math.toRadians(360), sizeX / 4, sizeY / 4);
//    g2d.rotate(Math.toRadians(model.getAngle()), sizeX / 4, sizeY / 4);
//    g2d.fillRect(sizeX / 8, sizeY / 8, sizeX / 4, sizeY / 4);
//  }
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int i = 0;
    for (ModelItem obj : this.model.getModel()) {
      int R = (int) (Math.random() * 256);
      int G = (int) (Math.random() * 256);
      int B = (int) (Math.random() * 256);
      Color c = new Color(R, G, B);
      g.setColor(c);
      g.fillRect(10 + i, 10 + i, 50 + i, 50 + i);
      i = 10;
    }
  }
}