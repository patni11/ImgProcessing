package model.macros;

/**
 * This class represents a type of filter macro that creates a blurred image.
 */
public class BlurMacro extends FilterMacro {

  /**
   * Constructor that initializes the filter kernel for blurring an image, the name of the image
   * to operate on and the name of the destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @throws IllegalArgumentException if filter kernel is null, filter kernel has even dimensions,
   *                                  or filter kernel is not square
   */
  public BlurMacro(String srcImageName, String destImageName) throws IllegalArgumentException {
    super(new double[][]{{1.0 / 16, 1.0 / 8, 1.0 / 16},
        {1.0 / 8, 1.0 / 4, 1.0 / 8},
        {1.0 / 16, 1.0 / 8, 1.0 / 16}}, srcImageName, destImageName);
  }
}
