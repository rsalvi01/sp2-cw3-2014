/**
 * 
 */
package cw3;

import java.util.ArrayList;

/**
 * @author jbliss02 & rsalvi01
 *
 * Creates a single instance of an elevator that serves a building
 * This class runs the logic that defines in which direction the lift
 * should go, counts the number of floors and terminates once all
 * customers have been picked up and dropped off
 */

public class Elevator {

	private final int NUMBERFLOORS;
	private ArrayList<Customer> customerList;
	private int currentFloor;
	public int movingDirection; //1 is up, 0 is not set, -1 is down
	public int numberMoves; //counter to show how many total moves the elevator makes
	
	/**
	 * Constructor
	 * @param customerList - the customers, with their start and destination floors
	 * @param numberFloors - the number of floors the elevator has to serve
	 */
	public Elevator(ArrayList<Customer> customerList, int numberFloors, int startingFloor)
	{
		this.customerList = customerList;
		this.NUMBERFLOORS = numberFloors;
		this.currentFloor = startingFloor;
		movingDirection = 0;
		numberMoves = 0;
	}
	/**
	 * 
	 * @param strategy - defines which lift strategy to run 
	 */
	
	public void go(int strategy)
	{
		while(customerList.size() > 0)
		{			
			pickUpCustomer();
			dropOffCustomer();
			
			switch(strategy){
			case 1:
				if(customerList.size() > 0) {basicMove();}
				break;
			case 2:
				if(customerList.size() > 0) {superMove();}
				break;	
			case 3:
				if(customerList.size() > 0) {superMove2();}
				break;
			}
			
			
		}
		
		System.out.println("finished in " + numberMoves);
	}
	
	/**
	 * the default lift strategy, one that
	 * moves up and down each floor one by one, stopping at each
	 * 
	 * If lift is not moving then move up, unless we are at top floor
	 * If lift is moving up then continue to move up, unless we are at top; in which case move down
	 * If lift is moving down then continue to move down, unless we are at bottonm; in which case move up
	 * 	 */	
	private void basicMove()
	{
		
		//set the initial moving direction, if it is not set
		if(movingDirection == 0)
		{
			movingDirection = currentFloor < NUMBERFLOORS ? 1 : -1;
		}
		
		if(movingDirection == 1 && currentFloor == NUMBERFLOORS)
		{
			movingDirection = -1;
		}
		else if(movingDirection == -1 && currentFloor == 0)
		{
			movingDirection = 1;
		}
		
		currentFloor += movingDirection;
		numberMoves++;

	}//basicMove()
	
	
	/**
	 * superMove() - the second lift strategy, checks whether the lift really has to move upwards
	 * rather than just blindly moving upwards. Still moves each floor, one by one
	 * 
	 * Loops through the list of customers to see whether any customer needs to travel upwards,
	 * or whether we need to travel upwards to collect a customer. As soon as we find we need to
	 * move upwards then the loop is exited, as there is no need to iterate over the remaining elements
	 * 
	 * 	 */	
	private void superMove()
	{				
		for (int i = 0; i < customerList.size(); i++)
		{
			if (customerList.get(i).isInElevator() && customerList.get(i).getDestinationFloor() > currentFloor)  
			{
				movingDirection = +1;
			}
			else if (!customerList.get(i).isInElevator() && customerList.get(i).getStartingFloor() > currentFloor)
			{
				movingDirection = +1;
			}
			else {movingDirection = -1;}
		}
		
		currentFloor += movingDirection;
		numberMoves++;		
	}
	
	/**
	 * superMove2() - the third lift strategy. Upgrades on superMove1() by only stopping at floors
	 * that are necessary.
	 * 
	 * Still has a preference to move up rather than down but can move up or down many floors at once
	 * 
	 * 	 */	
	private void superMove2()
	{				
		for (int i = 0; i < customerList.size(); i++)
		{
			if (customerList.get(i).isInElevator() && customerList.get(i).getDestinationFloor() > currentFloor)  
			{
				floorsToMove(1);
			}
			else if (!customerList.get(i).isInElevator() && customerList.get(i).getStartingFloor() > currentFloor)
			{
				floorsToMove(1);
			}
			else {floorsToMove(-1);}
		}
		
		currentFloor += movingDirection;
		numberMoves++;		
	}
	
	//1 is up, -1 is down
	private void floorsToMove(int direction)
	{
		if(direction == 1)
		{
			int nextFloorUp = NUMBERFLOORS;
			for(int i = 0; i < customerList.size(); i++)
			{
				if(customerList.get(i).isInElevator() && customerList.get(i).getDestinationFloor() > currentFloor)
				{
					if (nextFloorUp > customerList.get(i).getDestinationFloor())
					{
						nextFloorUp = customerList.get(i).getDestinationFloor();
					}
				}
				else if (!customerList.get(i).isInElevator() && customerList.get(i).getStartingFloor() > currentFloor)
				{
					if (nextFloorUp > customerList.get(i).getStartingFloor())
					{
						nextFloorUp = customerList.get(i).getStartingFloor();
					}
				}
			}//for each customer (i)
			movingDirection = (nextFloorUp - currentFloor) * direction;
		}
		else if (direction == -1)
		{
			int nextFloorDown = 0;
			for(int i = 0; i < customerList.size(); i++)
			{
				if(customerList.get(i).isInElevator() && customerList.get(i).getDestinationFloor() < currentFloor)
				{
					if (nextFloorDown < customerList.get(i).getDestinationFloor())
					{
						nextFloorDown = customerList.get(i).getDestinationFloor();
					}
				}
				else if (!customerList.get(i).isInElevator() && customerList.get(i).getStartingFloor() < currentFloor)
				{
					if (nextFloorDown < customerList.get(i).getStartingFloor())
					{
						nextFloorDown = customerList.get(i).getStartingFloor();
					}
				}
			}//for each customer (i)
			movingDirection = (currentFloor - nextFloorDown) * direction;
		}

	}//floorsToMove
	

	private void pickUpCustomer()
	{
		for(int i = 0; i < customerList.size(); i++)
		{
			if(customerList.get(i).getStartingFloor() == this.currentFloor)
			{
				customerList.get(i).setInElevator(true);
			}
		}
		
	}//pickUpCustomer()
	
	private void dropOffCustomer()
	{
		for(int i = customerList.size() - 1; i >= 0; i--)
		{
			if(customerList.get(i).getDestinationFloor() == this.currentFloor && customerList.get(i).isInElevator())
			{
				customerList.remove(i);
			}
		}
	}
	
	
	
}
