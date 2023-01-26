import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.Pixel;
import utils.TestUtils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains tests for the ImageImpl class.
 */
public class ImageImplTest {
  private Image expectedImage;
  private int expectedHeight;
  private int expectedWidth;

  @Before
  public void setup() {
    this.expectedImage = new ImageImpl(new Pixel[][]{{
            new Pixel(10, 20, 30)}, {
            new Pixel(40, 50, 60)}});
    this.expectedHeight = 2;
    this.expectedWidth = 1;
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullFails() {
    Pixel[][] arr = null;
    new ImageImpl(arr);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullRowFails() {
    Pixel[][] arr = new Pixel[][]{null};
    new ImageImpl(arr);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullPixelFails() {
    Pixel[][] arr = new Pixel[][]{{null}};
    new ImageImpl(arr);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorDifferentLenRowsFails() {
    Pixel[][] arr = new Pixel[][]{{new Pixel(0, 0, 0),
            new Pixel(0, 0, 0)},
        {new Pixel(0, 0, 0)}};
    new ImageImpl(arr);
  }

  @Test
  public void testConstructorPixelArrayWorks() {
    Image image = new ImageImpl(new Pixel[][]{{new Pixel(10, 20, 30)},
        {new Pixel(40, 50, 60)}});
    assertEquals(10, image.getPixelAt(0, 0).getRed());
    assertEquals(20, image.getPixelAt(0, 0).getGreen());
    assertEquals(30, image.getPixelAt(0, 0).getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorNullImageObjectFails() {
    Image nullImage = null;
    new ImageImpl(nullImage);
  }

  @Test
  public void testConstructorImageObjectWorks() {
    Image copyImage = new ImageImpl(this.expectedImage);
    assertTrue(TestUtils.equalsImages(this.expectedImage, copyImage));
  }

  @Test
  public void testGetHeightWorks() {
    assertEquals(this.expectedHeight, this.expectedImage.getHeight());
  }

  @Test
  public void testGetWidthWorks() {
    assertEquals(this.expectedWidth, this.expectedImage.getWidth());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtRowOutOfBoundsLessZero() {
    this.expectedImage.getPixelAt(-10, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtRowOutOfBoundsGreaterHeight() {
    this.expectedImage.getPixelAt(800, 5);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtColOutOfBoundsLessZero() {
    this.expectedImage.getPixelAt(5, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtColOutOfBoundsGreaterWidth() {
    this.expectedImage.getPixelAt(5, 1100);
  }

  @Test
  public void testGetPixelAtWorks() {
    Pixel actual = this.expectedImage.getPixelAt(0, 0);
    assertEquals(10, actual.getRed());
    assertEquals(20, actual.getGreen());
    assertEquals(30, actual.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsRowLessThanZero() {
    this.expectedImage.setPixelAt(-10, 5, 100, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsRowGreaterThanHeight() {
    this.expectedImage.setPixelAt(800, 5, 100, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsColLessThanZero() {
    this.expectedImage.setPixelAt(5, -10, 100, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsColGreaterThanWidth() {
    this.expectedImage.setPixelAt(5, 1100, 100, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsRedLessThanZero() {
    this.expectedImage.setPixelAt(5, 5, -100, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsRedGreaterThan255() {
    this.expectedImage.setPixelAt(5, 5, 256, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsGreenLessThanZero() {
    this.expectedImage.setPixelAt(5, 5, 100, -100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsGreenGreaterThan255() {
    this.expectedImage.setPixelAt(5, 5, 100, 256, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsBlueLessThanZero() {
    this.expectedImage.setPixelAt(5, 5, 100, 100, -100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetPixelAtFailsBlueGreaterThan255() {
    this.expectedImage.setPixelAt(5, 5, 100, 100, 256);
  }

  @Test
  public void testSetPixelAtWorks() {
    Pixel actualBefore = this.expectedImage.getPixelAt(0, 0);
    assertEquals(10, actualBefore.getRed());
    assertEquals(20, actualBefore.getGreen());
    assertEquals(30, actualBefore.getBlue());
    this.expectedImage.setPixelAt(0, 0, 90, 100, 110);
    Pixel actualAfter = this.expectedImage.getPixelAt(0, 0);
    assertEquals(90, actualAfter.getRed());
    assertEquals(100, actualAfter.getGreen());
    assertEquals(110, actualAfter.getBlue());
  }
}
