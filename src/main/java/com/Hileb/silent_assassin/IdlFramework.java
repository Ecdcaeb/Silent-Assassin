package com.Hileb.silent_assassin;

import com.Hileb.silent_assassin.gui.ModGuiElementLoader;
import com.Hileb.silent_assassin.init.ModOreDic;
import com.Hileb.silent_assassin.init.ModRecipes;
import com.Hileb.silent_assassin.init.RegistryHandler;
import com.Hileb.silent_assassin.keys.KeyboardManager;
import com.Hileb.silent_assassin.network.NetworkHandler;
import com.Hileb.silent_assassin.proxy.ProxyBase;
import com.Hileb.silent_assassin.util.Reference;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.Logger;

import static com.Hileb.silent_assassin.init.RegistryHandler.initRegistries;

@Mod(modid = IdlFramework.MODID, name = IdlFramework.NAME, version = IdlFramework.VERSION,dependencies="after:momostories;after:idealland;after:forestry;after:manametalmod;after:calculator")//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class IdlFramework {
    public static final String MODID = "silent_assassin";
    public static final String NAME = "more momostories";
    public static final String VERSION = "1.0.1.8";
    public static final String DEPEND="after:momostories"+after_mod("idealland")+after_mod("forestry")+after_mod("manametalmod")+after_mod("calculator");

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static IdlFramework instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();


        //RegistryHandler.preInitRegistries(event);


    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.Init();
        RegisterTileEntity();
        initRegistries(event);
        ModOreDic.init();
        new ModGuiElementLoader();
        if (!proxy.isServer())
        {
            KeyboardManager.init();
        }

        NetworkHandler.init();

		LogWarning("%s has finished its initializations", MODID);

	}

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        // Moved Spawning registry to last since forge doesn't auto-generate sub
        // "M' biomes until late
//        if (ModConfig.SPAWN_CONF.SPAWN) {
//            ModSpawn.registerSpawnList();
//        }

        TrashTalking();

        RegistryHandler.postInitReg();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }


    private void TrashTalking() {

    }

    private static void RegisterTileEntity() {
//        GameRegistry.registerTileEntity(TileEntityDeBoomOrb.class, new ResourceLocation(MODID, "deboom_orb_basic"));

        //GameRegistry.registerTileEntity(TileEntityBuilderFarm.class, new ResourceLocation(MODID, "builder_farm_basic"));
        //GameRegistry.registerTileEntity(TileEntityBookShelf.class, new ResourceLocation(MODID, "tile_entity_book_shelf"));
    }

    public static void LogWarning(String str, Object... args) {
        if (SHOW_WARN) {
            logger.warn(String.format(str, args));
        }
    }

    public static void LogWarning(String str) {
        if (SHOW_WARN) {
            logger.warn(str);
        }
    }

    public static void Log(String str) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(str);
//        }
    }

    public static void Log(String str, Object... args) {
//        if (ModConfig.GeneralConf.LOG_ON)
//        {
        logger.info(String.format(str, args));
//        }
    }
    public static String after_mod(String modid){
        return String.format(";after:%s", modid);
    }
}