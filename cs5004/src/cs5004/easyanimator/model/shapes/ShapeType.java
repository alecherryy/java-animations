package cs5004.easyanimator.model.shapes;

/**
 * This is an enum of all the Shapes available to the user. It includes: RECTANGLE and OVAL.
 * Throws an IllegalArgumentException if the type is null or invalid (not a rectangle or oval).
 */
public enum ShapeType {
  RECTANGLE, OVAL;

  @Override
  public String toString() {
    switch (this) {
      case RECTANGLE:
        return "rectangle";
      case OVAL:
        return "oval";
      default:
        throw new IllegalArgumentException("Invalid type.");
    }
  }
}
