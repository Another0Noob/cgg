package app.shapes;

import app.Hit;
import app.Ray;
import app.materials.Material;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record BackgroundShape(Material material) implements Shape {

  @Override
  public Hit intersect(Ray r) {
    var t = Double.POSITIVE_INFINITY;
    if (t > r.t_max()) {
      return null;
    }
    var p = r.point_at(t);
    // TODO: u, v = 0
    return new Hit(t, p, Vec3.normalize(Vec3.negate(r.dir())), material, Vec2.zero);
  }

}
