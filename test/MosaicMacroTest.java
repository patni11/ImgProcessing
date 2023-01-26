import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;
import model.macros.LoadMacro;
import model.macros.MosaicMock;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


/**
 * Test class for mosaicing an image with the MosaicMacro.
 */
public class MosaicMacroTest {

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
