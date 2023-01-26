package model.macros;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.Image;
import model.ImageImpl;
import model.ImageProcessingModel;
import model.Pixel;

/**
 * This class represents a macro that converts an image to a mosaic style image.
 */
public class MosaicMacro implements ImageProcessingMacro {

  private final String srcImageName;
  private final String destImageName;

  private final int seedCount;

  private final Random randomObject;

  Map<int[], List<Pixel>> clusterAssignments;

  Map<Pixel, int[]> pixelPositions;

  /**
   * Constructor that takes in the name of the image to operate on and the name of the
   * destination image.
   *
   * @param seedCount the amount of seeds to randomly place on the image
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   */
  public MosaicMacro(int seedCount, String srcImageName, String destImageName) {

    this.srcImageName = srcImageName;
    this.destImageName = destImageName;
    this.seedCount = seedCount;
    this.randomObject = new Random();
    this.clusterAssignments = new HashMap<>();
    this.pixelPositions = new HashMap<>();

  }

  /**
   * Test Constructor that takes in the name of the image to operate on and the name of the
   * destination image.
   *
   * @param seedCount the amount of seeds to randomly place on the image
   * @param srcImageName  name of image to operate on
   * @param destImageName name of destination image
   * @param randomObject  random object to generate random number
   */
  public MosaicMacro(int seedCount, String srcImageName,
                     String destImageName, Random randomObject) {

    this.srcImageName = srcImageName;
    this.destImageName = destImageName;
    this.seedCount = seedCount;
    this.randomObject = randomObject;
    this.clusterAssignments = new HashMap<>();
    this.pixelPositions = new HashMap<>();

  }

  /**
   * Executes the Mosaic function and saves the mosaic image in storage.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image name could not be found in model or FileWriter
   *                                  failed to save image as PPM file.
   */
  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {

    Image original = model.getImage(srcImageName);
    int height = original.getHeight();
    int width = original.getWidth();


    List<int[]> points = new ArrayList<>();
    getPoints(0, width, 0, height, this.seedCount, points, true);

    assignClustersToPoints(points, original, height, width);

    model.addImage(this.destImageName, newImage(original));

  }

  /**
   * Assigns all pixels in the same cluster to the average RGB value within
   *        that cluster.
   * @param original - Original image
   * @return - A new mosaic image with grouped clusters of pixels
   *        being transformed into the average RGB of all pixels within that cluster.
   */
  private Image newImage(Image original) {
    Pixel[][] pixels = new Pixel[original.getHeight()][original.getWidth()];


    for (int[] cluster : clusterAssignments.keySet()) {
      int[] newPixelValues = averageRGBOfCluster(cluster);
      for (Pixel p : clusterAssignments.get(cluster)) {
        int[] pos = pixelPositions.get(p);
        pixels[pos[0]][pos[1]] = new Pixel(newPixelValues[0], newPixelValues[1], newPixelValues[2]);
      }
    }
    Image newImage = new ImageImpl(pixels);
    return newImage;
  }

  /**
   * Returns the average RGB value of all pixels within a given cluster.
   * @param cluster - The cluster on the image
   * @return - An array of size 3 holding the average R, G, and B value
   *        of all pixels within the provided cluster.
   */
  private int[] averageRGBOfCluster(int[] cluster) {

    int r = 0;
    int g = 0;
    int b = 0;


    for (Pixel p : clusterAssignments.get(cluster)) {
      r += p.getRed();
      g += p.getGreen();
      b += p.getBlue();
    }

    int sum = clusterAssignments.get(cluster).size();
    return new int[]{r / sum, g / sum, b / sum};
  }

  /**
   * Randomly assigns clusters in the image using a merge sort(ish) approach.
   * @param ws - Starting width of the area where this cluster will be placed.
   * @param we - End with of the area where this cluster will be placed.
   * @param hs - Starting height of the area where this cluster will be placed.
   * @param he - Ending height of the area where this cluster will be placed.
   * @param seed - Amount of seeds designated to the current region.
   * @param points - The list of seeds placed on the image.
   * @param divider - divider that indicates when to move horizontally or vertically
   *                when determining where to place the cluster.
   */
  private void getPoints(int ws, int we, int hs, int he, int seed,
                         List<int[]> points, boolean divider) {


    if (seed == 1) {
      int[] temp = new int[2];
      temp[0] = randomObject.nextInt((we - ws)) + ws; // width
      temp[1] = randomObject.nextInt((he - hs)) + hs; // height
      points.add(temp);
      return;
    }

    if (seed < 1) {
      return;
    }

    int s1 = seed / 2;
    int s2 = seed - s1;

    if (divider) {
      getPoints(ws, (we + ws) / 2, hs, he, s1, points, false);
      getPoints((we + ws) / 2, we, hs, he, s2, points, false);
    } else {
      getPoints(ws, we, hs, (he + hs) / 2, s1, points, true);
      getPoints(ws, we, (he + hs) / 2, he, s2, points, true);
    }
  }

  /**
   * Assigns pixels to a cluster depending on their distance apart.
   * @param points - List of clusters on the image
   * @param original - Original image where the clusters are placed
   * @param height - Height of the image
   * @param width - Width of the image
   */
  private void assignClustersToPoints(List<int[]> points, Image original,
                                      int height, int width) {
    clusterAssignments = new HashMap<>();
    for (int row = 0; row < height; row++) {

      for (int col = 0; col < width; col++) {

        Pixel pixel = original.getPixelAt(row, col);

        pixelPositions.put(pixel, new int[]{row, col});

        int[] minCluster = (findClosestClusterForGivenPixel(row, col, points));
        List<Pixel> clusterPixels =
                clusterAssignments.getOrDefault(minCluster, new ArrayList<>());

        clusterPixels.add(pixel);

        clusterAssignments.put(minCluster, clusterPixels);
        //System.out.println("Pixel" + row + "," + col + "->" +
        // "Cluster at" + minCluster[0] + "," + minCluster[1]);
      }
    }

  }


  /**
   * Finds the closest cluster to a col and row position of a pixel.
   * @param col - Col position of the pixel
   * @param row - Row position of the pixel.
   * @param points - List of available clusters currently placed on the image.
   * @return - Returns the cluster closet to the given pixel position.
   */
  private int[] findClosestClusterForGivenPixel(int col, int row, List<int[]> points) {

    double min = calcDistanceFromClusterToPosition(points.get(0), row, col);
    int[] minCluster = points.get(0);

    for (int[] cluster : points) {
      double dist = calcDistanceFromClusterToPosition(cluster, row, col);
      if (dist < min) {
        min = dist;
        minCluster = cluster;
      }
    }

    return minCluster;
  }

  /**
   * Calculates the distance from a cluster to a given pixel position.
   * @param cluster - Provided cluster
   * @param row - Row position of the pixel
   * @param col - Col position of the pixel
   * @return - A double representing the distance between the pixel and cluster.
   */
  private double calcDistanceFromClusterToPosition(int[] cluster, int row, int col) {

    return Math.sqrt(Math.pow(col - cluster[1], 2) +
            Math.pow(row - cluster[0], 2));
  }


}
