package app;

import java.util.List;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class Raytracer implements Sampler {

  public Camera camera;
  public List<Sphere> scene;

  public Raytracer(List<Sphere> scene, Camera camera) {
    this.scene = scene;
    this.camera = camera;
  }

  @Override
  public Color getColor(Vec2 p) {
    var ray = camera.generate_ray(p);
    var min = Double.POSITIVE_INFINITY;
    Hit hit = null;
    for (Sphere sphere : scene) {
      var temp = sphere.intersect(ray);
      if (temp != null) {
        if (temp.t() < min) {
          min = temp.t();
          hit = temp;
        }
      }
    }

    if (hit != null) {
      return shade(hit.n(), hit.c());
    } else {
      return Color.white;
    }
  }

  static Color shade(Vec3 normal, Color color) {
    Vec3 lightDir = Vec3.normalize(new Vec3(1, 1, 0.5));
    double cos_angle = Math.max(0, Vec3.dot(lightDir, normal));
    Color ambient = Color.multiply(0.1, color);
    Color diffuse = Color.multiply(0.9 * cos_angle, color);
    return Color.add(ambient, diffuse);
  }

}
