# Decorative Displays

A set of applications for displays that can be placed in ones flat.

## Digital Frame

This is a Spring Boot application that implements functions of an extended digital frame. It is designed to run on a Raspberry Pi with an attached display. The backend consists of several Spring-MVC controllers that handle resource loading and an AngularJS frontend to display the content.

The application allows you to display images stored in a directory on the device. Additionally, the application allows to show short messages at the bottom of the screen. 

To build and run the application, first call

```sh
gradle bootRepackage
```
The build creates a runnable jar File that you can start with
```sh
java -jar /path/to/runnable.jar
```
Point your browser to http://localhost:8080 and you will see some sample content. (Shrink your browser window to fit the image to see the current date and time and the notifications on on the bottom of the screen)

If you want to display your own content, create a file called *application.properties* in the same directory as your jar file. The contents need to be teh following:

```dosini
# path to file containing notifications
text.filepath=/path/to/file
# Path to directory containing images
images.directory=/path/to/image/dir
```

### Current limitations
 * Supports only images of type .png
 * Images must fit the resolution of the display