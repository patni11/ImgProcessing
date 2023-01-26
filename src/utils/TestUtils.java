package utils;

import model.Image;

/**
 * This class contains utility methods for testing.
 */
public class TestUtils {
  /**
   * Helper method that checks if an image contains the expected RGB values.
   *
   * @param expected image with expected RGB values
   * @param actual   actual image
   */
  public static boolean equalsImages(Image expected, Image actual) {
    if (expected.getWidth() != actual.getWidth() || expected.getHeight() != actual.getHeight()) {
      return false;
    }
    boolean equal = true;
    for (int i = 0; i < expected.getHeight(); i++) {
      for (int j = 0; j < expected.getWidth(); j++) {
        if (expected.getPixelAt(i, j).getRed() != actual.getPixelAt(i, j).getRed()
                || expected.getPixelAt(i, j).getGreen() != actual.getPixelAt(i, j).getGreen()
                || expected.getPixelAt(i, j).getBlue() != actual.getPixelAt(i, j).getBlue()) {
          equal = false;
        }
      }
    }
    return equal;
  }

  /**
   * Prints a sequence of lines to output.
   *
   * @param lines array of lines to print
   * @return an Interaction representing printed output
   */
  public static Interaction prints(String... lines) {
    return (input, output) -> {
      for (String line : lines) {
        output.append(line).append('\n');
      }
    };
  }

  /**
   * A user providing input.
   *
   * @param in input from user
   * @return an Interaction representing the user providing input
   */
  public static Interaction inputs(String in) {
    return (input, output) -> {
      input.append(in).append('\n');
    };
  }
}
