package edu.uci.Inf122.TileMatchingMania.GUI.Input;

public class KeyInput extends Input {
    private Direction direction;

    public KeyInput(Direction d) {
        this.direction = d;
    }

    public Direction getDirection() { return direction; }
}
