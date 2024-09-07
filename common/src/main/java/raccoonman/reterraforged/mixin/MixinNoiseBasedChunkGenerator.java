package raccoonman.reterraforged.mixin;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import net.fabricmc.loader.impl.util.log.Log;
import net.fabricmc.loader.impl.util.log.LogCategory;
import net.minecraft.world.level.levelgen.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.core.Holder;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.blending.Blender;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import raccoonman.reterraforged.world.worldgen.GeneratorContext;
import raccoonman.reterraforged.world.worldgen.RTFRandomState;
import raccoonman.reterraforged.world.worldgen.rivergen.math.graph.WeightedGraph;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.TerrainUtils;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayer;

/*@Mixin(value = NoiseBasedChunkGenerator.class, priority = 9001 /* we need this so we don't break noisium )*/
class MixinNoiseBasedChunkGenerator {
/*
    @Shadow
    @Final
    private Holder<NoiseGeneratorSettings> settings;
*/
//TODO: Do this another way.
/*
    @Inject(at = @At("TAIL"), method = {"fillFromNoise", "populateNoise"}, require = 1)
    public void fillFromNoise$TAIL(Executor executor, Blender blender, RandomState randomState, StructureManager structureManager, ChunkAccess chunkAccess, CallbackInfoReturnable<CompletableFuture<ChunkAccess>> cir) {
        // TODO: Add heightmap to ElevationGeoLayer
        // TODO: Have an instance of GeoLayerManager ready for use here
        ChunkPos chunkPos = chunkAccess.getPos();
        GeneratorContext generatorContext;
        if ((Object) randomState instanceof RTFRandomState rtfRandomState && (generatorContext = rtfRandomState.generatorContext()) != null) {

        }
    }*/
}

