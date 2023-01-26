import org.junit.Before;
import org.junit.Test;

import model.Image;
import model.ImageImpl;
import model.ImageProcessingModel;
import model.ImageProcessingModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the ImageProcessingModelImpl class.
 */
public class ImageProcessingModelImplTest {

  private ImageProcessingModel model;
  private String imageName;
  private Image image;

  @Before
  public void setup() {
    this.model = new ImageProcessingModelImpl();
    this.imageName = "monkeys";
    this.image = new ImageImpl(new Pixel[][]{{new Pixel(10, 20, 30)},
        {new Pixel(40, 50, 60)}});
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetImageNotFoundFails() {
    this.model.getImage("test/images/bad-monkeys");
  }

  @Test
  public void testGetAndAddImageWorks() {
    this.model.addImage(this.imageName, this.image);
    Image addedImg = this.model.getImage(this.imageName);
    assertEquals(this.image.getHeight(), addedImg.getHeight());
    assertEquals(this.image.getWidth(), addedImg.getWidth());
    Pixel expected = this.image.getPixelAt(0, 0);
    Pixel actual = addedImg.getPixelAt(0, 0);
    assertEquals(expected.getRed(), actual.getRed());
    assertEquals(expected.getGreen(), actual.getGreen());
    assertEquals(expected.getBlue(), actual.getBlue());
  }

  @Test
  public void testGetAndAddImageCantBeModifiedOutsideWorks() {
    this.model.addImage(this.imageName, this.image);
    this.image.setPixelAt(0, 0, 0, 0, 0);
    Image addedImage = this.model.getImage(this.imageName);
    Pixel actual = addedImage.getPixelAt(0, 0);
    assertEquals(10, actual.getRed());
    assertEquals(20, actual.getGreen());
    assertEquals(30, actual.getBlue());
    addedImage.setPixelAt(0, 0, 0, 0, 0);
    Image getImage = this.model.getImage(this.imageName);
    actual = getImage.getPixelAt(0, 0);
    assertEquals(10, actual.getRed());
    assertEquals(20, actual.getGreen());
    assertEquals(30, actual.getBlue());
  }
}
