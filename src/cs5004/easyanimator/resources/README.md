# Java Easy Animator
**Contributors**: Clara Soares Bezerra and Alessia Pizzoccheri.
## Introduction
The purpose of this project is to build an application that helps to create simple but effective 2D animations from shapes.
- [The Model](##the-model)
    - [Shapes and Animations](###shapes-and-animations)
    - [Animation Model](###animation-model)
- [The View](##the-view)
- [The Controller](##the-controllerl)

### Overview 
Inside our `cs5004.cs5004.EasyAnimator.model` directory, we first have our over-arching `AnimationModel` Interface,
which represents an animation model with a series of methods implemented by the `AnimationModelImpl` Class. 
Such methods include: adding shapes and animations to the model, check whether there are shapes or
animations in the model, removing shapes from the model, and get a summary of each item in the
model.

Our application includes a `Utils` Class, which features a collection of static methods we use 
throughout the project and are useful for testing (i.e., checking if a number is
negative so we can throw exceptions in several constructors when a certain parameter cannot be negative).

Finally, we have two separate packages inside our model package — _shapes_ and _animations_. 
These packages follow the same pattern of the objects listed above — they contain an Interface, 
which is implemented by an Abstract Class, and extended by Classes, each representing an enum type.

## The Model
### Shapes
In our shapes package, we reasoned that there are two types of shapes to define in `ShapeType` — `RECTANGLE` and `OVAL`.
Since a square and a circle can be represented respectively as a rectangle and a circle whose height and width are equal, they are both encompassed by the aforementioned types.
In our `AbstractShape` class, we defined methods that are common to both ovals and rectangles; these include: getting and setting a shape’s fields, a boolean expressing whether or not a shape is visible (aka, appear/disappear), width,
height, color and position, as well as a method to get a description of the shape. We also define a super-constructor, which is called inside the `Oval` and `Rectangle` classes. 

In addition to these classes, we also have a `Coordinates` Class inside the shapes package, which represents the position
of a shape. It was easier to put the x and y coordinates together in a class, so to be able to pass in the coordinates of the shape as one object in the `AbstractShape` constructor.

### Animations
In our animations folder, we reasoned that there are three types of animations to define in
`AnimationType` — changing the color of a shape, changing the coordinates of a shape, and changing
the size of a shape. In `AbstractAnimation`, we defined methods that are common to all three of
these animations, which are mainly getter methods but also a method to produce a description of the
animation. Furthermore, we define a super-constructor that we call inside `ChangeColor`, `ChangeCoordinates`,
and `ChangeSize` (much like we did in our shapes class). Each of the three classes representing a
type of animation contains a method to implement the animation at a certain time, as well as to
update the field of a shape once it has been implemented. 

## The View
### What Changed 
To implement this portion of the project, we made some modifications to our existing packages.
Initially, we had implemented a `ModelItemImpl` class to combine a shapes and its animations under
one object. However, as the project evolved, we removed this class and modified the our `AnimationModelImpl`
to allow for 3 arrays:

- `ArrayList<Shapes>` which contains a list of shapes to be animated;
- `ArrayList<Animations>` which contains all the animations that will be on the shapes;
- `ArrayList<Integer>` which contains a list of integer parameters used by the SVG and Visual View to
set the boundaries of the animation container. 

Additionally, we added a static class to the `AnimationBuilder` class called `TweenModelBuilder`. 
We implement the interface inside a static class we declare in our AnimationModelImpl class, which 
adds shapes and animations to our model, and contains a method that returns a new 
AnimationModelImpl object with the updated changes. 

We added methods in our Shapes interface that return the representation of the shape in SVG tags, 
and defined their specific implementations in our Oval and Rectangle classes.

### The View Package
Finally, in our view package, we have a `View` interface, which contains methods that are common to 
all views, and is implemented by our `SVGView`, `TextualView` and `VisualAnimationView` classes. In
our `VisualAnimationView` class, we instantiate the `AnimateJPanel` class which extends the JPanel
built-in class and creates the animation "window" that will display our model.
The `TextualView` concrete class creates a `TextualView` object with a given speed, list of shapes
and list of animations. It outputs the description of the model to a file whose is specified by the 
user. Our `SVGView` class extends `TextualView` and calls its super-constructor to
produce an SVGView object with the before-mentioned fields, plus an additional field containing a list
of integer parameters. It also outputs to a file. In addition to implementing the View interface, 
our `VisualAnimationView` class extends `JFrame` in order to produce the visual representation 
of our model. It creates a VisualAnimationView object with a model's list of shapes and the speed 
at which the animation happens, it then generates an animation panel by calling the `AnimateJPanel` 
object.

## The Controller

### Cleaning up the view design
We created a super interface `View` that offers all functions, and then our individual 
views suppress some of them, by throwing UnsupportedOperationException. 

We created an `InteractiveView` class that contains the existing visual view as a component
within it, and adds the following abilities: Start, pause, resume, and restart the animation with 
explicit user input, enable/disable looping, export the view, and increase or decrease the speed of
the animation, as it is being played.

In our `View` interface, we added a few methods to implement in our `InteractiveView` class. All
other view sub-classes throw an UnsupportedOperationException in the body of this method. Our new
methods include: setIsLoop, which takes in a boolean loop to set the isLoop to. We also added a 
setButtonListener method, which takes in an action event of type ActionListener and gives 
the view an actionListener for the buttons in the view. We also have getFilename() method, which
gets the name of the file command from the user input. Finally, to include a checkbox
functionality, we added a getCheckBoxList() method, which returns a list of JCheckBox objects
from the view. A check box is an item that can be selected or deselected, and which displays its
state to the user. 

In our `Interactive View`, we create buttons for Start, Pause, Resume, Restart, and Export. There
is a checkbox for 'Loop,' which can be enabled or disabled, and a text box for Filename, so the
user can choose the name of the file to export to.

### EasyAnimator
We added an extra option to the -view command-line option: "playback". Specifying this option
opens the InteractiveView. We also created a static method createView that creates the view
according to the string that is passed in. At the end of the file, we added a try catch where the
controller starts the animation.

### AnimationController
We created an `AnimationController` interface, whose primary purpose is sto start the animation. It
also contains methods to get the log from the controller and the timer from the controller. It
is implemented by the following sub-classes: `InteractiveViewController`, `SVGViewController`, 
`TextualViewController`, and `VisualAnimationViewController` (so there is one controller sub-class for
each type of view). The controller takes inputs from the user and tells the model what to do and
the view what to show.

#### InteractiveViewController
The constructor of the `InteractiveViewController` takes in its associated model, view, the speed
of the animation, and the filename that the controller will write out to. The start method tells
the Interactive View to display the 

Our InteractiveViewController implements ActionListener. The setButtonListener will take anything
that implements this interface. The action listener will implement the action performed method, which
is called whenever something happens in the view that needs to be handled. We also created an
ActionListener inner class that is an explicit listener only for the timer.

#### SVGViewController
The constructor of the `SVGViewController` takes in a view of type View that the controller will
use to display, and the name of the file that the controller will write out to. The start method
in this controller tells the specified view to write out to the specified file. The getLog and
getTimer methods throw UnsupportedOperationExceptions.

#### TextualViewController
The constructor of the `TextualViewController` takes in a model of type AnimationModel that the
controller will be using, the view of type View that the controller will be using to display, and
the name of the file that the controller will write out to. The start() method goes through the
array lists of animations and shapes associated with the model. The first for loop iterates
through the array list of animations, and gets the animation at each index, recording its
associated shape and shape name. The second for loop then iterates through the array list of
shapes, and if the name of the shape at the current index matches the name of the shape
associated with the animation we found in the first for loop, we update the animation field of
that shape. This way we ensure that, if a shape is animated, its updated version post-animation
is recorded and its fields match the animation. Then, we tell the view to write out to a file with
whatever name is specified. The getLog and getTimer methods throw UnsupportedOperationExceptions.

#### VisualAnimationViewController
The constructor of the `VisualAnimationViewController` takes in a model of type AnimationModel that
the controller will be using, the view of type View that the controller will be using to display,
the speed at which the animation occurs, and a boolean 'isStarted,' which indicates whether or
not the animation has started. The default value of this parameter is set to false. The start
method of this controller was originally in our VisualAnimationView class, so we moved it. It sets
the value of isStarted to true, indicating the animation has begun. It goes through an array list
of shapes associated with the model and creates a shape visitor that corresponds to each shape
(that way we don't alter the shape itself), adding these shape visitors to a new list of shapes. Then,
we have a while loop that operates while the animation is still occurring. The first outer for
loop goes through the array list of animations associated with this model, saving the animation
at each index and its associated shape. The inner for loop then iterates through the array list of
shapes, and if the name of the shape at the current index matches the name of the shape
associated with the animation we found in the first for loop, we update the animation field of
that shape. The second for loop inside the while loop goes through the array list of animations
and records each animation's start and end times. If the start time of the animation is less
than or equal the elapsed units so far in this view, or its end time is greater than or equal to the
elapsed units, we implement the current animation at the time specified by the elapsed units. We
then change the shapes in the view to our new shapes list, and refresh the view. Once we break
out of the if condition and finish iterating through the array list of animations, we display the
view. The getLog and getTimer methods throw UnsupportedOperationExceptions. 

#### Keyboard Handler





