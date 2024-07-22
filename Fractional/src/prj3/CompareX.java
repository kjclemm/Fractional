/**
 * 
 */
package prj3;

import java.util.Comparator;

/**
 * Compares the x values of two coordinates to allow for sorting
 * 
 * @author Keenan Clemmitt and Andrew Hill
 * @version 04.22.23
 */
public class CompareX implements Comparator<Coordinate> {

    /**
     * This is the compare method for the x coordinates. If o1 is greater than o2 we
     * return 1, if it is less than o2 we return -1, and if it is equal we return 0.
     * 
     * @return 1 if greater -1 if less an 0 if equal
     */
    @Override
    public int compare(Coordinate o1, Coordinate o2) {
	if (o1.getX() > o2.getX()) {
	    return 1;
	} else if (o1.getX() < o2.getX()) {
	    return -1;
	} else {
	    return 0;
	}
    }

}