package app;

import cgg_tools.Color;
import cgg_tools.Vec3;

public record PointLight(Color color, Vec3 pos) implements Light {

  @Override
  public Color color_at(Vec3 receiver_position) {
    return Color.multiply(color, 1 / Vec3.squaredLength(Vec3.subtract(pos, receiver_position)));
  }

  @Override
  public double distance(Vec3 receiver_position) {
    return Vec3.distance(pos, receiver_position);
  }

  @Override
  public Vec3 to_light(Vec3 receiver_position) {
    return Vec3.normalize(Vec3.subtract(pos, receiver_position));
  }

}
