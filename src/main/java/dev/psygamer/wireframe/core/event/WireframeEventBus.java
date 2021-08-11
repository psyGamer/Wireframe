package dev.psygamer.wireframe.core.event;

import dev.psygamer.wireframe.core.WireframeCore;

import net.minecraftforge.eventbus.EventBusErrorMessage;
import net.minecraftforge.eventbus.api.*;

public class WireframeEventBus {
	
	public static IEventBus create() {
		return BusBuilder.builder()
				.setExceptionHandler(WireframeEventBus::handleException)
				.markerType(IWireframeEvent.class)
				.setTrackPhases(false)
				.build();
	}
	
	private static void handleException(
			final IEventBus bus, final Event event, final IEventListener[] listeners, final int index, final Throwable throwable
	) {
		WireframeCore.LOGGER.error(
				new EventBusErrorMessage(event, index, listeners, throwable)
		);
	}
}
