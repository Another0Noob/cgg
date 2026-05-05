package app;

import cgg_tools.Color;
import cgg_tools.Util;
import cgg_tools.Vec3;

public record BackgroundShape(Color color) implements Shape {

  @Override
  public Hit intersect(Ray r) {
    var t = Double.POSITIVE_INFINITY;
    if (t > r.t_max()) {
      return null;
    }
    var p = r.point_at(t);
    return new Hit(t, p, Vec3.normalize(Vec3.negate(r.dir())), color);
  }

}
