package cs5004.easyanimator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import javax.swing.*;

import cs5004.easyanimator.controller.AnimationController;
import cs5004.easyanimator.controller.InteractiveViewController;
import cs5004.easyanimator.controller.SVGViewController;
import cs5004.easyanimator.controller.TextualViewController;
import cs5004.easyanimator.controller.VisualAnimationViewController;
import cs5004.easyanimator.model.AnimationModel;
import cs5004.easyanimator.model.AnimationModelImpl;
import cs5004.easyanimator.model.TweenModelBuilder;
import cs5004.easyanimator.util.AnimationReader;
import cs5004.easyanimator.view.InteractiveView;
import cs5004.easyanimator.view.SVGView;
import cs5004.easyanimator.view.TextualView;
import cs5004.easyanimator.view.View;
import cs5004.easyanimator.view.VisualAnimationView;

/**
 * This is the main() method which acts as the entry point for our program. Our program takes in
 * inputs as command-line arguments and provides the appropriate view to the user.
 */
public final class EasyAnimator {


  /**
   * Returns the correct view according to the string taken in.
   *
   * @param view  string representation of what type of view to output
   * @param model model for view to work on
   * @param speed speed to which we are setting the view
   * @return the view
   * @throws IllegalArgumentException if the String view is invalid
   */
  public static View createView(String view, AnimationModel model, float speed)
      throws IllegalArgumentException {
    if (view.equals("text")) {
      return new TextualView(speed, model.getShapes(), model.getAnimations());
    } else if (view.equals("visual")) {
      return new VisualAnimationView(speed, model.getSettings(), model.getShapes(),
          model.getAnimations());
    } else if (view.equals("svg")) {
      return new SVGView(speed, model.getSettings(), model.getShapes(), model.getAnimations());
    } else if (view.equals("playback")) {
      return new InteractiveView(speed, model.getShapes(), model.getAnimations(), model.getEnd());
    } else {
      throw new IllegalArgumentException("Invalid view type");
    }
  }

  /**
   * This is the entry point of our program.
   *
   * @param args takes in the command line arguments
   * @throws FileNotFoundException when a certain file that we are looking for is not found
   */
  public static void main(String[] args) throws FileNotFoundException {

    String source = "test";
    String type = "";
    String out = "";
    int speed = 1; //default value
    String token;
    Appendable output = null;
    AnimationModel model = null;
    View view = null;
    AnimationController controller = null;

    // even number of inputs should be provided
    for (int i = 0; i < args.length - 1; i += 2) {

      token = args[i];

      // parse the command line input
      switch (token) {
        case "-in":
          source = args[i + 1];
          break;
        case "-view":
          type = args[i + 1];
          break;
        case "-out":
          out = args[i + 1];
          break;
        case "-speed":
          if (speed < 0) {
            throw new IllegalArgumentException("fps can not be negative");
          }
          speed = Integer.parseInt(args[i + 1]);
          break;
        default:
          JFrame frame = new JFrame();
          frame.setSize(100, 100);
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          JOptionPane.showMessageDialog(frame, "Invalid input",
              "Error", JOptionPane.ERROR_MESSAGE);
      }
    }

    // throw exception if input file and view type are not specified
    if (source.equals("") || type.equals("")) {
      System.out.println("Input file name or type of View, can not be empty.");
    }

    // if the output set is not specified and the view needs it, the default should be System.out
    if (out.equals("") || out.equals("out")) {
      output = new OutputStreamWriter(System.out); //new PrintStream(System.out);
    } else {
      try {
        output = new PrintStream(new File(out));
      } catch (IOException ignore) {
        // ignore it
      }
    }

    // create the objects from the file, using the parser and the builder
    // throw an exception if file is not found
    AnimationReader fileReader = new AnimationReader();
    Readable in = new FileReader(source);
    TweenModelBuilder<AnimationModel> builder = new AnimationModelImpl.AnimationModelBuilder();


    try {
      model = fileReader.parseFile(in, builder);

    } catch (Exception e) {
      //System.out.println(e.getMessage());
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame,
          "Invalid file", "Error", JOptionPane.ERROR_MESSAGE);
    }

    try {
      view = createView(type, model, speed);
    } catch (Exception e) {
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame, "Invalid view type",
          "Error", JOptionPane.ERROR_MESSAGE);
    }

    switch (type) {
      case "text":
        controller = new TextualViewController(model, view, out);
        break;
      case "visual":
        controller = new VisualAnimationViewController(model, view, speed);
        break;
      case "svg":
        controller = new SVGViewController(view, out);
        break;
      case "playback":
        controller = new InteractiveViewController(model, view, speed, out);
        break;
      default:
        JFrame frame = new JFrame();
        frame.setSize(100, 100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JOptionPane.showMessageDialog(frame, "Invalid view type",
            "Error", JOptionPane.ERROR_MESSAGE);
    }

    try {
      controller.start();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      JFrame frame = new JFrame();
      frame.setSize(100, 100);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      JOptionPane.showMessageDialog(frame, "ERROR",
          "Error", JOptionPane.ERROR_MESSAGE);
    }
  }
}