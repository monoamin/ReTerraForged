package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;

import java.util.HashMap;
import java.util.Map;

public class GeoChunkElevation {
    private  long[][] map;
    private ChunkPos chunkPos;
    public GeoChunkElevation(ChunkPos chunkPos, long[][] heights)
    {
        this.chunkPos = chunkPos;
        this.map = heights;
    }
}
