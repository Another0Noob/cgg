// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik, 04/2026
// contact hschirmacher@bht-berlin.de

package cgg_tools;

// import cgg_tools.Color; // for from_color

/**
 * Represents a 3D vector with x, y, and z components.
 * This class provides constants for common vectors and methods for accessing
 * components.
 */
public final record Vec3(double x, double y, double z) {

  /**
   * Returns a string representation of the vector.
   *
   * @return A string in the format "(Vec3: x.xx y.yy z.zz)".
   */
  @Override
  public String toString() {
    return String.format("(Vec3: %.2f %.2f %.2f)", x, y, z);
  }

    /** The zero vector (0, 0, 0). */
  public static final Vec3 zero = new Vec3(0, 0, 0);
  /** The unit vector along the x-axis (1, 0, 0). */
  public static final Vec3 xAxis = new Vec3(1, 0, 0);
  /** The unit vector along the y-axis (0, 1, 0). */
  public static final Vec3 yAxis = new Vec3(0, 1, 0);
  /** The unit vector along the z-axis (0, 0, 1). */
  public static final Vec3 zAxis = new Vec3(0, 0, 1);
  /** The negative unit vector along the x-axis (-1, 0, 0). */
  public static final Vec3 nxAxis = new Vec3(-1, 0, 0);
  /** The negative unit vector along the y-axis (0, -1, 0). */
  public static final Vec3 nyAxis = new Vec3(0, -1, 0);
  /** The negative unit vector along the z-axis (0, 0, -1). */
  public static final Vec3 nzAxis = new Vec3(0, 0, -1);

    /**
     * Calculates the sum of two or more Vec3 objects.
     *
     * @param a  The first vector.
     * @param b  The second vector.
     * @param vs Additional vectors (optional).
     * @return The sum of all input vectors.
     */
    public static Vec3 add(Vec3 a, Vec3 b, Vec3... vs) {
        Vec3 r = new Vec3(a.x() + b.x(), a.y() + b.y(), a.z() + b.z());
        for (var v : vs) r = new Vec3(r.x() + v.x(), r.y() + v.y(), r.z() + v.z());
        return r;
    }

    /**
     * Calculates the difference between two or more Vec3 objects.
     *
     * @param a  The first vector.
     * @param b  The second vector.
     * @param vs Additional vectors to subtract (optional).
     * @return The result of subtracting all subsequent vectors from the first.
     */
    public static Vec3 subtract(Vec3 a, Vec3 b, Vec3... vs) {
        Vec3 r = new Vec3(a.x() - b.x(), a.y() - b.y(), a.z() - b.z());
        for (var v : vs) r = new Vec3(r.x() - v.x(), r.y() - v.y(), r.z() - v.z());
        return r;
    }

    /**
     * Multiplies a Vec3 by a scalar value.
     *
     * @param s The scalar value.
     * @param a The vector to multiply.
     * @return The resulting scaled vector.
     */
    public static Vec3 multiply(double s, Vec3 a) {
        return new Vec3(s * a.x(), s * a.y(), s * a.z());
    }

    /**
     * Multiplies a Vec3 by a scalar value.
     *
     * @param a The vector to multiply.
     * @param s The scalar value.
     * @return The resulting scaled vector.
     */
    public static Vec3 multiply(Vec3 a, double s) {
        return new Vec3(s * a.x(), s * a.y(), s * a.z());
    }

    /**
     * Multiplies two Vec3 objects component-wise.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The resulting vector with component-wise multiplication.
     */
    public static Vec3 multiply(Vec3 a, Vec3 b) {
        return new Vec3(a.x() * b.x(), a.y() * b.y(), a.z() * b.z());
    }

    /**
     * Performs linear interpolation between two Vec3 objects.
     *
     * @param a The starting vector.
     * @param b The ending vector.
     * @param t The interpolation parameter (0.0 to 1.0).
     * @return The interpolated vector.
     */
    public static Vec3 interpolate(Vec3 a, Vec3 b, double t) {
        return add(multiply(a, 1 - t), multiply(b, t));
    }

    /**
     * Performs barycentric interpolation between three Vec3 objects.
     *
     * @param a   The first vector.
     * @param b   The second vector.
     * @param c   The third vector.
     * @param uvw The barycentric coordinates.
     * @return The interpolated vector.
     */
    public static Vec3 interpolate(Vec3 a, Vec3 b, Vec3 c, Vec3 uvw) {
        return add(
            multiply(a, uvw.u()),
            multiply(b, uvw.v()),
            multiply(c, uvw.w())
        );
    }

