package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.ElevationGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.OutputTileGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.PathGeoLayer;

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

    public Tile getTilePre(Int2D chunkPos) {
        ElevationGeoLayer elevationGeoLayer = (ElevationGeoLayer)getLayer(AbstractGeoLayer.Types.ELEVATION);
        return (Tile) elevationGeoLayer.getOrComputeChunk(chunkPos, this.generatorContext);
    }

    public Tile getTilePost(Int2D chunkPos) {
        OutputTileGeoLayer outputTileGeoLayer = (OutputTileGeoLayer)getLayer(AbstractGeoLayer.Types.OUTPUT_TILE);
        return (Tile) outputTileGeoLayer.getOrComputeChunk(chunkPos, this.generatorContext).getTile(chunkPos);
    }
}
