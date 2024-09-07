package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;

import java.util.HashMap;
import java.util.Map;

public class ElevationChunkMap {
    private final Map<ChunkPos, long[][]> map;
    public ElevationChunkMap()
    {
        map = new HashMap<>();
    }

    public void destroy(ChunkPos chunkPos)
    {
        map.remove(chunkPos);
    }

    public void add(ChunkPos chunkPos, long[][] heights)
    {
        map.put(chunkPos,heights);
    }

    public boolean exists(ChunkPos chunkPos) {
        return map.containsKey(chunkPos);
    }
}
