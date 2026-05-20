package app;

import cgg_tools.Vec3;
import cgg_tools.Util;

public record RectShape(Vec3 center, Vec3 dir, double width, double height, Material material) implements Shape {

  @Override
  public Hit intersect(Ray r) {
    var a = Vec3.dot(Vec3.subtract(center, r.origin()), dir);
    if (Util.almostEqual(a, 0)) {
      return null;
    }

    var b = Vec3.dot(r.dir(), dir);
    if (Util.almostEqual(b, 0)) {
      return null;
    }

    var t = a / b;
    if (!r.is_valid(t)) {
      return null;
    }

    var point = r.point_at(t);

    var diff = Vec3.subtract(point, center);

    if (Math.abs(diff.x()) > width / 2 || Math.abs(diff.z()) > height / 2) {
      return null;
    }

    return new Hit(t, point, dir, material);

  }

}
