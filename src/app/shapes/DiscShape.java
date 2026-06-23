package app.shapes;

import app.Hit;
import app.Ray;
import app.materials.Material;

import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

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

    var point = r.point_at(t);
    var diff = Vec3.subtract(point, origin);
    if (Vec3.length(diff) > radius) {
      return null;
    }

    var u = (diff.x() + radius) / (radius * 2);
    var v = 1 - (diff.z() + radius) / (radius * 2);

    return new Hit(t, point, n, material, new Vec2(u, v));
  }
}
