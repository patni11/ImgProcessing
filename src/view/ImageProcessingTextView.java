package view;

import java.io.IOException;

/**
 * This interface represents a view for the image processing program that provides methods for
 * rendering messages.
 */
public interface ImageProcessingTextView {
  /**
   * Renders given message.
   *
   * @param message message to render
   * @throws IOException if message failed to render
   */
  void renderMessage(String message) throws IOException;
}
