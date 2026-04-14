// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

/**
 * Represents a color with red, green, and blue components.
 * This class provides constants for common colors.
 */
public record Color(double r, double g, double b) {

    /**
     * Returns a string representation of the color.
     *
     * @return A string in the format "(Color: r.rr g.gg b.bb)".
     */
    @Override
    public String toString() {
        return String.format("(Color: %.2f %.2f %.2f)", r, g, b);
    }

    /** Black color (0, 0, 0). */
    public static final Color black = new Color(0, 0, 0);
    /** Gray color (0.5, 0.5, 0.5). */
    public static final Color gray = new Color(0.5, 0.5, 0.5);
    /** White color (1, 1, 1). */
    public static final Color white = new Color(1, 1, 1);
    /** Red color (1, 0, 0). */
    public static final Color red = new Color(1, 0, 0);
    /** Green color (0, 1, 0). */
    public static final Color green = new Color(0, 1, 0);
    /** Blue color (0, 0, 1). */
    public static final Color blue = new Color(0, 0, 1);
    /** Cyan color (0, 1, 1). */
    public static final Color cyan = new Color(0, 1, 1);
    /** Magenta color (1, 0, 1). */
    public static final Color magenta = new Color(1, 0, 1);
    /** Yellow color (1, 1, 0). */
    public static final Color yellow = new Color(1, 1, 0);

    /**
     * Adds two or more Color objects.
     *
     * @param a  The first color.
     * @param vs Additional colors to add (varargs).
     * @return The resulting Color after adding all components.
     */
    public static Color add(Color a, Color... vs) {
        for (Color v : vs) {
            a = new Color(a.r() + v.r(), a.g() + v.g(), a.b() + v.b());
        }
        return a;
    }

    /**
     * Subtracts two or more Color objects from the first one.
     *
     * @param a  The base color.
     * @param b  The first color to subtract.
     * @param vs Additional colors to subtract (varargs).
     * @return The resulting Color after subtracting all subsequent colors from the
     *         first.
     */
    public static Color subtract(Color a, Color b, Color... vs) {
        Color r = new Color(a.r() - b.r(), a.g() - b.g(), a.b() - b.b());
        for (Color v : vs) {
            r = new Color(r.r() - v.r(), r.g() - v.g(), r.b() - v.b());
        }
        return r;
    }

    /**
     * Multiplies a Color by a scalar value.
     *
     * @param s The scalar value.
     * @param a The color to multiply.
     * @return The resulting scaled color.
     */
    public static Color multiply(double s, Color a) {
        return new Color(s * a.r(), s * a.g(), s * a.b());
    }

    /**
     * Multiplies a Color by a scalar value.
     *
     * @param a The color to multiply.
     * @param s The scalar value.
     * @return The resulting scaled color.
     */
    public static Color multiply(Color a, double s) {
        return multiply(s, a);
    }

    /**
     * Multiplies two Color objects component-wise.
     *
     * @param a The first color.
     * @param b The second color.
     * @return The resulting color after component-wise multiplication.
     */
    public static Color multiply(Color a, Color b) {
        return new Color(a.r() * b.r(), a.g() * b.g(), a.b() * b.b());
    }

    /**
     * Divides a Color by a scalar value.
     *
     * @param a The color to divide.
     * @param s The scalar value.
     * @return The resulting divided color.
     */
    public static Color divide(Color a, double s) {
        return new Color(a.r() / s, a.g() / s, a.b() / s);
    }

    /**
     * Clamps the components of a Color to the range [0, 1].
     *
     * @param v The color to clamp.
     * @return A new Color with all components clamped between 0 and 1.
     */
    public static Color clamp(Color v) {
        return new Color(
            Math.min(1, Math.max(v.r(), 0)),
            Math.min(1, Math.max(v.g(), 0)),
            Math.min(1, Math.max(v.b(), 0))
        );
    }

    /**
     * Converts HSV (Hue, Saturation, Value) to RGB color.
     *
     * @param h Hue component (0.0 to 1.0).
     * @param s Saturation component (0.0 to 1.0).
     * @param v Value component (0.0 to 1.0).
     * @return The resulting RGB Color.
     */
    public static Color hsvToRgb(double h, double s, double v) {
        return multiply(
            v,
            add(multiply(s, subtract(hue(h), Color.white)), Color.white)
        );
    }

    /**
     * Converts a Color object interpreted as HSV to RGB.
     *
     * @param c The Color object interpreted as HSV (h=r, s=g, v=b).
     * @return The resulting RGB Color.
     */
    public static Color hsvToRgb(Color c) {
        return hsvToRgb(c.r(), c.g(), c.b());
    }

    /**
     * Performs linear interpolation between two Color objects.
     *
     * @param a The starting color.
     * @param b The ending color.
     * @param t The interpolation parameter (0.0 to 1.0).
     * @return The interpolated color.
     */
    public static Color interpolate(Color a, Color b, double t) {
        return new Color(
            a.r() * (1 - t) + b.r() * t,
            a.g() * (1 - t) + b.g() * t,
            a.b() * (1 - t) + b.b() * t
        );
    }

    /**
     * Performs barycentric interpolation between three Color objects.
     *
     * @param a   The first color.
     * @param b   The second color.
     * @param c   The third color.
     * @param uvw The barycentric coordinates.
     * @return The interpolated color.
     */
    public static Color interpolateColor(Color a, Color b, Color c, Vec3 uvw) {
        return add(
            multiply(a, uvw.u()),
            multiply(b, uvw.v()),
            multiply(c, uvw.w())
        );
    }

    /**
     * Helper method for HSV to RGB conversion.
     * Calculates the RGB color for a given hue value.
     *
     * @param h The hue value (0.0 to 1.0).
     * @return A Color object representing the RGB values for the given hue.
     */
    private static Color hue(double h) {
        double r = Math.abs(h * 6 - 3) - 1;
        double g = 2 - Math.abs(h * 6 - 2);
        double b = 2 - Math.abs(h * 6 - 4);
        return clamp(new Color(r, g, b));
    }


}