package app;

import java.util.List;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public class ColoredDiscs implements Sampler {

  private List<DiscModel2D> list;

  public ColoredDiscs(int num, int min_x, int max_x, int min_y, int max_y, double min_r, double max_r) {
    for (int i = 0; i < num; i++) {
      list.add(new DiscModel2D(null, i, null));
    }

  }

  @Override
  public Color getColor(Vec2 p) {
    for (DiscModel2D disc : list) {
      if (disc.coversPoint(p)) {
        return disc.color();
      }
    }
    return Color.green;
  }

}
