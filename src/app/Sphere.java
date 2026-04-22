package app;

import cgg_tools.Color;
import cgg_tools.Util;
import cgg_tools.Vec3;

public record Sphere(Vec3 center, double radius) {

  public Hit intersect(Ray r) {
    Vec3 x0o = Vec3.subtract(r.x0(), center);
    double a = Vec3.dot(r.d(), r.d());
    if (Util.almostEqual(a, 0)) {
      return null;
    }
    double b = 2 * Vec3.dot(x0o, r.d());
    double c = Vec3.dot(x0o, x0o) - Math.pow(radius, 2);

    double temp = Math.pow(b, 2) - 4 * a * c;
    double t;
    if (temp < 0) {
      return null;
    } else if (Util.almostEqual(temp, 0)) {
      t = (-b + Math.sqrt(temp)) / (2 * a);
      if (!r.is_valid(t)) {
        return null;
      }
    } else {
      double t1 = (-b + Math.sqrt(temp)) / (2 * a);
      double t2 = (-b - Math.sqrt(temp)) / (2 * a);

      if (r.is_valid(t1) && r.is_valid(t2)) {
        t = Math.min(t1, t2);
      } else if (r.is_valid(t1)) {
        t = t1;
      } else if (r.is_valid(t2)) {
        t = t2;
      } else {
        return null;
      }
    }

    Vec3 xt = r.point_at(t);
    Vec3 n = Vec3.normalize(Vec3.subtract(xt, center));

    return new Hit(t, xt, n, Color.cyan);
  }
}
