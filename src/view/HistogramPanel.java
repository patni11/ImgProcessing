package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import static java.lang.Math.max;

/**
 * This class represents a drawn histogram.
 */
public class HistogramPanel extends JPanel {

  private int[] values;
  private final Color color;

  /**
   * Constructor that initializes the data in this histogram.
   *
   * @throws IllegalArgumentException if values are null
   */
  public HistogramPanel(Color color, int width, int height) throws IllegalArgumentException {
    this.values = new int[256];
    this.color = color;
    this.setPreferredSize(new Dimension(width, height));
  }

  /**
   * Updates histogram with given values.
   *
   * @param values new values
   * @throws IllegalArgumentException if values are null
   */
  public void updateHistogram(int[] values) throws IllegalArgumentException {
    if (values == null) {
      throw new IllegalArgumentException("Values are null.");
    }
    this.values = values;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    int maxVal = 1;
    for (int value : values) {
      maxVal = max(maxVal, value);
    }

    int margin = 20;

    g.drawLine(margin, getHeight() - margin, margin, margin);
    g.drawLine(margin, getHeight() - margin, getWidth() - margin, getHeight() - margin);

    double xSpacing = ((double) getWidth() - 2 * margin) / (values.length - 1);
    double ySpacing = ((double) getHeight() - 2 * margin) / (maxVal - 1);

    Point[] points = new Point[256];
    for (int i = 0; i < values.length; i++) {
      points[i] = new Point((int) (i * xSpacing + margin),
              (int) ((maxVal - values[i]) * ySpacing + margin));
    }

    g.setColor(this.color);
    for (int i = 0; i < points.length - 1; i++) {
      g.drawLine(points[i].x, points[i].y, points[i + 1].x, points[i + 1].y);
    }
  }
}
