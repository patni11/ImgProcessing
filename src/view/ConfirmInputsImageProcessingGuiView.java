package view;

import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Image;
import model.ImageHistogramModel;

/**
 * This class is used to test that the ImageProcessingGuiController calls the correct methods in
 * the ImageProcessingGuiViewImpl, and that button clicks are handled correctly.
 */
public class ConfirmInputsImageProcessingGuiView implements ImageProcessingGuiView {

  private final StringBuilder log;
  private final JButton button;
  private final String image;

  /**
   * Constructor that initializes the log, button to test, and sample image path.
   *
   * @param log    log of method calls
   * @param button button to test
   * @param image  path of sample image
   */
  public ConfirmInputsImageProcessingGuiView(StringBuilder log, JButton button, String image)
          throws IllegalArgumentException {
    if (log == null || button == null || image == null) {
      throw new IllegalArgumentException("Argument(s) are null.");
    }
    this.log = log;
    this.button = button;
    this.image = image;
  }

  @Override
  public void makeVisible() {
    this.log.append("called makeVisible\n");
  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    this.button.addActionListener(listener);
  }

  @Override
  public String showLoadFileChooser() {
    this.log.append("called showLoadFileChooser\n");
    return this.image;
  }

  @Override
  public String showSaveFileChooser() {
    this.log.append("called showSaveFileChooser\n");
    return this.image;
  }

  @Override
  public void refresh(Image image, ImageHistogramModel model) {
    this.log.append("called refresh\n");
  }

  @Override
  public void showErrorMessage(String message) {
    this.log.append("called showErrorMessage\n");
  }

  @Override
  public String getMosaicSeeds() {
    return "5";
  }
}
