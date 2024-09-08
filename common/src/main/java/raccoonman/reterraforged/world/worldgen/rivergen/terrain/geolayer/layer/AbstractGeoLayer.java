package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.AbstractGeoChunk;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGeoLayer {
    public enum Types {
        ELEVATION,
        CHUNK_GRAPH,
        AREA_GRAPH,
        SPLINE_NET,
        TERRAIN_MASK
    }

    private Map<ChunkPos, AbstractGeoChunk> layerChunks;
    protected AbstractGeoLayer dependencyLayer;

    // Constructor to initialize layerObject
    public AbstractGeoLayer(AbstractGeoLayer dependencyLayer) {
        this.layerChunks = new HashMap<ChunkPos, AbstractGeoChunk>();
        this.dependencyLayer = dependencyLayer;
    }

    public Object getOrComputeChunk(ChunkPos chunkPos, GeneratorContext context) {
        return layerChunks.get(chunkPos);
    }

    public AbstractGeoChunk addChunk(ChunkPos chunkPos, AbstractGeoChunk data) {
        return layerChunks.put(chunkPos, data);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}