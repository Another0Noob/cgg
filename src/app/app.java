// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik 04/2026
// contact hschirmacher@bht-berlin.de

package app;

import java.util.ArrayList;

import cgg_tools.Color;
import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    test();
    int width = 1280;
    int height = 720;
    Camera camera = new Camera(45, width, height, new Vec3(0, 0, 0));
    var spheres = new ArrayList<Sphere>();
    spheres.add(new Sphere(new Vec3(0.8, 0.0, -6.0), 0.70, Color.cyan));
    spheres.add(new Sphere(new Vec3(1.9, 0.6, -7.0), 0.65, Color.blue));
    spheres.add(new Sphere(new Vec3(0.5, -0.7, -5.5), 0.45, Color.green));
    spheres.add(new Sphere(new Vec3(-2.0, -0.2, -9.0), 1.10, Color.red));
    spheres.add(new Sphere(new Vec3(1.0, -1.0, -8.0), 0.35, Color.yellow));
    spheres.add(new Sphere(new Vec3(-1.8, 1.1, -7.5), 0.38, Color.magenta));
    // This object defines the contents of the image.
    // It must implement the cgg_tools.Sampler interface.
    var obj = new Raytracer(spheres, camera);

    // iterate over all pixelzzz of the image
    var image = new Image(width, height);
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        image.setPixel(i, j, obj.getColor(new Vec2(i, j)));
      }
    }

    // Write the image to disk.
    image.writePNG("a02-spheres");
  }

  public static void test() {
    Ray ray1 = new Ray(Vec3.zero, Vec3.nzAxis, 0, Double.POSITIVE_INFINITY);
    Ray ray2 = new Ray(Vec3.zero, new Vec3(0, 1, -1), 0, Double.POSITIVE_INFINITY);
    Ray ray3 = new Ray(new Vec3(0, 0, -4), Vec3.nzAxis, 0, Double.POSITIVE_INFINITY);
    Ray ray4 = new Ray(Vec3.zero, Vec3.nzAxis, 0, 2);

    Sphere sphere1 = new Sphere(new Vec3(0, 0, -2), 1, Color.black);
    Sphere sphere2 = new Sphere(new Vec3(0, -1, -2), 1, Color.black);
    Sphere sphere3 = new Sphere(new Vec3(0, 0, -4), 1, Color.black);

    Hit h1 = sphere1.intersect(ray1);
    assert h1 != null;
    assert Util.almostEqual(h1.t(), 1);
    assert Vec3.almostEqual(h1.x(), new Vec3(0, 0, -1));
    assert Vec3.almostEqual(h1.n(), new Vec3(0, 0, 1));

    Hit h2 = sphere1.intersect(ray2);
    assert h2 == null;

    Hit h3 = sphere2.intersect(ray1);
    assert h3 != null;
    assert Util.almostEqual(h3.t(), 2);
    assert Vec3.almostEqual(h3.x(), new Vec3(0, 0, -2));
    assert Vec3.almostEqual(h3.n(), new Vec3(0, 1, 0));

    Hit h4 = sphere1.intersect(ray3);
    assert h4 == null;

    Hit h5 = sphere3.intersect(ray4);
    assert h5 == null;
    System.out.println("All tests passed!");
  }
}
