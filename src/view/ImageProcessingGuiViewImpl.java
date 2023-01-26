package view;

import java.awt.GridLayout;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Image;
import model.ImageHistogramModel;
import model.Pixel;

/**
 * This class represents a view for the image processing program that generates a graphical user
 * interface that the user can interact with to perform operations.
 */
public class ImageProcessingGuiViewImpl extends JFrame implements ImageProcessingGuiView {

  private final JScrollPane imagePanel;
  private final JPanel histogramPanel;
  private final JTextField mosaicValue;
  private final HistogramPanel redHistogram;
  private final HistogramPanel greenHistogram;
  private final HistogramPanel blueHistogram;
  private final HistogramPanel intensityHistogram;
  private final JButton loadButton;
  private final JButton saveButton;
  private final JButton blueComponentButton;
  private final JButton blurButton;
  private final JButton brightenButton;
  private final JButton darkenButton;
  private final JButton greenComponentButton;
  private final JButton greyscaleButton;
  private final JButton horizontalFlipButton;
  private final JButton intensityButton;
  private final JButton lumaButton;
  private final JButton redComponentButton;
  private final JButton sepiaButton;
  private final JButton sharpenButton;
  private final JButton valueButton;
  private final JButton verticalFlipButton;

  private final JButton mosaicButton;

  /**
   * Constructor that initializes the components in this GUI view.
   */
  public ImageProcessingGuiViewImpl() {
    super();
    this.setTitle("Image Processing Program");
    this.setSize(1200, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    JPanel titlePanel = new JPanel();
    GridLayout titlePanelLayout = new GridLayout(2, 1);
    titlePanel.setLayout(titlePanelLayout);
    titlePanel.setPreferredSize(new Dimension(1200, 200));

    JLabel title = new JLabel("Image Processing Program", SwingConstants.CENTER);
    title.setFont(new Font("Arial", Font.PLAIN, 50));
    titlePanel.add(title);

    JPanel loadSavePanel = new JPanel();
    GridLayout loadSavePanelLayout = new GridLayout(1, 2);
    loadSavePanelLayout.setHgap(150);
    loadSavePanel.setLayout(loadSavePanelLayout);
    loadSavePanel.setBorder(BorderFactory.createEmptyBorder(25, 350, 20, 350));
    loadSavePanel.setPreferredSize(new Dimension(1200, 100));

    this.loadButton = new JButton("load");
    this.loadButton.setFont(new Font("Arial", Font.PLAIN, 30));
    loadSavePanel.add(this.loadButton);

    this.saveButton = new JButton("save");
    this.saveButton.setFont(new Font("Arial", Font.PLAIN, 30));
    loadSavePanel.add(this.saveButton);

    titlePanel.add(loadSavePanel);

    this.add(titlePanel, BorderLayout.PAGE_START);

    this.imagePanel = new JScrollPane();
    this.imagePanel.setPreferredSize(new Dimension(600, 600));

    this.add(imagePanel, BorderLayout.CENTER);

    this.histogramPanel = new JPanel();
    GridLayout histogramLayout = new GridLayout(4, 1);
    this.histogramPanel.setLayout(histogramLayout);
    this.histogramPanel.setPreferredSize(new Dimension(300, 600));

    this.redHistogram = new HistogramPanel(Color.RED, 300, 150);
    this.greenHistogram = new HistogramPanel(Color.GREEN, 300, 150);
    this.blueHistogram = new HistogramPanel(Color.BLUE, 300, 150);
    this.intensityHistogram = new HistogramPanel(Color.GRAY, 300, 150);

    this.histogramPanel.add(this.redHistogram);
    this.histogramPanel.add(this.greenHistogram);
    this.histogramPanel.add(this.blueHistogram);
    this.histogramPanel.add(this.intensityHistogram);

    this.add(histogramPanel, BorderLayout.LINE_END);

    JPanel buttonsPanel = new JPanel();
    GridLayout buttonsLayout = new GridLayout(8, 2);
    buttonsLayout.setHgap(10);
    buttonsLayout.setVgap(20);
    buttonsPanel.setLayout(buttonsLayout);
    buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));
    buttonsPanel.setPreferredSize(new Dimension(300, 600));

    this.redComponentButton = new JButton("red component");
    buttonsPanel.add(this.redComponentButton);

    this.greenComponentButton = new JButton("green component");
    buttonsPanel.add(this.greenComponentButton);

    this.blueComponentButton = new JButton("blue component");
    buttonsPanel.add(this.blueComponentButton);

    this.valueButton = new JButton("value");
    buttonsPanel.add(this.valueButton);

