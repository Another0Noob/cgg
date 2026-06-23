package app.shapes;

import app.Hit;
import app.Ray;

public interface Shape {
  public Hit intersect(Ray r);
}
