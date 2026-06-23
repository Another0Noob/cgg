package app.samplers;

import cgg_tools.Color;
import cgg_tools.Mat4x4;
import cgg_tools.Sampler;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record TransformSampler(Sampler sampler, Mat4x4 trans) implements Sampler {

  @Override
  public Color getColor(Vec2 p) {
    var res = Mat4x4.multiplyPoint(Mat4x4.invert(trans), new Vec3(p.u(), p.v(), 0));
    return sampler.getColor(res.uv());
  }

}
