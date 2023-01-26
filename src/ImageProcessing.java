import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.ImageProcessingController;
import controller.ImageProcessingGuiController;
import controller.ImageProcessingTextController;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import view.ImageProcessingGuiView;
import view.ImageProcessingGuiViewImpl;
import view.ImageProcessingTextView;
import view.ImageProcessingTextViewImpl;

/**
 * This class contains the main method for this program.
 */
public class ImageProcessing {

  /**
   * Main method for running image processing program.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) throws IllegalArgumentException, IOException {
    Readable rd;
    if (args.length == 2 && args[0].equals("-file")) {
      try {
        rd = new FileReader(args[1]);
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found: " + args[1]);
      }
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageProcessingTextView view = new ImageProcessingTextViewImpl();
      ImageProcessingController controller = new ImageProcessingTextController(model, view, rd);
      controller.runController();
    } else if (args.length == 1 && args[0].equals("-text")) {
      rd = new BufferedReader(new InputStreamReader(System.in));
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageProcessingTextView view = new ImageProcessingTextViewImpl();
      ImageProcessingController controller = new ImageProcessingTextController(model, view, rd);
      controller.runController();
    } else if (args.length == 0) {
      ImageProcessingModel model = new ImageProcessingModelImpl();
      ImageProcessingGuiView view = new ImageProcessingGuiViewImpl();
      ImageProcessingController controller = new ImageProcessingGuiController(model, view);
      controller.runController();
    } else {
      throw new IllegalArgumentException("Invalid arguments provided.");
    }
  }
}

