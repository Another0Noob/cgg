package app;

import cgg_tools.Vec3;

public record Ray(Vec3 origin, Vec3 dir, double t_min, double t_max) {

  public Vec3 point_at(double t) {
    return Vec3.add(origin, Vec3.multiply(t, dir));
  }

  public boolean is_valid(double t) {
    return t_min <= t && t <= t_max;
  }

}
