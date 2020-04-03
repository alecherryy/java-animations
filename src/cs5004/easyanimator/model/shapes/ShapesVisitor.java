package cs5004.easyanimator.model.shapes;

/**
 * This is the ShapeVisitor interface, it's used to add new operations
 * to existing object structures without modifying the structures.
 */
public interface ShapesVisitor {
  /**
   * Returns the changed shape.
   *
   * @param oval the oval we are 'visiting,' and will make changes to.
   * @return a new oval with the appropriate changes made to it
   */
  Shapes visit(Oval oval);

  /**
   * Overloaded function. Returns shape that has the appropriate changes made to it.
   *
   * @param rectangle the rectangle we are 'visiting,' and will make changes to.
   * @return a new rectangle with the appropriate changes made to it
   */
  Shapes visit(Rectangle rectangle);
}