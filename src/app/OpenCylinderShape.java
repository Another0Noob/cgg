// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package app;

import static cgg_tools.Vec2.dot;

import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

/**
 * Represents a cylinder around the Y-axis, open at both ends
 *
 * @param radius   radius of the cylinder
 * @param y_min    lowest Y coordinate for the cylinder
 * @param y_max    highest Y coordinate for the cylinder
 * @param material material for the curved hull of the cylinder
 */
public record OpenCylinderShape(double radius, double y_min, double y_max, Material material) implements Shape {

  @Override
  public Hit intersect(Ray ray) {

    // origin and direction (x,z only)
    var o = new Vec2(ray.origin().x(), ray.origin().z());
    var d = new Vec2(ray.dir().x(), ray.dir().z());

    // calculations see ray-sphere-intersection slides by Schirmacher
    var a = dot(d, d);
    var b = 2 * dot(o, d);
    var c = dot(o, o) - radius * radius;
    var under_the_root = b * b - 4 * a * c;
    if (Util.almostEqual(a, 0) || under_the_root < 0)
      return null;

    // t, 3D position, and normal of two intersection points
    var t1 = (-b + Math.sqrt(under_the_root)) / (2 * a);
    var t2 = (-b - Math.sqrt(under_the_root)) / (2 * a);
    var x1 = ray.point_at(t1);
    var x2 = ray.point_at(t2);

    boolean t1_valid = ray.is_valid(t1) && x1.y() >= y_min && x1.y() <= y_max;
    boolean t2_valid = ray.is_valid(t2) && x2.y() >= y_min && x2.y() <= y_max;

    // both invalid?
    if (!t1_valid && !t2_valid)
      return null;

    // at least one is valid, now choose the valid smaller one
    if (t1_valid && (!t2_valid || t1 <= t2)) {
      // t1 wins
      var n1 = Vec3.normalize(new Vec3(x1.x(), 0, x1.z()));
      return new Hit(t1, x1, n1, material);
    } else {
      // t2 wins
      var n2 = Vec3.normalize(new Vec3(x2.x(), 0, x2.z()));
      return new Hit(t2, x2, n2, material);
    }
  }

}
