package prj3;

import java.util.Arrays;

/**
 * The Range Search data structure
 * 
 * @author Keenan Clemmitt and Andrew Hill
 *
 */
public class RangeSearch {
    private Coordinate[] x;
    private Coordinate[] y;
    private ZCoordinate[] z;
    KDTree tree;

    /**
     * The constructor of the class
     * 
     * @param points a 2 dimensional array, each row has the format [x,y,z], so the
     *               i-th point has x coordinate points[i-1][0], y coordinate
     *               points[i-1][1], and z coordinate points[i-1][2]
     */
    public RangeSearch(int[][] points) {
	x = new Coordinate[points.length];
	y = new Coordinate[points.length];
	z = new ZCoordinate[points.length];
	for (int i = 0; i < points.length; i++) { // establish 3 sets
	    x[i] = new Coordinate(points[i][0], points[i][1], points[i][2]);
	    y[i] = new Coordinate(points[i][0], points[i][1], points[i][2]);
	    z[i] = new ZCoordinate(points[i][0], points[i][1], points[i][2], i);
	}
	Arrays.sort(x, new CompareX()); // sorts the sets
	Arrays.sort(y, new CompareY());
	Arrays.sort(z, new CompareZ());
	for (int i = 0; i < z.length; i++) {
	    z[i].setIndex(i);
	}
	tree = new KDTree(x, y, z);
    }

    /**
     * Given the description of a rectangular prism in the 3 dimensional space, this
     * method returns the number points that lie inside or on the boundary of the
     * query range
     * 
     * @param xMin min x value
     * @param xMax max x value
     * @param yMin min y value
     * @param yMax max y value
     * @param zMin min z value
     * @param zMax max z value
     * 
     * @return the number of points in between the given range
     */
    public int query(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax) {
	ZCoordinate min = null;
	ZCoordinate max = null;
	boolean foundMin = false;
	boolean foundMax = false;
	ZCoordinate[] data = tree.getRoot().getZSet();
	int locationMax = BinarySearchMax(data, zMax, 0, data.length - 1);
	if (locationMax != -1) {
	    max = data[locationMax];

	}
	int locationMin = BinarySearchMin(data, zMin, 0, data.length - 1);
	if (locationMin != -1) {
	    min = data[locationMin];
	}
	int value = tree.searchKD(tree.getRoot(), xMin, xMax, yMin, yMax, zMin, zMax, min, max);
	return value;
    }

    /**
     * This method binary searches the original z associated structure to find the
     * point that is the greatest least than or equal to value of the target zMax
     * value
     * 
     * @param data   the root nodes set of ZCoordinates sorted
     * @param target the max z value we are searching for
     * @param min    the current min index value in the recursion
     * @param max    the current max index value in the recursion
     * @return the index value of the value that is the least greater than or equal
     *         to the target max z value or -1 if the value does not exist
     */
    private int BinarySearchMax(ZCoordinate[] data, int target, int min, int max) {
	int mid = min + ((max - min) / 2);
	if (min > max) { // value not present
	    return -1;
	}
	if (data[mid].getZ() == target) { // target value found
	    return mid;
	} else if (data[mid].getZ() > target) { // current value is above the target
	    return BinarySearchMax(data, target, min, mid - 1);
	} else { // moves to the right side of the mid for the search
	    int recur = BinarySearchMax(data, target, mid + 1, max); // current value is below the target but must check
								     // other values
	    return Math.max(recur, mid);
	}
    }

    /**
     * this function peforms a binary search to find the value that is the minimum
     * value less than or equal to the min z value given
     * 
     * @param data   sorted set of ZCoordinates on the root node
     * @param target the min z value being searched for
     * @param min    current min index of recursion
     * @param max    current max index of recursion
     * @return the index of the minimum value greater than or equal to the min z
     *         value
     */
    private int BinarySearchMin(ZCoordinate[] data, int target, int min, int max) {
	int mid = min + ((max - min) / 2);
	if (min > max) { // value not present
	    return -1;
	}
	if (data[mid].getZ() == target) { // target value is present
	    return mid;
	} else if (data[mid].getZ() > target) { // valid number found but others must be checked to find the minimum
						// greater than or equal to
	    int recur = BinarySearchMin(data, target, min, mid - 1);
	    if (recur == -1) { // value found is not valid
		return mid;
	    } else { // other valid values found returns the minimum
		return Math.min(recur, mid);
	    }
	} else { // moves to the right side of the mid
	    return BinarySearchMin(data, target, mid + 1, max);
	}
    }
}
