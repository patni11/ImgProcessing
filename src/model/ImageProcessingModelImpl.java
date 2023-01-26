package model;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents an image processing model that stores all the images in the program in a
 * hashmap.
 */
public class ImageProcessingModelImpl implements ImageProcessingModel {

  private final Map<String, Image> images;

  /**
   * Constructor that initializes hashmap to store images.
   */
  public ImageProcessingModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public void addImage(String imageName, Image image) {
    this.images.put(imageName, new ImageImpl(image));
  }

  @Override
  public Image getImage(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Image not found: " + imageName);
    }
    return new ImageImpl(this.images.get(imageName));
  }
}
