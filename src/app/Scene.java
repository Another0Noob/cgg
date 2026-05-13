package app;

import java.util.List;
import cgg_tools.Color;

public record Scene(GroupShape shapes, List<Light> lights, Color ambient) {

}
