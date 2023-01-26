package model.macros;

import model.Image;
import model.ImageProcessingModel;
import model.Pixel;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * This class represents a macro that brightens an image by an increment to create a new
 * image. The increment may be positive (brightening) or negative (darkening).
 */
public class BrightenMacro implements ImageProcessingMacro {

  private final int increment;
  private final String srcImageName;
  private final String destImageName;

  /**
   * Takes in increment as an argument.
   *
   * @param increment brightness to change by
   */
  public BrightenMacro(int increment, String srcImageName, String destImageName) {
    this.increment = increment;
    this.srcImageName = srcImageName;
    this.destImageName = destImageName;
  }

  /**
   * Brightens an image by an increment to create a new image, and adds it to given
   * ImageProcessingModel object. The increment may be positive (brightening) or negative
   * (darkening).
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image name could not be found in model or increment is
   *                                  too big or small.
   */
  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    Image destImage = model.getImage(this.srcImageName);
    for (int i = 0; i < destImage.getHeight(); i++) {
      for (int j = 0; j < destImage.getWidth(); j++) {
        Pixel p = destImage.getPixelAt(i, j);
        destImage.setPixelAt(i, j, max(0, min(255, p.getRed() + this.increment)),
                max(0, min(255, p.getGreen() + this.increment)),
                max(0, min(255, p.getBlue() + this.increment)));
      }
    }
    model.addImage(this.destImageName, destImage);
  }
}
