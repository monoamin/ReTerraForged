package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer;

import net.minecraft.util.Tuple;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;

import java.util.Map;

public abstract class GeoLayer {
    public enum Types {
        ELEVATION,
        CONNECTION_GRAPH
    }

    private Map<ChunkPos, Object> layerChunks;

    // Constructor to initialize layerObject
    public GeoLayer() {

    }

    public Object getOrComputeChunk(ChunkPos chunkPos) {
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