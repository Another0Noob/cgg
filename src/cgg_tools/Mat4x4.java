// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

/**
 * Represents a 4x4 matrix for 3D transformations.
 */
public final class Mat4x4 {

    /**
     * Returns a string representation of the matrix.
     *
     * @return A string representation of the matrix, with each row on a new line.
     */
    @Override
    public String toString() {
        String s = "";
        for (int r = 0; r < 4; r++) {
            s += String.format("(% .2f % .2f % .2f % .2f)\n", get(0, r), get(1, r), get(2, r), get(3, r));
        }
        return s;
    }

    /**
     * Checks if this matrix is equal to another object.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Mat4x4))
            return false;
        if (o == this)
            return true;
        Mat4x4 m = (Mat4x4) o;
        for (int i = 0; i != 16; i++)
            if (values[i] != m.values[i])
                return false;
        return true;
    }

    /**
     * Constructs a new Mat44 instance and initializes it as an identity matrix.
     */
    Mat4x4() {
        makeIdentity();
    }

    /**
     * Returns the internal array of matrix values.
     *
     * @return The array of matrix values.
     */
    double[] values() {
        return values;
    }

    void setValues(double[] values) {
        this.values = values;
    }

    /**
     * Gets the value at the specified column and row of the matrix.
     *
     * @param c The column index (0-3).
     * @param r The row index (0-3).
     * @return The value at the specified position.
     */
    double get(int c, int r) {
        return values[4 * c + r];
    }

    /**
     * Sets the value at the specified column and row of the matrix.
     *
     * @param c The column index (0-3).
     * @param r The row index (0-3).
     * @param v The value to set.
     */
    void set(int c, int r, double v) {
        values[4 * c + r] = v;
    }

    /**
     * Resets this matrix to the identity matrix.
     *
     * @return This matrix, reset to the identity matrix.
     */
    Mat4x4 makeIdentity() {
        values = new double[16];
        set(0, 0, 1.0f);
        set(1, 1, 1.0f);
        set(2, 2, 1.0f);
        set(3, 3, 1.0f);
        return this;
    }

