package dev.psygamer.wireframe;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.event.EventBus;
import dev.psygamer.wireframe.event.EventBusRegistrator;
import dev.psygamer.wireframe.event.api.IEventBus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Wireframe {
	
	public static final String MODID = "wireframe";
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final IEventBus EVENT_BUS = new EventBus();
	
	private static final List<Mod> mods = new ArrayList<>();
	private static Internal internal;
	
	public static void register(final Mod mod) {
		mods.add(mod);
	}
	
	public static ImmutableList<Mod> getMods() {
		return ImmutableList.copyOf(mods);
	}
	
	public static Internal getInternal() {
		return internal;
	}
	
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
			Wireframe.internal = this;
			
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
