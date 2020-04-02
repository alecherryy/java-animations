package cs5004.easyanimator.util;


public interface TweenModelBuilder<T> {

  TweenModelBuilder<T> addOval(
      String name,
      float x, float y,
      float xRadius, float yRadius,
      float red, float green, float blue,
      int appear, int disappear);

  TweenModelBuilder<T> addRectangle(
      String name,
      float minX, float minY,
      float width, float height,
      float red, float green, float blue,
      int appear, int disappear);

  TweenModelBuilder<T> addMove(
      String name,
      float fromX, float fromY, float toX, float toY,
      int start, int end);

  TweenModelBuilder<T> addColorChange(
      String name,
      float oldR, float oldG, float oldB, float newR, float newG, float newB,
      int start, int end);

  TweenModelBuilder<T> addScaleChange(
          String name,
          float fromSx, float fromSy, float toSx, float toSy, int start, int end);

  T build();

}
