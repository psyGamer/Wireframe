package dev.psyGamer.anvil.util.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class MethodUtil {
	
	public static boolean hasStaticMethods(final Class<?> clazz, final boolean includeSuperMethods) {
		return !getStaticMethods(clazz, includeSuperMethods).isEmpty();
	}
	
	public static List<Method> getStaticMethods(final Class<?> clazz, final boolean includeSuperMethods) {
		final List<Method> staticMethods = new ArrayList<>();
		
		if (includeSuperMethods) {
			for (final Method method : clazz.getMethods()) {
				if (Modifier.isStatic(method.getModifiers())) {
					staticMethods.add(method);
				}
			}
		} else {
			for (final Method declaredMethod : clazz.getDeclaredMethods()) {
				if (Modifier.isStatic(declaredMethod.getModifiers())) {
					staticMethods.add(declaredMethod);
				}
			}
		}
		
		return staticMethods;
	}
	
	public static List<Method> getMethodsByName(final Class<?> clazz, final String methodName) {
		final List<Method> methods = new ArrayList<>();
		
		for (final Method clazzMethod : clazz.getMethods()) {
			if (clazzMethod.getName().equalsIgnoreCase(methodName)) {
				methods.add(clazzMethod);
			}
		}
		
		return methods;
	}
}
