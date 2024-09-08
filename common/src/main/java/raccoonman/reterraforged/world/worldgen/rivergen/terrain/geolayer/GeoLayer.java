package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer;

import net.minecraft.util.Tuple;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class GeoLayer {
    public enum Types {
        ELEVATION,
        CONNECTION_GRAPH,
        FLOW_GRAPH,
        RIVER_SPLINE,
        RIVER_MASK
    }

    private Map<ChunkPos, Object> layerChunks;

    // Constructor to initialize layerObject
    public GeoLayer() {
        layerChunks = new HashMap<ChunkPos, Object>();
    }

    public Object getOrComputeChunk(ChunkPos chunkPos, GeneratorContext context) {
        return layerChunks.get(chunkPos);
    }

    public void addChunk(ChunkPos chunkPos, Object data) {
        layerChunks.put(chunkPos, data);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}