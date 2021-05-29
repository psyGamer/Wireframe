package dev.psyGamer.anvil.test;

import dev.psyGamer.anvil.core.AnvilCore;

import lombok.*;

import dev.psyGamer.anvil.lib.registry.ItemRegistry;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.util.List;

@Mod(
		modid = AnvilTest.MOD_ID,
		name = AnvilTest.MOD_NAME,
		version = AnvilTest.VERSION
)
public class AnvilTest {
	public static final String MOD_ID = "anvil";
	public static final String MOD_NAME = "Anvil";
	public static final String VERSION = "0.1";
	/**
	 * This is the instance of your mod as created by Forge. It will never be null.
	 */
	@Mod.Instance(MOD_ID)
	public static AnvilTest INSTANCE;
	
	public AnvilTest() {
		AnvilCore.Debug.verifyLibrary = true;
		AnvilCore.Debug.strictMode = false;
		
		AnvilCore.setup(MOD_ID, this);
	}
	
	public static void main(final String[] args) {
		new AnvilTest();
	}
	
	/**
	 * This is the first initialization event. Register tile entities here.
	 * The registry events below will have fired prior to entry to this method.
	 */
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		AnvilCore.preInit(event);
	}
	
	/**
	 * This is the second initialization event. Register custom recipes
	 */
	@Mod.EventHandler
	public void init(final FMLInitializationEvent event) {
		AnvilCore.init(event);
	}
	
	/**
	 * This is the final initialization event. Register actions from other mods here
	 */
	@Mod.EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		AnvilCore.postInit(event);
	}
}
