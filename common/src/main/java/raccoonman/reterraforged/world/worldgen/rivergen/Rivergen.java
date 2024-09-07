package raccoonman.reterraforged.world.worldgen.rivergen;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
//import net.minecraftforge.common.MinecraftForge;
//import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
//import net.minecraftforge.eventbus.api.IEventBus;
//import net.minecraftforge.eventbus.api.SubscribeEvent;
//import net.minecraftforge.fml.common.Mod;
//import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
//import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
//import net.minecraftforge.registries.DeferredRegister;
//import net.minecraftforge.registries.ForgeRegistries;
//import net.minecraftforge.registries.RegistryObject;
//import raccoonman.reterraforged.world.worldgen.rivergen.debug.DebugToolItem;
//import raccoonman.reterraforged.world.worldgen.rivergen.render.RenderHandler;
//import raccoonman.reterraforged.world.worldgen.rivergen.terrain.RGenChunkGenerator;

// TODO: Fix Registrations / move to appropriate place
//@Mod(Rivergen.MODID)
public class Rivergen {
    //public static final String MODID = "rivergen";
    //public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "rivergen");
    //public static final RegistryObject<Item> DEBUG_GETHEIGHT = ITEMS.register("get_height",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    //public static final RegistryObject<Item> DEBUG_GETNORMAL = ITEMS.register("get_normal",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    //public static final RegistryObject<Item> DEBUG_GETFLOW = ITEMS.register("get_flow",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    //public static final RegistryObject<Item> DEBUG_GETLOWEST = ITEMS.register("get_lowest",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    //public static final RegistryObject<Item> DEBUG_GETDENSITY= ITEMS.register("get_density",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    //public static final RegistryObject<Item> DEBUG_GETACCUMULATION = ITEMS.register("get_accumulation",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    //public static final RegistryObject<Item> DEBUG_STARTGEN = ITEMS.register("start_gen",
    //        () -> new DebugToolItem(new Item.Properties().stacksTo(1)));
    // Create a DeferredRegister for Chunk Generators
    //public static final DeferredRegister<Codec<? extends ChunkGenerator>> CHUNK_GENERATORS =
    //        DeferredRegister.create(BuiltInRegistries.CHUNK_GENERATOR.key(), "rivergen");
    // Register the custom Chunk Generator
    //public static final RegistryObject<Codec<NoiseBasedChunkGenerator>> RGEN_CHUNK_GENERATOR =
    //        CHUNK_GENERATORS.register("rgen_chunk_generator", () -> RGenChunkGenerator.CODEC);


    public Rivergen() {
        //IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        //modEventBus.addListener(this::setup);
        //MinecraftForge.EVENT_BUS.register(this);
        //MinecraftForge.EVENT_BUS.register(RenderHandler.class);
        //MinecraftForge.EVENT_BUS.register(RGenWorldState.class);
        //ITEMS.register(modEventBus);
        // Register event bus to listen for creative tab build events
        //modEventBus.addListener(this::buildContents);
        //CHUNK_GENERATORS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    /*
    private void setup(final FMLCommonSetupEvent event) {
    }
    */

    //@SubscribeEvent
    /*public void buildContents(BuildCreativeModeTabContentsEvent event) {
        // Add to ingredients tab
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(DEBUG_GETHEIGHT.get());
            event.accept(DEBUG_GETNORMAL.get());
            event.accept(DEBUG_GETFLOW.get());
            event.accept(DEBUG_GETLOWEST.get());
            event.accept(DEBUG_GETACCUMULATION.get());
            event.accept(DEBUG_GETDENSITY.get());
            event.accept(DEBUG_STARTGEN.get());
        }
    }*/
}