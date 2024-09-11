package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.TileCache;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.ElevationGeoLayer;

import java.util.List;

public class ElevationGeoChunk extends AbstractGeoChunk {
    private TileCache tileCache;
    public ElevationGeoChunk(Int2D chunkPos, ElevationGeoLayer parentGeoLayer, GeneratorContext generatorContext)
    {
        super(chunkPos,parentGeoLayer,generatorContext);
        this.tileCache = generatorContext.cache;
    }

    public Tile getTileChunk(){
        return tileCache.provide(chunkPos.x(),chunkPos.z());
    }

    /*
    // Populate heightmap from generatorContext
        //TODO: Add an utility method to Tile that returns the long[][]
        //  Or, simply use Tiles in the GeoLayer itself, which is probably better


        long[][] heightMap = new long[16][16];
        for (int x=0; x<16; x++){
            for (int z=0;z<16;z++){
                heightMap[x][z]=Math.round(tileCache.provide(chunkPos.x,chunkPos.z).getCellRaw(x,z).height*320);
            }
        }

     */
}
