package cs5004.EasyAnimator.util;

import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

import cs5004.EasyAnimator.model.TweenModelBuilder;

/**
 * A helper to read animation data and construct an animation from it.
 */
public class AnimationReader {

  /**
   * A factory for producing new animations, given a source of shapes and a
   * builder for constructing animations.
   *
   * <p>
   * The input file format consists of two types of lines:
   * <ul>
   * <li>Shape lines: the keyword "shape" followed by two identifiers (i.e.
   * alphabetic strings with no spaces), giving the unique name of the shape,
   * and the type of shape it is.</li>
   * <li>Motion lines: the keyword "motion" followed by an identifier giving the name
   * of the shape to move, and 16 integers giving the initial and final conditions of the motion:
   * eight numbers giving the time, the x and y coordinates, the width and height,
   * and the red, green and blue color values at the start of the motion; followed by 
   * eight numbers for the end of the motion.  See {@link AnimationBuilder#addMotion}</li>
   * </ul>
   * </p>
   *
   * @param readable The source of data for the animation
   * @param builder  A builder for helping to construct a new animation
   * @param <Doc>    The main model interface type describing animations
   * @return
   */
  public static <Doc> Doc parseFile(Readable readable, TweenModelBuilder<Doc> builder) {
    Objects.requireNonNull(readable, "Must have non-null readable source");
    Objects.requireNonNull(builder, "Must provide a non-null AnimationBuilder");
    Scanner s = new Scanner(readable);
    // Split at whitespace, and ignore # comment lines
    s.useDelimiter(Pattern.compile("(\\p{Space}+|#.*)+"));
    while (s.hasNext()) {
      String word = s.next();
      switch (word) {
        case "canvas":
          readCanvas(s, builder);
          break;
        case "shape":
          readShape(s, builder);
          break;
        case "motion":
          readMotion(s, builder);
          break;
        default:
          throw new IllegalStateException("Unexpected keyword: " + word + s.nextLine());
      }
    }
    return builder.build();
  }

  private static <Doc> void readCanvas(Scanner s, TweenModelBuilder<Doc> builder) {
    int[] vals = new int[4];
    String[] fieldNames = {"left", "top", "width", "height"};
    for (int i = 0; i < 4; i++) {
      vals[i] = getInt(s, "Canvas", fieldNames[i]);
    }
//    builder.setBounds(vals[0], vals[1], vals[2], vals[3]);
  }

  private static <Doc> void readShape(Scanner s, TweenModelBuilder<Doc> builder) {
    String name;
    String type;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Shape: Expected a name, but no more input available");
    }
    if (s.hasNext()) {
      type = s.next();
    } else {
      throw new IllegalStateException("Shape: Expected a type, but no more input available");
    }

    switch (type) {
      case "rectangle":
        builder.addRectangle(name, 180, 180, 200, 200, Color.RED, 1, 200);
        break;
      case "ellipse":
        builder.addOval(name, 180, 180, 200, 200, Color.RED, 1, 200);
        break;
      default:
        throw new IllegalStateException("This shape is not supported");
    }
  }

  private static <Doc> void readMotion(Scanner s, TweenModelBuilder<Doc> builder) {
    String[] fieldNames = new String[] {
      "initial time",
      "initial x-coordinate", "initial y-coordinate",
      "initial width", "initial height",
      "initial red value", "initial green value", "initial blue value",
      "final time",
      "final x-coordinate", "final y-coordinate",
      "final width", "final height",
      "final red value", "final green value", "final blue value",
    };
    int[] vals = new int[16];
    String name;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Motion: Expected a shape name, but no more input available");
    }
    for (int i = 0; i < 16; i++) {
      vals[i] = getInt(s, "Motion", fieldNames[i]);
    }
    int startT = vals[0];
    int endT = vals[8];
    int width = vals[3];
    ArrayList<Integer> test = new ArrayList<Integer>();
    int height = vals[4];
    Color color = new Color(vals[5], vals[6], vals[7]);
    Color newColor = new Color(vals[13], vals[14], vals[15]);

    if (vals[1] != vals[9] || vals[2] != vals[10]) {
      builder.addMove(name, vals[1], vals[2], vals[9], vals[10], startT, endT);
    }
    else if (color != newColor) {
      builder.addColorChange(name, color, newColor, startT, endT);
    }
    else if (width != vals[11] || height != vals[12]) {
      builder.addSizeChange(name, width, height, vals[11], vals[12], startT, endT);
    }
  }

  private static int getInt(Scanner s, String label, String fieldName) {
    if (s.hasNextInt()) {
      return s.nextInt();
    } else if (s.hasNext()) {
      throw new IllegalStateException(
              String.format("%s: expected integer for %s, got: %s", label, fieldName, s.next()));
    } else {
      throw new IllegalStateException(
              String.format("%s: expected integer for %s, but no more input available",
                            label, fieldName));
    }
  }

}