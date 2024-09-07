package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.util.Tuple;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GeoChunkGraph;

import java.util.HashMap;
import java.util.Map;

public class GraphGeoLayer extends GeoLayer {

    private final Map<ChunkPos, GeoChunkGraph> layerChunks;

    public GraphGeoLayer() {
        layerChunks = new HashMap<ChunkPos, GeoChunkGraph>();
    }

    public GeoChunkGraph getOrComputeChunk(ChunkPos chunkPos) {
        //TODO: Implement actual compute
        return layerChunks.get(chunkPos);
    }

    public void addChunk(ChunkPos chunkPos, WeightedGraph graphData) {
        layerChunks.put(chunkPos, new GeoChunkGraph(chunkPos, graphData));
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
