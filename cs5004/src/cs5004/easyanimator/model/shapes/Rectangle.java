package cs5004.easyanimator.model.shapes;
import java.awt.Color;
import cs5004.easyanimator.model.Utils;

public class Rectangle extends AbstractShape {

  public Rectangle(String name, int appear, int disappear, double x, double y, Color color,
                 Pair pos) {
    super(name, ShapeType.RECTANGLE, appear, disappear, x, y, color, pos);

  }

  @Override
  public String getLocation() {
    return "Min left corner: " + Utils.getPositionString(this.getPosition());
  }

  @Override
  public String widthString() {
    return "Width: ";
  }

  @Override
  public String heightString() {
    return "Height: ";
  }
}

