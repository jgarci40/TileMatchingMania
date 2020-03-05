package edu.uci.Inf122.TileMatchingMania.GameGrid;

/*
 * This class contains a 4-way connection for each cell in a grid. 
 * For example:
 * 	-------------
 * 	| A | B | C |
 * 	-------------
 * 	| D | E | F |
 * 	-------------
 * 	| G | H | I |
 * 	-------------
 * 
 *  In the grid above, E's 4-way connection is:
 *   left = D
 *  right = F
 *     up = B
 *   down = H
 */
public abstract class QuadNode {
    private QuadNode left = null;
    private QuadNode right = null;
    private QuadNode up = null;
    private QuadNode down = null;

    /*
     * Check if left neighboring cell exists.
     * 
     * @return boolean True if a left neighboring cell exists, false otherwise.
     */
    public boolean hasLeft() {
        return left != null;
    }

    /*
     * Check if right neighboring cell exists.
     * 
     * @return boolean True if a right neighboring cell exists, false otherwise.
     */
    public boolean hasRight() {
        return right != null;
    }

    /*
     * Check if up neighboring cell exists.
     * 
     * @return boolean True if a up neighboring cell exists, false otherwise.
     */
    public boolean hasUp() {
        return up != null;
    }

    /*
     * Check if down neighboring cell exists.
     * 
     * @return boolean True if a down neighboring cell exists, false otherwise.
     */
    public boolean hasDown() {
        return down != null;
    }
    
    /*
     * Set the left neighboring cell.
     */
    public void setLeft(QuadNode qn) {
        left = qn;
    }

    /*
     * Set the right neighboring cell.
     */
    public void setRight(QuadNode qn) {
        right = qn;
    }

    /*
     * Set the up neighboring cell.
     */
    public void setUp(QuadNode qn) {
        up = qn;
    }

    /*
     * Set the down neighboring cell.
     */
    public void setDown(QuadNode qn) {
        down = qn;
    }

    /*
     * SeGett the left neighboring cell.
     */
    public QuadNode getLeft() {
        return left;
    }

    /*
     * Get the right neighboring cell.
     */
    public QuadNode getRight() {
        return right;
    }
    
    /*
     * Get the up neighboring cell.
     */
    public QuadNode getUp() {
        return up;
    }

    /*
     * Get the down neighboring cell.
     */
    public QuadNode getDown() {
        return down;
    }
}
