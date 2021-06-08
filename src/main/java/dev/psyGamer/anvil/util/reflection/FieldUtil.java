package dev.psyGamer.anvil.util.reflection;

import lombok.SneakyThrows;

public class FieldUtil {
	
	@SneakyThrows
	public static Object getField(final Object obj, final String fieldName) {
		return obj.getClass().getField(fieldName).getType();
	}
}
