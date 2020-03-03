package edu.uci.Inf122.TileMatchingMania.GameGrid;

public abstract class QuadNode {
    private QuadNode left = null;
    private QuadNode right = null;
    private QuadNode up = null;
    private QuadNode down = null;

    public boolean hasLeft() {
        return left != null;
    }

    public boolean hasRight() {
        return right != null;
    }

    public boolean hasUp() {
        return up != null;
    }

    public boolean hasDown() {
        return down != null;
    }

    public void setLeft(QuadNode qn) {
        left = qn;
    }

    public void setRight(QuadNode qn) {
        right = qn;
    }

    public void setUp(QuadNode qn) {
        up = qn;
    }

    public void setDown(QuadNode qn) {
        down = qn;
    }

    public QuadNode getLeft() {
        return left;
    }

    public QuadNode getRight() {
        return right;
    }

    public QuadNode getUp() {
        return up;
    }

    public QuadNode getDown() {
        return down;
    }
}
