package dev.psygamer.ferrus.impl.v16;

import dev.psygamer.ferrus.core.FerrusCore;
import dev.psygamer.ferrus.impl.v16.registry.BlockRegistryImpl16;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

@Mod(FerrusCore.MODID)
public class Ferrus {
	
	public Ferrus() {
		final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		
		forgeEventBus.register(new BlockRegistryImpl16());
	}
}
