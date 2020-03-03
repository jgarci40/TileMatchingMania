package edu.uci.Inf122.TileMatchingMania.Factory;

import java.awt.*;

public class TileFactory implements Factory<TileFactory>{

    private int width;
    private int height;
    private Font font;
    private String text;

    public static final String DEFAULT_FONT_FAMILY = "Serif";
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_FONT_SIZE = 256;
    public static final int DEFAULT_WIDTH = 256;
    public static final int DEFAULT_HEIGHT = 256;
    public static final String DEFAULT_TEXT = "";

    private TileFactory TileFactory(){
        return new TileFactory();
    }

    @Override
    public TileFactory create() {
        return TileFactory();
    }
}
