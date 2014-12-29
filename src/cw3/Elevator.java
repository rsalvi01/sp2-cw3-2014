/**
 * 
 */
package cw3;

import java.util.ArrayList;

/**
 * @author jbliss02 & rsalvi01
 *
 * Creates a single instance of an elevator that serves a building
 * The maximum number of floors 
 *
 */
public class Elevator {

	private final int NUMBERFLOORS;
	private ArrayList<Customer> customerList;
	private int currentFloor;
	private int startingFloor;
	public int movingDirection; //1 is up, 0 is not set, -1 is down
	public int numberMoves;
	
	/**
	 * Constructor
	 * @param customerList 
	 * @param numberFloors
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
	public void floorsToMove(int direction)
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
	

	public void pickUpCustomer()
	{
		for(int i = 0; i < customerList.size(); i++)
		{
			if(customerList.get(i).getStartingFloor() == this.currentFloor)
			{
				customerList.get(i).setInElevator(true);
			}
		}
		
	}//pickUpCustomer()
	
	public void dropOffCustomer()
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
