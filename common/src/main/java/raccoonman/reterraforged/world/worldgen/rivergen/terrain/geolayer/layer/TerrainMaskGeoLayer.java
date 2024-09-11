package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.math.ConcurrentGridMap;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;

import java.util.HashMap;
import java.util.Map;

public class TerrainMaskGeoLayer extends AbstractGeoLayer {

    private final ConcurrentGridMap<GraphGeoChunk> layerChunks;

    public TerrainMaskGeoLayer(AbstractGeoLayer dependencyLayer) {
        super(dependencyLayer);
        layerChunks = new ConcurrentGridMap<GraphGeoChunk>();
    }

    public GraphGeoChunk getOrComputeChunk(Int2D chunkPos) {
        //TODO: Implement actual compute
        return layerChunks.get(chunkPos);
    }

    public void addChunk(Int2D chunkPos, WeightedGraph graphData) {
        //TODO: Fix
        //layerChunks.put(chunkPos, new GraphGeoChunk(chunkPos, graphData, this));
    }

    public void delChunk(Int2D chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(Int2D chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
