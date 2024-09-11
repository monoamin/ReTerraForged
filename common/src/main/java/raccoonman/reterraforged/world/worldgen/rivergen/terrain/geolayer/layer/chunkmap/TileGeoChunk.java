package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.Tile;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;

import java.util.List;
import java.util.Map;

public class TileGeoChunk extends AbstractGeoChunk {
    private Tile tile;
    public TileGeoChunk(Int2D chunkPos, AbstractGeoLayer parentGeoLayer, GeneratorContext generatorContext, Tile tile) {
        super(chunkPos, parentGeoLayer, generatorContext);
        this.tile = tile;
    }

    public Tile getTile(Int2D chunkPos) {
        return tile;
    }
}
