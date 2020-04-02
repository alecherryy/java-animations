package cs5004.easyanimator.model.animations;

/**
 * This is an enum of all the animations available to the user.
 * It includes: CHANGECOLOR, CHANGECOORDINATES, AND CHANGESIZE.
 */
public enum AnimationType { CHANGECOLOR, CHANGECOORDINATES, CHANGESIZE;

  /**
   * Returns the AnimattionType in a string and formats the string based on its value.
   *
   * @return the type in a string
   * @throws IllegalArgumentException if the type is null or invalid.
   */

  public String toString() {
    // initialize switch statement using shape type
    switch (this) {
      case CHANGESIZE:
        return "scaling";
      case CHANGECOLOR:
        return "changing color";
      case CHANGECOORDINATES:
        return "moving";
      default:
        throw new IllegalArgumentException("Invalid type.");
    }
  }
}
