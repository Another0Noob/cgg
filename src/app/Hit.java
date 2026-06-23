package app;

import cgg_tools.Vec2;
import cgg_tools.Vec3;

public record Hit(double t, Vec3 pos, Vec3 normal, Material material, Vec2 uv) {

}
