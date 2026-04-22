package app;

import cgg_tools.Vec3;

public record Ray(Vec3 x0, Vec3 d, double t_min, double t_max) {

  public Vec3 point_at(double t) {
    return Vec3.add(x0, Vec3.multiply(t, d));
  }

  public boolean is_valid(double t) {
    return t_min <= t && t <= t_max;
  }

}
