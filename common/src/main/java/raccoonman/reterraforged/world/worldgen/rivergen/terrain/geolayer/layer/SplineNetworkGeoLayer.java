package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.GraphNode;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.math.spline.SplineNode;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;

import java.util.HashMap;
import java.util.Map;

public class SplineNetworkGeoLayer extends GeoLayer {

    private final Map<ChunkPos, GraphNode> layerChunks;

    public SplineNetworkGeoLayer() {
        layerChunks = new HashMap<ChunkPos, GraphNode>();
    }

    public GraphNode getOrComputeChunk(ChunkPos chunkPos) {
        //TODO: Implement actual compute
        return layerChunks.get(chunkPos);
    }

    public void addChunk(ChunkPos chunkPos, GraphNode node) {
        layerChunks.put(chunkPos, node);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