    /**
     * Multiplies this matrix with another matrix.
     *
     * @param m The matrix to multiply with.
     * @return A new Mat44 instance representing the result of the multiplication.
     */
    public Mat4x4 multiply(Mat4x4 m) {
        Mat4x4 n = new Mat4x4();
        {
            {
                double v = 0;
                v += values[4 * 0 + 0] * m.values[4 * 0 + 0];
                v += values[4 * 1 + 0] * m.values[4 * 0 + 1];
                v += values[4 * 2 + 0] * m.values[4 * 0 + 2];
                v += values[4 * 3 + 0] * m.values[4 * 0 + 3];
                n.values[4 * 0 + 0] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 1] * m.values[4 * 0 + 0];
                v += values[4 * 1 + 1] * m.values[4 * 0 + 1];
                v += values[4 * 2 + 1] * m.values[4 * 0 + 2];
                v += values[4 * 3 + 1] * m.values[4 * 0 + 3];
                n.values[4 * 0 + 1] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 2] * m.values[4 * 0 + 0];
                v += values[4 * 1 + 2] * m.values[4 * 0 + 1];
                v += values[4 * 2 + 2] * m.values[4 * 0 + 2];
                v += values[4 * 3 + 2] * m.values[4 * 0 + 3];
                n.values[4 * 0 + 2] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 3] * m.values[4 * 0 + 0];
                v += values[4 * 1 + 3] * m.values[4 * 0 + 1];
                v += values[4 * 2 + 3] * m.values[4 * 0 + 2];
                v += values[4 * 3 + 3] * m.values[4 * 0 + 3];
                n.values[4 * 0 + 3] = v;
            }
        }
        {
            {
                double v = 0;
                v += values[4 * 0 + 0] * m.values[4 * 1 + 0];
                v += values[4 * 1 + 0] * m.values[4 * 1 + 1];
                v += values[4 * 2 + 0] * m.values[4 * 1 + 2];
                v += values[4 * 3 + 0] * m.values[4 * 1 + 3];
                n.values[4 * 1 + 0] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 1] * m.values[4 * 1 + 0];
                v += values[4 * 1 + 1] * m.values[4 * 1 + 1];
                v += values[4 * 2 + 1] * m.values[4 * 1 + 2];
                v += values[4 * 3 + 1] * m.values[4 * 1 + 3];
                n.values[4 * 1 + 1] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 2] * m.values[4 * 1 + 0];
                v += values[4 * 1 + 2] * m.values[4 * 1 + 1];
                v += values[4 * 2 + 2] * m.values[4 * 1 + 2];
                v += values[4 * 3 + 2] * m.values[4 * 1 + 3];
                n.values[4 * 1 + 2] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 3] * m.values[4 * 1 + 0];
                v += values[4 * 1 + 3] * m.values[4 * 1 + 1];
                v += values[4 * 2 + 3] * m.values[4 * 1 + 2];
                v += values[4 * 3 + 3] * m.values[4 * 1 + 3];
                n.values[4 * 1 + 3] = v;
            }
        }
        {
            {
                double v = 0;
                v += values[4 * 0 + 0] * m.values[4 * 2 + 0];
                v += values[4 * 1 + 0] * m.values[4 * 2 + 1];
                v += values[4 * 2 + 0] * m.values[4 * 2 + 2];
                v += values[4 * 3 + 0] * m.values[4 * 2 + 3];
                n.values[4 * 2 + 0] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 1] * m.values[4 * 2 + 0];
                v += values[4 * 1 + 1] * m.values[4 * 2 + 1];
                v += values[4 * 2 + 1] * m.values[4 * 2 + 2];
                v += values[4 * 3 + 1] * m.values[4 * 2 + 3];
                n.values[4 * 2 + 1] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 2] * m.values[4 * 2 + 0];
                v += values[4 * 1 + 2] * m.values[4 * 2 + 1];
                v += values[4 * 2 + 2] * m.values[4 * 2 + 2];
                v += values[4 * 3 + 2] * m.values[4 * 2 + 3];
                n.values[4 * 2 + 2] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 3] * m.values[4 * 2 + 0];
                v += values[4 * 1 + 3] * m.values[4 * 2 + 1];
                v += values[4 * 2 + 3] * m.values[4 * 2 + 2];
                v += values[4 * 3 + 3] * m.values[4 * 2 + 3];
                n.values[4 * 2 + 3] = v;
            }
        }
        {
            {
                double v = 0;
                v += values[4 * 0 + 0] * m.values[4 * 3 + 0];
                v += values[4 * 1 + 0] * m.values[4 * 3 + 1];
                v += values[4 * 2 + 0] * m.values[4 * 3 + 2];
                v += values[4 * 3 + 0] * m.values[4 * 3 + 3];
                n.values[4 * 3 + 0] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 1] * m.values[4 * 3 + 0];
                v += values[4 * 1 + 1] * m.values[4 * 3 + 1];
                v += values[4 * 2 + 1] * m.values[4 * 3 + 2];
                v += values[4 * 3 + 1] * m.values[4 * 3 + 3];
                n.values[4 * 3 + 1] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 2] * m.values[4 * 3 + 0];
                v += values[4 * 1 + 2] * m.values[4 * 3 + 1];
                v += values[4 * 2 + 2] * m.values[4 * 3 + 2];
                v += values[4 * 3 + 2] * m.values[4 * 3 + 3];
                n.values[4 * 3 + 2] = v;
            }
            {
                double v = 0;
                v += values[4 * 0 + 3] * m.values[4 * 3 + 0];
                v += values[4 * 1 + 3] * m.values[4 * 3 + 1];
                v += values[4 * 2 + 3] * m.values[4 * 3 + 2];
                v += values[4 * 3 + 3] * m.values[4 * 3 + 3];
                n.values[4 * 3 + 3] = v;
            }
        }
        return n;
    }

    private double[] values;


        /**
     * Creates a Mat44 (4x4 matrix) from three Vec3 objects representing the basis
     * vectors.
     *
     * @param b0 The first basis vector.
     * @param b1 The second basis vector.
     * @param b2 The third basis vector.
     * @return A new Mat44 object with the given basis vectors.
     */
    public static Mat4x4 matrix(Vec3 b0, Vec3 b1, Vec3 b2) {
        Mat4x4 m = new Mat4x4();
        m.set(0, 0, b0.x());
        m.set(1, 0, b0.y());
        m.set(2, 0, b0.z());
        m.set(0, 1, b1.x());
        m.set(1, 1, b1.y());
        m.set(2, 1, b1.z());
        m.set(0, 2, b2.x());
        m.set(1, 2, b2.y());
        m.set(2, 2, b2.z());
        return m;
    }

    /**
     * Creates a Mat44 (4x4 matrix) from four Vec3 objects representing the basis
     * vectors and translation.
     *
     * @param b0 The first basis vector.
     * @param b1 The second basis vector.
     * @param b2 The third basis vector.
     * @param b3 The translation vector.
     * @return A new Mat44 object with the given basis vectors and translation.
     */
    public static Mat4x4 matrix(Vec3 b0, Vec3 b1, Vec3 b2, Vec3 b3) {
        Mat4x4 m = new Mat4x4();
        m.set(0, 0, b0.x());
        m.set(1, 0, b0.y());
        m.set(2, 0, b0.z());
        m.set(0, 1, b1.x());
        m.set(1, 1, b1.y());
        m.set(2, 1, b1.z());
        m.set(0, 2, b2.x());
        m.set(1, 2, b2.y());
        m.set(2, 2, b2.z());
        m.set(0, 3, b3.x());
        m.set(1, 3, b3.y());
        m.set(2, 3, b3.z());
        return m;
    }

    /**
     * Returns the identity matrix.
     *
     * @return The 4x4 identity matrix.
     */
    public static Mat4x4 identity() {
        return Mat4x4.identity;
    }

    /**
     * Creates a translation matrix.
     *
     * @param t The translation vector.
     * @return A new Mat44 representing the translation.
     */
    public static Mat4x4 move(Vec3 t) {
        Mat4x4 m = new Mat4x4();
        m.set(3, 0, t.x());
        m.set(3, 1, t.y());
        m.set(3, 2, t.z());
        return m;
    }

    /**
     * Creates a translation matrix.
     *
     * @param x The x-component of the translation.
     * @param y The y-component of the translation.
     * @param z The z-component of the translation.
     * @return A new Mat44 representing the translation.
     */
    public static Mat4x4 move(double x, double y, double z) {
        Mat4x4 m = new Mat4x4();
        m.set(3, 0, x);
        m.set(3, 1, y);
        m.set(3, 2, z);
        return m;
    }

    /**
     * Creates a rotation matrix around an arbitrary axis.
     *
     * @param axis  The axis of rotation.
     * @param angle The angle of rotation in degrees.
     * @return A new Mat44 representing the rotation.
     */
    public static Mat4x4 rotate(Vec3 axis, double angle) {
        final Mat4x4 m = new Mat4x4();
        final double rad = Math.toRadians(angle);
        final double cosa = Math.cos(rad);
        final double sina = Math.sin(rad);
        final double l = Math.sqrt(
            axis.x() * axis.x() + axis.y() * axis.y() + axis.z() * axis.z()
        );
        final double rx = axis.x() / l;
        final double ry = axis.y() / l;
        final double rz = axis.z() / l;
        final double icosa = 1 - cosa;

        m.set(0, 0, icosa * rx * rx + cosa);
        m.set(0, 1, icosa * rx * ry + rz * sina);
        m.set(0, 2, icosa * rx * rz - ry * sina);

        m.set(1, 0, icosa * rx * ry - rz * sina);
        m.set(1, 1, icosa * ry * ry + cosa);
        m.set(1, 2, icosa * ry * rz + rx * sina);

        m.set(2, 0, icosa * rx * rz + ry * sina);
        m.set(2, 1, icosa * ry * rz - rx * sina);
        m.set(2, 2, icosa * rz * rz + cosa);
        return m;
    }

    /**
     * Creates a rotation matrix around an arbitrary axis defined by its components.
     *
     * @param ax    The x-component of the rotation axis.
     * @param ay    The y-component of the rotation axis.
     * @param az    The z-component of the rotation axis.
     * @param angle The angle of rotation in degrees.
     * @return A new Mat44 representing the rotation.
     */
    public static Mat4x4 rotate(double ax, double ay, double az, double angle) {
        return rotate(new Vec3(ax, ay, az), angle);
    }

    /**
     * Creates a scaling matrix.
     *
     * @param x The x-scale factor.
     * @param y The y-scale factor.
     * @param z The z-scale factor.
     * @return A new Mat44 representing the scaling transformation.
     */
    public static Mat4x4 scale(double x, double y, double z) {
        Mat4x4 m = new Mat4x4();
        m.set(0, 0, x);
        m.set(1, 1, y);
        m.set(2, 2, z);
        return m;
    }

    /**
     * Creates a scaling matrix.
     *
     * @param d The Vec3 containing scale factors for x, y, and z.
     * @return A new Mat44 representing the scaling transformation.
     */
    public static Mat4x4 scale(Vec3 d) {
        Mat4x4 m = new Mat4x4();
        m.set(0, 0, d.x());
        m.set(1, 1, d.y());
        m.set(2, 2, d.z());
        return m;
    }

    /**
     * Creates a perspective projection matrix.
     *
     * @param fovy The field of view angle in the y direction, in degrees.
     * @param a    The aspect ratio (width / height).
     * @param n    The distance to the near clipping plane.
     * @param f    The distance to the far clipping plane.
     * @return A new Mat44 representing the perspective projection.
     */
    public static Mat4x4 perspective(double fovy, double a, double n, double f) {
        final double fov = (fovy / 181.0) * Math.PI;
        final double d = 1 / Math.tan(fov / 2);
        Mat4x4 m = new Mat4x4();
        m.set(0, 0, d / a);
        m.set(1, 1, d);
        m.set(2, 2, (n + f) / (n - f));
        m.set(3, 2, (2 * n * f) / (n - f));
        m.set(2, 3, -1);
        m.set(3, 3, 0);
        return m;
    }

    /**
     * Creates a viewport transformation matrix.
     *
     * @param sx The x-coordinate of the viewport's lower-left corner.
     * @param sy The y-coordinate of the viewport's lower-left corner.
     * @param w  The width of the viewport.
     * @param h  The height of the viewport.
     * @param n  The near depth range.
     * @param f  The far depth range.
     * @return A new Mat44 representing the viewport transformation.
     */
    public static Mat4x4 viewport(
        double sx,
        double sy,
        double w,
        double h,
        double n,
        double f
    ) {
        Mat4x4 m = new Mat4x4();
        m.set(0, 0, w / 2);
        m.set(3, 0, sx + w / 2);
        m.set(1, 1, -h / 2); // Flip y
        m.set(3, 1, sy + h / 2);
        m.set(2, 2, (f - n) / 2);
        m.set(3, 2, (n + f) / 2);
        return m;
    }

    /**
     * Multiplies two or more Mat44 matrices.
     *
     * @param a  The first matrix.
     * @param b  The second matrix.
     * @param ms Additional matrices (varargs).
     * @return The result of multiplying all matrices in order.
     */
    public static Mat4x4 multiply(Mat4x4 a, Mat4x4 b, Mat4x4... ms) {
        Mat4x4 r = a.multiply(b);
        for (Mat4x4 m : ms) r = r.multiply(m);
        return r;
    }

    /**
     * Multiplies a Mat44 matrix with a Vec3 point, treating the Vec3 as a point
     * (w=1).
     *
     * @param m The matrix.
     * @param p The point.
     * @return The transformed point.
     */
    public static Vec3 multiplyPoint(Mat4x4 m, Vec3 p) {
        final double x =
            m.get(0, 0) * p.x() +
            m.get(1, 0) * p.y() +
            m.get(2, 0) * p.z() +
            m.get(3, 0);
        final double y =
            m.get(0, 1) * p.x() +
            m.get(1, 1) * p.y() +
            m.get(2, 1) * p.z() +
            m.get(3, 1);
        final double z =
            m.get(0, 2) * p.x() +
            m.get(1, 2) * p.y() +
            m.get(2, 2) * p.z() +
            m.get(3, 2);
        return new Vec3(x, y, z);
    }

    /**
     * Multiplies a Mat44 matrix with a Vec3 direction, treating the Vec3 as a
     * direction (w=0).
     *
     * @param m The matrix.
     * @param d The direction.
     * @return The transformed direction.
     */
    public static Vec3 multiplyDirection(Mat4x4 m, Vec3 d) {
        double x =
            m.get(0, 0) * d.x() + m.get(1, 0) * d.y() + m.get(2, 0) * d.z();
        double y =
            m.get(0, 1) * d.x() + m.get(1, 1) * d.y() + m.get(2, 1) * d.z();
        double z =
            m.get(0, 2) * d.x() + m.get(1, 2) * d.y() + m.get(2, 2) * d.z();
        return new Vec3(x, y, z);
    }

    /**
     * Transposes a Mat44 matrix.
     *
     * @param m The matrix to transpose.
     * @return The transposed matrix.
     */
    public static Mat4x4 transpose(Mat4x4 m) {
        Mat4x4 n = new Mat4x4();
        for (int c = 0; c != 4; c++) {
            for (int r = 0; r != 4; r++) {
                n.set(c, r, m.get(r, c));
            }
        }
        return n;
    }

    /**
     * Inverts a Mat44 matrix.
     *
     * @param m The matrix to invert.
     * @return The inverted matrix.
     * @throws RuntimeException if the matrix is singular and not invertible.
     */
    public static Mat4x4 invert(Mat4x4 m) {
        Mat4x4 ret = new Mat4x4();
        double[] mat = m.values();
        double[] dst = ret.values();
        double[] tmp = new double[12];

        /* temparray for pairs */
        double src[] = new double[16];

        /* array of transpose source matrix */
        double det;

        /* determinant */
        /*
         * transpose matrix
         */
        for (int i = 0; i < 4; i++) {
            src[i] = mat[i * 4];
            src[i + 4] = mat[i * 4 + 1];
            src[i + 8] = mat[i * 4 + 2];
            src[i + 12] = mat[i * 4 + 3];
        }

        /* calculate pairs for first 8 elements (cofactors) */
        tmp[0] = src[10] * src[15];
        tmp[1] = src[11] * src[14];
        tmp[2] = src[9] * src[15];
        tmp[3] = src[11] * src[13];
        tmp[4] = src[9] * src[14];
        tmp[5] = src[10] * src[13];
        tmp[6] = src[8] * src[15];
        tmp[7] = src[11] * src[12];
        tmp[8] = src[8] * src[14];
        tmp[9] = src[10] * src[12];
        tmp[10] = src[8] * src[13];
        tmp[11] = src[9] * src[12];

        /* calculate first 8 elements (cofactors) */
        dst[0] = tmp[0] * src[5] + tmp[3] * src[6] + tmp[4] * src[7];
        dst[0] -= tmp[1] * src[5] + tmp[2] * src[6] + tmp[5] * src[7];
        dst[1] = tmp[1] * src[4] + tmp[6] * src[6] + tmp[9] * src[7];
        dst[1] -= tmp[0] * src[4] + tmp[7] * src[6] + tmp[8] * src[7];
        dst[2] = tmp[2] * src[4] + tmp[7] * src[5] + tmp[10] * src[7];
        dst[2] -= tmp[3] * src[4] + tmp[6] * src[5] + tmp[11] * src[7];
        dst[3] = tmp[5] * src[4] + tmp[8] * src[5] + tmp[11] * src[6];
        dst[3] -= tmp[4] * src[4] + tmp[9] * src[5] + tmp[10] * src[6];
        dst[4] = tmp[1] * src[1] + tmp[2] * src[2] + tmp[5] * src[3];
        dst[4] -= tmp[0] * src[1] + tmp[3] * src[2] + tmp[4] * src[3];
        dst[5] = tmp[0] * src[0] + tmp[7] * src[2] + tmp[8] * src[3];
        dst[5] -= tmp[1] * src[0] + tmp[6] * src[2] + tmp[9] * src[3];
        dst[6] = tmp[3] * src[0] + tmp[6] * src[1] + tmp[11] * src[3];
        dst[6] -= tmp[2] * src[0] + tmp[7] * src[1] + tmp[10] * src[3];
        dst[7] = tmp[4] * src[0] + tmp[9] * src[1] + tmp[10] * src[2];
        dst[7] -= tmp[5] * src[0] + tmp[8] * src[1] + tmp[11] * src[2];

        /* calculate pairs for second 8 elements (cofactors) */
        tmp[0] = src[2] * src[7];
        tmp[1] = src[3] * src[6];
        tmp[2] = src[1] * src[7];
        tmp[3] = src[3] * src[5];
        tmp[4] = src[1] * src[6];
        tmp[5] = src[2] * src[5];
        tmp[6] = src[0] * src[7];
        tmp[7] = src[3] * src[4];
        tmp[8] = src[0] * src[6];
        tmp[9] = src[2] * src[4];
        tmp[10] = src[0] * src[5];
        tmp[11] = src[1] * src[4];

        /* calculate second 8 elements (cofactors) */
        dst[8] = tmp[0] * src[13] + tmp[3] * src[14] + tmp[4] * src[15];
        dst[8] -= tmp[1] * src[13] + tmp[2] * src[14] + tmp[5] * src[15];
        dst[9] = tmp[1] * src[12] + tmp[6] * src[14] + tmp[9] * src[15];
        dst[9] -= tmp[0] * src[12] + tmp[7] * src[14] + tmp[8] * src[15];
        dst[10] = tmp[2] * src[12] + tmp[7] * src[13] + tmp[10] * src[15];
        dst[10] -= tmp[3] * src[12] + tmp[6] * src[13] + tmp[11] * src[15];
        dst[11] = tmp[5] * src[12] + tmp[8] * src[13] + tmp[11] * src[14];
        dst[11] -= tmp[4] * src[12] + tmp[9] * src[13] + tmp[10] * src[14];
        dst[12] = tmp[2] * src[10] + tmp[5] * src[11] + tmp[1] * src[9];
        dst[12] -= tmp[4] * src[11] + tmp[0] * src[9] + tmp[3] * src[10];
        dst[13] = tmp[8] * src[11] + tmp[0] * src[8] + tmp[7] * src[10];
        dst[13] -= tmp[6] * src[10] + tmp[9] * src[11] + tmp[1] * src[8];
        dst[14] = tmp[6] * src[9] + tmp[11] * src[11] + tmp[3] * src[8];
        dst[14] -= tmp[10] * src[11] + tmp[2] * src[8] + tmp[7] * src[9];
        dst[15] = tmp[10] * src[10] + tmp[4] * src[8] + tmp[9] * src[9];
        dst[15] -= tmp[8] * src[9] + tmp[11] * src[10] + tmp[5] * src[8];

        /* calculate determinant */
        det =
            src[0] * dst[0] +
            src[1] * dst[1] +
            src[2] * dst[2] +
            src[3] * dst[3];

        if (det == 0.0f) {
            throw new RuntimeException("singular matrix is not invertible");
        }

        /* calculate matrix inverse */
        det = 1 / det;

        for (int j = 0; j < 16; j++) {
            dst[j] *= det;
        }

        return ret;
    }

        /** The identity matrix. */
    public static final Mat4x4 identity;

    static {
        identity = new Mat4x4();
    }

}
