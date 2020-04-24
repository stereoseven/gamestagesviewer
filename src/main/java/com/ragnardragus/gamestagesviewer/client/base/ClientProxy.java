package com.ragnardragus.gamestagesviewer.client.base;

import com.ragnardragus.gamestagesviewer.base.CommonProxy;
import com.ragnardragus.gamestagesviewer.client.gui.InventoryTabHandler;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

public class ClientProxy extends CommonProxy{

	public void registerItemRender(Item item, int meta, String id) {
		ModelLoader.setCustomModelResourceLocation(item, meta, new ModelResourceLocation(item.getRegistryName(), id));
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
		
		MinecraftForge.EVENT_BUS.register(InventoryTabHandler.class);
	}
	
}
