package cw3;

public class Customer {

	private int startingFloor;
	private int destinationFloor;
	private static int id;
	private boolean inElevator;
	private boolean finished;
	
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

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}
	
	
	/**
	 * @author jbliss02 & rsalvi01
	 * Creates ONE customer and sets the starting and destination floors 
	 * @param noOfFloors
	 */
	
	
	public Customer(int noOfFloors, int currentFloor, int destinationFloor)
	{
		id++;
		this.startingFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.inElevator = false;
		finished = false;
	}


}