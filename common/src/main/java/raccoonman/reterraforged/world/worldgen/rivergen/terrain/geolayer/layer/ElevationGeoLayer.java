package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.cell.Cell;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.TileCache;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.generation.TileGenerator;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap.ElevationGeoChunk;

import java.util.HashMap;
import java.util.Map;

public class ElevationGeoLayer extends AbstractGeoLayer {

    // This is a GeoLayer with a raw input, so we need some way of accessing Tiles and Cells
    private TileGenerator tileGenerator;
    private TileCache tileCache;

    public ElevationGeoLayer(TileGenerator tileGenerator, TileCache tileCache) {
        super(null); // Initialize super with null because we don't have any dependencies on GeoLayers
        this.tileGenerator = tileGenerator;
        //layerChunks = new HashMap<ChunkPos, ElevationGeoChunk>();
    }

    public ElevationGeoChunk getOrComputeChunk(ChunkPos chunkPos, GeneratorContext generatorContext) {
        // Populate heightmap from generatorContext
        //TODO: Add an utility method to Tile that returns the long[][]
        //  Or, simply use Tiles in the GeoLayer itself, which is probably better
        long[][] heightMap = new long[16][16];
        for (int x=0; x<16; x++){
            for (int z=0;z<16;z++){
                heightMap[x][z]=Math.round(tileCache.provideAtChunk(chunkPos.x,chunkPos.z).getCellRaw(x,z).height*320);
            }
        }
        ElevationGeoChunk elevationGeoChunk = new ElevationGeoChunk(chunkPos, heightMap,this, generatorContext,null);
        return (ElevationGeoChunk) super.addChunk(chunkPos,elevationGeoChunk);
    }
}
