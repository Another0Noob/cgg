// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

/**
 * Represents a 2D vector with x and y components.
 */
public final record Vec2(double x, double y) {

    /**
     * Returns the x-coordinate of the vector.
     * This method is an alias for the x() method.
     *
     * @return The x-coordinate (u-component) of the vector.
     */
    public double u() {
        return x;
    }

    /**
     * Returns the y-coordinate of the vector.
     * This method is an alias for the y() method.
     *
     * @return The y-coordinate (v-component) of the vector.
     */
    public double v() {
        return y;
    }

    /**
     * Returns a string representation of the vector.
     *
     * @return A string in the format "(Vec2: x.xx y.yy)".
     */
    @Override
    public String toString() {
        return String.format("(Vec2: %.2f %.2f)", x, y);
    }

    /** The zero vector (0, 0). */
    public static final Vec2 zero = new Vec2(0, 0);
    /** The unit vector along the x-axis (1, 0). */
    public static final Vec2 xAxis = new Vec2(1, 0);
    /** The unit vector along the y-axis (0, 1). */
    public static final Vec2 yAxis = new Vec2(0, 1);
    /** The negative unit vector along the x-axis (-1, 0). */
    public static final Vec2 nxAxis = new Vec2(-1, 0);
    /** The negative unit vector along the y-axis (0, -1). */
    public static final Vec2 nyAxis = new Vec2(0, -1);


    /**
     * Calculates the sum of two or more Vec2 objects.
     *
     * @param a  The first vector.
     * @param b  The second vector.
     * @param vs Additional vectors (optional).
     * @return The sum of all input vectors.
     */
    public static Vec2 add(Vec2 a, Vec2 b, Vec2... vs) {
        Vec2 r = new Vec2(a.x() + b.x(), a.y() + b.y());
        for (var v : vs) r = new Vec2(r.x() + v.x(), r.y() + v.y());
        return r;
    }

    /**
     * Calculates the difference between two or more Vec2 objects.
     *
     * @param a  The first vector.
     * @param b  The second vector.
     * @param vs Additional vectors to subtract (optional).
     * @return The result of subtracting all subsequent vectors from the first.
     */
    public static Vec2 subtract(Vec2 a, Vec2 b, Vec2... vs) {
        Vec2 r = new Vec2(a.x() - b.x(), a.y() - b.y());
        for (var v : vs) r = new Vec2(r.x() - v.x(), r.y() - v.y());
        return r;
    }

    /**
     * Performs linear interpolation between two Vec2 objects.
     *
     * @param a The starting vector.
     * @param b The ending vector.
     * @param t The interpolation parameter (0.0 to 1.0).
     * @return The interpolated vector.
     */
    public static Vec2 interpolate(Vec2 a, Vec2 b, double t) {
        return add(multiply(a, 1 - t), multiply(b, t));
    }

    /**
     * Performs barycentric interpolation between three Vec2 objects.
     *
     * @param a   The first vector.
     * @param b   The second vector.
     * @param c   The third vector.
     * @param uvw The barycentric coordinates.
     * @return The interpolated vector.
     */
    public static Vec2 interpolate(Vec2 a, Vec2 b, Vec2 c, Vec3 uvw) {
        return add(
            multiply(a, uvw.u()),
            multiply(b, uvw.v()),
            multiply(c, uvw.w())
        );
    }

    /**
     * Multiplies a Vec2 by a scalar value.
     *
     * @param s The scalar value.
     * @param a The vector to multiply.
     * @return The resulting scaled vector.
     */
    public static Vec2 multiply(double s, Vec2 a) {
        return new Vec2(s * a.x(), s * a.y());
    }

    /**
     * Multiplies a Vec2 by a scalar value.
     *
     * @param a The vector to multiply.
     * @param s The scalar value.
     * @return The resulting scaled vector.
     */
    public static Vec2 multiply(Vec2 a, double s) {
        return new Vec2(s * a.x(), s * a.y());
    }

    /**
     * Multiplies two Vec2 objects component-wise.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The resulting vector with component-wise multiplication.
     */
    public static Vec2 multiply(Vec2 a, Vec2 b) {
        return new Vec2(a.x() * b.x(), a.y() * b.y());
    }

    /**
     * Negates a Vec2 object.
     *
     * @param a The vector to negate.
     * @return The negated vector.
     */
    public static Vec2 negate(Vec2 a) {
        return new Vec2(-a.x(), -a.y());
    }

    /**
     * Divides a Vec2 by a scalar value.
     *
     * @param a The vector to divide.
     * @param s The scalar value.
     * @return The resulting divided vector.
     */
    public static Vec2 divide(Vec2 a, double s) {
        return new Vec2(a.x() / s, a.y() / s);
    }

    /**
     * Calculates the dot product of two Vec2 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The dot product of the two vectors.
     */
    public static double dot(Vec2 a, Vec2 b) {
        return a.x() * b.x() + a.y() * b.y();
    }

    /**
     * Calculates the "perpendicular cross" product of two Vec2 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The magnitude of the cross product (signed area of the
     *         parallelogram).
     */
    public static double cross(Vec2 a, Vec2 b) {
        return a.x() * b.y() - a.y() * b.x();
    }

    /**
     * Calculates the length of a Vec2.
     *
     * @param a The vector.
     * @return The length of the vector.
     */
    public static double length(Vec2 a) {
        return Math.sqrt(a.x() * a.x() + a.y() * a.y());
    }

    /**
     * Calculates the squared length of a Vec2.
     *
     * @param a The vector.
     * @return The squared length of the vector.
     */
    public static double squaredLength(Vec2 a) {
        return a.x() * a.x() + a.y() * a.y();
    }

    /**
     * Normalizes a Vec2 to unit length.
     *
     * @param a The vector to normalize.
     * @return The normalized vector.
     */
    public static Vec2 normalize(Vec2 a) {
        return divide(a, length(a));
    }

    /**
     * Calculates the component-wise modulus of two Vec2 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The resulting vector after component-wise modulus operation.
     */
    public static Vec2 mod(Vec2 a, Vec2 b) {
        return new Vec2(a.x() % b.x(), a.y() % b.y());
    }

    /**
     * Calculates the component-wise minimum of two Vec2 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return A new Vec2 with the minimum values of each component.
     */
    public static Vec2 min(Vec2 a, Vec2 b) {
        return new Vec2(Math.min(a.x(), b.x()), Math.min(a.y(), b.y()));
    }

    /**
     * Calculates the component-wise maximum of two Vec2 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return A new Vec2 with the maximum values of each component.
     */
    public static Vec2 max(Vec2 a, Vec2 b) {
        return new Vec2(Math.max(a.x(), b.x()), Math.max(a.y(), b.y()));
    }

    /**
     * compares two vectors robustly using some epsilon, component by component
     * @param a first vector
     * @param b second vector
     * @return true if vectors are "almost" the same
     */
    public static boolean almostEqual(Vec2 a, Vec2 b) {
        return 
            Util.almostEqual(a.x(),b.x()) && 
            Util.almostEqual(a.y(),b.y()); 
    }




}