package edu.uci.Inf122.TileMatchingMania.Factory;

import java.awt.*;

public abstract class TextSquareFactory implements Factory<TextSquareFactory>{

    private int width = DEFAULT_WIDTH;
    private int height = DEFAULT_HEIGHT;
    private Font font = new Font(DEFAULT_FONT_FAMILY, DEFAULT_FONT_STYLE, DEFAULT_FONT_SIZE);
    private String text = DEFAULT_TEXT;

    public static final String DEFAULT_FONT_FAMILY = "Serif";
    public static final int DEFAULT_FONT_STYLE = Font.PLAIN;
    public static final int DEFAULT_FONT_SIZE = 256;
    public static final int DEFAULT_WIDTH = 256;
    public static final int DEFAULT_HEIGHT = 256;
    public static final String DEFAULT_TEXT = "";


    public void TextSquareFactory(String fontFamily, int fontStyle, int fontSize, int width, int height, String text) {
        this.font = new Font(fontFamily, fontStyle, fontSize);
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void TextSquareFactory(Font font, int width, int height, String text) {
        this.font = font;
        this.width = width;
        this.height = height;
        this.text = text;
    }

    public void TextSquareFactory(TextSquareFactory otherTextSquareFactory){
        this.width = otherTextSquareFactory.width;
        this.height = otherTextSquareFactory.height;
        this.text = otherTextSquareFactory.text;
        this.font = otherTextSquareFactory.font;
    }



    public TextSquareFactory TextSquareFactory(){
        return this;
    }

    @Override
    public TextSquareFactory create() {
        return TextSquareFactory();
    }

    public abstract Image draw();

}
