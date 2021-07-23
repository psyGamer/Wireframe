package dev.psygamer.wireframecore;

import dev.psygamer.wireframecore.dependant.DependantsHandler;
import dev.psygamer.wireframecore.event.EventRegistrator;
import dev.psygamer.wireframecore.exceptions.FrameworkException;
import dev.psygamer.wireframecore.namespace.NamespaceHandler;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(WireframeCore.MODID)
public final class WireframeCore {
	
	public static final Logger LOGGER = LogManager.getLogger("Wireframe");
	public static final String MODID = "wireframe";
	
	public WireframeCore() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
		modEventBus.addListener(this::onModConstruct);
	}
	
	public static void register(final Class<?> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		if (!modClass.isAnnotationPresent(Mod.class)) {
			throw new FrameworkException("Mod class is not annotated with @Mod");
		}
		
		DependantsHandler.registerDependant(
				modClass,
				modLoadingContext
		);
		
		NamespaceHandler.registerNamespace(
				modClass.getAnnotation(Mod.class).value(),
				modClass.getPackage().getName()
		);
		
		EventRegistrator.addModLoadingContext(modLoadingContext);
	}
	
	private void onModConstruct(final FMLConstructModEvent event) {
		EventRegistrator.registerModLoadingEventBuses();
	}
}
