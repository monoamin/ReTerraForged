package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.item.Tier;
import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.cell.Cell;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.TileCache;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.generation.TileGenerator;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationGeoChunk;
import raccoonman.reterraforged.world.worldgen.util.PosUtil;

import java.util.HashMap;
import java.util.Map;

public class ElevationGeoLayer extends AbstractGeoLayer {

    // This is a GeoLayer with a raw input, so we need some way of accessing Tiles and Cells
    private TileGenerator tileGenerator;
    private TileCache tileCache;

    public ElevationGeoLayer(TileGenerator tileGenerator, TileCache tileCache) {
        super(null); // Initialize super with null because we don't have any dependencies on GeoLayers
        this.tileGenerator = tileGenerator;
        this.tileCache = tileCache;
        //layerChunks = new HashMap<ChunkPos, ElevationGeoChunk>();
    }

    @Override
    public Tile getOrComputeChunk(ChunkPos chunkPos, GeneratorContext context) {
        // Populate heightmap from generatorContext
        //TODO: Add an utility method to Tile that returns the long[][]
        //  Or, simply use Tiles in the GeoLayer itself, which is probably better
        if (tileCache != null) {
            return tileCache.provideAtChunkIfPresent(chunkPos.x, chunkPos.z);
        } else {
            return tileCache.provide(tileCache.chunkToTile(chunkPos.x), tileCache.chunkToTile(chunkPos.z));
        }
    }
}
