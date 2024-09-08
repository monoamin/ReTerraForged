package raccoonman.reterraforged.world.worldgen;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderGetter;
import raccoonman.reterraforged.data.worldgen.preset.settings.Preset;
import raccoonman.reterraforged.world.worldgen.cell.heightmap.Heightmap;
import raccoonman.reterraforged.world.worldgen.cell.heightmap.Levels;
import raccoonman.reterraforged.world.worldgen.cell.heightmap.WorldLookup;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.TileCache;
import raccoonman.reterraforged.world.worldgen.densityfunction.tile.generation.TileGenerator;
import raccoonman.reterraforged.world.worldgen.noise.module.Noise;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.AbstractGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.GeoLayerManager;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.ElevationGeoLayer;
import raccoonman.reterraforged.world.worldgen.rivergen.terrain.geolayer.layer.GraphGeoLayer;
import raccoonman.reterraforged.world.worldgen.util.Seed;

public class GeneratorContext {
    public Seed seed;
    public Levels levels;
    public Preset preset;
    public HolderGetter<Noise> noiseLookup;
    public TileGenerator generator;
    @Nullable
    public TileCache cache;
    public WorldLookup lookup;
    public GeoLayerManager geoLayerManager;

    public GeneratorContext(Preset preset, HolderGetter<Noise> noiseLookup, int seed, int tileSize, int tileBorder, int batchCount, @Nullable TileCache cache) {
        this.preset = preset;
        this.noiseLookup = noiseLookup;
        this.seed = new Seed(seed);
        this.levels = new Levels(preset.world().properties.terrainScaler(), preset.world().properties.seaLevel);
        this.generator = new TileGenerator(Heightmap.make(this), new WorldFilters(this), tileSize, tileBorder, batchCount);
        this.cache = cache;
        this.lookup = new WorldLookup(this);
        this.geoLayerManager = new GeoLayerManager();

        // Initialize GeoLayers
        this.geoLayerManager.addLayerIfAbsent(AbstractGeoLayer.Types.ELEVATION, new ElevationGeoLayer(null));
        this.geoLayerManager.addLayerIfAbsent(AbstractGeoLayer.Types.CHUNK_GRAPH, new GraphGeoLayer(this.geoLayerManager.getLayer(AbstractGeoLayer.Types.ELEVATION)));
        this.geoLayerManager.addLayerIfAbsent(AbstractGeoLayer.Types.AREA_GRAPH, new GraphGeoLayer(this.geoLayerManager.getLayer(AbstractGeoLayer.Types.CHUNK_GRAPH)));
        //this.geoLayerManager.addLayerIfAbsent(GeoLayer.Types.RIVER_SPLINE, new GraphGeoLayer());
        //this.geoLayerManager.addLayerIfAbsent(GeoLayer.Types.RIVER_MASK, new GraphGeoLayer());
    }

    public static GeneratorContext makeCached(Preset preset, HolderGetter<Noise> noiseLookup, int seed, int tileSize, int batchCount, boolean queue) {
    	GeneratorContext ctx = makeUncached(preset, noiseLookup, seed, tileSize, Math.min(2, Math.max(1, preset.filters().erosion.dropletLifetime / 16)), batchCount);
    	ctx.cache = new TileCache(tileSize, queue, ctx.generator);
    	ctx.lookup = new WorldLookup(ctx);
    	return ctx;
    }
    
    public static GeneratorContext makeUncached(Preset preset, HolderGetter<Noise> noiseLookup, int seed, int tileSize, int tileBorder, int batchCount) {
    	return new GeneratorContext(preset, noiseLookup, seed, tileSize, tileBorder, batchCount, null);
    }
}
