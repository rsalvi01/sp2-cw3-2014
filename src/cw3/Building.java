/**
 * 
 */
package cw3;

import java.util.ArrayList;

/**
 * @author jbliss02 & rsalvi01
 * Creates ONE building
 */
public class Building {

	public Elevator elevator;

	public Building(ArrayList<Customer> customerList, int numberFloors, int currentFloor)
	{
		elevator = new Elevator(customerList, numberFloors, currentFloor);		
	}
	
	
	
}