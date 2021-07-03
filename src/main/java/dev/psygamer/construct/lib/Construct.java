package dev.psygamer.construct.lib;

import dev.psygamer.construct.core.implementation.ImplementationHandler;

public interface Construct {
	
	final class Core {
		public static <T> T executeImplementation(final Object... parameters) {
			return ImplementationHandler.executeImplementation(parameters);
		}
		
		public static <T> T executeSpecificImplementation(final Class<?>[] parameterTypes, final Object... parameters) {
			return ImplementationHandler.executeSpecificImplementation(parameterTypes, parameters);
		}
	}
}
