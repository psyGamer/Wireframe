package dev.psygamer.construct.util.reflection;

import java.lang.reflect.Field;

public class FieldUtil {
	
	@SuppressWarnings("unchecked")
	public static <T, U> U getField(final Class<? super T> classToAccess, final T instance, final String fieldName) {
		try {
			final Field field = classToAccess.getDeclaredField(fieldName);
			
			field.setAccessible(true);
			
			return (U) field.get(instance);
		} catch (final NoSuchFieldException ex) {
			throw new IllegalArgumentException("Field " + fieldName + " was not found on " + classToAccess);
		} catch (final IllegalAccessException ex) {
			throw new IllegalArgumentException("Field " + fieldName + " could not be accessed in " + classToAccess);
		}
	}
}
