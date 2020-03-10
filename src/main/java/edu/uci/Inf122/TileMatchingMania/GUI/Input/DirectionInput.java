package edu.uci.Inf122.TileMatchingMania.GUI.Input;

public class DirectionInput extends Input {
    public enum Direction {UP, DOWN, LEFT, RIGHT, INVALID}
    private Direction direction;

    public DirectionInput(Direction d) {
        this.direction = d;
    }

    public Direction getDirection() { return direction; }
}
