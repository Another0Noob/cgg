// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik 04/2026
// contact hschirmacher@bht-berlin.de

package app;

import cgg_tools.Color;
import cgg_tools.Util;
import cgg_tools.Vec2;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    // int width = 1280;
    // int height = 720;
    // Camera camera = new Camera(45, width, height, new Vec3(0, 0, 0));
    //
    // // This object defines the contents of the image.
    // // It must implement the cgg_tools.Sampler interface.
    //
    // Ray test = camera.generate_ray(new Vec2(width / 2, height / 2));
    // System.out.println(test);
    // // iterate over all pixelzzz of the image
    // var image = new Image(width, height);
    // for (int i = 0; i != width; i++) {
    // for (int j = 0; j != height; j++) {
    // Ray ray = camera.generate_ray(new Vec2(i, j));
    // Vec3 temp = Vec3.normalize(ray.d());
    // Color color = new Color(temp.x(), temp.y(), 0);
    // image.setPixel(i, j, color);
    // }
    // }
    //
    // // Write the image to disk.
    // image.writePNG("a02_cam_directions");
    test();
  }

  public static void test() {
    Ray ray1 = new Ray(Vec3.zero, Vec3.nzAxis, 0, Double.POSITIVE_INFINITY);
    Ray ray2 = new Ray(Vec3.zero, new Vec3(0, 1, -1), 0, Double.POSITIVE_INFINITY);
    Ray ray3 = new Ray(new Vec3(0, 0, -4), Vec3.nzAxis, 0, Double.POSITIVE_INFINITY);
    Ray ray4 = new Ray(Vec3.zero, Vec3.nzAxis, 0, 2);

    Sphere sphere1 = new Sphere(new Vec3(0, 0, -2), 1);
    Sphere sphere2 = new Sphere(new Vec3(0, -1, -2), 1);
    Sphere sphere3 = new Sphere(new Vec3(0, 0, -4), 1);

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
