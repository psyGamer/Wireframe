package dev.psygamer.wireframe;

import dev.psygamer.wireframe.event.EventBus;
import dev.psygamer.wireframe.event.EventBusRegistrator;
import dev.psygamer.wireframe.event.api.IEventBus;
import dev.psygamer.wireframe.test.item_display.ItemDisplayBlock;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/** The main class of the Wireframe Framework */
public class Wireframe {
	
	public static final String MODID = "wireframe";
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final IEventBus EVENT_BUS = new EventBus();
	
	/** All registered mods which have Wireframe as dependency. */
	private static final List<Mod> mods = Lists.newArrayList(new InternalMod());
	
	/** Internal, do not use! */
	private static Internal internal;
	
	public static void register(final Mod mod) {
		mods.add(mod);
	}
	
	/** Returns a list of all child mods. */
	public static ImmutableList<Mod> getMods() {
		return ImmutableList.copyOf(mods);
	}
	
	/** Internal, do not use! */
	public static Internal getInternal() {
		return internal;
	}
	
	/**
	 * A class which all child mods must extend.
	 * This contains basic information about a child mod.
	 */
	public static class Mod {
		
		private final String modID, modName, modVersion, rootPackage;
		private final EventBus modEventBus = new EventBus();
		
		private final net.minecraftforge.eventbus.api.IEventBus internalModEventBus;
		
		/** Constructs a new child mod container */
		protected Mod(final String modID, final String modName, final String modVersion) {
			this.modID = modID;
			this.modName = modName;
			this.modVersion = modVersion;
			
			this.rootPackage = this.getClass()
								   .getPackage()
								   .getName();
			
			this.internalModEventBus = net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get()
																								 .getModEventBus();
		}
		
		public String getModID() {
			return this.modID;
		}
		
		public String getModName() {
			return this.modName;
		}
		
		public String getModVersion() {
			return this.modVersion;
		}
		
		/** The package-path of class which extends this class. */
		public String getRootPackage() {
			return this.rootPackage;
		}
		
		public EventBus getModEventBus() {
			return this.modEventBus;
		}
		
		/** Internal, do not use! */
		public net.minecraftforge.eventbus.api.IEventBus getInternalModEventBus() {
			return this.internalModEventBus;
		}
	}
	
	/** Internal, do not use! */
	@net.minecraftforge.fml.common.Mod(Wireframe.MODID)
	public static final class Internal {
		
		public Internal() {
			Wireframe.internal = this;
			
			final net.minecraftforge.eventbus.api.IEventBus modEventBus =
					net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get()
																			  .getModEventBus();
			
			modEventBus.addListener(this::onModConstruct);
			
			new ItemDisplayBlock();
		}
		
		private void onModConstruct(final net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent event) {
			EventBusRegistrator.registerModLoadingEventBuses();
			EventBusRegistrator.registerWireframeEventBuses();
		}
	}
	
	/** Internal, do not use! */
	private static final class InternalMod extends Mod {
		
		private InternalMod() {
			super("wireframe", "Wireframe", "1.0");
		}
	}
}
