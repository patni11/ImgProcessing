package model.macros;

import model.ImageProcessingModel;

/**
 * This interface represents a macro that performs an operation on a given image to create a new
 * image.
 */
public interface ImageProcessingMacro {

  /**
   * Executes macro on an Image object to create a new Image object, and adds it to given
   * ImageProcessingModel object.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if macro is given invalid arguments for its operation
   */
  void execute(ImageProcessingModel model) throws IllegalArgumentException;
}
