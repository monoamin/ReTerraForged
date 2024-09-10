package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.AbstractGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GeoChunkContext;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;

import java.util.ArrayList;
import java.util.List;

public class GraphGeoLayer extends AbstractGeoLayer {


    public GraphGeoLayer(AbstractGeoLayer contextLayer) {
        super(contextLayer);
    }

    @Override
    public GraphGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext context) {
        ElevationGeoLayer elevationGeoLayer = (ElevationGeoLayer) super.dependencyLayer;
        if ( super.exists(chunkPos) ) {
            return (GraphGeoChunk) super.getOrComputeChunk(chunkPos, context);
        } else {
            ElevationGeoChunk elevationGeoChunk = null; // (ElevationGeoChunk)elevationGeoLayer.getOrComputeChunk(chunkPos,context);
            WeightedGraph graph = null; //new WeightedGraph(elevationGeoChunk.getData(), chunkPos);
            return (GraphGeoChunk) super.addChunk(chunkPos, new GraphGeoChunk(chunkPos, this, context, GeoChunkContext.moore(chunkPos), graph));
        }
    }
}
