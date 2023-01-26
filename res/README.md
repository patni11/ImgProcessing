# Design Changes
All required features are complete and thoroughly tested. Our design changes are documented below.

## Controller
Since we created a new GUI view (see below), we needed to create a new controller that could 
communicate with the GUI view. Since we now needed a text controller and a GUI controller, we 
decided to make an interface for our controllers that contains a method to run the program. We set 
our GUI controller as the ActionListener for the buttons in our GUI view. Thus, 
we implemented the Command Design Pattern in our GUI controller by mapping button clicks from the 
GUI view to ImageProcessingMacro objects, which would perform operations on the image in the 
model that the user is working on.

## View
Since this assignment required us to create a GUI for our program, we saw it fit to create a new 
view. We realized that our current ImageProcessingView interface was not compatible with the GUI 
we wanted to create, so we created the ImageProcessingGuiView interface which would support GUI 
functions. We were required to use the JSwing library to create the GUI, and we used JPanel 
objects to store the image we are currently manipulating and JButton objects. The JButton 
objects represent buttons that can be pressed by the user to execute a certain manipulation on 
the current image. Each function implemented by the model is represented by its own button. 
When a button is pressed, the controller is alerted and will execute the corresponding 
ImageProcessingMacro on the active image in the model. When an unexpected error occurs, a dialog is 
shown to the user.

## Histogram
To implement histograms in our program, we created an ImageHistogramModel class that contains 
methods to retrieve numeric information about an image that can be visualized in a histogram. 
Since we needed to create four histograms for red, green, blue, and intensity components, we 
created four methods in our ImageHistogramModel that would retrieve this information. For 
example, for red components, we would retrieve a list with the counts of the 256 red component 
values in an image. Our controller would send this information to the view, which would use the 
HistogramPanel class that we created to draw the histogram in the GUI.

# How To Run
* ```java -jar res/assignment6.jar -file path-of-script-file```: the program opens the script 
  file and executes it
* ```java -jar res/assignment6.jar -text```: the program opens in an interactive text 
  mode, allowing the user to type the script and execute it one line at a time. 
* ```java -jar res/assignment6.jar```: the program opens the graphical user interface.
* use res/monkeys.jpg as a sample image to try out the program

# Citation
Sander, Sammy. “Apes Going on Holiday.” Pixabay, 2021, https://pixabay.com/photos/car-monkeys-driving-animals-6359827/. Accessed 3 Nov. 2022.