package com.somebody.idlframework;

import com.somebody.idlframework.gui.ModGuiElementLoader;
import com.somebody.idlframework.init.ModConfig;
import com.somebody.idlframework.init.ModRecipes;
import com.somebody.idlframework.init.ModSpawn;
import com.somebody.idlframework.init.RegistryHandler;
import com.somebody.idlframework.keys.KeyboardManager;
import com.somebody.idlframework.meta.MetaUtil;
import com.somebody.idlframework.network.NetworkHandler;
import com.somebody.idlframework.proxy.ProxyBase;
import com.somebody.idlframework.util.CommonDef;
import com.somebody.idlframework.util.Reference;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import org.apache.logging.log4j.*;

//To let the player be a traveling god who plays yin-yang magic.

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)//dependencies = "required-after:Forge@[14.23.5.2705,)"
public class Main {
    public static final String MODID = "idlframework";
    public static final String NAME = "IdlFramework";
    public static final String VERSION = "0.1.101";

    public static Logger logger;

    public static final boolean SHOW_WARN = true;

    @Mod.Instance
    public static Main instance;

    @SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
    public static ProxyBase proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();

        if (MODID.equals("untitled"))
        {
            logger.error("Please change your mod id in the main class.");
            
        }

        if (Reference.CLIENT_PROXY_CLASS.indexOf("somebody.idlframework.proxy.ClientProxy") > 0)
        {
            logger.warn("Have you changed your package name to author and modname?");
            
        }

        RegistryHandler.preInitRegistries(event);

    }

    @EventHandler
    public static void Init(FMLInitializationEvent event) {
        ModRecipes.Init();
        RegisterTileEntity();
        RegistryHandler.initRegistries(event);
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
        if (ModConfig.SPAWN_CONF.SPAWN) {
            ModSpawn.registerSpawnList();
        }

        MetaUtil.isIDLLoaded = Loader.isModLoaded("idealland");
        MetaUtil.isIRRLoaded = Loader.isModLoaded("itemrender");
        MetaUtil.isLoaded_TiC = Loader.isModLoaded("tconstruct");
        MetaUtil.isLoaded_Slashblade = Loader.isModLoaded("flammpfeil.slashblade");
        MetaUtil.isLoaded_Botania = Loader.isModLoaded("botania");
        MetaUtil.isLoaded_DWeapon = Loader.isModLoaded("dweapon");
        MetaUtil.isLoaded_AOA3 = Loader.isModLoaded(CommonDef.MOD_NAME_AOA3);
        MetaUtil.isLoaded_GC = Loader.isModLoaded("galacticraftcore");
        MetaUtil.isLoaded_Taoism = Loader.isModLoaded("taoism");
        MetaUtil.isLoaded_GOG = Loader.isModLoaded(CommonDef.MOD_NAME_GOG);

        TrashTalking();

        RegistryHandler.postInitReg();
    }

    @EventHandler
    public static void serverInit(FMLServerStartingEvent event) {
        RegistryHandler.serverRegistries(event);
    }


    private void TrashTalking() {
        if (MetaUtil.isIDLLoaded)
        {
            Main.Log("[Idealland Framework] Bow to Idealland.");
        }
        else {
            Main.Log("[Idealland Framework] Made with Idealland Framework.");
        }
    }

    private static void RegisterTileEntity() {
//        GameRegistry.registerTileEntity(TileEntityDeBoomOrb.class, new ResourceLocation(MODID, "deboom_orb_basic"));

        //GameRegistry.registerTileEntity(TileEntityBuilderFarm.class, new ResourceLocation(MODID, "builder_farm_basic"));
        //GameRegistry.registerTileEntity(TileEntityBuilderOne.class, new ResourceLocation(MODID, "builder.builder_one"));
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
}