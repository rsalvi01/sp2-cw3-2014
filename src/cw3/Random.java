/**
 *
 * 
 */
package cw3;

/**
 * @author jbliss02 & rsalvi01
 * 
 * A base class that contains a method to create a random number, based on a minimum and maximum number of floors
 * 
 * Does not allow 13 to be used as a floor as 13 is not a valid number as the building does not have a floor 13
 * 
 */
public class Random {

	
	/**..
	* Returns a pseudo-random number between min and max, inclusive.
	* 13 cannot be assigned as a starting or destination floor
	*
	* @param min Minimum value
	* @param max Maximum value. Must be greater than min.
	* @return Integer between min and max, inclusive.
	*/
	public static int randInt(int min, int max)
	{
		int value = 13;
		
		while (value == 13)
		{
			value = min + (int)(Math.random() * ((max - min) + 1));
		}
		
		return value;
	}
	
}
