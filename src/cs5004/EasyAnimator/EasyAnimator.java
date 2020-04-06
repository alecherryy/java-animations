package cs5004.EasyAnimator;

//import cs5004.animator.controller.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

import cs5004.EasyAnimator.model.AnimationModel;
import cs5004.EasyAnimator.model.AnimationModelImpl;
import cs5004.EasyAnimator.util.AnimationReader;
import cs5004.EasyAnimator.view.SVGView;
import cs5004.EasyAnimator.view.TextualView;
import cs5004.EasyAnimator.view.View;
import cs5004.EasyAnimator.view.VisualAnimationView;

/**
 * This is the main() method which acts as the entry point for our program.
 * Our program takes in inputs as command-line arguments and provides
 * the appropriate view to the user.
 */
public final class EasyAnimator {

  /**
   * This is the entry point of our program.
   *
   * @param args takes in the command line arguments
   * @throws FileNotFoundException when a certain file that we are looking for is not found
   */
  public static void main(String[] args) throws FileNotFoundException {

    String source = "";
    String type = "";
    String out = "";
    int speed = 1; //default value
    String token;
    Appendable output = null;

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
          System.out.println("Encountered error!");
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
    try {
      Readable in = new FileReader(source);
      AnimationModel model = AnimationReader.parseFile(in,
              new AnimationModelImpl.AnimationModelBuilder());

      View view = null;

      switch (type) {
        case "text":
          view = new TextualView(speed, model.getShapes(), model.getAnimations());
          view.write(out);
          break;
        case "visual":
          view = new VisualAnimationView(speed, model.getSettings(), model.getShapes());
          view.display();
          break;
        case "svg":
          view = new SVGView(speed, model.getSettings(), model.getShapes(), model.getAnimations());
          view.write(out);
          break;
      }

    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }
  }
}
