package model.macros;

import model.Image;
import model.ImageProcessingModel;
import model.Pixel;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * This class represents a macro that creates an image by applying a color transformation.
 */
public abstract class ColorTransMacro implements ImageProcessingMacro {

  final double[][] transMat;
  final String srcImageName;
  final String destImageName;

  /**
   * Constructor that takes in the transformation matrix, the name of the image to operate on and
   * the name of the destination image.
   *
   * @param transMat      transformation matrix
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @throws IllegalArgumentException if transformation matrix is null, transformation matrix length
   *                                  is not 3, or transformation matrix is not square
   */
  protected ColorTransMacro(double[][] transMat, String srcImageName, String destImageName)
          throws IllegalArgumentException {
    if (transMat == null) {
      throw new IllegalArgumentException("Filter cannot be null.");
    }
    if (transMat.length != 3) {
      throw new IllegalArgumentException("Filter size must be three.");
    }
    for (int i = 0, l = transMat.length; i < l; i++) {
      if (transMat[i] == null) {
        throw new IllegalArgumentException("At least one row is null.");
      }
      if (transMat[i].length != l) {
        throw new IllegalArgumentException("Filter matrix must be square.");
      }
    }
    this.transMat = transMat;
    this.srcImageName = srcImageName;
    this.destImageName = destImageName;
  }

  /**
   * Creates an image by applying a color transformation, and adds it to the given model.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image name could not be found in model
   */
  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    Image destImage = model.getImage(this.srcImageName);
    for (int i = 0; i < destImage.getHeight(); i++) {
      for (int j = 0; j < destImage.getWidth(); j++) {
        Pixel p = destImage.getPixelAt(i, j);
        double[] oldRGB = new double[]{p.getRed(), p.getGreen(), p.getBlue()};
        double[] transRGB = new double[3];
        for (int k = 0; k < this.transMat.length; k++) {
          double comp = 0;
          for (int l = 0; l < this.transMat.length; l++) {
            comp += oldRGB[l] * this.transMat[k][l];
          }
          transRGB[k] = comp;
        }
        destImage.setPixelAt(i, j, (int) max(0, min(255, transRGB[0])), (int) max(0,
                min(255, transRGB[1])), (int) max(0, min(255, transRGB[2])));
      }
    }
    model.addImage(this.destImageName, destImage);
  }
}
