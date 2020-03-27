package cs5004.easyanimator.model.animations;

/**
 * This is an enum of all the animations available to the user. It includes: CHANGECOLOR,
 * CHANGECOORDINATES, AND CHANGESIZE. Throws an IllegalArgumentException if the type is
 * null or invalid.
 */

public enum AnimationType {
  CHANGECOLOR, CHANGECOORDINATES, CHANGESIZE;

  @Override
  public String toString() {
    switch (this) {
      case CHANGECOLOR:
        return "change color";
      case CHANGECOORDINATES:
        return "change coordinates";
      case CHANGESIZE:
        return "change size";
      default:
        throw new IllegalArgumentException("Invalid type.");
    }
  }
}
