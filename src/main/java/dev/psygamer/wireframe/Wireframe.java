package dev.psygamer.wireframe;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.core.eventbus.EventBusRegistrator;
import dev.psygamer.wireframe.event.EventBus;
import dev.psygamer.wireframe.event.api.IEventBus;

public class Wireframe {
	
	public static final IEventBus EVENT_BUS = new EventBus();
	
	@net.minecraftforge.fml.common.Mod(WireframeCore.MODID)
	public static final class Internal {
		public Internal() {
			final net.minecraftforge.eventbus.api.IEventBus modEventBus =
					net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().getModEventBus();
			
			modEventBus.addListener(this::onModConstruct);
		}
		
		private void onModConstruct(final net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent event) {
			EventBusRegistrator.registerModLoadingEventBuses();
			EventBusRegistrator.registerWireframeEventBuses();
		}
	}
}
