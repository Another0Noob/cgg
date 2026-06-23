// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik 04/2026
// contact hschirmacher@bht-berlin.de

package app;

import java.util.ArrayList;

import cgg_tools.Color;
import cgg_tools.ConstantColorSampler;
import cgg_tools.Mat4x4;
import cgg_tools.Util;
import cgg_tools.Vec3;

public class app {

  public static void main(String[] args) {
    test();
    int width = 1280;
    int height = 720;

    var matC = Mat4x4.identity;
    var moveC = Mat4x4.move(5, 1, 8);
    var rotC = Mat4x4.rotate(Vec3.yAxis, 90);
    var rot2C = Mat4x4.rotate(Vec3.nxAxis, 10);
    matC = Mat4x4.multiply(matC, rotC, moveC, rot2C);
    Camera camera = new Camera(70, width, height, Vec3.zero, matC);

    var matS = Mat4x4.identity;
    var mat1 = phong(52, 86, 154, 60);
    var mat2 = phong(169, 194, 241, 40);
    var mat3 = phong(34, 53, 128, 40);

    var matC1R = Mat4x4.rotate(Vec3.zAxis, -45);
    var matC1M = Mat4x4.move(-2, 0, -9);
    var matC1 = Mat4x4.multiply(matC1M, matC1R);

    var matC2R = Mat4x4.rotate(Vec3.xAxis, -45);
    matC2R = Mat4x4.multiply(Mat4x4.rotate(Vec3.yAxis, 45), matC2R);
    var matC2M = Mat4x4.move(1, 1, -5.5);
    var matC2 = Mat4x4.multiply(matC2M, matC2R);

    var matC3S = Mat4x4.scale(1.5, 2, 1.5);
    var matC3M = Mat4x4.move(-5, 0, -5);
    var matC3 = Mat4x4.multiply(matC3M, matC3S);

    var matSnS = Mat4x4.scale(0.6, 0.6, 0.6);
    var matSnM = Mat4x4.move(2, 0.1, -5);
    var matSnR = Mat4x4.rotate(Vec3.yAxis, 90);
    var matSnR2 = Mat4x4.rotate(Vec3.xAxis, 90);
    var matSn = Mat4x4.multiply(matSnM, matSnR, matSnR2, matSnS);
    var snake = new SnakeShape(matSn, mat1, 0.8, 30, -10, 20);

    var matSn2S = Mat4x4.scale(0.4, 0.4, 0.4);
    var matSn2M = Mat4x4.move(3, 0.1, -3);
    var matSn2R2 = Mat4x4.rotate(Vec3.xAxis, 90);
    var matSn2R3 = Mat4x4.rotate(Vec3.zAxis, 90);
    var matSn2 = Mat4x4.multiply(matSn2M, matSn2R3, matSn2R2, matSn2S);
    var snake2 = new SnakeShape(matSn2, mat1, 1.2, 30, -50, 60);

    var deb = new uvDebugSampler();
    var s = new ConstantColorSampler(new Color(50, 50, 50));
    var uv = new PhongMaterial(deb, deb, deb, s);

    var shapes = new GroupShape(matS,
        new BackgroundShape(mat2),
        new RectShape(new Vec3(-2.0, -1, -6), 15, 15, uv),
        // new SphereShape(new Vec3(-3, 0.25, -6.5), 0.7, mat1),
        // new SphereShape(new Vec3(0, 0.0, -4.5), 0.3, mat1),
        // new SphereShape(new Vec3(3, -0.25, -6.5), 0.7, mat1),
        // new CuboidShape(new Vec3(2, 0, -4), new Vec3(0.5, 1, 0.5), mat1),
        // new CuboidShape(new Vec3(4, -0.5, -4), new Vec3(1, 0.5, 2), mat1),
        // new CuboidShape(new Vec3(-2, 1, -2), new Vec3(3, 3, 3), mat1),
        new GroupShape(matC1, new OpenCylinderShape(1, 0, 1, mat1)),
        new GroupShape(matC2, new ClosedCylinderShape(0.5, 0, 1.5, mat1, mat1)),
        new GroupShape(matC3, new OpenCylinderShape(1.2, 0, 1, mat1)), snake, snake2);

    var lights = new ArrayList<Light>();
    lights.add(new PointLight(Color.multiply(Color.white, 30), new Vec3(0, 5, -5)));
    lights.add(new PointLight(Color.multiply(rgb(245, 172, 186), 30), new Vec3(3, 4, -4)));
    lights.add(new PointLight(Color.multiply(rgb(88, 203, 252), 30), new Vec3(-3, 6, -6)));

    var scene = new Scene(shapes, lights, rgb(122, 136, 192));
    var obj = new Raytracer(camera, scene, Color.black);

    var image = new Image(width, height);
    image.sample(obj);

    image.writePNG("a07-uv-debug");
  }

  public static PhongMaterial phong(int r, int g, int b, double s) {
    var col = new ConstantColorSampler(rgb(r, g, b));
    var shine = new ConstantColorSampler(new Color(s, s, s));
    return new PhongMaterial(col, col, col, shine);
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
