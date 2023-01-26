import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Image;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;
import model.macros.BlueComponentMacro;
import model.macros.BlurMacro;
import model.macros.BrightenMacro;
import model.macros.GreenComponentMacro;
import model.macros.GreyscaleMacro;
import model.macros.HorizontalFlipMacro;
import model.macros.IntensityComponentMacro;
import model.macros.LoadMacro;
import model.macros.LumaComponentMacro;
import model.macros.MosaicMock;
import model.macros.RedComponentMacro;
import model.macros.SaveMacro;
import model.macros.SepiaMacro;
import model.macros.SharpenMacro;
import model.macros.ValueComponentMacro;
import model.macros.VerticalFlipMacro;
import utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains tests for the subclasses of the ImageProcessingMacro interface.
 */
public class ImageProcessingMacroTest {

  private ImageProcessingModel model;
  private ImageProcessingModel expectedModel;
  private String imageName;
  private String badImageName;

  @Before
  public void setup() {
    this.model = new ImageProcessingModelImpl();
    this.imageName = "monkeys";
    this.badImageName = "bad-monkeys";
    this.expectedModel = new ImageProcessingModelImpl();
    new LoadMacro("test/images/monkeys.ppm", this.imageName).execute(this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testRedComponentMacroImageNotFoundFails() {
    new RedComponentMacro(this.badImageName, "monkeys-red").execute(this.model);
  }

  @Test
  public void testRedComponentMacroWorks() {
    new RedComponentMacro(this.imageName, "monkeys-red").execute(this.model);
    Image actual = this.model.getImage("monkeys-red");
    new LoadMacro("test/images/monkeys-red-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreenComponentMacroImageNotFoundFails() {
    new GreenComponentMacro(this.badImageName, "monkeys-green").execute(this.model);
  }

  @Test
  public void testGreenComponentMacroWorks() {
    new GreenComponentMacro(this.imageName, "monkeys-green").execute(this.model);
    Image actual = this.model.getImage("monkeys-green");
    new LoadMacro("test/images/monkeys-green-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlueComponentMacroImageNotFoundFails() {
    new BlueComponentMacro(this.badImageName, "monkeys-blue").execute(this.model);
  }

  @Test
  public void testBlueComponentMacroWorks() {
    new BlueComponentMacro(this.imageName, "monkeys-blue").execute(this.model);
    Image actual = this.model.getImage("monkeys-blue");
    new LoadMacro("test/images/monkeys-blue-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));

  }

  @Test(expected = IllegalArgumentException.class)
  public void testValueComponentMacroImageNotFoundFails() {
    new ValueComponentMacro(this.badImageName, "monkeys-value").execute(this.model);
  }

  @Test
  public void testValueComponentMacroWorks() {
    new ValueComponentMacro(this.imageName, "monkeys-value").execute(this.model);
    Image actual = this.model.getImage("monkeys-value");
    new LoadMacro("test/images/monkeys-value-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIntensityComponentMacroImageNotFoundFails() {
    new IntensityComponentMacro(this.badImageName,
            "monkeys-intensity").execute(this.model);
  }

  @Test
  public void testIntensityComponentMacroWorks() {
    new IntensityComponentMacro(this.imageName,
            "monkeys-intensity").execute(this.model);
    Image actual = this.model.getImage("monkeys-intensity");
    new LoadMacro("test/images/monkeys-intensity-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLumaComponentMacroImageNotFoundFails() {
    new LumaComponentMacro(this.badImageName, "monkeys-luma").execute(this.model);
  }

  @Test
  public void testLumaComponentMacroWorks() {
    new LumaComponentMacro(this.imageName, "monkeys-luma").execute(this.model);
    Image actual = this.model.getImage("monkeys-luma");
    new LoadMacro("test/images/monkeys-luma-component.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testHorizontalFlipMacroImageNotFoundFails() {
    new HorizontalFlipMacro(this.badImageName,
            "monkeys-horizontal-flip").execute(this.model);
  }

  @Test
  public void testHorizontalFlipMacroWorks() {
    new HorizontalFlipMacro(this.imageName,
            "monkeys-horizontal-flip").execute(this.model);
    Image actual = this.model.getImage("monkeys-horizontal-flip");
    new LoadMacro("test/images/monkeys-horizontal-flip.ppm",
            "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testVerticalFlipMacroImageNotFoundFails() {
    new VerticalFlipMacro(this.badImageName, "monkeys-vertical-flip").execute(this.model);
  }

  @Test
  public void testVerticalFlipMacroWorks() {
    new VerticalFlipMacro(this.imageName, "monkeys-vertical-flip").execute(this.model);
    Image actual = this.model.getImage("monkeys-vertical-flip");
    new LoadMacro("test/images/monkeys-vertical-flip.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBrightenMacroImageNotFoundFails() {
    new BrightenMacro(0, this.badImageName, "monkeys-brighten").execute(this.model);
  }

  @Test
  public void testBrightenMacroPosIncrementWorks() {
    new BrightenMacro(20, this.imageName, "monkeys-brighten").execute(this.model);
    Image actual = this.model.getImage("monkeys-brighten");
    new LoadMacro("test/images/monkeys-brighten.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void testBrightenMacroNegIncrementWorks() {
    new BrightenMacro(-20, this.imageName, "monkeys-darken").execute(this.model);
    Image actual = this.model.getImage("monkeys-darken");
    new LoadMacro("test/images/monkeys-darken.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testBlurMacroImageNotFoundFails() {
    new BlurMacro(this.badImageName, "monkeys-blur").execute(this.model);
  }

  @Test
  public void testBlurMacroWorks() {
    new BlurMacro(this.imageName, "monkeys-blur").execute(this.model);
    Image actual = this.model.getImage("monkeys-blur");
    new LoadMacro("test/images/monkeys-blur.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSharpenMacroImageNotFoundFails() {
    new SharpenMacro(this.badImageName, "monkeys-sharpen").execute(this.model);
  }

  @Test
  public void testSharpenMacroWorks() {
    new SharpenMacro(this.imageName, "monkeys-sharpen").execute(this.model);
    Image actual = this.model.getImage("monkeys-sharpen");
    new LoadMacro("test/images/monkeys-sharpen.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGreyscaleMacroImageNotFoundFails() {
    new GreyscaleMacro(this.badImageName, "monkeys-greyscale").execute(this.model);
  }

  @Test
  public void testGreyscaleMacroWorks() {
    new GreyscaleMacro(this.imageName, "monkeys-greyscale").execute(this.model);
    Image actual = this.model.getImage("monkeys-greyscale");
    new LoadMacro("test/images/monkeys-greyscale.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSepiaMacroImageNotFoundFails() {
    new SepiaMacro(this.badImageName, "monkeys-sepia").execute(this.model);
  }

  @Test
  public void testSepiaMacroWorks() {
    new SepiaMacro(this.imageName, "monkeys-sepia").execute(this.model);
    Image actual = this.model.getImage("monkeys-sepia");
    new LoadMacro("test/images/monkeys-sepia.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadMacroFileNotFoundFails() {
    new LoadMacro("test/images/bad-monkeys.ppm", "load-monkeys").execute(this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLoadMacroBadFormatFails() {
    new LoadMacro("test/images/bad-monkeys.bad", "load-monkeys").execute(this.model);
  }

  @Test
  public void testLoadMacroPPMWorks() {
    new LoadMacro("test/images/monkeys.ppm", "load-monkeys").execute(this.model);
    Image actual = this.model.getImage("load-monkeys");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void testLoadMacroJPGWorks() {
    new LoadMacro("test/images/monkeys.jpg", "load-monkeys").execute(this.model);
    Image actual = this.model.getImage("load-monkeys");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void testLoadMacroPNGWorks() {
    new LoadMacro("test/images/monkeys.png", "load-monkeys").execute(this.model);
    Image actual = this.model.getImage("load-monkeys");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void testLoadMacroBMPWorks() {
    new LoadMacro("test/images/monkeys.bmp", "load-monkeys").execute(this.model);
    Image actual = this.model.getImage("load-monkeys");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveMacroImageNotFoundFails() {
    new SaveMacro("test/images/save-monkeys.ppm", "bad-monkeys").execute(this.model);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSaveMacroBadFormatFails() {
    new SaveMacro("test/images/save-monkeys.bad", "bad-monkeys").execute(this.model);
  }

  @Test
  public void testSaveMacroPPMWorks() {
    new SaveMacro("test/images/save-monkeys.ppm", "monkeys").execute(this.model);
    new LoadMacro("test/images/save-monkeys.ppm", "actual").execute(this.expectedModel);
    Image actual = this.expectedModel.getImage("actual");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void testSaveMacroJPGWorks() {
    new SaveMacro("test/images/save-monkeys.jpg", "monkeys").execute(this.model);
    assertTrue(new File("test/images/save-monkeys.jpg").exists());
  }

  @Test
  public void testSaveMacroPNGWorks() {
    new SaveMacro("test/images/save-monkeys.png", "monkeys").execute(this.model);
    new LoadMacro("test/images/save-monkeys.png", "actual").execute(this.expectedModel);
    Image actual = this.expectedModel.getImage("actual");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  @Test
  public void testSaveMacroBMPWorks() {
    new SaveMacro("test/images/save-monkeys.bmp", "monkeys").execute(this.model);
    new LoadMacro("test/images/save-monkeys.bmp", "actual").execute(this.expectedModel);
    Image actual = this.expectedModel.getImage("actual");
    new LoadMacro("test/images/monkeys.ppm", "expected").execute(this.expectedModel);
    Image expected = this.expectedModel.getImage("expected");
    assertTrue(TestUtils.equalsImages(expected, actual));
  }

  /**
   * Test to ensure that cluster assignments are properly assigned with a given
   * random object.
   */
  @Test
  public void testMosaic() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    LoadMacro load = new LoadMacro("res/test.ppm", "test");
    load.execute(model);

    Random randomObject = new Random(4);

    MosaicMock mosaicFuncMock = new MosaicMock(4, "test", "img2", randomObject);
    mosaicFuncMock.execute(model);

    int[] expCluster1 = new int[]{1, 3};
    int[] expCluster2 = new int[]{1, 1};
    int[] expCluster3 = new int[]{3, 3};
    int[] expCluster4 = new int[]{2, 0};

    Map<int[], List<Pixel>> actualClusterAssignments = mosaicFuncMock.expClusterAssignments();

    List<int[]> actualClusterPoints = new ArrayList<>();

    for (int[] actualClusters : actualClusterAssignments.keySet()) {
      System.out.println(actualClusters[0] + "," + actualClusters[1]);
      actualClusterPoints.add(actualClusters);
    }

    assertEquals(expCluster1[0], actualClusterPoints.get(0)[0]);
    assertEquals(expCluster1[1], actualClusterPoints.get(0)[1]);
    assertEquals(expCluster2[0], actualClusterPoints.get(1)[0]);
    assertEquals(expCluster2[1], actualClusterPoints.get(1)[1]);
    assertEquals(expCluster3[0], actualClusterPoints.get(2)[0]);
    assertEquals(expCluster3[1], actualClusterPoints.get(2)[1]);
    assertEquals(expCluster4[0], actualClusterPoints.get(3)[0]);
    assertEquals(expCluster4[1], actualClusterPoints.get(3)[1]);

  }

  /**
   * Test to ensure that seeds are randomly placed, using differently seeded
   * random objects passed into the alternate constructor of MosaicMacro.
   */
  @Test
  public void testRandomnessOfMosaic() {
    ImageProcessingModel model = new ImageProcessingModelImpl();
    LoadMacro load = new LoadMacro("res/test.ppm", "test");
    load.execute(model);

    Random randomObject = new Random(4);
    Random randomObject2 = new Random(6);

    MosaicMock mosaicFuncMock = new MosaicMock(4, "test", "img2", randomObject);
    mosaicFuncMock.execute(model);

    MosaicMock mosaicFuncMock2 = new MosaicMock(4, "test", "img3", randomObject2);
    mosaicFuncMock2.execute(model);

    int[] expCluster1 = new int[]{1, 3};
    int[] expCluster2 = new int[]{1, 1};
    int[] expCluster3 = new int[]{3, 3};
    int[] expCluster4 = new int[]{2, 0};

    Map<int[], List<Pixel>> actualClusterAssignments2 = mosaicFuncMock2.expClusterAssignments();

    List<int[]> actualClusterPoints2 = new ArrayList<>();

    for (int[] actualClusters : actualClusterAssignments2.keySet()) {
      System.out.println(actualClusters[0] + "," + actualClusters[1]);
      actualClusterPoints2.add(actualClusters);
    }

    assertNotEquals(expCluster1[0], actualClusterPoints2.get(0)[0]);
    assertNotEquals(expCluster3[0], actualClusterPoints2.get(2)[0]);
    assertNotEquals(expCluster4[0], actualClusterPoints2.get(3)[0]);


  }
}