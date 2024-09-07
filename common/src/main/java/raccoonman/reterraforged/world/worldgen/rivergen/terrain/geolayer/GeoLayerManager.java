package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer;

import java.util.HashMap;
import java.util.Map;

public class GeoLayerManager {
    Map<GeoLayer.Types, GeoLayer> contextLayers; // Now strictly GeoLayer

    public GeoLayerManager(){
        contextLayers = new HashMap<>();
    }

    public void addLayerIfAbsent(GeoLayer.Types type, GeoLayer layer){
        if (!contextLayers.containsKey(type)) contextLayers.put(type, layer);
    }

    public GeoLayer getLayer(GeoLayer.Types type){
        return contextLayers.get(type); // No cast needed
    }
}
