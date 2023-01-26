import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.StringReader;

import controller.ImageProcessingTextController;
import model.Image;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.macros.LoadMacro;
import utils.Interaction;
import utils.MockAppendable;
import utils.TestUtils;
import view.ImageProcessingTextView;
import view.ImageProcessingTextViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static utils.TestUtils.inputs;
import static utils.TestUtils.prints;

/**
 * This class contains tests for the ImageProcessingController class.
 */
public class ImageProcessingTextControllerTest {
  private Readable rd;
  private ImageProcessingModel model;
  private ImageProcessingModel expectedModel;
  private ImageProcessingTextView view;
  private String setupString;
  private String imageName;
  private String testFilePath;
  private String imageNameBrighten;
  private String imageNameDarken;
  private String imageNameRed;
  private String imageNameGreen;
  private String imageNameBlue;
  private String imageNameLuma;
  private String imageNameIntensity;
  private String imageNameValue;
  private String imageNameHorizontal;
  private String imageNameVertical;
  private String saveFilePath;
  private String badImageName;
  private String invalidPPM;
  private String invalidFormat;
  private String imageNameBlur;
  private String imageNameSharpen;
  private String imageNameGreyscale;
  private String imageNameSepia;

  @Before
  public void setup() {
    this.imageName = "monkeys";
    this.imageNameBrighten = "monkeys-brighten";
    this.imageNameDarken = "monkeys-darken";
    this.imageNameRed = "monkeys-red";
    this.imageNameGreen = "monkeys-green";
    this.imageNameBlue = "monkeys-blue";
    this.imageNameLuma = "monkeys-luma";
    this.imageNameIntensity = "monkeys-intensity";
    this.imageNameValue = "monkeys-value";
    this.imageNameHorizontal = "monkeys-horizontal";
    this.imageNameVertical = "monkeys-vertical";
    this.imageNameBlur = "monkeys-blur";
    this.imageNameSharpen = "monkeys-sharpen";
    this.imageNameGreyscale = "monkeys-greyscale";
    this.imageNameSepia = "monkeys-sepia";
    this.testFilePath = "test/images/monkeys.ppm";
    this.saveFilePath = "test/images/save-monkeys-controller.ppm";
    this.badImageName = "bad-monkeys";
    this.invalidPPM = "test/images/monkeys-invalid.ppm";
    this.invalidFormat = "test/images/monkeys-invalid.bad";
    this.setupString = "load " + this.testFilePath + " " + this.imageName + " ";
    this.model = new ImageProcessingModelImpl();
    this.expectedModel = new ImageProcessingModelImpl();
    this.view = new ImageProcessingTextViewImpl(new StringBuilder());
  }

  /**
   * Runs image processing program with the given interactions.
   *
   * @param model        an object of ImageProcessingModel
   * @param interactions list of interactions between user and program
   * @return string array containing expected and actual output
   */
  protected String[] testRun(ImageProcessingModel model, Interaction... interactions) {
    StringBuilder fakeUserInput = new StringBuilder();
    StringBuilder expectedOutput = new StringBuilder();

    for (Interaction interaction : interactions) {
      interaction.apply(fakeUserInput, expectedOutput);
    }

    StringReader input = new StringReader(fakeUserInput.toString());
    StringBuilder actualOutput = new StringBuilder();
    ImageProcessingTextView view = new ImageProcessingTextViewImpl(actualOutput);

    ImageProcessingTextController controller = new ImageProcessingTextController(model, view,
            input);
    controller.runController();

    return new String[]{expectedOutput.toString(), actualOutput.toString()};
  }

  @Test
  public void loadAndSaveCmdPPMWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("save " + this.saveFilePath + " " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro(this.saveFilePath, "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdJPGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.jpg monkeys"),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.jpg monkeys"),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(new File("test/images/save-monkeys-controller.jpg").exists());
  }

