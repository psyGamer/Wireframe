package dev.psygamer.wireframe;

import dev.psygamer.wireframe.event.EventBusRegistrator;

@net.minecraftforge.fml.common.Mod(Wireframe.MODID)
public class Main {
	
	public Main() {
		Wireframe.setNativeModImplementation(this);
		
		final net.minecraftforge.eventbus.api.IEventBus modEventBus =
				net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get()
																		  .getModEventBus();
		
		modEventBus.addListener(this::onModConstruct);
		
		//new ItemDisplayBlock();
	}
	
	private void onModConstruct(final net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent event) {
		EventBusRegistrator.registerModLoadingEventBuses();
		EventBusRegistrator.registerWireframeEventBuses();
	}
}
