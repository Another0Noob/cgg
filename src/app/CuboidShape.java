package app;

import cgg_tools.Mat4x4;
import cgg_tools.Vec3;

public class CuboidShape implements Shape {

  private GroupShape groupShape;

  public CuboidShape(Vec3 center, Vec3 size, Material material) {
    groupShape = new GroupShape(Mat4x4.identity,
        new RectShape());
  }

  @Override
  public Hit intersect(Ray r) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'intersect'");
  }

}
