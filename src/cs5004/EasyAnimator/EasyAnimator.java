//package cs5004.EasyAnimator;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.StringReader;
//import java.util.Scanner;
//
//import javax.swing.*;
//
//import cs5004.EasyAnimator.model.AnimationModel;
//import cs5004.EasyAnimator.model.AnimationModelImpl;
//import cs5004.EasyAnimator.model.TweenModelBuilder;
//import cs5004.EasyAnimator.util.AnimationReader;
//import cs5004.EasyAnimator.view.TextualView;
//import cs5004.EasyAnimator.view.View;
//
///**
// * Represents an animation runner. Compiles the MVC to represent an animation.
// */
//public class EasyAnimator {
//  /**
//   * Main method to run easy animation compiling model, view and controller.
//   *
//   * @param args arguments for the main method
//   */
//  public static void main(String[] args) {
//
//    Readable r = new StringReader(String.join(" ", args));
//
//    AnimationModel model = null;
//    Scanner scan = new Scanner(r);
//    String filename = "";
//    String viewType = "";
//    String output = "";
//    double speed = -1;
//
//    View view = null;
//
//    while (scan.hasNext()) {
//      String in = scan.next();
//
//      switch (in) {
//        case "-in":
//          if (filename.equals("") && scan.hasNext()) {
//            filename = scan.next();
//          }
//          break;
//        case "-view":
//          if (viewType.equals("") && scan.hasNext()) {
//            viewType = scan.next();
//          }
//          break;
//        case "-out":
//          if (output.equals("") && scan.hasNext()) {
//            output = scan.next();
//          }
//          break;
//        case "-speed":
//          if (speed == -1 && scan.hasNext()) {
//            speed = Double.parseDouble(scan.next());
//          }
//          break;
//        default:
//          JFrame frame = new JFrame();
//          frame.setSize(800, 800);
//          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//          break;
//      }
//    }
//
//    if (speed == -1) {
//      speed = 1;
//    }
//    if (output.equals("") || output.equals("out")) {
//      output = "System.out";
//    }
//
//    BufferedReader br = null;
//    try {
//      br = new BufferedReader(new FileReader("/resources/test.txt"));
//
//    } catch (Exception e) {
//      // do nothing
//    }
//
//    AnimationReader fileReader = new AnimationReader();
//    TweenModelBuilder<AnimationModel> builder = new AnimationModelImpl.AnimationModelBuilder();
//    fileReader.parseFile(br, builder);
//
//    try {
////      model = fileReader.parseFile(filename, simpleBuilder);
//    } catch (Exception e) {
//      //System.out.println(e.getMessage());
//      JFrame frame = new JFrame();
//      frame.setSize(100, 100);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
//    switch (viewType) {
//      case "text":
//        new TextualView((float) speed, model.getShapes(), model.getAnimations());
//        break;
//      case "visual":
////        new VisualAnimationView(model, view, speed);
//        break;
//      case "svg":
////        View svg = new SVGView(speed, shapes, animations);
////        svg.write(filename);
//        break;
//      default:
//        JFrame frame = new JFrame();
//        frame.setSize(800, 800);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//
////    try {
////      model.build();
////    } catch (Exception e) {
////      // do nothing
////    }
//  }
//}

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
    //to be implemented in next assignment
//    ModuleLayer.Controller controller = new ModuleLayer.Controller(new InputStreamReader(System.in), model);
    //IView view = new VisualView("Animation");
    //controller.setView(view);
//    Readable f = new FileReader("src/test.txt");
//    System.out.println(f);
    int timer = 0;
    String inFile = "src/cs5004/EasyAnimator/resources/test.txt";
    String viewType = "svg";
    String outFile = "src/cs5004/EasyAnimator/resources/test.svg";
    int fps = 1; //default value
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
          if (fps < 0) {
            throw new IllegalArgumentException("fps can not be negative");
          }
          fps = Integer.parseInt(args[i + 1]);
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
      AnimationModel model = AnimationReader.parseFile(in, new AnimationModelImpl.AnimationModelBuilder());

      View view = null;

      switch (viewType) {
      case "text":
         view = new TextualView(10, model.getShapes(), model.getAnimations());
        break;
      case "visual":
//        View visual = new VisualAnimationView(model, view, speed);
        break;
      case "svg":
        view = new SVGView(10, model.getShapes(), model.getAnimations());
        view.write("src/cs5004/EasyAnimator/resources/testout.svg");
        break;
    }
//      view.display();

    } catch (FileNotFoundException e) {
      System.out.println("File not found!");
    }

    //System.setOut(output);

  }
}
