package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GeoChunkElevation;

import java.util.HashMap;
import java.util.Map;

public class ElevationGeoLayer extends GeoLayer {

    private final Map<ChunkPos, GeoChunkElevation> layerChunks;

    public ElevationGeoLayer() {
        layerChunks = new HashMap<ChunkPos, GeoChunkElevation>();
    }

    // TODO: Fix
    public int[][] getOrComputeChunk(ChunkPos chunkPos) {
        return null;
    }

    // TODO: Fix
    public void addChunk(ChunkPos chunkPos, long[][] heightData) {
        layerChunks.put(chunkPos, new GeoChunkElevation(chunkPos, heightData));
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
