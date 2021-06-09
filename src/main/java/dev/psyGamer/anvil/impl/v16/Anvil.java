package dev.psyGamer.anvil.impl.v16;

import dev.psyGamer.anvil.core.AnvilCore;
import dev.psyGamer.anvil.impl.v16.registry.BlockRegistryImpl16;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;

@Mod("anvil")
public class Anvil {
	
	public Anvil() {
		final IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;
		
		forgeEventBus.register(new BlockRegistryImpl16());
	}
}
