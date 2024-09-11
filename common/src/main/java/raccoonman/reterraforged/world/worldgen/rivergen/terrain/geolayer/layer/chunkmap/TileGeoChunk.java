package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;

import java.util.List;
import java.util.Map;

public class TileGeoChunk extends AbstractGeoChunk {
    public TileGeoChunk(Int2D chunkPos, AbstractGeoLayer parentGeoLayer, GeneratorContext generatorContext, GraphGeoChunk pathGeoChunk) {
        super(chunkPos, parentGeoLayer, generatorContext);
    }

    public Tile getTile(Int2D chunkPos) {
        return null;
    }
}
