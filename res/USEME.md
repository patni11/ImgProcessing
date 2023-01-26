# How To Run
* ```java -jar res/assignment6.jar -file path-of-script-file```: the program opens the script
  file and executes it
* ```java -jar res/assignment6.jar -text```: the program opens in an interactive text
  mode, allowing the user to type the script and execute it one line at a time.
* ```java -jar res/assignment6.jar```: the program opens the graphical user interface.
* use res/monkeys.jpg as a sample image to try out the program

# Supported Text Commands

## load
usage: load src_path dest_image_name <br>
example: load res/1.jpg monkeys <br>
condition(s): src_path must be a ppm/png/jpg/bmp image

## red-component
usage: red-component src_image_name dest_image_name <br>
example: red-component monkeys red-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## green-component
usage: green-component src_image_name dest_image_name <br>
example: green-component monkeys green-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## blue-component
usage: blue-component src_image_name dest_image_name <br>
example: blue-component monkeys blue-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## luma-component
usage: luma-component src_image_name dest_image_name <br>
example: luma-component monkeys luma-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## intensity-component
usage: intensity-component src_image_name dest_image_name <br>
example: intensity-component monkeys intensity-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## value-component
usage: value-component src_image_name dest_image_name <br>
example: value-component monkeys value-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## brighten
usage: brighten increment src_image_name dest_image_name <br>
example: brighten 20 monkeys brighten-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## vertical-flip
usage: vertical-flip src_image_name dest_image_name <br>
example: vertical-flip monkeys vertical-flip-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## horizontal-flip
usage: horizontal-flip src_image_name dest_image_name <br>
example: horizontal-flip monkeys horizontal-flip-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## blur
usage: blur src_image_name dest_image_name <br>
example: blur monkeys blur-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## sharpen
usage: sharpen src_image_name dest_image_name <br>
example: sharpen monkeys sharpen-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## greyscale
usage: greyscale src_image_name dest_image_name <br>
example: greyscale monkeys greyscale-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## sepia
usage: sepia src_image_name dest_image_name <br>
example: sepia monkeys sepia-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## mosaic
usage: mosaic seed_count src_image_name dest_image_name <br>
example: mosaic monkeys mosaic-monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command

## save
usage: save dest_path src_image_name <br>
example: save res/monkeys.png monkeys <br>
condition(s): src_image_name must be an image that has been loaded in using the load command and
dest_path must be a ppm/png/jpg/bmp image

# Supported GUI Operations

* load an image into program
  * usage: press the "load" button and choose an image from file chooser to load in
  * condition(s): selected image must be a ppm/png/jpg/bmp image

* visualize red component of an image
  * usage: press the "red component" button
  * condition(s): an image must have been loaded in

* visualize green component of an image
    * usage: press the "green component" button
    * condition(s): an image must have been loaded in

* visualize blue component of an image
    * usage: press the "blue component" button
    * condition(s): an image must have been loaded in

* visualize luma component of an image
    * usage: press the "luma" button
    * condition(s): an image must have been loaded in

* visualize intensity component of an image
    * usage: press the "intensity" button
    * condition(s): an image must have been loaded in

* visualize value component of an image
    * usage: press the "value" button
    * condition(s): an image must have been loaded in

* brighten an image
    * usage: press the "brighten" button
    * condition(s): an image must have been loaded in

* darken an image
    * usage: press the "darken" button
    * condition(s): an image must have been loaded in

* flip an image vertically
    * usage: press the "vertical flip" button
    * condition(s): an image must have been loaded in

* flip an image horizontally
    * usage: press the "horizontal flip" button
    * condition(s): an image must have been loaded in

* blur an image
    * usage: press the "blur" button
    * condition(s): an image must have been loaded in

* sharpen an image
    * usage: press the "sharpen" button
    * condition(s): an image must have been loaded in

* apply greyscale filter to an image
    * usage: press the "greyscale" button
    * condition(s): an image must have been loaded in

* apply mosaic filter to an image
    * usage: press the "mosaic" button after entering a seedcount next to the button in a text field
    * condition(s): an image must have been loaded in

* apply sepia filter to an image
    * usage: press the "sepia" button
    * condition(s): an image must have been loaded in

* save an image
    * usage: press the "save" button, choose a directory, and name the saved image
    * condition(s): an image must have been loaded in and the saved image must be in 
      ppm/png/jpg/bmp format
