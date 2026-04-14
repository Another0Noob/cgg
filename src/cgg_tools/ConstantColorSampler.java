// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

public record ConstantColorSampler(Color color) implements Sampler {

  @Override
  public Color getColor(Vec2 at) {
    return color;
  }
}
