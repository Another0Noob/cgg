
// Modul "Computergrafik Grundlagen", Studiengang Medieninformatik Bachelor
// Berliner Hochschule für Technik BHT
// Autor: Hartmut Schirmacher

package app.materials;

import app.Hit;

import cgg_tools.Color;
import cgg_tools.Vec3;

public interface Material {

  public default boolean does_ambient_lighting() {
    return false;
  }

  public default boolean does_direct_lighting() {
    return false;
  }

  public default boolean does_emission() {
    return false;
  }

  public default boolean does_reflection() {
    return false;
  }

  /* Calculates ambient reflected light, independent of concrete light sources */
  public default Color ambient(Hit hit, Color ambient_light) {
    throw new UnsupportedOperationException("Unimplemented method 'ambient'");
  }

  /*
   * Calculates the fraction of incoming_light that is reflected from direction
   * to_light
   * into direction to_viewer. This method can be called for direct lighting as
   * well as
   * for calculation of global illumination (inter-object reflections).
   */
  public default Color shade(Hit hit, Vec3 to_viewer, Vec3 to_light, Color incoming_light) {
    throw new UnsupportedOperationException("Unimplemented method 'shade'");
  }

  /* Amount of light directly emitted by the material */
  public default Color emission(Hit hit, Vec3 to_viewer) {
    throw new UnsupportedOperationException("Unimplemented method 'emission'");
  }

  /*
   * for a given viewing direction, calculate one ray representing a valid
   * reflection
   * direction for this material. may use stochastic sampling / random numbers.
   */
  public default Vec3 generate_reflection_direction(Hit hit, Vec3 to_viewer) {
    throw new UnsupportedOperationException("Unimplemented method 'generate_reflection_direction'");
  }

}
