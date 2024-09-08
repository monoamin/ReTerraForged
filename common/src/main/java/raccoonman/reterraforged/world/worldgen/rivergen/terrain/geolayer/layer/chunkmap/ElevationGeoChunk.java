package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.ElevationGeoLayer;

import java.util.List;

public class ElevationGeoChunk extends AbstractGeoChunk {
    private final long[][] map;
    private int maxY;
    public ElevationGeoChunk(ChunkPos chunkPos, long[][] heights, ElevationGeoLayer parentGeoLayer, GeneratorContext context, List<ChunkPos> contextGeoChunks)
    {
        super(chunkPos,parentGeoLayer,context,contextGeoChunks);
        this.map = heights;
    }

    public long[][] getData()
    {
        return map;
    }

    public Int2D getMaxPos(){
        int max = 0;
        int xMax = -1, zMax = -1;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > max) {
                    max = (int)map[i][j];
                    xMax = i;
                    zMax = j;
                }
            }
        }
        return new Int2D(xMax,zMax);
    }
}