  @Test
  public void loadAndSaveCmdPNGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.png monkeys"),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.png monkeys"),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.png",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdBMPWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.bmp monkeys"),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.bmp monkeys"),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.bmp",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdPPMToJPGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.jpg " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(new File("test/images/save-monkeys-controller.jpg").exists());
  }

  @Test
  public void loadAndSaveCmdPPMToPNGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.png " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.png",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdPPMToBMPWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.bmp " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.bmp",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdJPGToPPMWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.jpg " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.ppm " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.ppm",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdJPGToPNGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.jpg " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.png " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.png",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdJPGToBMPWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.jpg " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.bmp " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.bmp",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdPNGToPPMWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.png " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.ppm " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.ppm",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdPNGToJPGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.png " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.jpg " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(new File("test/images/save-monkeys-controller.jpg").exists());
  }

  @Test
  public void loadAndSaveCmdPNGToBMPWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.png " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.bmp " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.bmp",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdBMPToPPMWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.bmp " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.ppm " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.ppm",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadAndSaveCmdBMPToJPGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.bmp " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.jpg " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(new File("test/images/save-monkeys-controller.jpg").exists());
  }

  @Test
  public void loadAndSaveCmdBMPToPNGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load test/images/monkeys.bmp " + this.imageName),
            prints("Successful load operation!"),
            inputs("save test/images/save-monkeys-controller.png " + this.imageName + " "),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actualLoad = this.model.getImage(this.imageName);
    new LoadMacro("test/images/save-monkeys-controller.png",
            "actualSave").execute(this.expectedModel);
    Image actualSave = this.expectedModel.getImage("actualSave");
    new LoadMacro(this.testFilePath, "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actualLoad));
    assertTrue(TestUtils.equalsImages(expected, actualSave));
  }

  @Test
  public void loadImageNotFound() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load " + this.badImageName + ".ppm " + this.badImageName),
            prints("File not found: " + this.badImageName + ".ppm"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void loadInvalidPPM() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load " + this.invalidPPM + " " + this.badImageName),
            prints("Invalid PPM file: plain RAW file should begin with P3"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void loadInvalidFormat() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("load " + this.invalidFormat + " " + this.badImageName),
            prints("Unable to read image: " + this.invalidFormat),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void saveImageNotFound() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("save " + this.testFilePath + " " + this.badImageName),
            prints("Image not found: bad-monkeys"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void saveImageInvalidFormat() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("save " + this.invalidFormat + " " + this.imageName),
            prints("File format of file is not supported: test/images/monkeys-invalid.bad"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void brightenWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("brighten 20 " + this.imageName + " " + this.imageNameBrighten),
            prints("Successful brighten operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameBrighten);
    new LoadMacro("test/images/monkeys-brighten.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void brightenImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("brighten 20 " + this.badImageName + " " + this.imageNameBrighten),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void darkenWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("brighten -20 " + this.imageName + " " + this.imageNameDarken),
            prints("Successful brighten operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameDarken);
    new LoadMacro("test/images/monkeys-darken.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void lumaComponentWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("luma-component " + this.imageName + " " + this.imageNameLuma),
            prints("Successful luma-component operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameLuma);
    new LoadMacro("test/images/monkeys-luma-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void lumaComponentImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("luma-component " + this.badImageName + " " + this.imageNameLuma),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void intensityComponentWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("intensity-component " + this.imageName + " " + this.imageNameIntensity),
            prints("Successful intensity-component operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameIntensity);
    new LoadMacro("test/images/monkeys-intensity-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void intensityComponentImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("intensity-component " + this.badImageName + " " + this.imageNameIntensity),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void valueComponentWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("value-component " + this.imageName + " " + this.imageNameValue),
            prints("Successful value-component operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameValue);
    new LoadMacro("test/images/monkeys-value-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void valueComponentImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("value-component " + this.badImageName + " " + this.imageNameValue),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void redComponentWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("red-component " + this.imageName + " " + this.imageNameRed),
            prints("Successful red-component operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameRed);
    new LoadMacro("test/images/monkeys-red-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void redComponentImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("red-component " + this.badImageName + " " + this.imageNameRed),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void greenComponentWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("green-component " + this.imageName + " " + this.imageNameGreen),
            prints("Successful green-component operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameGreen);
    new LoadMacro("test/images/monkeys-green-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void greenComponentImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("green-component " + this.badImageName + " " + this.imageNameGreen),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void blueComponentWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("blue-component " + this.imageName + " " + this.imageNameBlue),
            prints("Successful blue-component operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameBlue);
    new LoadMacro("test/images/monkeys-blue-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void blueComponentImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("blue-component " + this.badImageName + " " + this.imageNameBlue),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void horizontalFlipWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("horizontal-flip " + this.imageName + " " + this.imageNameHorizontal),
            prints("Successful horizontal-flip operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameHorizontal);
    new LoadMacro("test/images/monkeys-horizontal-flip.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void horizontalFlipImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("horizontal-flip " + this.badImageName + " " + this.imageNameHorizontal),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void verticalFlipWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("vertical-flip " + this.imageName + " " + this.imageNameVertical),
            prints("Successful vertical-flip operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameVertical);
    new LoadMacro("test/images/monkeys-vertical-flip.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void verticalFlipImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("vertical-flip " + this.badImageName + " " + this.imageNameVertical),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void blurWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("blur " + this.imageName + " " + this.imageNameBlur),
            prints("Successful blur operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameBlur);
    new LoadMacro("test/images/monkeys-blur.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void blurImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("blur " + this.badImageName + " " + this.imageNameBlur),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void sharpenWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("sharpen " + this.imageName + " " + this.imageNameSharpen),
            prints("Successful sharpen operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameSharpen);
    new LoadMacro("test/images/monkeys-sharpen.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void sharpenImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("sharpen " + this.badImageName + " " + this.imageNameSharpen),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void greyscaleWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("greyscale " + this.imageName + " " + this.imageNameGreyscale),
            prints("Successful greyscale operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameGreyscale);
    new LoadMacro("test/images/monkeys-greyscale.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void greyscaleImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("greyscale " + this.badImageName + " " + this.imageNameGreyscale),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void sepiaWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("sepia " + this.imageName + " " + this.imageNameSepia),
            prints("Successful sepia operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    Image actual = this.model.getImage(this.imageNameSepia);
    new LoadMacro("test/images/monkeys-sepia.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");

    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void sepiaImageNotFoundFails() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("sepia " + this.badImageName + " " + this.imageNameSepia),
            prints("Image not found: " + this.badImageName),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test
  public void loadPerformOpAndSaveCmdPPMToPNGWorks() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs(setupString),
            prints("Successful load operation!"),
            inputs("sepia " + this.imageName + " " + this.imageNameSepia),
            prints("Successful sepia operation!"),
            inputs("save test/images/save-monkeys-sepia-controller.png " + this.imageNameSepia),
            prints("Successful save operation!"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);

    new LoadMacro("test/images/save-monkeys-sepia-controller.png",
            "actual").execute(this.expectedModel);
    Image actual = this.expectedModel.getImage("actual");
    new LoadMacro("test/images/monkeys-sepia.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");


    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void modelNull() {
    this.model = null;
    new ImageProcessingTextController(this.model, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void readableNull() {
    this.rd = null;
    new ImageProcessingTextController(this.model, view, rd);
  }

  @Test(expected = IllegalArgumentException.class)
  public void viewNull() {
    this.view = null;
    new ImageProcessingTextController(this.model, view, rd);
  }

  @Test
  public void commandNotFound() {
    String[] output = this.testRun(this.model,
            prints("Welcome!"),
            inputs("bad"),
            prints("Command not found: bad"),
            prints("Bye!"));
    assertEquals(output[0], output[1]);
  }

  @Test(expected = IllegalStateException.class)
  public void throwsIOException() {
    ImageProcessingModel badModel = new ImageProcessingModelImpl();
    ImageProcessingTextView badView = new ImageProcessingTextViewImpl(new MockAppendable());
    rd = new StringReader("load test/images/monkeys.ppm monkeys");
    ImageProcessingTextController badController = new ImageProcessingTextController(badModel,
            badView, rd);
    badController.runController();
  }
}
