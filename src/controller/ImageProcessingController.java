package controller;

/**
 * This interface represents a controller that can run an image processing program.
 */
public interface ImageProcessingController {
  /**
   * Runs an image processing program.
   *
   * @throws IllegalStateException if there is an error while the controller is running the program
   */
  void runController() throws IllegalStateException;
}
