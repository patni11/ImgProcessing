package model.macros;

import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Pixel;

/**
 * Mosaic Mock to test the mosaic funciton. This is different as it has a function to retrieve
 * cluster Assignments
 */
public class MosaicMock extends MosaicMacro {

  /**
   * Constructor that takes in the name of the image to operate on and the name of the
   * destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   */

  public MosaicMock(int seedCount, String srcImageName, String destImageName) {
    super(seedCount, srcImageName, destImageName);

  }

  /**
   * Test Constructor that takes in the name of the image to operate on and the name of the
   * destination image.
   *
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @param randomObject  random object to generate random number
   */
  public MosaicMock(int seedCount, String srcImageName, String destImageName, Random randomObject) {
    super(seedCount, srcImageName, destImageName, randomObject);

  }

  /**
   * To get the cluster assignments for testing purposes.
   *
   * @return returns a map containing clusters and their assigned pixels.
   */
  public Map<int[], List<Pixel>> expClusterAssignments() {
    return this.clusterAssignments;
  }
}
