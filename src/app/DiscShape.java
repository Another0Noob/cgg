package app;

import cgg_tools.Vec3;
import cgg_tools.Color;
import cgg_tools.Util;

public record DiscShape(Vec3 origin, double radius, Material material) implements Shape {

  @Override
  public Hit intersect(Ray r) {
    var n = Vec3.yAxis;
    var a = Vec3.dot(Vec3.subtract(origin, r.origin()), n);
    if (Util.almostEqual(a, 0)) {
      return null;
    }
    var b = Vec3.dot(r.dir(), n);
    if (Util.almostEqual(b, 0)) {
      return null;
    }
    var t = a / b;
    if (!r.is_valid(t)) {
      return null;
    }
    return new Hit(t, r.point_at(t), n, material);
  }
}
