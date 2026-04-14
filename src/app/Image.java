
package app;

import cgg_tools.Color;
import cgg_tools.ImageWriter;

public class Image implements cgg_tools.Image {

  private int width;
  private int height;
  private double[] pixels;

  // TODO create a double array for storage of the RGB Pixels
  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixels = new double[width * height *3];
  }

  // TODO store the RGB values of the pixel (i,j) in the image
  @Override
  public void setPixel(int i, int j, Color color) {
    pixels[3* (j * width + i)] = color.r();
    pixels[3* (j * width + i) + 1] = color.g();
    pixels[3* (j * width + i) + 2] = color.b();
  }

  // TODO use ImageWriter.writePNG to implement this
  public void writePNG(String name) {
    ImageWriter.writePNG(name, pixels, width, height);
  }

  // TODO return the RGB color of a given pixel (i,j)
  @Override
  public Color getPixel(int i, int j) {
    return Color.black;
  }

  // TODO return the image width
  @Override
  public int width() {
    return -1;
  }

  // TODO return the image height
  @Override
  public int height() {
    return -1;
  }

}
