package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.Image;
import model.ImageHistogramModel;
import model.ImageProcessingModel;
import model.macros.BlueComponentMacro;
import model.macros.BlurMacro;
import model.macros.BrightenMacro;
import model.macros.GreenComponentMacro;
import model.macros.GreyscaleMacro;
import model.macros.HorizontalFlipMacro;
import model.macros.ImageProcessingMacro;
import model.macros.IntensityComponentMacro;
import model.macros.LoadMacro;
import model.macros.LumaComponentMacro;
import model.macros.MosaicMacro;
import model.macros.RedComponentMacro;
import model.macros.SaveMacro;
import model.macros.SepiaMacro;
import model.macros.SharpenMacro;
import model.macros.ValueComponentMacro;
import model.macros.VerticalFlipMacro;
import view.ImageProcessingGuiView;

/**
 * This class represents a controller that runs an image processing program with a graphical user
 * interface.
 */
public class ImageProcessingGuiController implements ImageProcessingController, ActionListener {

  private final ImageProcessingModel model;
  private final ImageProcessingGuiView view;
  private final Map<String, ImageProcessingMacro> knownCommands;
  private final String activeImage;

  /**
   * Constructor that initializes the model and view.
   *
   * @param model model of image processing program
   * @param view  GUI view for image processing program
   * @throws IllegalArgumentException if model or view are null
   */
  public ImageProcessingGuiController(ImageProcessingModel model, ImageProcessingGuiView view)
          throws IllegalArgumentException {
    if (model == null || view == null) {
      throw new IllegalArgumentException("Model or view is null");
    }
    this.model = model;
    this.view = view;
    this.knownCommands = new HashMap<>();
    this.activeImage = "image";

    knownCommands.put("horizontal flip", new HorizontalFlipMacro(this.activeImage,
            this.activeImage));
    knownCommands.put("vertical flip", new VerticalFlipMacro(this.activeImage, this.activeImage));

    knownCommands.put("value", new ValueComponentMacro(this.activeImage,
            this.activeImage));
    knownCommands.put("luma", new LumaComponentMacro(this.activeImage, this.activeImage));
    knownCommands.put("intensity", new IntensityComponentMacro(this.activeImage,
            this.activeImage));

    knownCommands.put("red component", new RedComponentMacro(this.activeImage, this.activeImage));
    knownCommands.put("green component", new GreenComponentMacro(this.activeImage,
            this.activeImage));
    knownCommands.put("blue component", new BlueComponentMacro(this.activeImage, this.activeImage));

    knownCommands.put("brighten", new BrightenMacro(20, this.activeImage, this.activeImage));
    knownCommands.put("darken", new BrightenMacro(-20, this.activeImage, this.activeImage));

    knownCommands.put("blur", new BlurMacro(this.activeImage, this.activeImage));
    knownCommands.put("sharpen", new SharpenMacro(this.activeImage, this.activeImage));

    knownCommands.put("greyscale", new GreyscaleMacro(this.activeImage, this.activeImage));
    knownCommands.put("sepia", new SepiaMacro(this.activeImage, this.activeImage));
  }

  /**
   * Runs an image processing program with a GUI.
   *
   * @throws IllegalStateException if there is an error while the controller is running the program
   */
  @Override
  public void runController() throws IllegalStateException {
    this.view.setButtonListeners(this);
    this.view.makeVisible();
  }

  /**
   * Invoked when an action occurs.
   *
   * @param action the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent action) {
    String cmd = action.getActionCommand();
    if (cmd.equals("load")) {
      String srcPath = this.view.showLoadFileChooser();
      if (srcPath.equals("")) {
        return;
      }
      if (new File(srcPath).exists()) {
        ImageProcessingMacro loadMacro = new LoadMacro(srcPath, this.activeImage);
        try {
          loadMacro.execute(this.model);
        } catch (IllegalArgumentException e) {
          this.view.showErrorMessage(e.getMessage());
        }
        Image activeImage = this.model.getImage(this.activeImage);
        ImageHistogramModel histogramModel = new ImageHistogramModel(activeImage);
        this.view.refresh(activeImage, histogramModel);
      } else {
        this.view.showErrorMessage("File does not exist.");
      }
    } else if (cmd.equals("save")) {
      String destPath = this.view.showSaveFileChooser();
      if (destPath.equals("")) {
        return;
      }
      ImageProcessingMacro saveMacro = new SaveMacro(destPath, this.activeImage);
      try {
        saveMacro.execute(this.model);
      } catch (IllegalArgumentException e) {
        this.view.showErrorMessage("Failed to save image.");
      }
    } else if (cmd.equals("mosaic")) {
      ImageProcessingMacro mosaicMacro = new MosaicMacro(
              Integer.parseInt(view.getMosaicSeeds()), this.activeImage, this.activeImage);
      mosaicMacro.execute(this.model);
      Image activeImage = this.model.getImage(this.activeImage);
      ImageHistogramModel histogramModel = new ImageHistogramModel(activeImage);
      this.view.refresh(activeImage, histogramModel);
    } else {
      ImageProcessingMacro macro = knownCommands.getOrDefault(action.getActionCommand(), null);
      if (macro == null) {
        this.view.showErrorMessage("Command not found: " + cmd);
      } else {
        try {
          macro.execute(this.model);
          Image activeImage = this.model.getImage(this.activeImage);
          ImageHistogramModel histogramModel = new ImageHistogramModel(activeImage);
          this.view.refresh(activeImage, histogramModel);
        } catch (IllegalArgumentException e) {
          this.view.showErrorMessage("Failed to perform operation: " + cmd);
        }
      }
    }
  }
}
