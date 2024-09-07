package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.util.Tuple;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.phys.Vec2;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphChunkMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GraphGeoLayer extends GeoLayer {

    private final Map<ChunkPos, GraphChunkMap> layerChunks;

    public GraphGeoLayer() {
        layerChunks = new HashMap<ChunkPos, GraphChunkMap>();
    }

    public GraphChunkMap getOrComputeChunk(ChunkPos chunkPos) {
        //TODO: Implement actual compute
        return layerChunks.get(chunkPos);
    }

    public void addChunk(ChunkPos chunkPos, WeightedGraph graphData) {
        layerChunks.get(chunkPos).add(graphData, chunkPos);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.get(chunkPos).remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }

    public Object getValueAbsolute(Vec2 coord) {

        Tuple<ChunkPos, Vec2> pos = TerrainUtils.getPosRelative(coord);
        ChunkPos cPos = pos.getA();
        Vec2 bPos = pos.getB();

        GraphChunkMap item = getOrComputeChunk(cPos);
        WeightedGraph graph = item.get(cPos);
        double nodeWeight = graph.getNode(bPos).getWeight();

        return 1;
    }
}
