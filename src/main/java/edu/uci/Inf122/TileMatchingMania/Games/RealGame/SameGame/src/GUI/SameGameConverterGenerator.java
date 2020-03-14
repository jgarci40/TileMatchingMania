package edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.ConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlackSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.BlueSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.GreenSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.RedSquare;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.BlueState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.EmptyState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.GreenState;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.SameGame.src.State.RedState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.HashMap;
import java.util.Map;

public class SameGameConverterGenerator extends ConverterGenerator {
    @Override
    public Map<String, StateToDrawableConverter> generate(Map<String, StateCollection> collections) throws Exception {
        Map<String, StateToDrawableConverter> converters = new HashMap<>();

        for(String key : collections.keySet()) {
            StateCollection states = collections.get(key);
            StateToDrawableConverter converter = new StateToDrawableConverter(states);
            converter.addDrawable(new EmptyState(), new BlackSquare());
            converter.addDrawable(new GreenState(), new GreenSquare());
            converter.addDrawable(new RedState(), new RedSquare());
            converter.addDrawable(new BlueState(), new BlueSquare());
            converters.put(key, converter);
        }
        return converters;
    }
}
