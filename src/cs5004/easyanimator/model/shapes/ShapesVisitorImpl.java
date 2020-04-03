package cs5004.easyanimator.model.shapes;
import java.awt.Color;

/**
 * This class implements the ShapeVisitor interface and all of its methods in order to create
 * a copy of the shape object passed in as a parameter (either a rectangle or an oval).
 */

public class ShapesVisitorImpl implements ShapesVisitor {

  @Override
  public Shapes visit(Oval oval) {
    Coordinates pos = new Coordinates(oval.getPosition().getX(), oval.getPosition().getY());
    // returns a Color object.
    Color col = oval.getColor();
    Color rgbCol = new Color(col.getRed(), col.getGreen(),
            col.getBlue());
    Shapes newOval = new Oval(oval.getName(), oval.getAppearTime(), oval.getDisappearTime(),
            oval.getD1(), oval.getD2(), rgbCol, pos);
    return newOval;
  }

  @Override
  public Shapes visit(Rectangle rectangle) {
    Coordinates pos = new Coordinates(rectangle.getPosition().getX(),
            rectangle.getPosition().getY());
    // returns a Color object.
    Color col = rectangle.getColor();
    Color rgbCol = new Color(col.getRed(), col.getGreen(), col.getBlue());
    Shapes newRectangle = new Rectangle(rectangle.getName(), rectangle.getAppearTime(),
            rectangle.getDisappearTime(), rectangle.getD1(), rectangle.getD2(), rgbCol, pos);
    return newRectangle;
  }
}