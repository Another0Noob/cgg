package app;

import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class SnakeShape implements Shape {
  private GroupShape shapes;

  @Override
  public Hit intersect(Ray r) {
    return shapes.intersect(r);
  }

  public SnakeShape(Mat4x4 matrix, Material material, double length, double angle1, double angle2, double angle3) {
    var move = Mat4x4.move(length, 0, 0);
    var rot1 = Mat4x4.rotate(Vec3.yAxis, angle1);
    var rot2 = Mat4x4.rotate(Vec3.yAxis, angle2);
    var rot3 = Mat4x4.rotate(Vec3.yAxis, angle3);
    var mat1 = Mat4x4.multiply(move, rot1);
    var mat2 = Mat4x4.multiply(move, rot2);
    var mat3 = Mat4x4.multiply(move, rot3);

    var part0 = MakePart(material, length);
    var part1 = MakePart(material, length);
    var part2 = MakePart(material, length);
    var part3 = MakePart(material, length);

    var segment3 = new GroupShape(mat3, part3);
    var segment2 = new GroupShape(mat2, part2, segment3);
    var segment1 = new GroupShape(mat1, part1, segment2);
    var root = new GroupShape(Mat4x4.identity, part0, segment1);
    shapes = new GroupShape(matrix, root);
  }

  private GroupShape MakePart(Material material, double length) {
    var cube = new CuboidShape(new Vec3(length / 2, 0, 0), new Vec3(length, 0.2, 0.2), material);
    var joint = new SphereShape(Vec3.zero, 0.25, material);
    return new GroupShape(Mat4x4.identity, joint, cube);
  }

}
