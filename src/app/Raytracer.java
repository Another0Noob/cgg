package app;

import app.lights.Light;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Raytracer(Camera camera, Scene scene, Color bg) implements Sampler {

  @Override
  public Color getColor(Vec2 p) {
    var ray = camera.generate_ray(p);
    var hit = scene.shapes().intersect(ray);
    if (hit == null) {
      return bg;
    } else {
      var material = hit.material();
      Color color = material.ambient(hit, scene.ambient());
      for (Light light : scene.lights()) {
        var sRay = new Ray(
            hit.pos(),
            light.to_light(hit.pos()),
            Util.EPSILON,
            light.distance(hit.pos()));
        var sHit = scene.shapes().intersect(sRay);
        if (sHit == null) {
          color = Color.add(color, material.shade(hit,
              Vec3.negate(ray.dir()),
              light.to_light(hit.pos()),
              light.color_at(hit.pos())));

        }
      }
      return Color.clamp(color);
    }
  }
}
