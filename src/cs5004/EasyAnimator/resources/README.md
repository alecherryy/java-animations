# Java Easy Animator
**Contributors**: Clara Soares Bezerra and Alessia Pizzoccheri.
## Introduction
The purpose of this project is to build an application that helps to create simple but effective 2D animations from shapes.
- [The Model](##the-model)
    - [Shapes and Animations](###shapes-and-animations)
    - [Animation Model](###animation-model)
- [The View](##the-view)

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