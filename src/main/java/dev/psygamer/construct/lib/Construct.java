package dev.psygamer.construct.lib;

public interface Construct {
	
	final class Core {
		public static <T> T executeImplementation(final Object... parameters) {
			return null; // Actual Implementation in impl branch
		}
		
		public static <T> T executeSpecificImplementation(final Class<?>[] parameterTypes, final Object... parameters) {
			return null; // Actual Implementation in impl branch
		}
	}
}
