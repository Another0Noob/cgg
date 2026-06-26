package app.materials;

import app.Hit;
import cgg_tools.Color;
import cgg_tools.Vec3;
import cgg_tools.Sampler;

public record DiffuseEmitterMaterial(Sampler color) implements Material {

  @Override
  public boolean does_emission() {
    return true;
  }

  @Override
  public Color emission(Hit hit, Vec3 to_viewer) {
    return color.getColor(hit.uv());
  }

}
