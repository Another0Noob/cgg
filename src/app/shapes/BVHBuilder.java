package app.shapes;

import cgg_tools.BoundingBox;
import cgg_tools.Mat4x4;

import java.util.ArrayList;
import java.util.List;

public class BVHBuilder {

  private static final int MAX_SHAPES_PER_LEAF = 2;

  /**
   * Erstellt aus einer flachen Liste von Shapes eine hierarchische Szene.
   */
  public static Shape buildHierarchy(List<Shape> shapes) {
    // Basis-Fall 1: Keine Shapes
    if (shapes == null || shapes.isEmpty()) {
      return null;
    }

    // Basis-Fall 2: Maximale Anzahl für ein Blatt erreicht
    if (shapes.size() <= MAX_SHAPES_PER_LEAF) {
      GroupShape leaf = new GroupShape(Mat4x4.identity());
      for (Shape s : shapes) {
        leaf.add(s);
      }
      return leaf;
    }

    // 1. Ermittle die Gesamt-Bounding-Box, die alle Shapes umschließt
    BoundingBox totalBB = calculateTotalBB(shapes);

    // 2. Teile diese BB entlang ihrer längsten Achse in zwei Hälften (Nutzt deine
    // Methoden)
    BoundingBox leftBB = totalBB.splitLeft();
    BoundingBox rightBB = totalBB.splitRight();

    // Listen für die Partitionierung
    List<Shape> leftShapes = new ArrayList<>();
    List<Shape> rightShapes = new ArrayList<>();
    List<Shape> remainingShapes = new ArrayList<>(); // Für Shapes, die in keine Hälfte sauber passen

    // 3. Sortiere alle Shapes in jeweils eine der beiden Hälften ein
    for (Shape shape : shapes) {
      BoundingBox shapeBB = shape.bounding_box();

      // Prüfen, ob das Shape vollständig in die linke oder rechte BB passt
      boolean fitsLeft = leftBB.contains(shapeBB);
      boolean fitsRight = rightBB.contains(shapeBB);

      if (fitsLeft && !fitsRight) {
        leftShapes.add(shape);
      } else if (fitsRight && !fitsLeft) {
        rightShapes.add(shape);
      } else {
        // Das Shape schneidet die Grenze (passt weder nur in links noch nur in rechts)
        remainingShapes.add(shape);
      }
    }

    // --- Absicherung gegen Endlosrekursion ---
    // Wenn alle Shapes auf einer Seite landen oder alle auf der Grenze liegen,
    // bricht die Rekursion auf natürliche Weise ab, indem wir ein Blatt erzeugen.
    if (leftShapes.isEmpty() && rightShapes.isEmpty()) {
      GroupShape leaf = new GroupShape(Mat4x4.identity());
      for (Shape s : shapes)
        leaf.add(s);
      return leaf;
    }

    // 4. Rekursiver Schritt für die beiden Hälften
    Shape leftChild = buildHierarchy(leftShapes);
    Shape rightChild = buildHierarchy(rightShapes);

    // 5. Erzeuge den aktuellen Gruppenknoten
    GroupShape currentGroup = new GroupShape(Mat4x4.identity());

    if (leftChild != null) {
      currentGroup.add(leftChild);
    }
    if (rightChild != null) {
      currentGroup.add(rightChild);
    }

    // Shapes, die auf der Grenze lagen, werden direkt diesem Gruppenknoten
    // hinzugefügt
    for (Shape s : remainingShapes) {
      currentGroup.add(s);
    }

    return currentGroup;
  }

  /**
   * Hilfsmethode zur Berechnung der umschließenden Gesamt-Bounding-Box
   */
  private static BoundingBox calculateTotalBB(List<Shape> shapes) {
    BoundingBox box = null;
    for (Shape s : shapes) {
      BoundingBox sBB = s.bounding_box();
      if (sBB == null)
        continue;

      if (box == null) {
        // Kopie erstellen, um Seiteneffekte zu vermeiden
        box = new BoundingBox(sBB.min(), sBB.max());
      } else {
        box.extend(sBB); // vorausgesetzt, extend modifiziert oder gibt eine erweiterte Box zurück
      }
    }
    return box;
  }
}
