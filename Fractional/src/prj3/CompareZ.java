/**
 * 
 */
package prj3;

import java.util.Comparator;

/**
 * Compares the Z values of two coordinates to allow for sorting
 * 
 * @author Keenan Clemmitt and Andrew Hill
 * @version 04.22.23
 *
 */
public class CompareZ implements Comparator<Coordinate> {

    /**
     * This is the compare method for the Z coordinates. If o1 is greater than o2 we
     * return 1, if it is less than o2 we return -1, and if it is equal we return 0.
     * 
     * @return 1 if greater -1 if less and 0 if even
     */
    @Override
    public int compare(Coordinate o1, Coordinate o2) {
	if (o1.getZ() > o2.getZ()) {
	    return 1;
	} else if (o1.getZ() < o2.getZ()) {
	    return -1;
	} else {
	    return 0;
	}
    }

}
