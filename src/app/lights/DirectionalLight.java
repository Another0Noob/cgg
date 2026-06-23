package app.lights;

import cgg_tools.Color;
import cgg_tools.Vec3;

public record DirectionalLight(Color color, Vec3 dir) implements Light {

  @Override
  public Color color_at(Vec3 receiver_position) {
    return color;
  }

  @Override
  public double distance(Vec3 receiver_position) {
    return Double.MAX_VALUE;
  }

  @Override
  public Vec3 to_light(Vec3 receiver_position) {
    return Vec3.normalize(Vec3.negate(dir));
  }

}
