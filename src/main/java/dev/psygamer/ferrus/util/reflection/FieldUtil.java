package dev.psygamer.ferrus.util.reflection;

import java.lang.reflect.Field;

public class FieldUtil {
	
	public static Object getField(final Object obj, final String fieldName) {
		try {
			final Field field = obj.getClass().getField(fieldName);
			
			field.setAccessible(true);
			
			return field.get(obj);
		} catch (final NoSuchFieldException ex) {
			throw new IllegalArgumentException("Field " + fieldName + " was not found on " + obj);
		} catch (final IllegalAccessException ex) {
			throw new IllegalArgumentException("Field " + fieldName + " could not be accessed in " + obj);
		}
	}
}
