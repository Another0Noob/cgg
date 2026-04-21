package app;

import cgg_tools.Color;
import cgg_tools.Vec2;

public record DiscModel2D(Vec2 center, double radius, Color color) {

  public boolean coversPoint(Vec2 p) {
    return Vec2.length(Vec2.subtract(p, center)) <= radius;
  }
}
