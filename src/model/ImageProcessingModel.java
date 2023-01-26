package model;

/**
 * This interface represents an image processing model that stores all the images in the program.
 */
public interface ImageProcessingModel {

  /**
   * Adds a copy of given Image object, referred to henceforth by given image name.
   *
   * @param imageName name to refer to Image object
   * @param image     Image object to add
   */
  void addImage(String imageName, Image image);

  /**
   * Returns a copy of Image object with given image name.
   *
   * @param imageName the name of the Image object
   * @return Image object with given image name
   * @throws IllegalArgumentException if Image object with given image name could not be found
   */
  Image getImage(String imageName) throws IllegalArgumentException;
}
