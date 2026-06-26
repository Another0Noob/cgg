package app.materials;

import app.Hit;
import cgg_tools.Color;
import cgg_tools.RandomUtil;
import cgg_tools.Sampler;
import cgg_tools.Vec3;

public record DiffuseMaterial(Sampler albedo) implements Material {

  @Override
  public boolean does_reflection() {
    return true;
  }

  @Override
  public Vec3 generate_reflection_direction(Hit hit, Vec3 to_viewer) {
    Vec3 r = RandomUtil.randomDirection();
    while (!(Vec3.length(r) <= 1)) {
      r = RandomUtil.randomDirection();
    }
    return Vec3.add(r, hit.normal());
  }

  @Override
  public Color shade(Hit hit, Vec3 to_viewer, Vec3 to_light, Color incoming_light) {
    return Color.multiply(incoming_light, albedo.getColor(hit.uv()));
  }

}
