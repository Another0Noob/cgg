package app;

import cgg_tools.Color;
import cgg_tools.Vec3;

public record PhongMaterial(Color k_ambient, Color k_diffuse, Color k_specular, double shine) implements Material {

  @Override
  public Color ambient(Hit hit, Color ambient_light) {
    return Color.multiply(k_ambient, ambient_light);
  }

  @Override
  public Color shade(Hit hit, Vec3 to_viewer, Vec3 to_light, Color incoming_light) {
    var normalNormal = Vec3.normalize(hit.normal());

    double diffuseDot = Vec3.dot(normalNormal, Vec3.normalize(to_light));

    double specularDot = Vec3.dot(
        Vec3.reflect(Vec3.normalize(to_light), normalNormal),
        Vec3.normalize(to_viewer));

    if (diffuseDot < 0) {
      return Color.black;
    }

    var diffuse = Color.multiply(
        diffuseDot,
        Color.multiply(k_diffuse, incoming_light));

    var specular = Color.multiply(
        Math.pow(specularDot, shine),
        Color.multiply(k_specular, incoming_light));

    return Color.add(diffuse, specular);
  }
}
