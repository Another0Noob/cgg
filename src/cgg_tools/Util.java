// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

// import cgg_tools.Vec3;
// import cgg_tools.Color;

public class Util {

    /** Epsilon value for floating-point comparisons. */
    public static final double EPSILON = 1e-7;

    /**
     * Checks if a value is between two other values, inclusive.
     *
     * @param a The lower bound.
     * @param b The upper bound.
     * @param v The value to check.
     * @return true if v is between a and b (inclusive), false otherwise.
     */
    public static boolean in(double a, double b, double v) {
        return a <= v && v <= b;
    }

    /**
     * Compares two double values for near equality.
     *
     * @param a The first value.
     * @param b The second value.
     * @return true if the absolute difference between a and b is less than EPSILON,
     *         false otherwise.
     */
    public static boolean almostEqual(double a, double b) {
        return Math.abs(a - b) <= EPSILON;
    }
    
    /**
     * Creates a new Color object from a Vec3.
     *
     * @param v The Vec3 object representing RGB components.
     * @return A new Color object with RGB values from the Vec3.
     */
    public static Color to_color(Vec3 v) {
    return new Color(v.x(), v.y(), v.z());
    }
    
        /**
     * Creates a new Vec3 object from a Color object.
     *
     * @param c The Color object.
     * @return A new Vec3 object with r, g, and b components from the Color.
     */
    public static Vec3 to_vec3(Color c) {
        return new Vec3(c.r(), c.g(), c.b());
    }

}
