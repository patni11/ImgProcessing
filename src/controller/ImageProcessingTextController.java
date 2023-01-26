package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.function.Function;

import model.ImageProcessingModel;
import model.macros.BlueComponentMacro;
import model.macros.BlurMacro;
import model.macros.BrightenMacro;
import model.macros.GreenComponentMacro;
import model.macros.GreyscaleMacro;
import model.macros.HorizontalFlipMacro;
import model.macros.ImageProcessingMacro;
import model.macros.IntensityComponentMacro;
import model.macros.LoadMacro;
import model.macros.LumaComponentMacro;
import model.macros.MosaicMacro;
import model.macros.RedComponentMacro;
import model.macros.SaveMacro;
import model.macros.SepiaMacro;
import model.macros.SharpenMacro;
import model.macros.ValueComponentMacro;
import model.macros.VerticalFlipMacro;
import view.ImageProcessingTextView;

/**
 * This class reads input from a Readable and relays them to an ImageProcessingModel object to
 * perform operations on images.
 */
public class ImageProcessingTextController implements ImageProcessingController {
  private final ImageProcessingModel model;
  private final ImageProcessingTextView view;
  private final Readable readable;
  private final Map<String, Function<Scanner, ImageProcessingMacro>> knownCommands;

  /**
   * Constructor that takes in a ImageProcessingModel and Readable object, and sets available
   * image processing commands.
   *
   * @param readable the Readable object to read input from
   * @param model    the ImageProcessingModel object with images to operate on
   */
  public ImageProcessingTextController(ImageProcessingModel model,
                                       ImageProcessingTextView view, Readable readable)
          throws IllegalArgumentException {
    if (model == null || view == null || readable == null) {
      throw new IllegalArgumentException("Model, view, or readable is null");
    }
    this.model = model;
    this.view = view;
    this.readable = readable;
    this.knownCommands = new HashMap<>();

    knownCommands.put("load", s -> new LoadMacro(s.next(), s.next()));
    knownCommands.put("save", s -> new SaveMacro(s.next(), s.next()));

    knownCommands.put("horizontal-flip", s -> new HorizontalFlipMacro(s.next(), s.next()));
    knownCommands.put("vertical-flip", s -> new VerticalFlipMacro(s.next(), s.next()));

    knownCommands.put("value-component", s -> new ValueComponentMacro(s.next(), s.next()));
    knownCommands.put("luma-component", s -> new LumaComponentMacro(s.next(), s.next()));
    knownCommands.put("intensity-component", s -> new IntensityComponentMacro(s.next(), s.next()));

    knownCommands.put("red-component", s -> new RedComponentMacro(s.next(), s.next()));
    knownCommands.put("green-component", s -> new GreenComponentMacro(s.next(), s.next()));
    knownCommands.put("blue-component", s -> new BlueComponentMacro(s.next(), s.next()));

    knownCommands.put("brighten", s -> new BrightenMacro(s.nextInt(), s.next(), s.next()));

    knownCommands.put("blur", s -> new BlurMacro(s.next(), s.next()));
    knownCommands.put("mosaic", s -> new MosaicMacro(s.nextInt(), s.next(), s.next()));
    knownCommands.put("sharpen", s -> new SharpenMacro(s.next(), s.next()));

    knownCommands.put("greyscale", s -> new GreyscaleMacro(s.next(), s.next()));
    knownCommands.put("sepia", s -> new SepiaMacro(s.next(), s.next()));
  }

  /**
   * Reads input from Readable object and performs corresponding image processing operations
   * on images stored in ImageProcessingModel object.
   *
   * @throws IllegalStateException if the controller is unable to transmit output
   */
  public void runController() throws IllegalStateException {
    Scanner sc = new Scanner(this.readable);

    try {
      this.view.renderMessage("Welcome!\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render message.");
    }

    while (sc.hasNext()) {
      ImageProcessingMacro macro;
      String in = sc.next();
      if (in.equalsIgnoreCase("q") || in.equalsIgnoreCase("quit")) {
        break;
      }
      Function<Scanner, ImageProcessingMacro> cmd = knownCommands.getOrDefault(in, null);
      if (cmd == null) {
        try {
          this.view.renderMessage("Command not found: " + in + "\n");
        } catch (IOException e) {
          throw new IllegalStateException("Failed to render message.");
        }
      } else {
        try {
          macro = cmd.apply(sc);
          try {
            macro.execute(this.model);
            try {
              this.view.renderMessage("Successful " + in + " operation!\n");
            } catch (IOException e) {
              throw new IllegalStateException("Failed to render message.");
            }
          } catch (IllegalArgumentException e1) {
            try {
              this.view.renderMessage(e1.getMessage() + "\n");
            } catch (IOException e2) {
              throw new IllegalStateException("Failed to render message.");
            }
          }
        } catch (NoSuchElementException e1) {
          try {
            this.view.renderMessage("Not enough arguments supplied for command: " + in + "\n");
          } catch (IOException e2) {
            throw new IllegalStateException("Failed to render message.");
          }
        }

      }
    }
    try {
      this.view.renderMessage("Bye!\n");
    } catch (IOException e) {
      throw new IllegalStateException("Failed to render message.");
    }
  }
}
