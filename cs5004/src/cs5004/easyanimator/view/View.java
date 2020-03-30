package cs5004.easyanimator.view;

import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;

import javax.swing.*;

import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shape;


public interface View {

  String getDescription();

  void writeOut(String fileName);

  AnimationModel getModel();

  double getTick();

  void makeVisible();

  void refresh();

  void setShapes(List<Shape> shapes);

  List<Shape> getShapes();

  List<Animations> getAnimations();



}