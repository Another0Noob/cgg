package app;

import app.lights.Light;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Raytracer(Camera camera, Scene scene, Color bg, int trace_depth) implements Sampler {

  @Override
  public Color getColor(Vec2 p) {
    var ray = camera.generate_ray(p);
    return getColor(ray, trace_depth);
  }

  private Color getColor(Ray ray, int trace_depth) {
    if (trace_depth == 0) {
      return Color.black;
    }
    var hit = scene.shapes().intersect(ray);
    if (hit == null) {
      return bg;
    } else {
      var material = hit.material();
      Color color = Color.black;

      if (material.does_ambient_lighting()) {
        color = Color.add(color, material.ambient(hit, scene.ambient()));
      }

      Vec3 to_viewer = Vec3.negate(ray.dir());

      if (material.does_direct_lighting()) {
        for (Light light : scene.lights()) {
          var sRay = new Ray(
              hit.pos(),
              light.to_light(hit.pos()),
              Util.EPSILON,
              light.distance(hit.pos()));
          var sHit = scene.shapes().intersect(sRay);
          if (sHit == null) {
            color = Color.add(color, material.shade(hit,
                to_viewer,
                light.to_light(hit.pos()),
                light.color_at(hit.pos())));

          }
        }
      }

      if (material.does_emission()) {
        color = Color.add(color, material.emission(hit, to_viewer));
      }

      if (material.does_reflection()) {
        Vec3 reflect_dir = material.generate_reflection_direction(hit, to_viewer);
        Ray reflect_ray = new Ray(hit.pos(), reflect_dir, Util.EPSILON, Double.POSITIVE_INFINITY);
        Color incoming = getColor(reflect_ray, trace_depth - 1);
        Color fraction = material.shade(hit, to_viewer, reflect_dir, incoming);
        color = Color.add(color, fraction);
      }
      return Color.clamp(color);
    }
  }
}
