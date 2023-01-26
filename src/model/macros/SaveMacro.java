package model.macros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;

import model.Image;
import model.ImageProcessingModel;
import model.Pixel;

/**
 * This class represents a macro that saves an Image object to the specified path as a PPM file.
 */
public class SaveMacro implements ImageProcessingMacro {

  private final String destPath;
  private final String srcImageName;

  /**
   * Constructor that takes in the path to save the image to and the name of the image to save.
   *
   * @param destPath     path to save image to
   * @param srcImageName name of image to save
   */
  public SaveMacro(String destPath, String srcImageName) {
    this.destPath = destPath;
    this.srcImageName = srcImageName;
  }

  /**
   * Saves an image to the specified path.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image name could not be found in model or FileWriter
   *                                  failed to save image as PPM file.
   */
  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    Image image = model.getImage(this.srcImageName);
    if (this.destPath.endsWith(".ppm")) {
      try {
        FileWriter myWriter = new FileWriter(this.destPath);
        myWriter.write("P3\n");
        myWriter.write(image.getWidth() + " ");
        myWriter.write(image.getHeight() + "\n");
        myWriter.write("255\n");
        for (int i = 0; i < image.getHeight(); i++) {
          for (int j = 0; j < image.getWidth(); j++) {
            Pixel p = image.getPixelAt(i, j);
            myWriter.write(p.getRed() + "\n");
            myWriter.write(p.getGreen() + "\n");
            myWriter.write(p.getBlue() + "\n");
          }
        }
        myWriter.close();
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to save file: " + this.destPath);
      }
    } else {
      ArrayList<String> formats = new ArrayList<>(Arrays.asList(ImageIO.getReaderFormatNames()));
      String extension = "";
      int index = this.destPath.lastIndexOf('.');
      if (index > 0) {
        extension = this.destPath.substring(index + 1);
      }
      if (!formats.contains(extension)) {
        throw new IllegalArgumentException("File format of file is not supported: "
                + this.destPath);
      }
      BufferedImage buffImg = new BufferedImage(image.getWidth(), image.getHeight(),
              BufferedImage.TYPE_INT_RGB);
      for (int r = 0; r < image.getHeight(); r++) {
        for (int c = 0; c < image.getWidth(); c++) {
          Pixel p = image.getPixelAt(r, c);
          Color color = new Color(p.getRed(), p.getGreen(), p.getBlue());
          int rgb = color.getRGB();
          buffImg.setRGB(c, r, rgb);
        }
      }
      try {
        ImageIO.write(buffImg, extension, new File(this.destPath));
      } catch (IOException e) {
        throw new IllegalArgumentException("Failed to save file: " + this.destPath);
      }
    }

  }
}
