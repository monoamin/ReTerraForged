package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;

import java.util.ArrayList;
import java.util.List;

public class GeoChunkContext {
    public static List<ChunkPos> moore(ChunkPos center, int radius) {
        List<ChunkPos> neighbors = new ArrayList<>();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x != 0 || z != 0) {  // Ensure we don't include the center
                    neighbors.add(new ChunkPos(center.x + x, center.z + z));
                }
            }
        }
        return neighbors;
    }

    public static List<ChunkPos> neumann(ChunkPos center, int radius) {
        List<ChunkPos> neighbors = new ArrayList<>();
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (Math.abs(x) + Math.abs(z) <= radius && (x != 0 || z != 0)) {  // Ensure within radius and not the center
                    neighbors.add(new ChunkPos(center.x + x, center.z + z));
                }
            }
        }
        return neighbors;
    }

    public static List<ChunkPos> single(ChunkPos center) {
        List<ChunkPos> neighbors = new ArrayList<>();
        neighbors.add(center);
        return neighbors;
    }
}