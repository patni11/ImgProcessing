package model;

/**
 * This class represents a pixel in an image.
 */
public class Pixel {
  private int red;
  private int green;
  private int blue;

  /**
   * Constructor that creates Pixel object with given RGB values.
   *
   * @param red   value of red component (0-255)
   * @param green value of green component (0-255)
   * @param blue  value of blue component (0-255)
   * @throws IllegalArgumentException if RGB values are not in the range 0-255
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
      throw new IllegalArgumentException("RGB values must be in the range 0-255");
    }
    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  /**
   * Gets the red component of this Pixel.
   *
   * @return red component as an int
   */
  public int getRed() {
    return this.red;
  }

  /**
   * Gets the green component of this Pixel.
   *
   * @return green component as an int
   */
  public int getGreen() {
    return this.green;
  }

  /**
   * Gets the blue component of this Pixel.
   *
   * @return blue component as an int
   */
  public int getBlue() {
    return this.blue;
  }

  /**
   * Sets the red component of this Pixel to a given value.
   *
   * @param val value of red component from 0-255
   * @throws IllegalArgumentException if given value is not in the range 0-255
   */
  public void setRed(int val) throws IllegalArgumentException {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("RGB values must be in the range 0-255");
    }
    this.red = val;
  }

  /**
   * Sets the green component of this Pixel to a given value.
   *
   * @param val value of green component from 0-255
   * @throws IllegalArgumentException if given value is not in the range 0-255
   */
  public void setGreen(int val) throws IllegalArgumentException {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("RGB values must be in the range 0-255");
    }
    this.green = val;
  }

  /**
   * Sets the blue component of this Pixel to a given value.
   *
   * @param val value of blue component from 0-255
   * @throws IllegalArgumentException if given value is not in the range 0-255
   */
  public void setBlue(int val) throws IllegalArgumentException {
    if (val < 0 || val > 255) {
      throw new IllegalArgumentException("RGB values must be in the range 0-255");
    }
    this.blue = val;
  }
}
