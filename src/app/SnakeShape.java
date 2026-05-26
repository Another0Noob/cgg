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
    var cube = new CuboidShape(new Vec3(length / 2, 0, 0), new Vec3(length, 0.2, 0.2), material);
    var joint = new SphereShape(Vec3.zero, 0.25, material);
    // adjust cube and joint position. move up

    var part = new GroupShape(Mat4x4.identity, joint, cube);

    var scale = Mat4x4.scale(length, length, length);
    var move = Mat4x4.move(length, 0, 0);
    var rot1 = Mat4x4.rotate(Vec3.yAxis, angle1);
    var rot2 = Mat4x4.rotate(Vec3.yAxis, angle2);
    var rot3 = Mat4x4.rotate(Vec3.yAxis, angle3);

    var mat1 = Mat4x4.multiply(rot1, move, scale);
    var mat2 = Mat4x4.multiply(rot2, move, scale);
    var mat3 = Mat4x4.multiply(rot3, move, scale);

    var segment3 = new GroupShape(mat3, part);
    var segment2 = new GroupShape(mat2, part, segment3);
    var segment1 = new GroupShape(mat1, part, segment2);
    var root = new GroupShape(scale, part, segment1);

    shapes = new GroupShape(matrix, root);
  }

}
