package model.macros;

import model.Image;
import model.ImageProcessingModel;
import model.Pixel;


/**
 * This class represents a macro that creates a greyscale image with the intensity-component of an
 * image.
 */
public class IntensityComponentMacro implements ImageProcessingMacro {

  private final String srcImageName;
  private final String destImageName;

  /**
   * Constructor that takes in the name of the image to operate on and the name of the
   * destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   */
  public IntensityComponentMacro(String srcImageName, String destImageName) {
    this.srcImageName = srcImageName;
    this.destImageName = destImageName;
  }

  /**
   * Creates a greyscale image with the intensity-component of an image, and adds it to the given
   * model.
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
        int intensity = (p.getRed() + p.getGreen() + p.getBlue()) / 3;
        destImage.setPixelAt(i, j, intensity, intensity, intensity);
      }
    }
    model.addImage(this.destImageName, destImage);
  }
}