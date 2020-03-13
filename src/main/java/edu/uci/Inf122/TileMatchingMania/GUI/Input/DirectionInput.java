package edu.uci.Inf122.TileMatchingMania.GUI.Input;

/*
 * This input type controls for the direction that a player 
 * enters such as the 4 arrow keys.
 */
public class DirectionInput extends Input {
    public enum Direction {UP, DOWN, LEFT, RIGHT, INVALID}
    private Direction direction;

    /*
     * DirectionInput constructor.
     * 
     * @param d A specific direction or invalid.
     */
    public DirectionInput(Direction d) {
        this.direction = d;
    }

    /*
     * Get the current direction.
     * 
     * @return Direction The current direction.
     */
    public Direction getDirection() { return direction; }
}
