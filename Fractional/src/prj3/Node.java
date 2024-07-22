/**
 * 
 */
package prj3;

/**
 * Node class to represent a node in the KD tree can be a split node or a leaf
 * node
 * 
 * @author Keenan Cleemmitt and Andrew Hill
 * @version 04.22.23
 */
public class Node {
    private boolean isSplit; // Determines if this node is a split node.
    private ZCoordinate zSet[]; // This is the set of Z coordinates.
    private Coordinate coordinate; // This is the current coordinate.
    private Node left; // Left child node.
    private Node right; // Right child node.
    Region reg; // This is the region the node is in.

    /**
     * Constructor for when the node is a split node
     * 
     * @param mid   is the middle value of the Z coordinates.
     * @param zSet  is the set of Z coordinates.
     * @param left  is the left child node.
     * @param right is the right child node.
     * @param reg   is the region this node is in.
     */
    public Node(int mid, ZCoordinate[] zSet, Node left, Node right, Region reg) {
	isSplit = true;
	this.zSet = zSet;
	coordinate = null;
	this.left = left;
	this.right = right;
	this.reg = reg;
    }

    /**
     * This is the constructor for when the node is not a split node
     * 
     * @param cord is the coordinate of the leaf node.
     */
    public Node(Coordinate cord) {
	isSplit = false;
	coordinate = cord;
	zSet = null;
	left = null;
	right = null;
	this.reg = null;
    }

    /**
     * gets data on weather node is split or not
     * 
     * @return true if a split node or false if not.
     */
    public boolean isSplit() {
	return isSplit;
    }

    /**
     * gets the z structure with a node
     * 
     * @return the set of Z coordinates.
     */
    public ZCoordinate[] getZSet() {
	return zSet;
    }

    /**
     * gets the coordinate
     * 
     * @return the coordinate.
     */
    public Coordinate getCoordinate() {
	return coordinate;
    }

    /**
     * gets the left node
     * 
     * @return the left child node.
     */
    public Node getLeft() {
	return left;
    }

    /**
     * gets the right node
     * 
     * @return the right child node.
     */
    public Node getRight() {
	return right;
    }

    /**
     * gets the region
     * 
     * @return the region.
     */
    public Region getRegion() {
	return reg;
    }

}
