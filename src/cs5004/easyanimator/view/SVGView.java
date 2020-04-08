package cs5004.easyanimator.view;

import java.util.ArrayList;

import cs5004.easyanimator.model.animations.Animations;
import cs5004.easyanimator.model.shapes.Shapes;

/**
 * This class represents an SVGView, it extends the TextualView class and includes
 * all methods available on the View interface.
 */
public class SVGView extends TextualView {
  private ArrayList<Integer> settings;
  /**
   * Class constructor for the SVGView object with its given speed,
   * shapes list and animations list.
   *
   * @param speed the speed of the animation
   * @param settings the lists of the view boundaries
   * @param shapes the lists of the shapes in the  model
   * @param animations the list of the animations in model
   */
  public SVGView(float speed, ArrayList<Integer> settings,
                 ArrayList<Shapes> shapes, ArrayList<Animations> animations) {
    super(speed, shapes, animations);
    this.settings = settings;
  }


  /**
   * Returns a list of integers that represent the settings.
   *
   * @return a list representing the settings.
   */
  public ArrayList<Integer> getSettings() {
    return this.settings;
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
    // set overflow to "auto" to enable scrolling
    svg.append("<svg width=\"" + this.settings.get(2) + "\" height=\"" + this.settings.get(3)
            + "\" version=\"1.1\" overflow=\"auto\" "
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
