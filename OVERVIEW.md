# Oversikt
2.2.1)
Interfaces: 
IBDKillable, IBDMovingObject and IBDObject are the interfaces that are being used in .bdobjects. There are also three more interfaces in the task: IGrid, IList, IGenerator.

The interface IDBKillable realises the consequences of dying in the game.
The interface IBDMovingObject is an interface that all objects that wants to move have to implement. In the interface the next step is planned with the next position.
The interface of IBDObject is something that all objects in the game has to implement. The most important functionality in the interface is the step() method which updates the position and state of the objects.

2.2.2)
The connection between the interfaces is that they are all dependent on the other interfaces. The step() method in IBDObject is something that the other interfaces are depending on to do their job.

2.2.3)
All the object Classes inherites traits from the Abstract Classes. Without these traits it would'nt be possible to know what kind of traits said object should have.

There are four abstract classes: AbstractBDFallingObject, AbstractBDKillingObject, AbstractBDMovingObject, AbstractBDObject. AbstractBDFallingObject is a class that contains the logic of objects that should be able to fall. 
AbstractBDKillingObject contains the logic of objects that should be able to killed. And all objects that can be killed can move.
AbstractBDMovingObject implemenets the logic of objects that can move. It implements the prepareMove() methods, which is independent of the type of object. 
AbstractBDObject gets and sets the position for different objects in a map.
These classes are abstract because they're going to be applying to many other classes. If they hadn't been abstract there would've been one type of implementation, and considering that so many classes is depending on these classes there would have been bound to have a lot of errors. 

2.2.4)
The main part of the game is written in the BoulderDashGUI.java. This is where the map is drawn and registers if the player is dead or alive. It also registers what buttons are being pushed.

2.2.5)
Abstraction is being used here to hide and simplify the implementation complexity. It's being used to simplify the interface so that one can access the underlying implementation. 

2.2.6)
To make a new object, depending on what type of object you want to make, one would have to inherit from one of the abstract classes to have some sort of identity on the object. Then you have define how it should look in the map, and how it should move etc. All of this would have to be a new class.

2.2.7)
It's implemented that a diamond falls in the inheritance from AbstractBDFallingObject.  

Questions)

We need the getPosition method because we need to find and save the position of objects. We could save the position of each object as well, but it's much easier to get the position when we could just call upon one method, instead of trying to find the exact object that we need.



Description of the system.

This system is intended to be a 2-dimensional game wich has different objects. Amongst those objects are a player, bugs, diamonds, sand, rocks... But to play the game you need something to play it on, a map. An for the program to read what's supposed to be the map. And for that one creates a mapreader. The mapreader reads from the chosen document what size the map should be, and what it shall contain. It creates a grid.
The map is then created. The reader reads the different characters in the document, and it is then printed with it's corresponding value. Stone, sand and so forth. In the map, the bugs are supposed to act a certain way, which is spesified in BDBug.java. It says there that a bug should move in a west, north, east, south direction constantly. It also says that if the object in the next position of the bug is killable (Which basically only goes for the player in this case), the bug should kill it. The bug class also checks if the next position of the bug is a valid move for the bug. 
The same kind of procedure goes for the other objects, such as diamonds and rocks. It's checked if these objects can fall down, if the space below the object is free, and if it is, and the object is supposed to be falling down, the object falls down one space. The criteria for any object other than the player to be able to move is that there is nothing in the space that's going to be moved to. The criteria for the player to move is just that there isn't a rock that can't be moved in the way, as long as it's inside the map grid. There is no action that ends the game, unless you end it yourself, or if you get eaten by the bug. If neither of those happen, you could walk around as long as you want. The game is step-based, so nothing happens unless a step is called upon. In every class that is depending on something to happen has a call to the step method. 





Credits:

I have not created any of the pictures or gifs used in this task. 
Sonic gif: 
http://giphy.com/gifs/sonic-the-hedgehog-TmkgRTgTyVjjy. 

The rings that are used as diamonds:
http://giphy.com/gifs/sonic-sprite-facundogomez-MYfNzAwLrln68,0

Rocks and sand: 
http://opengameart.org/content/2d-minecraft-sprite-pack

The license says that there is no copyright on said product, but I'd like to give a reference either way. 




