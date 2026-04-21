// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik 04/2026
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Color;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    int width = 1280;
    int height = 720;
    Camera camera = new Camera(45, width, height, new Vec3(0, 0, 0));

    // This object defines the contents of the image.
    // It must implement the cgg_tools.Sampler interface.

    Ray test = camera.generate_ray(new Vec2(width / 2, height / 2));
    System.out.println(test);
    // iterate over all pixelzzz of the image
    var image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        Ray ray = camera.generate_ray(new Vec2(i, j));
        Vec3 temp = Vec3.normalize(ray.d());
        Color color = new Color(temp.x(), temp.y(), 0);
        image.setPixel(i, j, color);
      }
    }

    // Write the image to disk.
    image.writePNG("a02_cam_directions");
  }
}
