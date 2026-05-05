package app;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Raytracer(Camera camera, GroupShape scene, Color bg) implements Sampler {

  @Override
  public Color getColor(Vec2 p) {
    var ray = camera.generate_ray(p);
    var hit = scene.intersect(ray);
    if (hit == null) {
      return bg;
    } else {
      return hit.material().shade(hit, null, null, Color.cyan);
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
