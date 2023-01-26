import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageHistogramModel;
import model.ImageImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the ImageHistogramModel test.
 */
public class ImageHistogramModelTest {

  private ImageHistogramModel model;

  @Before
  public void setup() {
    Image image = new ImageImpl(new Pixel[][]{{new Pixel(10, 20, 30),
            new Pixel(10, 20, 30)},
        {new Pixel(30, 20, 10), new Pixel(0, 30, 30)}});
    this.model = new ImageHistogramModel(image);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullImageFails() {
    new ImageHistogramModel(null);
  }

  @Test
  public void testRedHistogram() {
    int[] expectedCounts = new int[256];
    expectedCounts[0] = 1;
    expectedCounts[10] = 2;
    expectedCounts[30] = 1;
    int[] actualCounts = this.model.getRedHistogram();
    for (int i = 0; i < expectedCounts.length; i++) {
      assertEquals(expectedCounts[i], actualCounts[i]);
    }
  }

  @Test
  public void testGreenHistogram() {
    int[] expectedCounts = new int[256];
    expectedCounts[20] = 3;
    expectedCounts[30] = 1;
    int[] actualCounts = this.model.getGreenHistogram();
    for (int i = 0; i < expectedCounts.length; i++) {
      assertEquals(expectedCounts[i], actualCounts[i]);
    }
  }

  @Test
  public void testBlueHistogram() {
    int[] expectedCounts = new int[256];
    expectedCounts[10] = 1;
    expectedCounts[30] = 3;
    int[] actualCounts = this.model.getBlueHistogram();
    for (int i = 0; i < expectedCounts.length; i++) {
      assertEquals(expectedCounts[i], actualCounts[i]);
    }
  }
}
