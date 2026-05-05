package app;

import cgg_tools.Color;
import cgg_tools.Vec3;

public interface Light {

  public Vec3 to_light(Vec3 receiver_position);

  public double distance(Vec3 receiver_position);

  public Color color_at(Vec3 receiver_position);
}
