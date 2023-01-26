package model;

/**
 * This interface represents an image.
 */
public interface Image {

  /**
   * Returns width of image in pixels.
   *
   * @return width of image in pixels
   */
  int getWidth();

  /**
   * Returns height of image in pixels.
   *
   * @return height of image in pixels
   */
  int getHeight();

  /**
   * Gets Pixel object of image at given position.
   *
   * @param row the row of the pixel (starting from 0)
   * @param col the column of the pixel (starting from 0)
   * @return Pixel object of image at given position
   * @throws IllegalArgumentException if given position is out of bounds
   */
  Pixel getPixelAt(int row, int col) throws IllegalArgumentException;

  /**
   * Sets RGB values of pixel at given position of image.
   *
   * @param row   the row of the pixel (starting from 0)
   * @param col   the column of the pixel (starting from 0)
   * @param red   value of red component (0-255)
   * @param green value of green component (0-255)
   * @param blue  value of blue component (0-255)
   * @throws IllegalArgumentException if given position is out of bounds, or given RGB components
   *                                  are not in the range 0-255
   */
  void setPixelAt(int row, int col, int red, int green, int blue) throws IllegalArgumentException;
}
