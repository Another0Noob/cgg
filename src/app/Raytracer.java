package app;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Raytracer(Camera camera, GroupShape shapes, Scene lights, Color bg) implements Sampler {

  @Override
  public Color getColor(Vec2 p) {
    var ray = camera.generate_ray(p);
    var hit = shapes.intersect(ray);
    if (hit == null) {
      return bg;
    } else {
      Color color = Color.black;
      for (Light light : lights.lights()) {
        color = Color.add(color,
            hit.material().shade(hit,
                Vec3.negate(ray.dir()),
                light.to_light(hit.pos()),
                light.color_at(hit.pos())));
      }
      return Color.add(color, hit.material().ambient(hit, lights.ambient()));
    }
  }
}
