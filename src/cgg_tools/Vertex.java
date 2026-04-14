// module "Computergrafik Grundlagen", authors Hartmut Schirmacher + Henrik Tramberend
// Berliner Hochschule für Technik
// contact hschirmacher@bht-berlin.de

package cgg_tools;

public record Vertex(Vec3 position, Vec3 normal, Vec2 uv, Color color) {

    public Vertex(Vec3 position, Vec3 normal, Vec2 uv) {
        this(position, normal, uv, Color.magenta);
    }

    public String toString() {
        return "(Vertex: " + position + " " + normal + " " + uv + " " + color + ")";
    }
}
