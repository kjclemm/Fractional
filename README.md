# Fractional
This repository contains a data structure that supports range queries over a 3 dimensional point set.

## Problem
Given a set S of points in 3 dimensional space and the description of a rectangular prism in 3 dimensions space (a range of x, y, and z values), return the number of points of S that lie inside or on the boundary of the query prisum.

## Approach 
In order to effeciently solve this problem a hybrid data structure was created. This data strcutre was KD-tree (https://www.cs.umd.edu/class/spring2021/cmsc420-0101/Lects/lect13-kd-query.pdf) merged with an assoicated range tree (https://www.cs.umd.edu/class/spring2021/cmsc420-0101/Lects/lect16-range-tree.pdf).

## What is a KD Tree 
A KD tree is a binary tree in which every node is a k-dimensional point. Each non-leaf node generates a splitting hyperplane that divides the space into two half-spaces. Points to the left of this hyperplane are represented by the left subtree of that node, and points to the right of the hyperplane are represented by the right subtree.

## What is a Range Tree: 
A range tree is a balanced binary search tree, similar to a binary search tree but with additional information. Each node in a range tree represents a point and stores a secondary tree which is a range tree of the next dimension, containing all the points of the subtree of the node.

## Implementation: 
Public RangeSearch(int[][] points) is the constructor of the the class RangeSearch where the main functionality takes place. The input parameter points is a 2-dimensinal array containing the corrdinates of the points, where each row of this array is of the form [x,y,z], determining the coordiantes of a point. So the i-th point is a point wtih x coordinate points[i-1][0], y coordinate points[i-1][1], and z coordinate points [i-1][2].<br><br>
Public int query(int xMin, int xMax, int yMin, int yMax, int zMin, int zMax): given the description of the rectangular prism, this method returns the number of points that lie inside or on the boundary of the query range. More preciely, this method returns the number of input points p=(x,y,z) such that xMin <= x <= xMax, yMin <= y <= yMax, and zMin <= z <= zMax

## Testing
