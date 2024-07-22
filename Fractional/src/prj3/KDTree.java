/**
 * 
 */
package prj3;

/**
 * KD Tree class creates a KD Tree with (x,y,z) coordinates where the main
 * Structure is based off of the X and Y and the associated structure is based
 * off of the Z the Z structure uses fractional cascading to improve the search
 * time
 * 
 * @author Keenan Clemmitt and Andrew Hill
 * @version 4.22.23
 * 
 */
public class KDTree {
    private Node tree;

    /**
     * KDTree constructor takes in sorted set of points in terms of x then y and
     * then z.
     * 
     * @param x points sorted in terms of x
     * @param y points sorted in terms of y
     * @param z points sorted in terms of z
     */
    public KDTree(Coordinate[] x, Coordinate[] y, ZCoordinate[] z) {
	tree = BuildKDTree(x, y, z, 0); // calls the helper function starting at depth zero
    }

    /**
     * helper function of the constructor of the KD Tree method takes in a value for
     * the depth of the tree
     * 
     * @param x     points sorted in terms of x
     * @param y     points sorted in terms of y
     * @param z     points sorted in terms of z
     * @param depth the current depth of the tree
     * @return a new KDTree
     */
    private Node BuildKDTree(Coordinate[] x, Coordinate[] y, ZCoordinate[] z, int depth) {
	int mid;
	Coordinate[] xLeft; // establish splits for the points
	Coordinate[] xRight;
	Coordinate[] yLeft;
	Coordinate[] yRight;
	ZCoordinate[] zLeft;
	ZCoordinate[] zRight;
	int yMin = y[0].getY(); // build the region for the current node
	int yMax = y[y.length - 1].getY();
	int xMin = x[0].getX();
	int xMax = x[x.length - 1].getX();
	if (x.length == 1) { // when length of points in array is 1 then one point is left and becomes a leaf
	    return new Node(x[0]);
	} else if (depth % 2 == 0) { // more than one point left and depth is even so split will be off of x
	    mid = x[x.length / 2].getX(); // gets the mid x value
	    xLeft = splitLeftDominant(x); // splits the x values to xLeft and xRight
	    xRight = splitRightDominant(x);
	    yLeft = new Coordinate[xLeft.length];
	    yRight = new Coordinate[xRight.length];
	    zLeft = new ZCoordinate[xLeft.length];
	    zRight = new ZCoordinate[xRight.length];
	    int j = 0;
	    int k = 0;
	    int a = 0;
	    int b = 0;
	    if (x.length > 2) { // if there are more than two points left
		for (int i = 0; i < y.length; i++) { // loop through all points in y and z
		    if (y[i].getX() <= mid) { // point in y has a x value less than or equal to the mid so add to yLeft
			yLeft[j] = y[i];
			j++;
		    } else { // point has an X value greater than the mid x value so add to yRight
			yRight[k] = y[i];
			k++;
		    }
		    if (z[i].getX() <= mid) { // point in z has a value of X <= the mid x value so add to zLeft
			zLeft[a] = new ZCoordinate(z[i].getX(), z[i].getY(), z[i].getZ(), a);
			a++;
		    } else { // point in z has a x value > the mid x value so add to zRight
			zRight[b] = new ZCoordinate(z[i].getX(), z[i].getY(), z[i].getZ(), b);
			b++;
		    }
		}
	    } else { // there is only 2 points left so split points up from each other
		yLeft[0] = xLeft[0];
		yRight[0] = xRight[0];
		zLeft[0] = new ZCoordinate(xLeft[0].getX(), xLeft[0].getY(), xLeft[0].getZ(), 0);
		zRight[0] = new ZCoordinate(xRight[0].getX(), xRight[0].getY(), xRight[0].getZ(), 0);
	    }
	} else { // depth is odd so split based on y
	    mid = y[y.length / 2].getY(); // calculates the mid y value
	    yLeft = splitLeftDominant(y); // splits the y set
	    yRight = splitRightDominant(y);
	    xLeft = new Coordinate[yLeft.length];
	    xRight = new Coordinate[yRight.length];
	    zLeft = new ZCoordinate[yLeft.length];
	    zRight = new ZCoordinate[yRight.length];
	    int j = 0;
	    int k = 0;
	    int a = 0;
	    int b = 0;
	    if (y.length > 2) { // when there are more than two points left
		for (int i = 0; i < x.length; i++) { // loop through all values in z and x
		    if (x[i].getY() <= mid) { // point in x has a y value <= mid y value add left
			xLeft[j] = x[i];
			j++;
		    } else { // point in x has a y value > mid y value so add right
			xRight[k] = x[i];
			k++;
		    }
		    if (z[i].getY() <= mid) { // point in z has a y value <= mid y so add left
			zLeft[a] = new ZCoordinate(z[i].getX(), z[i].getY(), z[i].getZ(), a);
			a++;
		    } else { // point in z has a y value > mid y so add right
			zRight[b] = new ZCoordinate(z[i].getX(), z[i].getY(), z[i].getZ(), b);
			b++;
		    }

		}
	    } else { // there are only two points left so must be split up
		xLeft[0] = yLeft[0];
		xRight[0] = yRight[0];
		zLeft[0] = new ZCoordinate(yLeft[0].getX(), yLeft[0].getY(), yLeft[0].getZ(), 0);
		zRight[0] = new ZCoordinate(yRight[0].getX(), yRight[0].getY(), yRight[0].getZ(), 0);
	    }
	}
	Node left = BuildKDTree(xLeft, yLeft, zLeft, depth + 1); // builds and returns KDTree left
	Node right = BuildKDTree(xRight, yRight, zRight, depth + 1); // builds and returns KDTree right
	buildZ(z, left, right); // sets the pointers for associated structure
	return new Node(mid, z, left, right, new Region(xMin, xMax, yMin, yMax)); // creates a node that holds the left
										  // and right subtrees
    }

