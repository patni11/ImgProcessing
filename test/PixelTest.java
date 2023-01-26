import org.junit.Before;
import org.junit.Test;

import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the Pixel class.
 */
public class PixelTest {

  private Pixel p;

  @Before
  public void setup() {
    this.p = new Pixel(50, 100, 150);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRedOutOfBoundsLessZero() {
    new Pixel(-10, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGreenOutOfBoundsLessZero() {
    new Pixel(100, -10, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBlueOutOfBoundsLessZero() {
    new Pixel(100, 100, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorRedOutOfBoundsGreater255() {
    new Pixel(256, 100, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorGreenOutOfBoundsGreater255() {
    new Pixel(100, 256, 100);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructorBlueOutOfBoundsGreater255() {
    new Pixel(100, 100, 256);
  }

  @Test
  public void testGetRedWorks() {
    assertEquals(50, this.p.getRed());
  }

  @Test
  public void testGetGreenWorks() {
    assertEquals(100, this.p.getGreen());
  }

  @Test
  public void testGetBlueWorks() {
    assertEquals(150, this.p.getBlue());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetRedFailLessZero() {
    p.setRed(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetRedFailGreater255() {
    p.setRed(256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGreenLessZero() {
    p.setGreen(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetGreenGreater255() {
    p.setGreen(256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBlueLessZero() {
    p.setBlue(-3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testSetBlueGreater255() {
    p.setBlue(256);
  }

  @Test
  public void testSetRedWorks() {
    this.p.setRed(10);
    assertEquals(10, this.p.getRed());
  }

  @Test
  public void testSetGreenWorks() {
    this.p.setGreen(10);
    assertEquals(10, this.p.getGreen());
  }

  @Test
  public void testSetBlueWorks() {
    this.p.setBlue(10);
    assertEquals(10, this.p.getBlue());
  }
}
