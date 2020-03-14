package edu.uci.Inf122.TileMatchingMania.GUI;

import edu.uci.Inf122.TileMatchingMania.State.StateCollection;

import java.util.Map;

public abstract class ConverterGenerator {
    public abstract Map<String, StateToDrawableConverter> generate(Map<String, StateCollection> collections) throws Exception;
}
