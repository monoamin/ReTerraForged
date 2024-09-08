package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;

import java.util.HashMap;
import java.util.Map;

public class GraphGeoLayer extends GeoLayer {

    private final Map<ChunkPos, GraphGeoChunk> layerChunks;

    public GraphGeoLayer() {
        layerChunks = new HashMap<ChunkPos, GraphGeoChunk>();
    }

    @Override
    public GraphGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext context) {
        GraphGeoChunk graphGeoChunk = null;
        ElevationGeoLayer elevationGeoLayer = (ElevationGeoLayer) context.geoLayerManager.getLayer(Types.ELEVATION);
        if ( layerChunks.containsKey(chunkPos) ) {return  layerChunks.get(chunkPos);}
        else {
            ElevationGeoChunk elevationGeoChunk = elevationGeoLayer.getOrComputeChunk(chunkPos, context);
            WeightedGraph graph = new WeightedGraph(elevationGeoChunk.get(), chunkPos);
            return addChunk(chunkPos, new GraphGeoChunk(chunkPos,graph, this));
        }
    }

    public GraphGeoChunk addChunk(ChunkPos chunkPos, GraphGeoChunk graphGeoChunk) {
        return layerChunks.put(chunkPos, graphGeoChunk);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
