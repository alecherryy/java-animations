Inside our cs5004.easyanimator.model class, we first have our over-arching AnimationModel interface,
 which represents an animation model and is implemented by the AnimationModelImpl class. The class
 contains methods add shapes and animations to the model, check whether there are shapes or
 animations in the model, removing shapes from the model, and get a summary of each item in the
 model. We also have a ModelItem interface, which is implemented by ModelItemImpl, and establishes
 the relationship between shapes and animations. The methods in this interface and class allow for
 adding and removing animations to / from a shape. We also have a Utils class, with static methods
 we use in a few classes and are also useful for testing (for example, checking if a number is
 negative so we can throw exceptions in several constructors when a certain parameter cannot be
 negative).

Then, we have two separate packages inside our model package — shapes and animations. These packages
 follow the same pattern — they contain an interface, which is implemented by an abstract class,
 which is then extended by classes which each represent an enum type. In our shapes package, we
 reasoned that there are two types of shapes to define in ShapeType — a rectangle and an oval.
 Since a square is a rectangle and a circle is an oval, they are both encompassed by the type.
 In our AbstractShape class, we defined methods that are common to both ovals and rectangles.
 These include getting and setting a shape’s fields, such as appear time, disappear time, width,
 height, color, and position, as well as a method to get a description of the shape. We also define
 a super-constructor, which is called inside the Oval and Rectangle classes. In addition to these
 classes, we also have a Coordinates class inside the shapes package, which represents the position
 of a shape. It was easier to put the x- and y-coordinates together in a class, that way we were
 able to pass in the coordinates of the shape as one object in the AbstractShape constructor.

In our animations folder, we reasoned that there are three types of animations to define in
AnimationType — changing the color of a shape, changing the coordinates of a shape, and changing
the size of a shape. In AbstractAnimation, we defined methods that are common to all three of
these changes, which are mainly getter methods but also a method to produce a description of the
animation. We also define a super-constructor that we call inside ChangeColor, ChangeCoordinates,
and ChangeSize (much like we did in our shapes class). Each of the three classes representing a
type of animation contains a method to implement the animation at a certain time, as well as to
update the field of a shape once it has been implemented.