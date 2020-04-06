# Java Easy Animator
**Contributors**: Clara Soares Bezerra and Alessia Pizzoccheri.
## Introduction
The purpose of this project is to build an application that helps to create simple but effective 2D animations from shapes.

### Overview 
Inside our `cs5004.cs5004.EasyAnimator.model` directory, we first have our over-arching `AnimationModel` Interface,
which represents an animation model with a series of methods implemented by the `AnimationModelImpl` Class. Such methods include: adding shapes and animations to the model, check whether there are shapes or
animations in the model, removing shapes from the model, and get a summary of each item in the
model.

We also have a `ModelItem` Interface, which is implemented by the `ModelItemImpl` class, and establishes
the relationship between shapes and animations. The methods in this interface and class allow for
adding/removing animations to/from a shape. 

Our application includes a `Utils` Class, which includes a collection of static methods we use in a few classes and are useful for testing (i.e., checking if a number is
negative so we can throw exceptions in several constructors when a certain parameter cannot be negative).

Finally, we have two separate packages inside our model package — _shapes_ and _animations_. These packages follow the same pattern of the objects listed above — they contain an Interface, which is implemented by an Abstract Class,
and extended by Classes, each representing an enum type.

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

### Appear/Disappear
As you review our project, you will notice that there is not method nor animation to allow Shapes to _Appear/Disappear_. That's because we were not able to reach an agreement on how to implement this functionality.
Specifically, we are having concerns on whether such functionality should be yet another `Animation` or be part of the `Shape` itself. 

One argument is that shape objects should be "stateless", meaning
they do not interact with the timeline and have no concept of _visibility_. They either exist or don't and only feature properties that would be appropriate to a shape (i.e. width, height, color, position, etc.). In this scenario, 
the ability to appear and disappear would be added to the `Animations` and would inherit some of the properties shared among all animations.

Another argument is that _visibility_ should, indeed, be a property of each shape, as well as time references according to which the shape appears and disappears. This implies
a `Shape` class would include two additional parameters in its constructor before it can be instantiated; said parameters would be `startTime` and `endTime`.

After much discussion, we have decided to leave this portion of the implementation for the next round of this assignment, during which we will
be giving more thought to how exactly animations and shapes exist and behave within the larger `AnimatioModel`. 

### View Package
To implement this portion of the project, we made some modifications to our existing packages. 

In our util package, we decided to create a TweenModelBuilder interface. The purpose of this 
interface is to read in a file containing the animation and build a model accordingly. We implement
the interface inside a static class we declare in our AnimationModelImpl class, which adds shapes 
and animations to our model, and contains a method that returns a new AnimationModelImpl object with
the updated changes.

We also made some modifications to our shapes package. We added methods in our Shapes interface 
that return the representation of the shape in SVG tags, and defined their specific implementations
in our Oval and Rectangle classes. We also created a visitor design pattern through our
ShapesVisitor interface so we could add new operations to existing shape object structures without
modifying the structures themselves directly (and instead making copies of them). So, in our static
builder class inside our AnimationModelImpl class, we can create new shapes that follow the same
pattern as the original shapes but have the  appropriate changes made to them (such as a new
color, new size, or new position, depending on what animation we are adding). We do this to 
ensure that our model follows the second SOLID principle -- our classes should be open for extension
but closed for modification.

Finally, in our view package, we have a View interface, which contains methods that are common to 
all views, and is implemented by our SVGView, TextualView, and VisualAnimationView classes. Our
AnimateJPanel class extends JPanel and creates the animation ‘window’ that will display our model.
The TextualView sub-class creates a TextualView object with its given speed, array list of shapes,
and array list of animations parameters. It outputs the description of the model to a file whose
name the user can specify. Our SVGView class extends TextualView and calls its super-constructor to
produce an SVGView object with the before-mentioned fields. It also outputs to a file. In addition
to implementing the View interface, our VisualAnimationView class extends JFrame in order to
produce the visual representation of our model. It creates a VisualAnimationView object with a
model's list of shapes and the speed at which the animation happens, and creates an animation
panel by creating an AnimateJPanel object.