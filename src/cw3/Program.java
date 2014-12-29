/**
 * 
 */
package cw3;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author jbliss02 & rsalvi01
 *
 *The main driving program that takes an input from the user
 *creates a list of customers, creates the building class and runs the elevator to collect
 *and drop off each person
 *
 *Prints out the result
 */
public class Program {

	/**
	 * @param args
	 */
	
	private static ArrayList<Customer> customerList; 
	private static Building building;
	private static int numberFloors;
	private static int numberCustomers;
	
	
	/**
	 * Calls the method that asks the user for input
	 * Then calls methods to create the building and customer class
	 * Then runs the latest lift strategy (3)
	 * Reports the results
	 * 
	 */
	public static void main(String[] args) {
		
		getUserInput(); //ask the user to define the number of customers to ferry, and the number of floors in the building
		createCustomerList(numberCustomers,numberFloors); //initialise and populate the list of customers
		createBuilding(customerList,numberFloors); //initialise the building
		
		//report the results
		System.out.println(String.valueOf(numberCustomers) + " customers require the lift in a building with " + 
						   String.valueOf(numberFloors) + " floors");
		
		building.elevator.go(3);
		System.out.println("Strategy 3 made " + building.elevator.getNumberMoves() + " moves");
			
	}
	
	/**
	 * Uses the scanner class to prompt the user for their inputs
	 * for the number of customers and number of floors the elevator has
	 * ensures the numbers entered are proper integers
	 * 
	 * sets the class variables numberCustomers and numberFloors
	 * 
	 */
	private static void getUserInput(){
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the number of customers ");
		
		while(!scanner.hasNextInt()) //non ints
		{
			System.out.print("Invalid entry, try again: ");
			scanner.next();
		}
		
		numberCustomers = scanner.nextInt();
		
		System.out.print("Enter the number of floors ");
		
		while(!scanner.hasNextInt()) //non ints
		{
			System.out.print("Invalid entry, try again: ");
			scanner.next();
		}
		
		numberFloors = scanner.nextInt();

		scanner.close();
		
	}//getUserInput()
	
	/**
	 * Populates the customerList with a set of customers
	 * If the customer has the same start and destination floor then the customer
	 * is not added to the list as their journey is considered finished
	 * 
	 * @param  numberCustomers the number of individual customers to create
	 * @param  numberFloors the number of floors the building has

	 */
	private static void createCustomerList(int numberCustomers, int numberFloors) 
	{
		customerList = new ArrayList<Customer>();
		
		for(int i = 0; i < numberCustomers; i++)
		{
			Customer c = new Customer(numberFloors);
			if(c.getStartingFloor() != c.getDestinationFloor())
			{
				customerList.add(c);
			}
		}
	}//createCustomerList()
	
	/**
	 * Populates the class variable building
	 * 
	 * @param  customerList - the array list created from the user input containing customer and their travel information
	 * @param  numberFloors the number of floors the building has

	 */
	private static void createBuilding(ArrayList<Customer> customerList, int numberFloors)
	{
		building = new Building(customerList, numberFloors);
	}
	

	
	
	
	
	
	

}
