
package app;

import cgg_tools.Color;
import cgg_tools.ImageWriter;
import cgg_tools.Sampler;
import cgg_tools.Vec2;

public class Image implements cgg_tools.Image {

  private int width;
  private int height;
  private double[] pixels;

  public Image(int width, int height) {
    this.width = width;
    this.height = height;
    this.pixels = new double[width * height * 3];
  }

  @Override
  public void setPixel(int i, int j, Color color) {
    int index = 3 * (j * width + i);
    pixels[index] = color.r();
    pixels[index + 1] = color.g();
    pixels[index + 2] = color.b();
  }

  public void sample(Sampler obj) {
    for (int i = 0; i != width; i++) {
      for (int j = 0; j != height; j++) {
        this.setPixel(i, j, obj.getColor(new Vec2(i, j)));
      }
    }
  }

  public void writePNG(String name) {
    ImageWriter.writePNG(name, pixels, width, height);
  }

  @Override
  public Color getPixel(int i, int j) {
    int index = 3 * (j * width + i);
    double r = pixels[index];
    double g = pixels[index + 1];
    double b = pixels[index + 2];
    return new Color(r, g, b);
  }

  @Override
  public int width() {
    return width;
  }

  @Override
  public int height() {
    return height;
  }

}
