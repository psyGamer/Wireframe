package dev.psygamer.wireframe.util.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
	
	public static Method getStaticMethod(final Class<?> clazz, final String methodName, final Class<?>[] parameterTypes) {
		final Stream<Method> possibleMethods = getStaticMethodsByName(clazz, methodName).stream()
				.filter(method -> {
					final Class<?>[] methodParameterTypes = method.getParameterTypes();
					
					if (parameterTypes.length != methodParameterTypes.length) {
						return false;
					}
					
					for (int i = 0 ; i < parameterTypes.length ; i++) {
						final Class<?> parameterType = parameterTypes[i];
						final Class<?> methodParameterType = methodParameterTypes[i];
						
						if (parameterType == null) {
							continue;
						}
						
						if (parameterType != methodParameterType) {
							return false;
						}
					}
					
					return true;
				});
		
		if (possibleMethods.count() > 1) {
			return null;
		}
		
		return possibleMethods.findFirst().orElse(null);
	}
}
