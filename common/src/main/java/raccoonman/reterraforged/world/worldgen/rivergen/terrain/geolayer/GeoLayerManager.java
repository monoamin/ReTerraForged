package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer;

import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;

import java.util.HashMap;
import java.util.Map;

public class GeoLayerManager {
    Map<AbstractGeoLayer.Types, AbstractGeoLayer> contextLayers; // Now strictly GeoLayer

    public GeoLayerManager(){
        contextLayers = new HashMap<>();
    }

    public AbstractGeoLayer addLayerIfAbsent(AbstractGeoLayer.Types type, AbstractGeoLayer layer){
        if (!contextLayers.containsKey(type)) return contextLayers.put(type, layer);
        else { return null;}
    }

    public AbstractGeoLayer getLayer(AbstractGeoLayer.Types type){
        return contextLayers.get(type); // No cast needed
    }
}
