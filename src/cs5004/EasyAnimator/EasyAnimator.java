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
 * This is the main() method which acts as the entry point for our program. Our program takes in
 * inputs as command-line arguments and provides the appropriate view to the user.
 */
public final class EasyAnimator {

  /**
   * This is the entry point of our program.
   *
   * @param args takes in the command line arguments
   * @throws FileNotFoundException when a certain file that we are looking for is not found
   */
  public static void main(String[] args) throws FileNotFoundException {

    String inFile = "";
    String viewType = "";
    String outFile = "";
    int speed = 1; //default value
    String token;
    Appendable output = null;

    // even number of inputs should be provided
    for (int i = 0; i < args.length - 1; i += 2) {

      token = args[i];

      switch (token) {
        case "-in":
          inFile = args[i + 1];
          break;
        case "-view":
          viewType = args[i + 1];
          break;
        case "-out":
          outFile = args[i + 1];
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

    //Providing an input file (the -in pair) and a view (the -view pair) are mandatory
    if (inFile.equals("") || viewType.equals("")) {
      System.out.println("Input file name or type of View, can not be empty.");
    }

    //If the output set is not specified and the view needs it, the default should be System.out
    if (outFile.equals("") || outFile.equals("out")) {
      output = new OutputStreamWriter(System.out); //new PrintStream(System.out);
    } else {
      try {
        output = new PrintStream(new File(outFile));
      } catch (IOException ignore) {
        // ignore it
      }
    }

    //create the objects from the file, using the parser and the builder
    //throw an exception if file is not found
    try {
      Readable in = new FileReader(inFile);
      AnimationModel model = AnimationReader.parseFile(in,
              new AnimationModelImpl.AnimationModelBuilder());

      View view = null;

      switch (viewType) {
      case "text":
         view = new TextualView(10, model.getShapes(), model.getAnimations());
        break;
      case "visual":
        View visual = new VisualAnimationView(speed, model.getShapes());
        break;
      case "svg":
        view = new SVGView(100, model.getShapes(), model.getAnimations());
        view.write("src/cs5004/EasyAnimator/resources/test.svg");
        break;
    }
      view.display();

    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }

    //System.setOut(output);

  }
}
