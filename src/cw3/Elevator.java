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
 * 
 * Customers are removed from the customerList once their journey has been completed
 */

public class Elevator extends Random{

	private final int NUMBERFLOORS;
	private ArrayList<Customer> customerList;
	private int currentFloor;
	public int movingDirection; //1 is up, 0 is not set, -1 is down
	public int numberMoves; //counter to show how many total moves the elevator makes
	
	/**
	 * Constructor - sets the elevator variables
	 * This is the constructor to be used in production as it sets the current floor to a random number
	 *  * 
	 * @param customerList - the customers, with their start and destination floors
	 * @param numberFloors - the number of floors the elevator has to serve
	 */
	public Elevator(ArrayList<Customer> customerList, int numberFloors)
	{
		this.customerList = customerList;
		this.NUMBERFLOORS = numberFloors;
		this.currentFloor = randInt(0, numberFloors);
		movingDirection = 0;
		numberMoves = 0;
	}
	
	/**
	 * Overloaded Constructor - sets the elevator variables
	 * This is the constructor to be used in test as it sets the startingFloor so can be used to 
	 * validate elevator strategies match the expected outcome more easily
	 * 
	 * @param customerList - the customers, with their start and destination floors
	 * @param numberFloors - the number of floors the elevator has to serve
	 * @param startingFloor - the floor the elevator starts on
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
	 * The main part of the program, when called runs the strategies until
	 * all customers have completed their strategy
	 * @param strategy - defines which lift strategy to run 
	 */
	
	public void go(int strategy)
	{
		//loop through the customer list until the list has no elements
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
				//System.out.println(customerList.size());
				//printCustomers();
				if(customerList.size() > 0) {superMove2();}
				break;
			}
			
		}
		
		//all customers have been dropped off
		System.out.println("finished in " + numberMoves);
		
	}//go()
	
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
	 * superMove - the second lift strategy, checks whether the lift really has to move upwards
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
	 * superMove2 - the third lift strategy. Upgrades on superMove1() by only stopping at floors
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
				floorsToMove(1); break;
			}
			else if (!customerList.get(i).isInElevator() && customerList.get(i).getStartingFloor() > currentFloor)
			{
				floorsToMove(1);break;
			}
			else {
				floorsToMove(-1); break;}
		}
		
		currentFloor += movingDirection;
		numberMoves++;		
	}
	
	/**
	 * Loops through the customer list to see how many floors to move to complete the next step (i.e. collect
	 * or drop off a customer)
	 * 
	 * Sets the class variable - movingDirection with the number of floors to move
	 * 
	 * @param direction - which way the lift is moving 1 is up, -1 is down
	 * 	 
	 * */	
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
	

	/**
	 * Called each time a lift arrives at a floor, iterates through the customer list
	 * to see whether a customer is here waiting to be picked up
	 * if so sets the inElevator variable on that customer 
	 * 	 
	 * */	
	private void pickUpCustomer()
	{
		for(int i = 0; i < customerList.size(); i++)
		{
			if(customerList.get(i).getStartingFloor() == this.currentFloor && !customerList.get(i).isInElevator())
			{
				customerList.get(i).setInElevator(true);
			}
		}
		
	}//pickUpCustomer()
	
	
	/**
	 * Called each time a lift arrives at a floor, iterates through the customer list
	 * to see whether a customer is in the lift waiting to be dropped off
	 * if so removes the customer from the list to show their journey has eneded 
	 * */	
	private void dropOffCustomer()
	{
		for(int i = customerList.size() - 1; i >= 0; i--)
		{
			if(customerList.get(i).getDestinationFloor() == this.currentFloor && customerList.get(i).isInElevator())
			{
				customerList.remove(i);
			}
		}
	}//dropOffCustomer()
	
	
	
}
