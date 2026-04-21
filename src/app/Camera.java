package app;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class Camera {

  double a;
  double w;
  double h;

  Vec3 origin = Vec3.zero;

  public Camera(double a, double w, double h, Vec3 origin) {
    this.a = a;
    this.w = w;
    this.h = h;
    this.origin = origin;
  }

  public Ray generate_ray(Vec2 pos) {
    return new Ray(this.origin, new Vec3(-(this.w / 2) + pos.x(), this.h / 2 - pos.y(),
        -(this.w / 2) / Math.tan(Math.toRadians(this.a / 2))), 0, Double.POSITIVE_INFINITY);
  }

}
