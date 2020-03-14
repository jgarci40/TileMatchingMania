package edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.GUI;

import edu.uci.Inf122.TileMatchingMania.GUI.ConverterGenerator;
import edu.uci.Inf122.TileMatchingMania.GUI.StateToDrawableConverter;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.Drawable.*;
import edu.uci.Inf122.TileMatchingMania.Games.RealGame.The2048.src.State.*;
import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.HashMap;
import java.util.Map;

public class The2048ConverterGenerator extends ConverterGenerator {
    @Override
    public Map<String, StateToDrawableConverter> generate(Map<String, StateCollection> collections) throws Exception {
        Map<String, StateToDrawableConverter> converters = new HashMap<>();
        for(String key : collections.keySet()) {
            System.out.println(key);
            StateCollection states = collections.get(key);
            StateToDrawableConverter converter = new StateToDrawableConverter(states);
            converter.addDrawable(new EmptyBlockState(), new EmptyBlock());
            converter.addDrawable(new Block2State(), new Block2());
            converter.addDrawable(new Block4State(), new Block4());
            converter.addDrawable(new Block8State(), new Block8());
            converter.addDrawable(new Block16State(), new Block16());
            converter.addDrawable(new Block32State(), new Block32());
            converter.addDrawable(new Block64State(), new Block64());
            converter.addDrawable(new Block128State(), new Block128());
            converter.addDrawable(new Block256State(), new Block256());
            converter.addDrawable(new Block512State(), new Block512());
            converter.addDrawable(new Block1024State(), new Block1024());
            converter.addDrawable(new Block2048State(), new Block2048());
            converters.put(key, converter);
        }
        return converters;
    }
}
