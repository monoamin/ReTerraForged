package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GeoChunkContext;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.PathGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.TileGeoChunk;

public class OutputTileGeoLayer extends AbstractGeoLayer {

    public OutputTileGeoLayer(AbstractGeoLayer dependencyLayer) {
        super(dependencyLayer);
    }

    @Override
    public TileGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext generatorContext) {
        PathGeoLayer pathGeoLayer = (PathGeoLayer) dependencyLayer;
        GraphGeoChunk pathGeoChunk = pathGeoLayer.getOrComputeChunk(chunkPos,generatorContext);
        return new TileGeoChunk(chunkPos, this, generatorContext, GeoChunkContext.single(chunkPos),pathGeoChunk);
    }

}
