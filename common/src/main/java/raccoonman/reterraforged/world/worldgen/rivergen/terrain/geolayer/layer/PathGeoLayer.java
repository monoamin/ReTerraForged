package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.AbstractGeoChunk;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.GraphGeoChunk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PathGeoLayer extends AbstractGeoLayer {

    public PathGeoLayer(AbstractGeoLayer dependencyLayer) {
        super(dependencyLayer);
    }

    @Override
    public GraphGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext context) {
        GraphGeoLayer graphGeoLayer = (GraphGeoLayer) super.dependencyLayer;
        AbstractGeoChunk elevationMax = graphGeoLayer.getOrComputeChunk(chunkPos, context);

        int radius = 1;
        List<GraphGeoChunk> neighbors = new ArrayList<>();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x != 0 && z != 0) {
                    neighbors.add(graphGeoLayer.getOrComputeChunk(new ChunkPos(chunkPos.x + x, chunkPos.z + z), context));
                }
            }
        }

        // Identify maxima
        // Traverse and prune


        return  null;
    }

    /*
    public GraphGeoChunk addChunk(ChunkPos chunkPos, GraphGeoChunk graphGeoChunk) {
        return layerChunks.put(chunkPos, graphGeoChunk);
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
    */

}
