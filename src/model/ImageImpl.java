package model;

/**
 * This class represents an image as a 2D array of Pixel objects.
 */
public class ImageImpl implements Image {

  private final Pixel[][] image;

  /**
   * Constructor initializes ImageImpl object using given 2D array of Pixel objects representing
   * an image.
   *
   * @param image 2D array of Pixel objects representing an image
   * @throws IllegalArgumentException if array is null, any Pixel objects are null, or rows in
   *                                  array are not all the same length
   */
  public ImageImpl(Pixel[][] image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image array is null.");
    }
    for (int i = 0; i < image.length; i++) {
      if (image[i] == null) {
        throw new IllegalArgumentException("At least one row is null.");
      }
      if (image[i].length != image[0].length) {
        throw new IllegalArgumentException("Rows in image array are not the same length.");
      }
    }
    this.image = new Pixel[image.length][image[0].length];
    for (int r = 0; r < image.length; r++) {
      for (int c = 0; c < image[0].length; c++) {
        if (image[r][c] == null) {
          throw new IllegalArgumentException("At least one pixel is null.");
        }
        this.image[r][c] = new Pixel(image[r][c].getRed(), image[r][c].getGreen(),
                image[r][c].getBlue());
      }
    }
  }

  /**
   * Constructor that creates a copy of given Image object.
   *
   * @param image Image object to copy
   * @throws IllegalArgumentException if given Image object is null
   */
  public ImageImpl(Image image) throws IllegalArgumentException {
    if (image == null) {
      throw new IllegalArgumentException("Image is null.");
    }
    this.image = new Pixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        this.image[i][j] = image.getPixelAt(i, j);
      }
    }
  }

  @Override
  public int getWidth() {
    return image[0].length;
  }

  @Override
  public int getHeight() {
    return image.length;
  }

  @Override
  public Pixel getPixelAt(int row, int col) throws IllegalArgumentException {
    if (row < 0 || row >= this.getHeight() || col < 0 || col >= this.getWidth()) {
      throw new IllegalArgumentException("Row or col is out of bounds.");
    }
    return new Pixel(this.image[row][col].getRed(), this.image[row][col].getGreen(),
            this.image[row][col].getBlue());
  }

  @Override
  public void setPixelAt(int row, int col, int red, int green, int blue)
          throws IllegalArgumentException {
    if (row < 0 || row >= this.getHeight() || col < 0 || col >= this.getWidth()) {
      throw new IllegalArgumentException("Row or col is out of bounds.");
    }
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("RGB values must be in the range 0-255");
    }
    this.image[row][col].setRed(red);
    this.image[row][col].setGreen(green);
    this.image[row][col].setBlue(blue);
  }
}
