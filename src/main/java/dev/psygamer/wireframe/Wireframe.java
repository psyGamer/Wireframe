package dev.psygamer.wireframe;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.eventbus.EventBusRegistrator;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(WireframeCore.MODID)
public class Wireframe {
	
	public Wireframe() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.addListener(this::onModConstruct);
	}
	
	private void onModConstruct(final FMLConstructModEvent event) {
		EventBusRegistrator.registerModLoadingEventBuses();
		EventBusRegistrator.registerWireframeEventBuses();
	}
}
