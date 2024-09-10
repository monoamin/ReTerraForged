package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.ElevationGeoLayer;

import java.util.HashMap;
import java.util.Map;

public class GeoLayerManager {
    Map<AbstractGeoLayer.Types, AbstractGeoLayer> contextLayers; // Now strictly GeoLayer
    public GeneratorContext generatorContext;

    public GeoLayerManager(GeneratorContext generatorContext){
        contextLayers = new HashMap<>();
        this.generatorContext = generatorContext;
    }

    public AbstractGeoLayer addLayerIfAbsent(AbstractGeoLayer.Types type, AbstractGeoLayer layer){
        if (!contextLayers.containsKey(type)) return contextLayers.put(type, layer);
        else { return null;}
    }

    public AbstractGeoLayer getLayer(AbstractGeoLayer.Types type){
        return contextLayers.get(type); // No cast needed
    }

    public Tile getBaseTile(ChunkPos chunkPos) {
        ElevationGeoLayer elevationGeoLayer = (ElevationGeoLayer)getLayer(AbstractGeoLayer.Types.ELEVATION);
        return (Tile) elevationGeoLayer.getOrComputeChunk(chunkPos, this.generatorContext);
    }
}
