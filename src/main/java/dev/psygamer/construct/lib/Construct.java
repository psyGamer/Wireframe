package dev.psygamer.construct.lib;

public interface Construct {
	
	final class Core {
		public static Object executeImplementation(final Object... parameters) {
			return null; // Actual Implementation in impl branch
		}
		
		public static Object executeSpecificImplementation(final Class<?>[] parameterTypes, final Object... parameters) {
			return null; // Actual Implementation in impl branch
		}
	}
}
