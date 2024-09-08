package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;

import java.util.List;

public abstract class AbstractGeoChunk
{
    protected final AbstractGeoLayer parentGeoLayer;
    protected final GeneratorContext generatorContext;
    protected final List<ChunkPos> contextGeoChunks;
    protected final ChunkPos chunkPos;

    public AbstractGeoChunk(ChunkPos chunkPos, AbstractGeoLayer parentGeoLayer, GeneratorContext generatorContext, List<ChunkPos> contextGeoChunks) {
        this.chunkPos = chunkPos;
        this.parentGeoLayer = parentGeoLayer;
        this.generatorContext = generatorContext;
        this.contextGeoChunks = contextGeoChunks;
    }
}
