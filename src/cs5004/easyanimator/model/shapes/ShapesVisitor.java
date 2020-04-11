package cs5004.easyanimator.model.shapes;

/**
 * We created a visitor design pattern so we can separate our algorithm from the object structure
 * on which it operates. This way, we can add new operations to existing object structures
 * without modifying the structures. So, in our AnimationModelImpl class, we can create new
 * shapes that follow the same pattern as the original shapes but have the appropriate changes
 * made to them (such as a new color, new size, or new position). This way, we ensure that our
 * model follows the second SOLID principle -- our classes should be open for extension but closed
 * for modification.
 */

public interface ShapesVisitor {
  /**
   * Returns the changed shape.
   *
   * @param oval the oval we are 'visiting,' and will make changes to.
   * @return new shape, of ShapeType oval, which will have the appropriate changes made to it.
   */
  Shapes visit(Oval oval);

  /**
   * Overloaded function. Returns shape that has the appropriate changes made to it.
   *
   * @param rectangle the rectangle we are 'visiting,' and will make changes to.
   * @return new shape, of ShapeType rectangle, which will have the appropriate changes made to it.
   */
  Shapes visit(Rectangle rectangle);
}
