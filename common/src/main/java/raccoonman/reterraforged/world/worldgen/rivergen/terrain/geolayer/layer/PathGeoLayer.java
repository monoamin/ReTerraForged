package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.*;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PathGeoLayer extends AbstractGeoLayer {

    public PathGeoLayer(AbstractGeoLayer dependencyLayer) {
        super(dependencyLayer);
    }

    @Override
    public GraphGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext generatorContext) {
        GraphGeoLayer graphGeoLayer = (GraphGeoLayer) dependencyLayer;
        WeightedGraph graphAtChunk = graphGeoLayer.getOrComputeChunk(chunkPos, generatorContext).graph;
        WeightedGraph spanningSubgraph = graphAtChunk.getSubGraphFromHighest();
        PathGeoChunk geoChunk = new PathGeoChunk(chunkPos, this, generatorContext, GeoChunkContext.single(chunkPos), spanningSubgraph);
        return (GraphGeoChunk) layerChunks.put(chunkPos, geoChunk);
    }

}