    /**
     * Negates a Vec3 object.
     *
     * @param a The vector to negate.
     * @return The negated vector.
     */
    public static Vec3 negate(Vec3 a) {
        return new Vec3(-a.x(), -a.y(), -a.z());
    }

    /**
     * Divides a Vec3 by a scalar value.
     *
     * @param a The vector to divide.
     * @param s The scalar value.
     * @return The resulting divided vector.
     */
    public static Vec3 divide(Vec3 a, double s) {
        return new Vec3(a.x() / s, a.y() / s, a.z() / s);
    }

    /**
     * Calculates the dot product of two Vec3 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The dot product of the two vectors.
     */
    public static double dot(Vec3 a, Vec3 b) {
        return a.x() * b.x() + a.y() * b.y() + a.z() * b.z();
    }

    /**
     * Calculates the cross product of two Vec3 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The cross product of the two vectors.
     */
    public static Vec3 cross(Vec3 a, Vec3 b) {
        return new Vec3(
            a.y() * b.z() - a.z() * b.y(),
            a.z() * b.x() - a.x() * b.z(),
            a.x() * b.y() - a.y() * b.x()
        );
    }

    /**
     * Calculates the length of a Vec3.
     *
     * @param a The vector.
     * @return The length of the vector.
     */
    public static double length(Vec3 a) {
        return Math.sqrt(a.x() * a.x() + a.y() * a.y() + a.z() * a.z());
    }

    public static double distance(Vec3 a, Vec3 b) {
        return length(subtract(b, a));
    }

    /**
     * Calculates the squared length of a Vec3.
     *
     * @param a The vector.
     * @return The squared length of the vector.
     */
    public static double squaredLength(Vec3 a) {
        return a.x() * a.x() + a.y() * a.y() + a.z() * a.z();
    }

    /**
     * Normalizes a Vec3 to unit length.
     *
     * @param a The vector to normalize.
     * @return The normalized vector.
     */
    public static Vec3 normalize(Vec3 a) {
        return divide(a, length(a));
    }

    /**
     * Calculates the component-wise modulus of two Vec3 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return The resulting vector after component-wise modulus operation.
     */
    public static Vec3 mod(Vec3 a, Vec3 b) {
        return new Vec3(a.x() % b.x(), a.y() % b.y(), a.z() % b.z());
    }

    /**
     * Calculates the component-wise minimum of two Vec3 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return A new Vec3 with the minimum values of each component.
     */
    public static Vec3 min(Vec3 a, Vec3 b) {
        return new Vec3(
            Math.min(a.x(), b.x()),
            Math.min(a.y(), b.y()),
            Math.min(a.z(), b.z())
        );
    }

    /**
     * Calculates the component-wise maximum of two Vec3 objects.
     *
     * @param a The first vector.
     * @param b The second vector.
     * @return A new Vec3 with the maximum values of each component.
     */
    public static Vec3 max(Vec3 a, Vec3 b) {
        return new Vec3(
            Math.max(a.x(), b.x()),
            Math.max(a.y(), b.y()),
            Math.max(a.z(), b.z())
        );
    }


    /**
     * Calculates the reflection of a vector on a surface.
     *
     * @param d The vector to be reflected, pointing away (!) from the surface
     * @param n The surface normal vector.
     * @return A new Vec3 
     */
    public static Vec3 reflect(Vec3 d, Vec3 n) {
        return subtract(multiply(2.0 * dot(n, d), n), d);
    }

    /**
     * compares two vectors robustly using some epsilon, component by component
     * @param a first vector
     * @param b second vector
     * @return true if vectors are "almost" the same
     */
    public static boolean almostEqual(Vec3 a, Vec3 b) {
        return 
            Util.almostEqual(a.x(),b.x()) && 
            Util.almostEqual(a.y(),b.y()) && 
            Util.almostEqual(a.z(),b.z());
    }

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
   * Returns the z-coordinate of the vector.
   * This method is an alias for the z() method.
   *
   * @return The z-coordinate (w-component) of the vector.
   */
  public double w() {
    return z;
  }

  public Vec2 uv() {
    return new Vec2(x, y);
  }



}