    /**
     * splits the given array into a sub array left of the mid
     * 
     * @param cord the array being split
     * @return all points left or equal to the mid in the sorted array
     */
    private Coordinate[] splitLeftDominant(Coordinate[] cord) {
	Coordinate result[];
	if (cord.length > 2) { // there are more than two points
	    result = new Coordinate[(cord.length / 2) + 1];
	    for (int i = 0; i < result.length; i++) {
		result[i] = cord[i];
	    }
	} else { // only two coordinates so left coordinate is only value in the result
	    result = new Coordinate[1];
	    result[0] = cord[0];
	}
	return result;
    }

    /**
     * splits the given array with all values greater than the mid value
     * 
     * @param cord the array being passed in and to be split
     * @return the right set of values greater than the mid value
     */
    private Coordinate[] splitRightDominant(Coordinate[] cord) {
	Coordinate[] result;
	if (cord.length % 2 == 0) { // checks for evenness or oddness to determine split
	    result = new Coordinate[(cord.length / 2) - 1];
	} else { // odd array
	    result = new Coordinate[cord.length / 2];
	}
	if (cord.length > 2) { // more than two nodes left
	    for (int i = 0; i < (cord.length - 1 - (cord.length / 2)); i++) {
		result[i] = cord[(cord.length / 2) + i + 1];
	    }
	} else { // only two nodes left right most node is the only addition to result
	    result = new Coordinate[1];
	    result[0] = cord[1];
	}
	return result;
    }

