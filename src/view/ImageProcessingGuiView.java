package view;

import java.awt.event.ActionListener;

import model.Image;
import model.ImageHistogramModel;

/**
 * This interface represents a view for the image processing program with a graphical user
 * interface for the user to interact with.
 */
public interface ImageProcessingGuiView {

  /**
   * Displays the GUI view.
   */
  void makeVisible();

  /**
   * Provides the view with an action listener for all buttons. When a button is pressed,
   * control goes to the action listener.
   *
   * @param listener button listener
   */
  void setButtonListeners(ActionListener listener);

  /**
   * Shows a file chooser window for loading a file and returns the path to the file that is
   * selected by the user.
   *
   * @return path of selected file
   */
  String showLoadFileChooser();

  /**
   * Shows a file chooser window for saving a file and returns the path to the file that is
   * selected by the user.
   *
   * @return path of selected file
   */
  String showSaveFileChooser();

  /**
   * Refreshes image and histograms in view.
   */
  void refresh(Image image, ImageHistogramModel model);

  /**
   * Displays an error message in view.
   *
   * @param message error message to display
   */
  void showErrorMessage(String message);

  String getMosaicSeeds();
}
