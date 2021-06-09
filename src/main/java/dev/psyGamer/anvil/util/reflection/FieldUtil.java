package dev.psyGamer.anvil.util.reflection;

import lombok.SneakyThrows;

public class FieldUtil {
	
	public static Object getField(final Object obj, final String fieldName) {
		try {
			return obj.getClass().getField(fieldName).getType();
		} catch (final NoSuchFieldException ex) {
			throw new IllegalArgumentException("Field " + fieldName + " was not found on " + obj);
		}
	}
}
