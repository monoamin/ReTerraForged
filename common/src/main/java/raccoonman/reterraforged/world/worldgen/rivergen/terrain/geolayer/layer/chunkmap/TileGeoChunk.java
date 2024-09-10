package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;

import java.util.List;

public class TileGeoChunk extends AbstractGeoChunk {
    public TileGeoChunk(ChunkPos chunkPos, AbstractGeoLayer parentGeoLayer, GeneratorContext generatorContext, List<ChunkPos> contextGeoChunks, GraphGeoChunk pathGeoChunk) {
        super(chunkPos, parentGeoLayer, generatorContext, contextGeoChunks);
    }

    public Tile getTile(ChunkPos chunkPos) {
        return null;
    }
}
