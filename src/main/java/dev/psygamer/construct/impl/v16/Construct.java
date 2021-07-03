package dev.psygamer.construct.impl.v16;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.impl.v16.registry.BlockRegistryImpl16;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ConstructCore.MODID)
public class Construct {
	
	public Construct() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.register(new BlockRegistryImpl16());
	}
}