    this.lumaButton = new JButton("luma");
    buttonsPanel.add(this.lumaButton);

    this.intensityButton = new JButton("intensity");
    buttonsPanel.add(this.intensityButton);

    this.brightenButton = new JButton("brighten");
    buttonsPanel.add(this.brightenButton);

    this.darkenButton = new JButton("darken");
    buttonsPanel.add(this.darkenButton);

    this.horizontalFlipButton = new JButton("horizontal flip");
    buttonsPanel.add(this.horizontalFlipButton);

    this.verticalFlipButton = new JButton("vertical flip");
    buttonsPanel.add(this.verticalFlipButton);

    this.blurButton = new JButton("blur");
    buttonsPanel.add(this.blurButton);

    this.sharpenButton = new JButton("sharpen");
    buttonsPanel.add(this.sharpenButton);

    this.greyscaleButton = new JButton("greyscale");
    buttonsPanel.add(this.greyscaleButton);

    this.sepiaButton = new JButton("sepia");
    buttonsPanel.add(this.sepiaButton);

    this.mosaicButton = new JButton("mosaic");
    buttonsPanel.add(this.mosaicButton);
    this.mosaicValue = new JTextField(5);
    buttonsPanel.add(mosaicValue);

    this.add(buttonsPanel, BorderLayout.LINE_START);

    this.pack();

  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setButtonListeners(ActionListener listener) {
    this.redComponentButton.addActionListener(listener);
    this.greenComponentButton.addActionListener(listener);
    this.blueComponentButton.addActionListener(listener);
    this.valueButton.addActionListener(listener);
    this.lumaButton.addActionListener(listener);
    this.intensityButton.addActionListener(listener);
    this.brightenButton.addActionListener(listener);
    this.darkenButton.addActionListener(listener);
    this.horizontalFlipButton.addActionListener(listener);
    this.verticalFlipButton.addActionListener(listener);
    this.blurButton.addActionListener(listener);
    this.sharpenButton.addActionListener(listener);
    this.greyscaleButton.addActionListener(listener);
    this.sepiaButton.addActionListener(listener);
    this.loadButton.addActionListener(listener);
    this.saveButton.addActionListener(listener);
    this.mosaicButton.addActionListener(listener);
  }

  /**
   * Shows a file chooser window for loading a file and returns the path to the file that is
   * selected by the user.
   *
   * @return path of selected file
   */
  @Override
  public String showLoadFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setDialogTitle("Load image");
    ArrayList<String> acceptedFormats =
            new ArrayList<>(Arrays.asList(ImageIO.getReaderFormatNames()));
    acceptedFormats.add("ppm");
    FileNameExtensionFilter restrict = new FileNameExtensionFilter("images",
            acceptedFormats.toArray(new String[0]));
    fileChooser.addChoosableFileFilter(restrict);

    int status = fileChooser.showOpenDialog(this);
    if (status == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    } else {
      return "";
    }
  }

  public String getMosaicSeeds() {

    return this.mosaicValue.getText();
  }

  /**
   * Shows a file chooser window for saving a file and returns the path to the file that is
   * selected by the user.
   *
   * @return path of selected file
   */
  @Override
  public String showSaveFileChooser() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setDialogTitle("Save image");
    ArrayList<String> acceptedFormats =
            new ArrayList<>(Arrays.asList(ImageIO.getReaderFormatNames()));
    acceptedFormats.add("ppm");
    FileNameExtensionFilter restrict = new FileNameExtensionFilter("images",
            acceptedFormats.toArray(new String[0]));
    fileChooser.addChoosableFileFilter(restrict);

    int status = fileChooser.showSaveDialog(this);
    if (status == JFileChooser.APPROVE_OPTION) {
      return fileChooser.getSelectedFile().getAbsolutePath();
    } else {
      return "";
    }
  }


  /**
   * Refreshes image and histograms in view.
   */
  public void refresh(Image image, ImageHistogramModel model) {
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
    JLabel imageLabel = new JLabel(new ImageIcon(buffImg));
    this.imagePanel.setViewportView(imageLabel);

    this.redHistogram.updateHistogram(model.getRedHistogram());
    this.greenHistogram.updateHistogram(model.getGreenHistogram());
    this.blueHistogram.updateHistogram(model.getBlueHistogram());
    this.intensityHistogram.updateHistogram(model.getIntensityHistogram());
    this.histogramPanel.repaint();
  }

  /**
   * Displays an error message in view.
   *
   * @param message error message to display
   */
  @Override
  public void showErrorMessage(String message) {
    JOptionPane.showMessageDialog(this, message);
  }
}
