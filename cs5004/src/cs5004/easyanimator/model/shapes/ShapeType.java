package cs5004.easyanimator.model.shapes;

/**
 * This is an enum of all the Shapes available to the user.
 * It includes: RECTANGLE and OVAL.
 */
public enum ShapeType {
  RECTANGLE, OVAL;

  /**
   * Returns the ShapeType in a string and formats the string based on its value.
   *
   * @return the type in a string
   * @throws IllegalArgumentException if the type is null or invalid
   *                                  (i.e., not a rectangle nor oval)
   */
  public String toString() {
    // initialize switch statement using shape type
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
