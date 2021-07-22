package dev.psygamer.wireframe.impl.v16;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.impl.v16.registry.BlockRegistryImpl16;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WireframeCore.MODID)
public class Wireframe {
	
	public Wireframe() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.register(new BlockRegistryImpl16());
	}
}
