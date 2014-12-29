package cw3;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Test;

public class ElevatorTest {


	@Test
	public void moveFloors_singleCustomer()
	{
		//move one
		Customer c = new Customer(10, 2, 3); //customer who wants to move one floor
		ArrayList<Customer> customers = new ArrayList<Customer>();
		customers.add(c);
		Elevator elevator = new  Elevator(customers, 10, 2);
		elevator.go(1);
		assertEquals("moveFloors_singleCustomer 1", 1, elevator.numberMoves);

		//move 8
		customers = new ArrayList<Customer>();
		c = new Customer(10, 2, 10); //customer who wants to move eight floors
		customers.add(c);
		elevator = new  Elevator(customers, 10, 2);
		elevator.go(1);
		assertEquals("moveFloors_singleCustomer 2", 8, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 2
		//customer starts at floor 10 and wants to go to floor 2
		//16 moves - 8 up and 8 down
		customers = new ArrayList<Customer>();
		c = new Customer(10, 10, 2); //customer who wants to move eight floors
		customers.add(c);
		elevator = new  Elevator(customers, 10, 2);
		elevator.go(1);
		assertEquals("moveFloors_singleCustomer 2", 16, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 4 and wants to go to floor 5
		customers = new ArrayList<Customer>();
		c = new Customer(10, 4, 5);
		customers.add(c);
		elevator = new  Elevator(customers, 10, 5);
		elevator.go(1);
		assertEquals("moveFloors_singleCustomer 2", 20, elevator.numberMoves);
	}

	@Test
	public void superMoveWithOneCustomer()
	{
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 4 and wants to go to floor 5
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Customer c = new Customer(10, 4, 5);
		customers.add(c);
		Elevator elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 2, elevator.numberMoves);
	
	}
	
	@Test
	public void superMoveWithOneCustomer1()
	{
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 2 and wants to go to floor 9
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Customer c = new Customer(10, 2, 9);
		customers.add(c);
		Elevator elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 10, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 6 and wants to go to floor 9
		customers = new ArrayList<Customer>();
		c = new Customer(10, 6, 9);
		customers.add(c);
		elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 4, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 7 and wants to go to floor 3
		customers = new ArrayList<Customer>();
		c = new Customer(10, 7, 3);
		customers.add(c);
		elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 6, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 4 and wants to go to floor 1
		customers = new ArrayList<Customer>();
		c = new Customer(10, 4, 1);
		customers.add(c);
		elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 4, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 5 and wants to go to floor 7
		customers = new ArrayList<Customer>();
		c = new Customer(10, 5, 7);
		customers.add(c);
		elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 2, elevator.numberMoves);
		
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 5 and wants to go to floor 0
		customers = new ArrayList<Customer>();
		c = new Customer(10, 5, 0);
		customers.add(c);
		elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 5, elevator.numberMoves);
	
	}

	@Test
	public void superMoveWithManyCustomers()
	{
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 4 and wants to go to floor 5
		ArrayList<Customer> customers = new ArrayList<Customer>();
		Customer c = new Customer(10, 2, 9);
		customers.add(c);
		c = new Customer(10, 6, 9);
		customers.add(c);
		c = new Customer(10, 7, 3);
		customers.add(c);
		c = new Customer(10, 4, 1);
		customers.add(c);
		c = new Customer(10, 5, 7);
		customers.add(c);
		c = new Customer(10, 5, 0);
		customers.add(c);
		
		Elevator elevator = new  Elevator(customers, 10, 5);
		elevator.go(2);
		assertEquals("moveFloors_singleCustomer 2", 22, elevator.numberMoves);
	
	}
	
	@Test
	public void comparingMoves()
	{
		//lift has 10 floors and starts at floor 5
		//customer starts at floor 4 and wants to go to floor 5
		ArrayList<Customer> customers = new ArrayList<Customer>();
		ArrayList<Customer> customers2 = new ArrayList<Customer>();
		Customer c = new Customer(10, 2, 9);
		Customer c2 = new Customer(10, 2, 9);
		customers.add(c);
		customers2.add(c2);
		c = new Customer(10, 6, 9);
		c2 = new Customer(10, 6, 9);
		customers.add(c);
		customers2.add(c2);
		c = new Customer(10, 7, 3);
		c2 = new Customer(10, 7, 3);
		customers.add(c);
		customers2.add(c2);
		c = new Customer(10, 4, 1); 
		c2 = new Customer(10, 4, 1);
		customers.add(c);
		customers2.add(c2);
		c = new Customer(10, 5, 7);
		c2 = new Customer(10, 5, 7);
		customers.add(c);
		customers2.add(c2);
		c = new Customer(10, 5, 0);
		c2 = new Customer(10, 5, 0);
		customers.add(c);
		customers2.add(c2);
		
		Elevator elevator = new  Elevator(customers, 10, 5);
		elevator.go(1);
		assertEquals("moveFloors_singleCustomer 2", 24, elevator.numberMoves);
		
		Elevator elevator2 = new  Elevator(customers2, 10, 5);
		elevator2.go(2);
		assertEquals("moveFloors_singleCustomer 2", 22, elevator2.numberMoves);
		
	}
	

	@Test
	public void superMove2WithManyCustomers()
	{
		
		for(int i = 0; i < 11; i++){
			
			//lift has 10 floors and starts at floor 5
			ArrayList<Customer> customers = new ArrayList<Customer>();
			Customer c = new Customer(10, 7, 8);
			customers.add(c);
			c = new Customer(10, 6, 9);
			customers.add(c);
			c = new Customer(10, 7, 3) ;
			customers.add(c);
			c = new Customer(10, 4, 1);
			customers.add(c);
			c = new Customer(10, 5, 7);
			customers.add(c);
			c = new Customer(10, 5, 0);
			customers.add(c);
			
			Elevator elevator = new  Elevator(customers, 10, 5);
			elevator.go(3);
			assertEquals("moveFloors_singleCustomer 2", 8, elevator.numberMoves);
		}
		

	
	}

}