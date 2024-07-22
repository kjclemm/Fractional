/**
 * 
 */
package prj3;

/**
 * Creates a Z Coordinate which is a subclass of Coordinate which has pointers
 * to other Coordiantes and the index value of where it is stores
 * 
 * @author Keenan Clemmitt and Andrew Hill
 * @version 04.22.23
 */
public class ZCoordinate extends Coordinate {

    private Coordinate leftPointerMax; // The maximum value of the left set of z values.
    private Coordinate rightPointerMax; // The maximum value of the right set of z values.
    private Coordinate leftPointerMin; // The minimum value of the left set of z values.
    private Coordinate rightPointerMin; // The minimum value of the right set of z values.
    private int index; // The index of the coordinate in the Z coordinate array.

    /**
     * @param x     is the x value of the z coordinate
     * @param y     is the y value of the z coordinate
     * @param z     is the z value of the coordinate we want.
     * @param index is the index of the coordinate in the Z coordinate array.
     */
    public ZCoordinate(int x, int y, int z, int index) {
	super(x, y, z);
	leftPointerMax = null;
	rightPointerMax = null;
	leftPointerMin = null;
	rightPointerMin = null;
	this.index = index;
    }

    /**
     * gets the left max pointer
     * 
     * @return the maximum value of the left set of z values.
     */
    public Coordinate getLeftPointerMax() {
	return leftPointerMax;
    }

    /**
     * gets the right max pointer
     * 
     * @return the maximum value in the right set of z values.
     */
    public Coordinate getRightPointerMax() {
	return rightPointerMax;
    }

    /**
     * This method sets the maximum pointer for the left Z set.
     * 
     * @param pointer is the pointer we want to set.
     */
    public void setLeftPointerMax(Coordinate pointer) {
	leftPointerMax = pointer;
    }

    /**
     * This method sets the maximum pointer for the right Z set.
     * 
     * @param pointer is the pointer we want to set.
     */
    public void setRightPointerMax(Coordinate pointer) {
	rightPointerMax = pointer;
    }

    /**
     * 
     * @return the minimum value of the left pointer.
     */
    public Coordinate getLeftPointerMin() {
	return leftPointerMin;
    }

    /**
     * 
     * @return the minimum value of the right pointer.
     */
    public Coordinate getRightPointerMin() {
	return rightPointerMin;
    }

    /**
     * This method sets the minimum pointer for the left Z set.
     * 
     * @param pointer is the pointer we want to set.
     */
    public void setLeftPointerMin(Coordinate pointer) {
	leftPointerMin = pointer;
    }

    /**
     * This method sets the minimum pointer for the right Z set.
     * 
     * @param pointer is the pointer we want to set.
     */
    public void setRightPointerMin(Coordinate pointer) {
	rightPointerMin = pointer;
    }

    /**
     * 
     * @return the index of the z coordinate.
     */
    public int getIndex() {
	return index;
    }

    /**
     * sets the Z value to desired index.
     * 
     * @param index is the index we want to set the Z value.
     */
    public void setIndex(int index) {
	this.index = index;
    }

}
