package view;

import java.io.IOException;

/**
 * This class represents a view for the image processing program that writes messages to an
 * output destination.
 */
public class ImageProcessingTextViewImpl implements ImageProcessingTextView {

  private final Appendable destination;

  /**
   * Constructor that sets output to System.out.
   */
  public ImageProcessingTextViewImpl() {
    this.destination = System.out;
  }

  /**
   * Constructor that sets output to the given destination.
   *
   * @param destination Appendable object
   * @throws IllegalArgumentException if given Appendable object is null
   */
  public ImageProcessingTextViewImpl(Appendable destination) throws IllegalArgumentException {
    if (destination == null) {
      throw new IllegalArgumentException("Appendable is null.");
    }
    this.destination = destination;
  }

  /**
   * Renders given message to the destination of this view.
   *
   * @param message message to render
   * @throws IOException if message failed to render
   */
  @Override
  public void renderMessage(String message) throws IOException {
    this.destination.append(message);
  }
}
