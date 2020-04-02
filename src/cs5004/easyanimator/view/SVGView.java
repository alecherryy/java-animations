package cs5004.easyanimator.view;
import java.util.ArrayList;
import java.util.List;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

public class SVGView extends TextualView {
  /**
   * Constructs a TextualView object with its given speed, shapes list, and animations list.
   *
   * @param speed      the speed at which the animation happens as a double
   * @param shapes     the lists of the shapes present in the  model
   * @param animations the list of the animations present in model
   */
  public SVGView(float speed, ArrayList<Shapes> shapes, ArrayList<Animations> animations) {
    super(speed, shapes, animations);
  }

  @Override
  public String getTextDescription() {
    List<Shapes> shapes = this.getShapes();
    List<Animations> animations = this.getAnimations();
    float speed = this.getSpeed();

    String state = "<svg width=\"1000\" height=\"1000\" version=\"1.1\"\n"
        + "xmlns=\"http://www.w3.org/2000/svg\">\n";

    for (int i = 0; i < shapes.size(); i++) {
      Shapes currentShape = shapes.get(i);
      state += currentShape.toSVGTag();
      for (int j = 0; j < animations.size(); j++) {
        Animations currentA = animations.get(j);
        Shapes currentS = currentA.getShape();
        if (currentShape.getName().equals(currentS.getName())) {
          state += currentA.toSVGTag(this.getSpeed());
        }
      }
      state += currentShape.svgEndTag() + "\n";
    }
    state += "</svg>";

    return state;
  }
}
