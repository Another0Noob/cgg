package app;

import java.util.ArrayList;
import java.util.List;

public class GroupShape implements Shape {

  public List<Shape> scene = new ArrayList<>();

  public GroupShape(Shape... shapes) {
    for (Shape shape : shapes) {
      scene.add(shape);
    }
  }

  @Override
  public Hit intersect(Ray r) {
    var min = Double.POSITIVE_INFINITY;
    Hit hit = null;
    for (Shape shape : scene) {
      var temp = shape.intersect(r);
      if (temp != null) {
        if (temp.t() < min) {
          min = temp.t();
          hit = temp;
        }
      }
    }
    return hit;
  }

  public void add(Shape s) {
    scene.add(s);
  }

}
