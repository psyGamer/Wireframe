package dev.psygamer.wireframe.util.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MethodUtil {
	
	public static boolean hasStaticMethods(final Class<?> clazz) {
		return !getStaticMethods(clazz).isEmpty();
	}
	
	public static List<Method> getStaticMethods(final Class<?> clazz) {
		final List<Method> staticMethods = new ArrayList<>();
		
		for (final Method declaredMethod : clazz.getDeclaredMethods()) {
			if (Modifier.isStatic(declaredMethod.getModifiers())) {
				staticMethods.add(declaredMethod);
			}
		}
		
		return staticMethods;
	}
	
	public static List<Method> getStaticMethodsByName(final Class<?> clazz, final String methodName) {
		return getMethodsByName(clazz, methodName).stream()
				.filter(method -> Modifier.isStatic(method.getModifiers()))
				.collect(Collectors.toList());
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
	
	public static Method getStaticMethod(final Class<?> libraryClass, final String methodName, final Class<?>[] parameterTypes) {
		return getStaticMethodsByName(libraryClass, methodName).stream()
				.filter(method -> Arrays.equals(method.getParameterTypes(), parameterTypes))
				.findFirst()
				.orElse(null);
	}
}
