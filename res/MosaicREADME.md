# Implement Image Mosaicking Correctly? Y
# Supported a script command for it? Y
# Exposed it through the GUI? Y

# Mosaic Implementation
* To implement image mosaicing, we created a MosaicMacro function object that inherited the existing ImageProcessingMacro interface.
* After making a functional modification object, we modified the GUI view to implement a button and text field that allows a user to mosaic an image with a given amount of seeds.
* Then, to support text input, we modified the ImageController to place a MosaicMacro function object in the knownCommands list.
* After all of these changes, our implementation was harmonious with their program. We relied on these small changes to the controllers and view because of the use of private fields with no accessibility outside.
* For the view, we simply incorporated a new JButton and JTextField into the panel, and in the controller we placed a new function into the knownCommands map.
* In all, all functionality was retained, and the only decisions to edit on the provided code revolved around private fields. 


# Mosaic Functionality
* We used a merge sort like algorithm that evenly distributed the image into regions. Then, we randomly placed a cluster within each of these regions to ensure that a mosaic would roughly have a similar number of pixels in each cluster. We then looped through each cluster and assigned the average RGB of the cluster to each pixel, producing the final mosaic image. 
