//package cs5004.easyanimator.util;
//
//import java.awt.*;
//
//import javax.swing.*;
//
//import cs5004.easyanimator.view.Board;
//
//public class SwingTimerEx extends JFrame {
//
//  public SwingTimerEx() {
//
//    initUI();
//  }
//
//  private void initUI() {
//
//    add(new Board());
//
//    setResizable(false);
//    pack();
//
//    setTitle("Star");
//    setLocationRelativeTo(null);
//    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//  }
//
//  public static void main(String[] args) {
//
//    EventQueue.invokeLater(() -> {
//      SwingTimerEx ex = new SwingTimerEx();
//      ex.setVisible(true);
//    });
//  }
//}