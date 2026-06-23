package app;

import app.shapes.GroupShape;
import app.lights.Light;

import java.util.List;
import cgg_tools.Color;

public record Scene(GroupShape shapes, List<Light> lights, Color ambient) {

}
