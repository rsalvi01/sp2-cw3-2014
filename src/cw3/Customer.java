package cw3;

/**
 * @author jbliss02 & rsalvi01
 * Customer class - holds the information about a single elevator customer
 * @param noOfFloors
 */

public class Customer extends Random{

	private int startingFloor; //randomly selected starting floor
	private int destinationFloor; //randomly selected destination floor
	private static int id; //unique id for this user
	private boolean inElevator; //whether they are in the elevator or not

	public int getId() {
		return id;
	}
	
	public int getStartingFloor() {
		return startingFloor;
	}

	public void setStartingFloor(int startingFloor) {
		this.startingFloor = startingFloor;
	}

	public int getDestinationFloor() {
		return destinationFloor;
	}

	public void setDestinationFloor(int destinationFloor) {
		this.destinationFloor = destinationFloor;
	}

	public boolean isInElevator() {
		return inElevator;
	}

	public void setInElevator(boolean inElevator) {
		this.inElevator = inElevator;
	}

	/**
	 * @author jbliss02 & rsalvi01
	 * Creates ONE customer and sets the starting and destination floors using the random function from the base class
	 * This is the constructor that will be used in the production system
	 * @param noOfFloors - the number of floors this customer can travel across (i.e. how many floors in the building)
	 */
	
	public Customer(int noOfFloors)
	{
		id++;
		this.startingFloor = randInt(0, noOfFloors);
		this.destinationFloor = randInt(0, noOfFloors);
		this.inElevator = false;
	}
	
	/**
	 * @author jbliss02 & rsalvi01
	 * Overloaded constructor with additional parameters
	 * Creates ONE customer and sets the starting and destination floors
	 * This is the constructor that will be used whilst testing as we can define the start and end floors to test
	 * each strategy is working as expected
	 * 
	 * @param noOfFloors - the number of floors this customer can travel across (i.e. how many floors in the building)
	 * @param currentFloor - what floor the customer starts on
	 * @param destinationFloor - what floor the customer wants to move to
	 */
	public Customer(int noOfFloors, int currentFloor, int destinationFloor)
	{
		id++;
		this.startingFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.inElevator = false;
	}



}