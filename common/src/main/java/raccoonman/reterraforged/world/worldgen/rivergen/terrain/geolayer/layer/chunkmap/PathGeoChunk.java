package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.GraphNode;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.GraphGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.PathGeoLayer;

import java.util.List;
import java.util.Map;

public class PathGeoChunk extends AbstractGeoChunk {
    public WeightedGraph graph;
    public PathGeoChunk(Int2D chunkPos, PathGeoLayer parentPathGeoLayer, GeneratorContext context, WeightedGraph graph)
    {
        super(chunkPos, parentPathGeoLayer, context);
        this.graph = graph;
    }
}
