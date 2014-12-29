/**
 * 
 * 
 */
package cw3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
	private static final int NUMBERSTRATEGIES = 2; //the number of lift strategies supported
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
		
		//run each of the strategies and report the results
		System.out.println(String.valueOf(numberCustomers) + " customers require the lift in a building with " + 
				   String.valueOf(numberFloors) + " floors");
		
		ArrayList<Integer> strategies = returnStrategies(); //get a list of each strategy id
		ArrayList<Integer> results = new ArrayList<Integer>(); //holds the number of results for each strategy so can be compared
		
		//run the main elevator method for each strategy
		for(int i = 0; i < strategies.size(); i++){
			
			ArrayList<Customer> copy = new ArrayList<Customer>(customerList); //take a copy of the class variable so each strategy starts with the same, unaltered customerList
			createBuilding(copy,numberFloors); //initialise the building
			building.elevator.go(strategies.get(i)); //run the strategy
			results.add(building.elevator.getNumberMoves()); //add the number of moves to the results arrayList
			
			//report and compare the results
			String stResult = "Strategy " + String.valueOf(strategies.get(i)) +" made " + building.elevator.getNumberMoves() + " moves";
			
			if(i > 0){ //more than one strategy has run so we can compare to the last
				
				double difference = (results.get(i - 1) / results.get(i)); 
				
				if(difference > 1){
					stResult += " which is " + String.valueOf(difference * 100) + "% more efficient than strategy " + String.valueOf(strategies.get(i - 1));
				}
				else if(difference < 1){
					stResult += " which is " + String.valueOf(difference * 100) + "% less efficient than strategy " + String.valueOf(strategies.get(i - 1));					
				}
				else 
				{
					stResult += " which has the same efficiency as strategy " + String.valueOf(strategies.get(i - 1));						
				}
				
			}

			System.out.println(stResult);
		}
				
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
	
	
	/**
	 * Returns an array list of integers, each integer relates to a lift strategy
	 * 
	 */
	private static ArrayList<Integer> returnStrategies()
	{
		ArrayList<Integer> ret = new ArrayList<Integer>();
		for(int i = 1; i < NUMBERSTRATEGIES + 1; i++){
			ret.add(i);
		}
		return ret;
		
	}//returnStrategies()
	

}
