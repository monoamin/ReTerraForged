package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;

import java.util.HashMap;
import java.util.Map;

public class PrunedGraphGeoLayer extends GeoLayer {

    private final Map<ChunkPos, GraphGeoChunk> layerChunks;

    public PrunedGraphGeoLayer() {
        layerChunks = new HashMap<ChunkPos, GraphGeoChunk>();
    }

    public GraphGeoChunk getOrComputeChunk(ChunkPos chunkPos) {
        //TODO: Implement actual compute
        return layerChunks.get(chunkPos);
    }

    public void addChunk(ChunkPos chunkPos, WeightedGraph graphData) {
        //TODO: Fix
        //layerChunks.put(chunkPos, new GraphGeoChunk(chunkPos, graphData, this);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
