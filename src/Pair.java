/**
 * This class represent a value pair of integers. It can be used
 * to represent coordinates or sizes.
 */
public class Pair {
  public final int x;
  public final int y;

  /**
   * This is the Pair class constructor; it takes two integer
   * as its parameters, an x value and a y value.
   *
   * @param x first pair item
   * @param y second pair item
   */
  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Returns the x value of the Pair.
   *
   * @return x value
   */
  public int getX() {
    return this.x;
  }

  /**
   * Returns the y value of the Pair.
   *
   * @return y value
   */
  public int getY() {
    return this.y;
  }

  /**
   * Returns the Pair in a string.
   *
   * @return the pair in a string
   */
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
