package app.shapes;

import java.util.ArrayList;
import java.util.List;

import app.Hit;
import app.Ray;
import cgg_tools.BoundingBox;
import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class GroupShape implements Shape {

  public List<Shape> shapeList = new ArrayList<>();
  public Mat4x4 mat;
  private Mat4x4 iMat;
  private Mat4x4 tMat;
  private BoundingBox bb;

  public GroupShape(Mat4x4 mat, Shape... shapes) {
    this.mat = mat;
    this.bb = null;
    for (Shape shape : shapes) {
      shapeList.add(shape);
    }
    iMat = Mat4x4.invert(mat);
    tMat = Mat4x4.transpose(iMat);

    updateBoundingBox();
  }

  @Override
  public Hit intersect(Ray r) {
    var origin = Mat4x4.multiplyPoint(iMat, r.origin());
    var dir = Mat4x4.multiplyDirection(iMat, r.dir());
    var t_min = r.t_min();
    var t_max = r.t_max();
    Hit hit = null;
    if (!bb.intersect(origin, dir, t_min, t_max)) {
      return hit;
    }
    var min = Double.POSITIVE_INFINITY;
    var lr = new Ray(origin, dir, t_min, t_max);
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
          hit.material(), hit.uv());
    }
    return hit;
  }

  @Override
  public BoundingBox bounding_box() {
    return bb;
  }

  public void add(Shape s) {
    shapeList.add(s);
    updateBoundingBox();
  }

  public void updateBoundingBox() {
    this.bb = null;

    for (Shape shape : shapeList) {
      BoundingBox childBB;

      if (shape instanceof GroupShape) {
        GroupShape childGroup = (GroupShape) shape;
        childGroup.updateBoundingBox();
        childBB = childGroup.bounding_box();
      } else {
        childBB = shape.bounding_box();
      }

      if (childBB != null) {
        BoundingBox transformedBB = childBB.transform(this.mat);

        if (this.bb == null) {
          this.bb = transformedBB;
        } else {
          this.bb.extend(transformedBB);
        }
      }
    }
  }

}
