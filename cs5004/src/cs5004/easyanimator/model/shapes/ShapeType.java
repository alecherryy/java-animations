package cs5004.easyanimator.model.shapes;

/**
 * This is an enum of all the Shapes available to the
 * user. It includes: RECTANGLE and OVAL.
 */
public enum ShapeType {
  RECTANGLE, OVAL;

  public String toString() {
    if (this == RECTANGLE) {
      return "rectangle";
    }
    else if (this == OVAL) {
      return "oval";
    }
    else {
      throw new AssertionError("Shape must be a rectangle or an oval.");
    }
  }
}
