package app.materials;

import app.Hit;
import cgg_tools.Color;
import cgg_tools.Vec3;

public record MirrorMaterial(double factor) implements Material {

  @Override
  public boolean does_reflection() {
    return true;
  }

  @Override
  public Vec3 generate_reflection_direction(Hit hit, Vec3 to_viewer) {
    return Vec3.reflect(to_viewer, hit.normal());
  }

  @Override
  public Color shade(Hit hit, Vec3 to_viewer, Vec3 to_light, Color incoming_light) {
    return Color.multiply(factor, incoming_light);
  }

}
