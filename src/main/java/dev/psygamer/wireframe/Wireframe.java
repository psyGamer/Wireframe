package dev.psygamer.wireframe;

import dev.psygamer.wireframe.core.impl.ImplementationHandler;

public interface Wireframe {
	
	final class Core {
		public static <T> T executeImplementation(final Object... parameters) {
			return ImplementationHandler.executeImplementation(parameters);
		}
		
		public static <T> T executeSpecificImplementation(final Class<?>[] parameterTypes, final Object... parameters) {
			return ImplementationHandler.executeSpecificImplementation(parameterTypes, parameters);
		}
	}
}
