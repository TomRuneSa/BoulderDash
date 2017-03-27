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







