package app;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class Camera {

  double a;
  double w;
  double h;

  Vec3 origin;

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

  public double getA() {
    return a;
  }

  public void setA(double a) {
    this.a = a;
  }

  public double getW() {
    return w;
  }

  public void setW(double w) {
    this.w = w;
  }

  public double getH() {
    return h;
  }

  public void setH(double h) {
    this.h = h;
  }

  public Vec3 getOrigin() {
    return origin;
  }

  public void setOrigin(Vec3 origin) {
    this.origin = origin;
  }

}
