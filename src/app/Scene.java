package app;

import java.util.List;
import cgg_tools.Color;

public record Scene(List<Light> lights, Color ambient) {

}
