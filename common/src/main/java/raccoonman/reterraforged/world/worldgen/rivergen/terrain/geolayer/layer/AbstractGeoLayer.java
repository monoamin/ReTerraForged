package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import it.unimi.dsi.fastutil.Hash;
import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.ConcurrentGridMap;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.AbstractGeoChunk;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGeoLayer {
    public enum Types {
        ELEVATION,
        WEIGHTED_GRAPH,
        PATHS,
        SPLINE_NET,
        TERRAIN_MASK,
        OUTPUT_TILE
    }

    public ConcurrentGridMap<AbstractGeoChunk> layerChunks;
    protected AbstractGeoLayer dependencyLayer;

    // Constructor to initialize layerObject
    public AbstractGeoLayer(AbstractGeoLayer dependencyLayer) {
        this.layerChunks = new ConcurrentGridMap<AbstractGeoChunk>();
        this.dependencyLayer = dependencyLayer;
    }

    public Object getOrComputeChunk(Int2D chunkPos, GeneratorContext context) {
        return layerChunks.get(chunkPos);
    }

    public AbstractGeoChunk addChunk(Int2D chunkPos, AbstractGeoChunk data) {
        return layerChunks.putIfAbsent(chunkPos, data);
    }

    public void delChunk(Int2D chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(Int2D chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}