package model.macros;

/**
 * This class represents a type of color transformation macro that creates a sepia image.
 */
public class SepiaMacro extends ColorTransMacro {

  /**
   * Constructor that initializes the transformation matrix for creating a sepia image, the name
   * of the image to operate on and the name of the destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @throws IllegalArgumentException if transformation matrix is null, transformation matrix length
   *                                  is not 3, or transformation matrix is not square
   */
  public SepiaMacro(String srcImageName, String destImageName) throws IllegalArgumentException {
    super(new double[][]{{0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}}, srcImageName, destImageName);
  }
}
