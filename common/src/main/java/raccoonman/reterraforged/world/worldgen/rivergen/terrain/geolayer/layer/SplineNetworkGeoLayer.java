package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.math.ConcurrentGridMap;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.GraphNode;

import java.util.HashMap;
import java.util.Map;

public class SplineNetworkGeoLayer extends AbstractGeoLayer {

    private final ConcurrentGridMap<GraphNode> layerChunks;

    public SplineNetworkGeoLayer(AbstractGeoLayer dependencyLayer) {
        super(dependencyLayer);
        layerChunks = new ConcurrentGridMap<GraphNode>();
    }

    public GraphNode getOrComputeChunk(Int2D chunkPos) {
        //TODO: Implement actual compute
        return layerChunks.get(chunkPos);
    }

    public void addChunk(Int2D chunkPos, GraphNode node) {
        layerChunks.put(chunkPos, node);
    }

    public void delChunk(Int2D chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(Int2D chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
