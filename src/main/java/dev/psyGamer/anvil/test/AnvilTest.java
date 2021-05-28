package dev.psyGamer.anvil.test;

import dev.psyGamer.anvil.core.Anvil;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

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
		Anvil.setup();
	}
	
	public static void main(final String[] args) {
		Anvil.setup();
	}
	
	/**
	 * This is the first initialization event. Register tile entities here.
	 * The registry events below will have fired prior to entry to this method.
	 */
	@Mod.EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		Anvil.preInit(event);
	}
	
	/**
	 * This is the second initialization event. Register custom recipes
	 */
	@Mod.EventHandler
	public void init(final FMLInitializationEvent event) {
		Anvil.init(event);
	}
	
	/**
	 * This is the final initialization event. Register actions from other mods here
	 */
	@Mod.EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		Anvil.postInit(event);
	}
}
