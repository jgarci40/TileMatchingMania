package edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.ConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.GUI.Drawable.RGBSquare.*;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.State.BlackState;
import edu.uci.Inf122.TileMatchingMania.Games.Tests.TestGame03_MasonicV2.src.State.WhiteState;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.HashMap;
import java.util.Map;

public class MasonicConverterGenerator extends ConverterGenerator {
    @Override
    public Map<String, StateToDrawableConverter> generate(Map<String, StateCollection> collections) throws Exception {
        Map<String, StateToDrawableConverter> converters = new HashMap<>();
        for(String key : collections.keySet()) {
            StateCollection states = collections.get(key);
            StateToDrawableConverter converter = new StateToDrawableConverter(states);
            converter.addDrawable(new BlackState(), new BlackSquare());
            converter.addDrawable(new WhiteState(), new WhiteSquare());
            converters.put(key, converter);
        }
        return converters;
    }
}
