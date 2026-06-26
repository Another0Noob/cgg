package app.materials;

import app.Hit;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec3;

public record PhongMaterial(Sampler k_ambient, Sampler k_diffuse, Sampler k_specular, Sampler shine)
    implements Material {

  @Override
  public Color ambient(Hit hit, Color ambient_light) {
    return Color.multiply(k_ambient.getColor(hit.uv()), ambient_light);
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
        Color.multiply(k_diffuse.getColor(hit.uv()), incoming_light));

    var specular = Color.multiply(
        Math.pow(specularDot, shine.getColor(hit.uv()).r()),
        Color.multiply(k_specular.getColor(hit.uv()), incoming_light));

    return Color.add(diffuse, specular);
  }

  @Override
  public boolean does_ambient_lighting() {
    return true;
  }

  @Override
  public boolean does_direct_lighting() {
    return true;
  }

}
