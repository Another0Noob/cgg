package app.shapes;

import app.Hit;
import app.Ray;
import app.materials.Material;
import cgg_tools.BoundingBox;
import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class CuboidShape implements Shape {

  private GroupShape groupShape;

  public CuboidShape(Vec3 center, Vec3 size, Material material) {
    var rect = new RectShape(Vec3.zero, 1, 1, material);

    groupShape = new GroupShape(
        Mat4x4.multiply(
            Mat4x4.move(center),
            Mat4x4.scale(size)),

        new GroupShape(Mat4x4.move(0, 0.5, 0), rect),

        new GroupShape(
            Mat4x4.multiply(Mat4x4.move(0, -0.5, 0), Mat4x4.rotate(Vec3.xAxis, 180)),
            rect),

        new GroupShape(
            Mat4x4.multiply(Mat4x4.move(-0.5, 0, 0), Mat4x4.rotate(Vec3.zAxis, 90)),
            rect),

        new GroupShape(
            Mat4x4.multiply(Mat4x4.move(0.5, 0, 0), Mat4x4.rotate(Vec3.zAxis, -90)),
            rect),

        new GroupShape(
            Mat4x4.multiply(Mat4x4.move(0, 0, 0.5), Mat4x4.rotate(Vec3.xAxis, -90)),
            rect),

        new GroupShape(
            Mat4x4.multiply(Mat4x4.move(0, 0, -0.5), Mat4x4.rotate(Vec3.xAxis, 90)),
            rect));
  }

  @Override
  public BoundingBox bounding_box() {
    return groupShape.bounding_box();
  }

  @Override
  public Hit intersect(Ray r) {
    return groupShape.intersect(r);
  }

}
