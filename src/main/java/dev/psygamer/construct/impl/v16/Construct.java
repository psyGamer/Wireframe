package dev.psygamer.construct.impl.v16;

import dev.psygamer.construct.core.ConstructCore;
import dev.psygamer.construct.impl.v16.registry.BlockRegistryImpl16;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

@Mod(ConstructCore.MODID)
public class Construct {
	
	public Construct() {
		final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		
		forgeEventBus.register(new BlockRegistryImpl16());
	}
}
