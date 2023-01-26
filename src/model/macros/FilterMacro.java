package model.macros;

import model.Image;
import model.ImageProcessingModel;
import model.Pixel;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * This class represents a macro that creates an image by applying a filter.
 */
public abstract class FilterMacro implements ImageProcessingMacro {
  final double[][] kernel;
  final String srcImageName;
  final String destImageName;

  /**
   * Constructor that takes in the filter kernel, the name of the image to operate on and the
   * name of the destination image.
   *
   * @param kernel        filter kernel
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @throws IllegalArgumentException if filter kernel is null, filter kernel has even dimensions,
   *                                  or filter kernel is not square
   */
  protected FilterMacro(double[][] kernel, String srcImageName, String destImageName)
          throws IllegalArgumentException {
    if (kernel == null) {
      throw new IllegalArgumentException("Filter cannot be null.");
    }
    if (kernel.length % 2 == 0) {
      throw new IllegalArgumentException("Invalid filter size.");
    }
    for (int i = 0, l = kernel.length; i < l; i++) {
      if (kernel[i] == null) {
        throw new IllegalArgumentException("At least one row is null.");
      }
      if (kernel[i].length != l) {
        throw new IllegalArgumentException("Filter matrix must be square.");
      }
    }
    this.kernel = kernel;
    this.srcImageName = srcImageName;
    this.destImageName = destImageName;

  }

  /**
   * Creates an image by applying a filter, and adds it to the given model.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image name could not be found in model
   */
  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    Image origImage = model.getImage(this.srcImageName);
    Image destImage = model.getImage(this.srcImageName);
    int offset = kernel.length / 2;
    for (int i = 0; i < destImage.getHeight(); i++) {
      for (int j = 0; j < destImage.getWidth(); j++) {
        double redLinComb = 0;
        double greenLinComb = 0;
        double blueLinComb = 0;
        for (int k = 0; k < this.kernel.length; k++) {
          for (int l = 0; l < this.kernel.length; l++) {
            Pixel p = origImage.getPixelAt(min(destImage.getHeight() - 1, max(0, i - offset + k)),
                    min(destImage.getWidth() - 1, max(0, j - offset + l)));
            redLinComb += p.getRed() * this.kernel[k][l];
            greenLinComb += p.getGreen() * this.kernel[k][l];
            blueLinComb += p.getBlue() * this.kernel[k][l];
          }
        }
        destImage.setPixelAt(i, j, (int) max(0, min(255, redLinComb)), (int) max(0, min(255,
                greenLinComb)), (int) max(0, min(255, blueLinComb)));
      }
    }
    model.addImage(this.destImageName, destImage);
  }
}
