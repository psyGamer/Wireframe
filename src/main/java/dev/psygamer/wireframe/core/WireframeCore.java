package dev.psygamer.wireframe.core;

import dev.psygamer.wireframe.core.dependant.Dependant;
import dev.psygamer.wireframe.core.dependant.Namespace;
import dev.psygamer.wireframe.core.eventbus.EventBusRegistrator;
import dev.psygamer.wireframe.core.eventbus.WireframeEventBus;
import dev.psygamer.wireframe.core.exceptions.FrameworkException;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.eventbus.api.IEventBus;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WireframeCore.MODID)
public final class WireframeCore {
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final String MODID = "wireframe";
	
	public static final IEventBus EVENT_BUS = WireframeEventBus.create();
	
	public WireframeCore() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.addListener(this::onModConstruct);
	}
	
	public static void register(final Class<?> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new FrameworkException("Mod class is not annotated with @Mod");
		}
		
		new Dependant<>(
				modClass,
				modLoadingContext
		);
		
		new Namespace(
				modClass.getAnnotation(Mod.class).value(),
				modClass.getPackage().getName()
		);
	}
	
	private void onModConstruct(final FMLConstructModEvent event) {
		EventBusRegistrator.registerModLoadingEventBuses();
		EventBusRegistrator.registerWireframeEventBuses();
	}
	
	
}
