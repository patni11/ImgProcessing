import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import utils.MockAppendable;
import view.ImageProcessingTextView;
import view.ImageProcessingTextViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains tests for the ImageProcessingViewImpl class.
 */
public class ImageProcessingTextViewImplTest {

  private ImageProcessingTextView view;
  private Appendable ap;
  private ImageProcessingTextView badView;

  @Before
  public void setup() {
    this.ap = new StringBuilder();
    this.view = new ImageProcessingTextViewImpl(this.ap);
    Appendable badAp = new MockAppendable();
    this.badView = new ImageProcessingTextViewImpl(badAp);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorNullAppendableFails() {
    new ImageProcessingTextViewImpl(null);
  }

  @Test
  public void renderMessageWorks() throws IOException {
    this.view.renderMessage("Hello");
    assertEquals("Hello", this.ap.toString());
  }

  @Test(expected = IOException.class)
  public void renderMessageThrowsIOException() throws IOException {
    this.badView.renderMessage("Hello");
  }
}
