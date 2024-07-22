/**
 * 
 */
package prj3;

/**
 * Creates the Coordinate object to store the x y and z values of a coordinate
 * 
 * @author Keenan Clemmitt and Andrew Hill
 * @version 04.22.23
 *
 */
public class Coordinate {

    private int x; // x coordinate
    private int y; // y coordinate
    private int z; // z coordinate

    /**
     * Constructor makes a new three dimensional coordinate.
     * 
     * @param x is the x coordinate
     * @param y is the y coordinate
     * @param z is the z coordinate
     */
    public Coordinate(int x, int y, int z) {
	this.x = x;
	this.y = y;
	this.z = z;
    }

    /**
     * gets the x value
     * 
     * @return the x coordinate.
     */
    public int getX() {
	return x;
    }

    /**
     * gets the y value
     * 
     * @return the y coordinate.
     */
    public int getY() {
	return y;
    }

    /**
     * gets the z value
     * 
     * @return the Z coordinate.
     */
    public int getZ() {
	return z;
    }

}