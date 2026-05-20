// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik 04/2026
// contact hschirmacher@bht-berlin.de

package app;

import java.util.ArrayList;

import cgg_tools.Color;
import cgg_tools.Mat4x4;
import cgg_tools.Util;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    test();
    int width = 1280;
    int height = 720;

    var matC = Mat4x4.identity;
    var moveC = Mat4x4.move(5.5, 1, 8);
    var rotC = Mat4x4.rotate(Vec3.yAxis, 90);
    var rot2C = Mat4x4.rotate(Vec3.nxAxis, 10);
    matC = Mat4x4.multiply(matC, rotC, moveC, rot2C);
    Camera camera = new Camera(70, width, height, Vec3.zero, matC);

    var matS = Mat4x4.identity;
    var mat1 = phong(52, 86, 154, 60);
    var mat2 = phong(169, 194, 241, 40);
    var mat3 = phong(34, 53, 128, 40);
    var shapes = new GroupShape(matS,
        new BackgroundShape(mat2),
        new RectShape(new Vec3(-2.0, -1, -6), Vec3.yAxis, 15, 15, mat3),
        new SphereShape(new Vec3(-3, 0.25, -6.5), 0.7, mat1),
        new SphereShape(new Vec3(0, 0.0, -4.5), 0.3, mat1),
        new SphereShape(new Vec3(3, -0.25, -6.5), 0.7, mat1));

    var lights = new ArrayList<Light>();
    lights.add(new PointLight(Color.multiply(Color.white, 30), new Vec3(0, 5, -5)));
    lights.add(new PointLight(Color.multiply(rgb(245, 172, 186), 30), new Vec3(3, 4, -4)));
    lights.add(new PointLight(Color.multiply(rgb(88, 203, 252), 30), new Vec3(-3, 6, -6)));

    var scene = new Scene(shapes, lights, rgb(122, 136, 192));
    var obj = new Raytracer(camera, scene, Color.black);

    var image = new Image(width, height);
    image.sample(obj);

    image.writePNG("a05-cuboids");
  }

  public static PhongMaterial phong(int r, int g, int b, double s) {
    var col = rgb(r, g, b);
    return new PhongMaterial(col, col, col, s);
  }

  public static Color rgb(int r, int g, int b) {
    return new Color((double) r / 255, (double) g / 255, (double) b / 255);
  }

  public static void test() {
    Ray ray1 = new Ray(Vec3.zero, Vec3.nzAxis, 0, Double.POSITIVE_INFINITY);
    Ray ray2 = new Ray(Vec3.zero, new Vec3(0, 1, -1), 0, Double.POSITIVE_INFINITY);
    Ray ray3 = new Ray(new Vec3(0, 0, -4), Vec3.nzAxis, 0, Double.POSITIVE_INFINITY);
    Ray ray4 = new Ray(Vec3.zero, Vec3.nzAxis, 0, 2);

    var mat = new DummyMaterial();
    SphereShape sphere1 = new SphereShape(new Vec3(0, 0, -2), 1, mat);
    SphereShape sphere2 = new SphereShape(new Vec3(0, -1, -2), 1, mat);
    SphereShape sphere3 = new SphereShape(new Vec3(0, 0, -4), 1, mat);

    Hit h1 = sphere1.intersect(ray1);
    assert h1 != null;
    assert Util.almostEqual(h1.t(), 1);
    assert Vec3.almostEqual(h1.pos(), new Vec3(0, 0, -1));
    assert Vec3.almostEqual(h1.normal(), new Vec3(0, 0, 1));

    Hit h2 = sphere1.intersect(ray2);
    assert h2 == null;

    Hit h3 = sphere2.intersect(ray1);
    assert h3 != null;
    assert Util.almostEqual(h3.t(), 2);
    assert Vec3.almostEqual(h3.pos(), new Vec3(0, 0, -2));
    assert Vec3.almostEqual(h3.normal(), new Vec3(0, 1, 0));

    Hit h4 = sphere1.intersect(ray3);
    assert h4 == null;

    Hit h5 = sphere3.intersect(ray4);
    assert h5 == null;

    var l = new PointLight(Color.white, new Vec3(0, 3, 4));

    assert Vec3.almostEqual(l.to_light(new Vec3(0, 3, 4)), new Vec3(0, 3, 4));
    assert Util.almostEqual(l.distance(new Vec3(0, 0, 0)), 5);
    assert Util.almostEqual(l.color_at(new Vec3(0, 0, 0)).r(), 1.0 / 25.0);
  }
}
