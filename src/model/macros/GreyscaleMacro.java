package model.macros;

/**
 * This class represents a type of color transformation macro that creates a greyscale image.
 */
public class GreyscaleMacro extends ColorTransMacro {

  /**
   * Constructor that initializes the transformation matrix for creating a greyscale image, the name
   * of the image to operate on and the name of the destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @throws IllegalArgumentException if transformation matrix is null, transformation matrix length
   *                                  is not 3, or transformation matrix is not square
   */
  public GreyscaleMacro(String srcImageName, String destImageName)
          throws IllegalArgumentException {
    super(new double[][]{{0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722},
        {0.2126, 0.7152, 0.0722}}, srcImageName, destImageName);
  }
}
