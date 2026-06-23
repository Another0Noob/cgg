package app.d2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cgg_tools.Color;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public class ColoredDiscs implements Sampler {

  private List<DiscModel2D> list = new ArrayList<>();

  public ColoredDiscs(int num, double min_x, double max_x, double min_y, double max_y, double min_r, double max_r) {
    for (int i = 0; i < num; i++) {
      Random rand = new Random();
      Vec2 center = new Vec2(rand.nextDouble(min_x, max_x), rand.nextDouble(min_y, max_y));
      double radius = rand.nextDouble(min_r, max_r);
      Color color = new Color(rand.nextDouble(), rand.nextDouble(), rand.nextDouble());

      list.add(new DiscModel2D(center, radius, color));
    }

  }

  @Override
  public Color getColor(Vec2 p) {
    for (DiscModel2D disc : list) {
      if (disc.coversPoint(p)) {
        return disc.color();
      }
    }
    return Color.cyan;
  }

}
