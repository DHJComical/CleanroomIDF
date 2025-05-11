package com.comical.examplemod.init;

import com.comical.examplemod.blocks.ModBlocks;
import com.comical.examplemod.enchantments.ModEnchantmentInit;
import com.comical.examplemod.entity.ModEntityInit;
import com.comical.examplemod.entity.RenderHandler;
import com.comical.examplemod.item.ModItems;
import com.comical.examplemod.util.IHasModel;
import com.comical.examplemod.util.ModSoundHandler;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber
public class RegistryHandler {
	@SubscribeEvent
	public static void onItemRegister(RegistryEvent.Register<Item> event)
	{
		event.getRegistry().registerAll(ModItems.ITEMS.toArray(new Item[0]));
	}
	
	@SubscribeEvent
	public static void onBlockRegister(RegistryEvent.Register<Block> event) {
		event.getRegistry().registerAll(ModBlocks.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public static void onEnchantmentRegister(RegistryEvent.Register<Enchantment> event)
	{
		ModEnchantmentInit.BeforeRegister();
		event.getRegistry().registerAll(ModEnchantmentInit.ENCHANTMENT_LIST.toArray(new Enchantment[0]));

//		for (Enchantment enchantment : Enchantment.REGISTRY) {
//			Main.Log("registered enchantments: %s", enchantment.getName());
//		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onModelRegister(ModelRegistryEvent event)
	{
		for(Item item : ModItems.ITEMS)
		{
			if (item instanceof IHasModel)
			{
				((IHasModel)item).registerModels();
			}
		}
		
		for(Block block : ModBlocks.BLOCKS)
		{
			if (block instanceof IHasModel)
			{
				((IHasModel)block).registerModels();
			}
		}

		RenderHandler.registerEntityRenders();
	}

	public static void preInitRegistries(FMLPreInitializationEvent event)
	{
		//GameRegistry.registerWorldGenerator(new ModWorldGenOld(), 100);
		//GameRegistry.registerWorldGenerator(new ModWorldGenNew(), 120);

		InitBiome.registerBiomes();
		InitDimension.registerDimensions();

		ModEntityInit.registerEntities();
	}

	public static void postInitReg()
	{
		//WorldType TYPE_ONE = new WorldTypeOne();
	}

	public static void initRegistries(FMLInitializationEvent event)
	{
		ModSoundHandler.soundRegister();
	}

	public static void serverRegistries(FMLServerStartingEvent event)
    {
        //event.registerServerCommand(new CommandDimTeleport());
    }
}
