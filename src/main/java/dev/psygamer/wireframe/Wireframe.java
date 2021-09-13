package dev.psygamer.wireframe;

import dev.psygamer.wireframe.event.EventBus;
import dev.psygamer.wireframe.event.EventBusRegistrator;
import dev.psygamer.wireframe.event.api.IEventBus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Wireframe {
	
	public static final String MODID = "wireframe";
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final IEventBus EVENT_BUS = new EventBus();
	
	public static class Mod {
		private final String modID, modName, modVersion;
		
		protected Mod(final String modID, final String modName, final String modVersion) {
			this.modID = modID;
			this.modName = modName;
			this.modVersion = modVersion;
		}
	}
	
	@net.minecraftforge.fml.common.Mod(Wireframe.MODID)
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
