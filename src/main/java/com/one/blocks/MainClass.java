package com.one.blocks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item.Properties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("onesblocks")
public class MainClass {
	
	public static MainClass instance;
	public static String modid = "onesblocks"; //same as Mod()
	
	private static final Logger logger = LogManager.getLogger(modid);
	
	public MainClass() 
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientRegistries);
		MinecraftForge.EVENT_BUS.register(this);
		instance = this;
	}
	
	private void setup(final FMLCommonSetupEvent event)
	{
		logger.info(modid + " Loading");
	}
	
	private void clientRegistries(final FMLClientSetupEvent event)
	{
		logger.info(modid + " ClientRegistries");
		
	}
	
	@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
	public static class RegistryEvents
	{
		@SubscribeEvent
		public static void RegisterItems(final RegistryEvent.Register<Item> event)
		{
			event.getRegistry().registerAll
			(
					ItemList.tutorial_item = new Item(new Item.Properties().group(ItemGroup.MISC)).setRegistryName(loc("tutorial_item")),
					ItemList.test_block_item = new BlockItem(BlockList.test_block,new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.test_block.getRegistryName()),
					ItemList.anti_ice_item = new BlockItem(BlockList.anti_ice,new Item.Properties().group(ItemGroup.MISC)).setRegistryName(BlockList.anti_ice.getRegistryName())
			);
			logger.info("Registered Items");
		}
		
		@SubscribeEvent
		public static void RegisterBlocks(final RegistryEvent.Register<Block> event)
		{
			event.getRegistry().registerAll
			(
					BlockList.test_block = new Block(Block.Properties.create(Material.IRON)
							.slipperiness(1000)
							.hardnessAndResistance(2.0f, 3.0f)
							.sound(SoundType.LADDER)
							).setRegistryName(loc("test_block")),
					BlockList.anti_ice = new Block(Block.Properties.create(Material.IRON)
							.slipperiness(-1000)
							.hardnessAndResistance(2.0f, 3.0f)
							.sound(SoundType.LADDER)
							).setRegistryName(loc("anti_ice"))
			);
			logger.info("Registered Items");
		}
		
		private static ResourceLocation loc(String name) { return new ResourceLocation(modid,name); }
		
	}
}
