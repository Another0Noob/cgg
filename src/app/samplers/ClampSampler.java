package app.samplers;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Util;
import cgg_tools.Vec2;

public record ClampSampler(Sampler sampler, Color color) implements Sampler {

  @Override
  public Color getColor(Vec2 p) {
    if (Util.in(0, 1, p.u()) && Util.in(0, 1, p.v())) {
      return sampler.getColor(p);
    } else {
      return color;
    }
  }

}
