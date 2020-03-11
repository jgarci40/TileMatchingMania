package edu.uci.Inf122.TileMatchingMania.GameGrid;

/*
 * This class will hold the information to determine if 
 * a search algorithm can proceed traversing a path. 
 * A valid path is determined by matching 
 * conditions of neighboring node(s). For example, if
 * State is identical for tiles they will be included in the path.
 */
public class ValidPaths {
    private boolean up;
    private boolean down;
    private boolean left;
    private boolean right;

    /*
     * Set whether up is a valid direction or not.
     */
    public void setUp(boolean bool) {
        up = bool;
    }

    /*
     * Get whether up is a valid direction or not.
     * 
     * @return boolean True if can go up, false otherwise.
     */
    public boolean getUp() {
        return up;
    }

    /*
     * Set whether down is a valid direction or not.
     */
    public void setDown(boolean bool) {
        down = bool;
    }

    /*
     * Get whether down is a valid direction or not.
     * 
     * @return boolean True if can go down, false otherwise.
     */
    public boolean getDown() {
        return down;
    }

    /*
     * Set whether left is a valid direction or not.
     */
    public void setLeft(boolean bool) {
        left = bool;
    }

    /*
     * Get whether left is a valid direction or not.
     * 
     * @return boolean True if can go left, false otherwise.
     */
    public boolean getLeft() {
        return left;
    }

    /*
     * Set whether right is a valid direction or not.
     */
    public void setRight(boolean bool) {
        right = bool;
    }

    /*
     * Get whether right is a valid direction or not.
     * 
     * @return boolean True if can go right, false otherwise.
     */
    public boolean getRight() {
        return right;
    }
}
