// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik 04/2026
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Vec2;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;

    // This object defines the contents of the image.
    // It must implement the cgg_tools.Sampler interface.
    var obj = new ColoredDiscs(500, 30.0, 1250.0, 20.0, 700.0, 10.0, 100.0);

    // iterate over all pixelzzz of the image
    var image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        image.setPixel(i, j, obj.getColor(new Vec2(i, j)));
      }
    }

    // Write the image to disk.
    image.writePNG("a01-discs");
  }
}
