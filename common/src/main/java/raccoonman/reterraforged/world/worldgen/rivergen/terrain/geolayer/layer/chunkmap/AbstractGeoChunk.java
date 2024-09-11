package raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.chunkmap;

import net.minecraft.world.level.ChunkPos;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.rivergen.math.Int2D;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;

import java.util.List;
import java.util.Map;

public abstract class AbstractGeoChunk
{
    protected final AbstractGeoLayer parentGeoLayer;
    protected GeneratorContext generatorContext;
    protected final Int2D chunkPos;

    public AbstractGeoChunk(Int2D chunkPos, AbstractGeoLayer parentGeoLayer, GeneratorContext generatorContext) {
        this.chunkPos = chunkPos;
        this.parentGeoLayer = parentGeoLayer;
        this.generatorContext = generatorContext;
    }
}
