package app;

import cgg_tools.Color;
import cgg_tools.Vec3;

public class DummyMaterial implements Material {

  @Override
  public Color ambient(Hit hit, Color ambient_light) {
    return Color.black;
  }

  @Override
  public Color shade(Hit hit, Vec3 to_viewer, Vec3 to_light, Color incoming_light) {
    Vec3 lightDir = Vec3.normalize(new Vec3(1, 1, 0.5));
    double cos_angle = Math.max(0, Vec3.dot(lightDir, hit.normal()));
    Color ambient = Color.multiply(0.1, incoming_light);
    Color diffuse = Color.multiply(0.9 * cos_angle, incoming_light);
    return Color.add(ambient, diffuse);
  }

}
