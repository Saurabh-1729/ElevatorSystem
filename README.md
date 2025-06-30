The Elevator System can be improved by optimizing how we are choosing the best
elevators, based on direction as well as the distance.
Then we can drop all the users that have requested for the same destination.
If the load allows we can pick up the multiple users from the same floor.


	1. In this design first we need to understand that each elevator will run on its own thread.
	2. In Elevator class we can have ID, Direction(idle, up, down), current floor, capacity, request queue(Blocking queue), set of destinations (this is required, if two users have the same destinations)
	3. So in the queue we will put the request and will handle it in first come first serve basis
	4. First we take out the current request, move to the source and then we move to the destination, we will add sleep in between to simulate movement.
	5. We have direction ENUM
	6. We have request class with source and destination floor
	7. In controller, we will handle the request:
	• First we will check the best elevator based on minimum distance and capacity, if found, we will add the request to that elevator thread.
	• This class will maintain the list of elevators.