    /**
     * sets the pointers of the associated z structure to allow for fractional
     * cascading and improved query time
     * 
     * @param z     the associated structure (sorted set of coordinates in terms of
     *              z)
     * @param left  the left subtree
     * @param right the right subtree
     */
    private void buildZ(ZCoordinate[] z, Node left, Node right) {

	for (int i = 0; i < z.length; i++) {
	    ZCoordinate c = z[i];
	    ZCoordinate[] leftSet = left.getZSet();
	    ZCoordinate[] rightSet = right.getZSet();
	    boolean l = false;
	    boolean r = false;
	    if (left.isSplit() && right.isSplit()) { // when node being evaluated children are both split nodes
		for (int j = 0; j < leftSet.length; j++) { // finds minimum on left and max on left
		    if (leftSet[j].getZ() >= c.getZ()) {
			c.setLeftPointerMin(leftSet[j]);
			break;
		    }
		}
		for (int j = leftSet.length - 1; j >= 0; j--) {
		    if (leftSet[j].getZ() <= c.getZ()) {
			c.setLeftPointerMax(leftSet[j]);
			break;
		    }
		}
		for (int j = 0; j < rightSet.length; j++) {
		    if (rightSet[j].getZ() >= c.getZ()) {
			c.setRightPointerMin(rightSet[j]);
			break;
		    }
		}
		for (int j = rightSet.length - 1; j >= 0; j--) {
		    if (rightSet[j].getZ() <= c.getZ()) {
			c.setRightPointerMax(rightSet[j]);
			break;
		    }
		}
	    } else if (right.isSplit()) { // only right child is a split node
		for (int j = 0; j < rightSet.length; j++) {
		    if (rightSet[j].getZ() >= c.getZ()) {
			c.setRightPointerMin(rightSet[j]);
			break;
		    }
		}
		for (int j = rightSet.length - 1; j >= 0; j--) {
		    if (rightSet[j].getZ() <= c.getZ()) {
			c.setRightPointerMax(rightSet[j]);
			break;
		    }
		}
	    } else if (left.isSplit()) { // only left child is a split node
		for (int j = 0; j < leftSet.length; j++) {
		    if (leftSet[j].getZ() >= c.getZ()) {
			c.setLeftPointerMin(leftSet[j]);
			break;
		    }
		}
		for (int j = leftSet.length - 1; j >= 0; j--) {
		    if (leftSet[j].getZ() <= c.getZ()) {
			c.setLeftPointerMax(leftSet[j]);
			break;
		    }
		}
	    }
	}

    }

    /**
     * gets the root node of the tree
     * 
     * @return root of the tree which will be the KD tree
     */
    public Node getRoot() {
	return tree;
    }

    /**
     * Searches the KD Tree for the number of nodes in between the range given
     * 
     * @param root       top of the tree or subtree
     * @param xMin       the minimum x value a point can have
     * @param xMax       the max x value a point can have
     * @param yMin       the minimum y value a point can have
     * @param yMax       the max y value a point can have
     * @param zMin       the minimum z value a point can have
     * @param zMax       the maximum z value a point can have
     * @param pointerMin pointer to the min z value in a set
     * @param pointerMax pointer to the max z value in a set
     * @return the number of points in the given range
     */
    public int searchKD(Node root, int xMin, int xMax, int yMin, int yMax, int zMin, int zMax, Coordinate pointerMin,
	    Coordinate pointerMax) {
	if (!root.isSplit()) { // leaf node must check point
	    Coordinate c = root.getCoordinate();
	    if (c.getX() >= xMin && c.getX() <= xMax && c.getY() >= yMin && c.getY() <= yMax && c.getZ() <= zMax
		    && c.getZ() >= zMin) { // point is valid
		return 1;

	    } else { // point is invalid
		return 0;
	    }
	} else { // the node is a split node
	    int reg = root.getRegion().inRegion(new Region(xMin, xMax, yMin, yMax)); // checks if node is black , grey
										     // or white
	    if (reg == 2) { // node is black
		if (pointerMin != null && pointerMax != null) { // if both points in z structure exist then all z values
								// in between them is the number of valid points
		    return (1 + ((ZCoordinate) pointerMax).getIndex() - ((ZCoordinate) pointerMin).getIndex());
		} else { // no z values in range
		    return 0;
		}
	    } else if (reg == 1) { // the node is grey
		if (pointerMin != null && pointerMax != null) { // returns left and right subtrees
		    return searchKD(root.getLeft(), xMin, xMax, yMin, yMax, zMin, zMax,
			    ((ZCoordinate) pointerMin).getLeftPointerMin(),
			    ((ZCoordinate) pointerMax).getLeftPointerMax())
			    + searchKD(root.getRight(), xMin, xMax, yMin, yMax, zMin, zMax,
				    ((ZCoordinate) pointerMin).getRightPointerMin(),
				    ((ZCoordinate) pointerMax).getRightPointerMax());
		} else { // null pointers encountered white subtree
		    return 0;
		}
	    } else { // white node
		return 0;
	    }
	}
    }
}
