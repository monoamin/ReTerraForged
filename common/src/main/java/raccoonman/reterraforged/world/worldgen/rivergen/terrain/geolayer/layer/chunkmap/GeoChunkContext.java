package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class GeoChunkContext {
    public static List<ChunkPos> moore(ChunkPos center) {
        List<ChunkPos> neighbors = new ArrayList<>();
        for (int x = -1; x<=1;x++) {
            for (int z = -1; z <= 1; z++) {
                neighbors.add(new ChunkPos(center.x + x, center.z + z));
            }
        }
        return neighbors;
    }
}