package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
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
    public GraphGeoChunk getOrComputeChunk(Int2D chunkPos, GeneratorContext generatorContext) {
        ElevationGeoLayer elevationGeoLayer = (ElevationGeoLayer) dependencyLayer;
        if ( exists(chunkPos) ) {
            return getOrComputeChunk(chunkPos, generatorContext);
        } else {
            WeightedGraph graph = WeightedGraph.fromTile(elevationGeoLayer.getOrComputeChunk(chunkPos, generatorContext), chunkPos);
            return (GraphGeoChunk)
                    addChunk(chunkPos, new GraphGeoChunk(chunkPos, this, generatorContext, layerChunks.getMooreNeighbors(chunkPos, 1), graph));
        }
    }
}
