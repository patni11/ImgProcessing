import org.junit.Before;
import org.junit.Test;

import javax.swing.JButton;

import controller.ImageProcessingController;
import controller.ImageProcessingGuiController;
import model.Image;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.macros.LoadMacro;
import utils.TestUtils;
import view.ConfirmInputsImageProcessingGuiView;
import view.ImageProcessingGuiView;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains tests for the ImageProcessingGuiController class that also ensures that
 * the ImageProcessingGuiView class is receiving the correct inputs from the controller.
 */
public class ImageProcessingGuiControllerTest {

  private ImageProcessingModel model;
  private ImageProcessingGuiView view;
  private ImageProcessingController controller;
  private StringBuilder log;
  private String activeImage;
  private ImageProcessingModel expectedModel;

  @Before
  public void setup() {
    this.log = new StringBuilder();
    this.model = new ImageProcessingModelImpl();
    this.activeImage = "image";
    this.expectedModel = new ImageProcessingModelImpl();
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullModel() {
    new ImageProcessingGuiController(null, this.view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullView() {
    new ImageProcessingGuiController(this.model, null);
  }

  @Test
  public void loadButtonWorks() {
    JButton loadButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, loadButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    loadButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n",
            this.log.toString());
  }

  @Test
  public void saveButtonWorks() {
    JButton saveButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, saveButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    saveButton.doClick();
    saveButton.setText("save");
    saveButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\ncalled "
                    + "showSaveFileChooser\n",
            this.log.toString());
    new LoadMacro("test/images/monkeys.png",
            "actual").execute(this.expectedModel);
    new LoadMacro("test/images/monkeys.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    Image actual = this.expectedModel.getImage("actual");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void showErrorMessageWorks() {
    JButton saveButton = new JButton("save");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, saveButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    saveButton.doClick();
    assertEquals("called makeVisible\ncalled showSaveFileChooser\ncalled showErrorMessage\n",
            this.log.toString());
  }

  @Test
  public void blueComponentButtonWorks() {
    JButton blueComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, blueComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    blueComponentButton.doClick();
    blueComponentButton.setText("blue component");
    blueComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-blue-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void blurButtonWorks() {
    JButton blurButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, blurButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    blurButton.doClick();
    blurButton.setText("blur");
    blurButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-blur.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void brightenButtonWorks() {
    JButton brightenButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, brightenButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    brightenButton.doClick();
    brightenButton.setText("brighten");
    brightenButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-brighten.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void greenComponentButtonWorks() {
    JButton greenComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, greenComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    greenComponentButton.doClick();
    greenComponentButton.setText("green component");
    greenComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-green-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void greyscaleButtonWorks() {
    JButton greenComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, greenComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    greenComponentButton.doClick();
    greenComponentButton.setText("greyscale");
    greenComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-greyscale.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void horizontalFlipButtonWorks() {
    JButton horizontalFlipButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, horizontalFlipButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    horizontalFlipButton.doClick();
    horizontalFlipButton.setText("horizontal flip");
    horizontalFlipButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-horizontal-flip.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void intensityComponentButtonWorks() {
    JButton intensityComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, intensityComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    intensityComponentButton.doClick();
    intensityComponentButton.setText("intensity");
    intensityComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-intensity-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void lumaComponentButtonWorks() {
    JButton lumaComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, lumaComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    lumaComponentButton.doClick();
    lumaComponentButton.setText("luma");
    lumaComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-luma-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void redComponentButtonWorks() {
    JButton redComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, redComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    redComponentButton.doClick();
    redComponentButton.setText("red component");
    redComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-red-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void sepiaButtonWorks() {
    JButton sepiaButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, sepiaButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    sepiaButton.doClick();
    sepiaButton.setText("sepia");
    sepiaButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-sepia.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void sharpenButtonWorks() {
    JButton sharpenButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, sharpenButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    sharpenButton.doClick();
    sharpenButton.setText("sharpen");
    sharpenButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-sharpen.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void valueComponentButtonWorks() {
    JButton valueComponentButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, valueComponentButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    valueComponentButton.doClick();
    valueComponentButton.setText("value");
    valueComponentButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-value-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void verticalFlipButtonWorks() {
    JButton verticalFlipButton = new JButton("load");
    this.view = new ConfirmInputsImageProcessingGuiView(this.log, verticalFlipButton, "test"
            + "/images/monkeys.png");
    this.controller = new ImageProcessingGuiController(this.model, this.view);
    this.controller.runController();
    verticalFlipButton.doClick();
    verticalFlipButton.setText("vertical flip");
    verticalFlipButton.doClick();
    assertEquals("called makeVisible\ncalled showLoadFileChooser\ncalled refresh\n"
                    + "called refresh\n",
            this.log.toString());

    Image actual = this.model.getImage(this.activeImage);
    new LoadMacro("test/images/monkeys-vertical-flip.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }
}
