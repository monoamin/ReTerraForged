package raccoonman.reterraforged.mixin;

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

