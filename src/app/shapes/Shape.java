package app.shapes;

import app.Hit;
import app.Ray;
import cgg_tools.BoundingBox;

public interface Shape {
  public Hit intersect(Ray r);

  public BoundingBox bounding_box();
}
