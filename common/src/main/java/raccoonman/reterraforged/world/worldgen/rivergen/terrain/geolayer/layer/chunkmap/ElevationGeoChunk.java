package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;

public class ElevationGeoChunk {
    private  long[][] map;
    private ChunkPos chunkPos;
    public ElevationGeoChunk(ChunkPos chunkPos, long[][] heights)
    {
        this.chunkPos = chunkPos;
        this.map = heights;
    }

    public long[][] get()
    {
        return map;
    }
}
