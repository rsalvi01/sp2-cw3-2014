package cw3;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void createCustomers() //tests the id that is created
	{
		Customer c = new Customer(10);
		assertEquals("Create 1", 1, c.getId());
		
		Customer c2 = new Customer(7);
		assertEquals("Create 2", 2, c2.getId());
		assertEquals("Create 3", 3, new Customer(100).getId());

	}

	@Test
	public void testFloors() //tests the number of floors
	{
		
		assertTrue("x", new Customer(20).getStartingFloor() <= 20 );
		
		for(int i = 0; i < 100; i++)
		{
			assertTrue("x", new Customer(i).getStartingFloor() <= i ); //starting floor cannot be higher than the max floors
			assertTrue("x", new Customer(i).getStartingFloor() >= 0 ); //starting floor cannot be lower than zero
			assertTrue("x", new Customer(i).getStartingFloor() != 13 ); //starting floor cannot be 13
			assertTrue("x", new Customer(i).getDestinationFloor() <= i );
			assertTrue("x", new Customer(i).getDestinationFloor() >= 0 );
			assertTrue("x", new Customer(i).getDestinationFloor() != 13 );
		}
	}
	
}
