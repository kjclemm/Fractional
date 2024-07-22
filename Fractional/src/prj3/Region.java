/**
 * 
 */
package prj3;

/**
 * Builds the associated region with a given node in the KD tree
 * 
 * @author Keenan Clemmitt and Andrew Hill
 * @version 04.22.23
 */
public class Region {
    // stores max x value of box
    int xMax;
    // stores minimum x value of box
    int xMin;
    // stores max y value of box
    int yMax;
    // stores minimum y value of box
    int yMin;

    /**
     * Constructor for the region object
     * 
     * @param xMin minimum x value of the box
     * @param xMax max x value of the box
     * @param yMin minimum y value of the box
     * @param yMax max y value of the box
     */
    public Region(int xMin, int xMax, int yMin, int yMax) {
	this.xMax = xMax;
	this.xMin = xMin;
	this.yMax = yMax;
	this.yMin = yMin;
    }

    /**
     * checks if the calling region is inside the region in the parameters
     * 
     * @param target the region we are checking that we want to be in
     * @return 0 if not in region 1 if partially in region and 2 if fully in region
     */
    public int inRegion(Region target) {
	if (xMin >= target.xMin && xMax <= target.xMax && yMin >= target.yMin && yMax <= target.yMax) { // black node
	    return 2;
	} else if ((xMin >= target.xMin || yMin >= target.yMin) || (xMax <= target.xMax || yMax <= target.yMax)) { // grey
														   // node
	    return 1;
	} else if (target.xMin <= xMin || target.xMax <= xMax || target.yMin >= yMin || target.yMax <= yMax) { // grey
													       // node
	    return 1;
	} else { // not contained white
	    return 0;
	}
    }
}
