package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.cell.Cell;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationGeoChunk;

import java.util.HashMap;
import java.util.Map;

public class ElevationGeoLayer extends GeoLayer {

    private final Map<ChunkPos, ElevationGeoChunk> layerChunks;

    public ElevationGeoLayer() {
        layerChunks = new HashMap<ChunkPos, ElevationGeoChunk>();
    }

    public ElevationGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext generatorContext) {
        // TODO: This is super weirdly placed
        if (layerChunks.containsKey(chunkPos)) { return layerChunks.get(chunkPos); }
        else {
            Tile tile = generatorContext.cache.provideAtChunk(chunkPos.x, chunkPos.z);
            if (!generatorContext.geoLayerManager.getLayer(GeoLayer.Types.ELEVATION).exists(chunkPos)) {
                long[][] chunkHeightmap = new long[16][16];

                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        Cell thisCell = tile.lookup(x, z);
                        chunkHeightmap[x][z] = Math.round(thisCell.height * 320);
                    }
                }
                return this.addChunk(chunkPos, new ElevationGeoChunk(chunkPos, chunkHeightmap));
            }
        }
        return null;
    }

    public ElevationGeoChunk addChunk(ChunkPos chunkPos, ElevationGeoChunk geoChunk) {
        layerChunks.put(chunkPos, geoChunk);
        return geoChunk;
    }

    public void delChunk(ChunkPos chunkPos) {
        layerChunks.remove(chunkPos);
    }

    public boolean exists(ChunkPos chunkPos) {
        return layerChunks.containsKey(chunkPos);
    }
}
