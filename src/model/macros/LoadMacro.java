package model.macros;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.ImageImpl;
import model.ImageProcessingModel;
import model.Pixel;

/**
 * This class represents a macro that loads an image from the specified path.
 */
public class LoadMacro implements ImageProcessingMacro {

  private final String srcPath;
  private final String destImageName;

  /**
   * Constructor that takes in the path of the image and the name to refer to the image.
   *
   * @param srcPath       path of image to load
   * @param destImageName name to refer to image
   */
  public LoadMacro(String srcPath, String destImageName) {
    this.srcPath = srcPath;
    this.destImageName = destImageName;
  }

  /**
   * Loads an image from the specified path, and adds it to the given model.
   *
   * @param model ImageProcessingModel object that stores all the images in the program
   * @throws IllegalArgumentException if image with the specified path could not be found
   */
  @Override
  public void execute(ImageProcessingModel model) throws IllegalArgumentException {
    Pixel[][] image;
    if (this.srcPath.endsWith(".ppm")) {
      Scanner sc;
      try {
        sc = new Scanner(new FileInputStream(this.srcPath));
      } catch (FileNotFoundException e) {
        throw new IllegalArgumentException("File not found: " + srcPath);
      }
      StringBuilder builder = new StringBuilder();
      while (sc.hasNextLine()) {
        String s = sc.nextLine();
        if (s.charAt(0) != '#') {
          builder.append(s).append(System.lineSeparator());
        }
      }
      sc = new Scanner(builder.toString());
      String token;
      token = sc.next();
      if (!token.equals("P3")) {
        throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
      }
      int width = sc.nextInt();
      int height = sc.nextInt();
      int maxVal = sc.nextInt();
      image = new Pixel[height][width];
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          image[i][j] = new Pixel(r, g, b);
        }
      }
    } else {
      File file = new File(this.srcPath);
      BufferedImage buffImg;
      try {
        buffImg = ImageIO.read(file);
      } catch (IOException e) {
        throw new IllegalArgumentException("Unable to read image: " + this.srcPath);
      }
      image = new Pixel[buffImg.getHeight()][buffImg.getWidth()];
      for (int r = 0; r < buffImg.getHeight(); r++) {
        for (int c = 0; c < buffImg.getWidth(); c++) {
          Color color = new Color(buffImg.getRGB(c, r));
          image[r][c] = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
        }
      }
    }
    model.addImage(this.destImageName, new ImageImpl(image));
  }
}
