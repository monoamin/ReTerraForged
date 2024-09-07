package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationChunkMap;

import java.util.HashMap;
import java.util.Map;

public class ElevationGeoLayer extends GeoLayer {

    private final Map<ChunkPos, ElevationChunkMap> layerChunks;

    public ElevationGeoLayer() {
        layerChunks = new HashMap<>();
    }

    // TODO: Fix
    public int[][] getOrComputeChunk(ChunkPos chunkPos) {
        return null;
    }

    // TODO: Fix
    public void addChunk(ChunkPos chunkPos, int[][] heightData) {

    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
