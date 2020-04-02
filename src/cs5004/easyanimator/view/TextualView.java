package cs5004.easyanimator.view;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import cs5004.easyanimator.model.Utils;
import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class implements all the methods in View and represents the view as a text object.
 */
public class TextualView implements View {
  private float speed;
  private ArrayList<Shapes> shapes;
  private ArrayList<Animations> animations;

  /**
   * Constructs a TextualView object with its given speed, shapes list, and animations list.
   *
   * @param speed the speed at which the animation happens as a double
   * @param shapes the lists of the shapes present in the  model
   * @param animations the list of the animations present in model
   */
  public TextualView(float speed, ArrayList<Shapes> shapes, ArrayList<Animations> animations) {
    this.speed = speed;
    this.shapes = shapes;
    this.animations = animations;
  }

  @Override
  public void display() throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Textual view does not include this functionality.");

  }

  @Override
  public void setShapes(ArrayList<Shapes> shapes) {
    this.shapes = shapes;

  }

  @Override
  public void refresh() {
    throw new UnsupportedOperationException("Textual view does not include this functionality.");

  }

  @Override
  public ArrayList<Shapes> getShapes() {
    return this.shapes;
  }

  @Override
  public ArrayList<Animations> getAnimations() {
    return this.animations;
  }

  @Override
  public String getTextDescription() throws UnsupportedOperationException {
    String state = "";

    if (shapes.size() != 0) {
      state += "Shapes:\n";
    }

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      double newAppearTime = (double) currentShape.getAppearTime() / this.speed;
      double newDisappearTime = (double) currentShape.getDisappearTime() / this.speed;
      String currentStr = "";
      currentStr += "Name: " + currentShape.getName() + "\n" + "Type: "
          + currentShape.getShapeType().toString() + "\n"
          + currentShape.getLocation() + ", "
          + currentShape.getDimensions() + ", Color: "
          + Utils.colorAsString(currentShape.getColor()) + "\n"
          + "Appears at t=" + newAppearTime + "s\n" + "Disappears at t="
          + newDisappearTime + "s\n";
      state += currentStr+ "\n";
    }
    

    for (int i = 0; i < animations.size(); i++) {
      Animations currentAnimation = animations.get(i);
      double newAppearTime = (double) currentAnimation.getStartTime() / this.speed;
      double newDisappearTime = (double) currentAnimation.getEndTime() / this.speed;

      String currentStr = "";
      currentStr+= "shape " + currentAnimation.getShape().getName() + " "
          + currentAnimation.getChange() + " from "
          + currentAnimation.getStartState() + " to " + currentAnimation.getEndState()
          + " from t=" + newAppearTime
          + "s to t=" + newDisappearTime + "s";
      state += currentStr + "\n";
    }
    return state;
  }

  @Override
  public void write(String fileName) {
    String description = this.getTextDescription();
    try {
      BufferedWriter output;
      if (fileName.equals("System.out")) {
        output = new BufferedWriter(new OutputStreamWriter(System.out));
      } else {
        File file = new File(fileName);
        output = new BufferedWriter(new FileWriter(file));
      }
      output.write(description);
      output.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public float getSpeed() throws UnsupportedOperationException {
    return this.speed;
  }

  @Override
  public void displayErrorMsg(String error) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Textual view does not include this functionality.");

  }

  @Override
  public void displayButton(ActionListener event) throws UnsupportedOperationException {
    throw new UnsupportedOperationException("Textual view does not include this functionality.");
  }
}

