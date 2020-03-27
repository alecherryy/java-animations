package cs5004.easyanimator.model.shapes;
import java.awt.Color;
import cs5004.easyanimator.model.Utils;

  public class Oval extends AbstractShape {

    public Oval(String name, int appear, int disappear, double x, double y, Color color, Pair pos) {
      super(name, ShapeType.OVAL, appear, disappear, x, y, color, pos);

    }

    @Override
    public String getLocation() {
      return "Center: " + Utils.getPositionString(this.getPosition());
    }

    @Override
    public String widthString() {
      return "X radius: ";
    }

    @Override
    public String heightString() {
      return "Y radius: ";
    }
  }

