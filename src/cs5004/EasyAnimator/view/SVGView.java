package cs5004.EasyAnimator.view;
import java.util.ArrayList;

import cs5004.EasyAnimator.model.animations.Animations;
import cs5004.EasyAnimator.model.shapes.Shapes;

/**
 * This class represents an SVGView, it extends the TextualView class and includes
 * all methods available on the View interface.
 */
public class SVGView extends TextualView {

  /**
   * Class constructor for the SVGView object with its given speed,
   * shapes list and animations list.
   *
   * @param speed the speed of the animation
   * @param shapes the lists of the shapes in the  model
   * @param animations the list of the animations in model
   */
  public SVGView(float speed, ArrayList<Shapes> shapes, ArrayList<Animations> animations) {
    super(speed, shapes, animations);
  }

  /**
   * Returns the description of the view in a string.
   *
   * @return the view description in a string
   * @throws UnsupportedOperationException if the view does not support this method
   */
  public String getTextDescription() {
    StringBuilder svg = new StringBuilder();

    // create svg mark up
    svg.append("<svg width=\"1000\" height=\"1000\" version=\"1.1\" "
            + "xmlns=\"http://www.w3.org/2000/svg\">\n");

    for (Shapes s : this.getShapes()) {
      svg.append(s.toSVGTag());
      for (Animations a : this.getAnimations()) {
        if (s.getName().equals(a.getShape().getName())) {
          svg.append(a.toSVGTag(this.getSpeed()));
        }
      }
      svg.append(s.svgEndTag());
    }
    svg.append("</svg>");

    return svg.toString();
  }
}
