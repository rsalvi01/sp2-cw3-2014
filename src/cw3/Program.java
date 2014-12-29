/**
 * 
 */
package cw3;

import java.util.ArrayList;

/**
 * @author jbliss02 & rsalvi01
 *
 *The main driving program that takes an input from the user
 *creates a list of customers, creates the building class and runs the elevator to collect
 *and drop off each person
 *
 */
public class Program {

	/**
	 * @param args
	 */
	
	private static ArrayList<Customer> customerList; 
	private static Building building;
	private static int numberFloors;
	private static int numberCustomers;
	
	public static void main(String[] args) {
		
		/**TODO - take these values from the user and validate */
		numberFloors = 10;
		numberCustomers = 8;
		
		customerList = new ArrayList<Customer>();
		createCustomerList(numberCustomers,numberFloors);
		createBuilding(customerList,numberFloors);

		
	}
	
	/**
	 * Populates the customerList with a set of customers
	 * Creates a random start and destination floor for each customer
	 * If the customer has the same start and destination floor then the customer
	 * is not added to the list as their journey is considered finished
	 * 
	 * @param  numberCustomers the number of individual customers to create
	 * @param  numberFloors the number of floors the building has

	 */
	private static void createCustomerList(int numberCustomers, int numberFloors) 
	{
		for(int i = 0; i < numberCustomers; i++)
		{
			Customer c = new Customer(numberFloors);
			if(c.getStartingFloor() != c.getDestinationFloor())
			{
				customerList.add(c);
			}
		}
	}//createCustomerList()
	
	private static void createBuilding(ArrayList<Customer> customerList, int numberFloors)
	{
		building = new Building(customerList, numberFloors);
	}
	

	
	
	
	
	
	

}
