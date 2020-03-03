package edu.uci.Inf122.TileMatchingMania.Factory;

import java.awt.*;

public abstract class RGBSquareFactory implements Factory<RGBSquareFactory>{

    private int r;
    private int b;
    private int g;
    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;

    public static final int DEFAULT_WIDTH = 256;
    public static final int DEFAULT_HEIGHT = 256;
    public static final int DEFAULT_R = 0;
    public static final int DEFAULT_G = 0;
    public static final int DEFAULT_B = 0;


    public RGBSquareFactory(int r, int g, int b, int width, int height) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.width = width;
        this.height = height;
    }

    public RGBSquareFactory (RGBSquareFactory otherRGBSquareFactory){
        this.r = otherRGBSquareFactory.r;
        this.b = otherRGBSquareFactory.b;
        this.g = otherRGBSquareFactory.g;
        this.width = otherRGBSquareFactory.width;
        this.height = otherRGBSquareFactory.height;
    }

    private RGBSquareFactory RGBSquareFactory(){
        return this;
    }

    @Override
    public RGBSquareFactory create() {
        return RGBSquareFactory();
    }

    public abstract Image draw();
}
