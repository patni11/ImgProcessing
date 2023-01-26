package model;

/**
 * This class represents a histogram model that gets attributes from an image to be displayed in
 * a histogram.
 */
public class ImageHistogramModel {

  private final Image image;

  /**
   * Constructor that initializes histogram model with given image.
   *
   * @param image to represent in histogram model
   * @throws IllegalArgumentException if image is null
   */
  public ImageHistogramModel(Image image) throws IllegalArgumentException {
    this.image = new ImageImpl(image);
  }

  /**
   * Retrieves red components from image.
   *
   * @return list of red components
   */
  public int[] getRedHistogram() {
    int[] counts = new int[256];
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int redC = this.image.getPixelAt(i, j).getRed();
        counts[redC] += 1;
      }
    }
    return counts;
  }

  /**
   * Retrieves green components from image.
   *
   * @return list of red components
   */
  public int[] getGreenHistogram() {
    int[] counts = new int[256];
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int greenC = this.image.getPixelAt(i, j).getGreen();
        counts[greenC] += 1;
      }
    }
    return counts;
  }

  /**
   * Retrieves blue components from image.
   *
   * @return list of red components
   */
  public int[] getBlueHistogram() {
    int[] counts = new int[256];
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        int blueC = this.image.getPixelAt(i, j).getBlue();
        counts[blueC] += 1;
      }
    }
    return counts;
  }

  /**
   * Retrieves intensity components from image.
   *
   * @return list of intensity components
   */
  public int[] getIntensityHistogram() {
    int[] counts = new int[256];
    for (int i = 0; i < this.image.getHeight(); i++) {
      for (int j = 0; j < this.image.getWidth(); j++) {
        Pixel p = this.image.getPixelAt(i, j);
        int intensity = (p.getRed() + p.getGreen() + p.getBlue()) / 3;
        counts[intensity] += 1;
      }
    }
    return counts;
  }
}
