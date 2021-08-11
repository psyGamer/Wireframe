package dev.psygamer.wireframe.core.util.reflection;

import java.util.Arrays;

public final class ObjectUtil {
	
	public static Class<?>[] getClassTypes(final Object... objects) {
		return Arrays.stream(objects)
				.map(param -> {
					try {
						return param.getClass();
					} catch (final NullPointerException ex) {
						return null;
					}
				})
				.toArray(Class[]::new);
	}
}
