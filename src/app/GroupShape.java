package app;

import java.util.ArrayList;
import java.util.List;

import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class GroupShape implements Shape {

  public List<Shape> shapeList = new ArrayList<>();
  public Mat4x4 mat;
  private Mat4x4 iMat;
  private Mat4x4 tMat;

  public GroupShape(Mat4x4 mat, Shape... shapes) {
    this.mat = mat;
    for (Shape shape : shapes) {
      shapeList.add(shape);
    }
    iMat = Mat4x4.invert(mat);
    tMat = Mat4x4.transpose(iMat);
  }

  @Override
  public Hit intersect(Ray r) {
    var lr = new Ray(
        Mat4x4.multiplyPoint(iMat, r.origin()),
        Mat4x4.multiplyDirection(iMat, r.dir()),
        r.t_min(), r.t_max());
    var min = Double.POSITIVE_INFINITY;
    Hit hit = null;
    for (Shape shape : shapeList) {
      var temp = shape.intersect(lr);
      if (temp != null) {
        if (temp.t() <= min) {
          min = temp.t();
          hit = temp;
          lr = new Ray(lr.origin(), lr.dir(), lr.t_min(), min);
        }
      }
    }
    if (hit != null) {
      return new Hit(hit.t(), Mat4x4.multiplyPoint(mat, hit.pos()),
          Vec3.normalize(Mat4x4.multiplyDirection(tMat, hit.normal())),
          hit.material());
    }
    return hit;
  }

  public void add(Shape s) {
    shapeList.add(s);
  }

}
