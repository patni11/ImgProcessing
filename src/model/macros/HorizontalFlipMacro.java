package model.macros;

import model.Image;
import model.ImageImpl;
import model.ImageProcessingModel;
import model.Pixel;

/**
 * This class represents a macro that flips an image horizontally to create a new image.
 */
public class HorizontalFlipMacro implements ImageProcessingMacro {
  private final String srcImageName;
  private final String destImageName;

  /**
   * Constructor that takes in the name of the image to operate on and the name of the
   * destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   */
  public HorizontalFlipMacro(String srcImageName, String destImageName) {
    this.srcImageName = srcImageName;
    this.destImageName = destImageName;
  }

  /**
   * Flips an image horizontally to create a new image, and adds it to given ImageProcessingModel
   * object.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image name could not be found in model
   */
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    Image origImage = model.getImage(this.srcImageName);
    Image destImage = new ImageImpl(origImage);
    for (int i = 0; i < destImage.getHeight(); i++) {
      for (int j = 0; j < destImage.getWidth(); j++) {
        Pixel p = origImage.getPixelAt(i, destImage.getWidth() - j - 1);
        destImage.setPixelAt(i, j, p.getRed(), p.getGreen(), p.getBlue());
      }
    }
    model.addImage(destImageName, destImage);
  }
}
