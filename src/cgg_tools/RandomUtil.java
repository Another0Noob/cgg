// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de
package cgg_tools;

// import cgg_tools.Vec2;
// import cgg_tools.Vec3;
// import cgg_tools.Color;

public class RandomUtil {

        /**
     * Generates a random double between 0 and 1.
     *
     * @return A random double between 0 and 1.
     */
    public static double random() {
        return Random.random();
    }

    /**
     * Generates a random Vec2 with components between -1 and 1.
     *
     * @return A random Vec2.
     */
    public static Vec2 randomVec2() {
        return new Vec2(Random.random() * 2 - 1, Random.random() * 2 - 1);
    }

    /**
     * Generates a random 3D direction vector.
     *
     * @return A random Vec3 representing a direction.
     */
    public static Vec3 randomDirection() {
        return new Vec3(
            Random.random() * 2 - 1,
            Random.random() * 2 - 1,
            Random.random() * 2 - 1
        );
    }

    /**
     * Generates a random Color with components between 0 and 1.
     *
     * @return A random Color.
     */
    public static Color randomColor() {
        return new Color(Random.random(), Random.random(), Random.random());
    }

    /**
     * Generates a random color with specified saturation and value (brightness).
     *
     * @param s The saturation component (0.0 to 1.0).
     * @param v The value (brightness) component (0.0 to 1.0).
     * @return A random Color with the specified saturation and value.
     */
    public static Color randomHue(double s, double v) {
        return Color.hsvToRgb(Random.random(), s, v);
    }

    /**
     * Generates a random fully saturated and bright color.
     *
     * @return A random Color with full saturation and brightness.
     */
    public static Color randomHue() {
        return randomHue(1.0, 1.0);
    }

    /**
     * Generates a random gray color.
     *
     * @return A random Color in the gray scale.
     */
    public static Color randomGray() {
        double v = Random.random() * 0.4 + 0.3;
        return new Color(v,v,v);
    }

}
