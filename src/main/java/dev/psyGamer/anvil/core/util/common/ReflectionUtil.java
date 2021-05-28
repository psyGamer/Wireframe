package dev.psyGamer.anvil.core.util.common;

import javax.tools.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

public class ReflectionUtil {
	
	public static List<Class<?>> getClasses(final String packageName) {
		return getClassesWithAnnotation(packageName, null);
	}
	
	public static List<Class<?>> getClassesWithAnnotation(final String packageName, final Class<? extends Annotation> annotationClass) {
		return getClassesWithAnnotation(packageName, annotationClass, true);
	}
	
	public static List<Class<?>> getClassesWithAnnotation(final String packageName, final Class<? extends Annotation> annotationClass, final boolean includeSuperClass) {
		try {
			final List<Class<?>> classes = new ArrayList<>();
			final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
			final StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
			
			@SuppressWarnings("rawtypes") final HashSet kinds = new HashSet() {{
				//noinspection unchecked
				add(JavaFileObject.Kind.CLASS);
			}};
			
			@SuppressWarnings("unchecked") final Iterable<JavaFileObject> list = fileManager.list(StandardLocation.CLASS_PATH, packageName, kinds, true);
			
			for (final JavaFileObject javaFileObject : list) {
				final String raw = javaFileObject.toUri().toString();
				final int index = raw.lastIndexOf(packageName.replace('.', '/'));
				
				if (index > 0) {
					String fullName = packageName.replace('.', '/') + raw.split(packageName.replace('.', '/'))[1];
					
					fullName = fullName.replaceAll("/", ".");
					fullName = fullName.substring(0, fullName.length() - ".class".length());
					
					Class<?> clazz = Class.forName(fullName);
					
					if (annotationClass == null || clazz.isAnnotationPresent(annotationClass)) {
						classes.add(clazz);
					} else if (includeSuperClass) {
						while (!clazz.isAnnotationPresent(annotationClass)) {
							clazz = clazz.getSuperclass();
						}
						
						classes.add(clazz);
					}
				}
			}
			
			return classes;
		} catch (final Exception ex) {
			ex.printStackTrace();
			
			return null;
		}
	}
	
	public static boolean hasStaticMethods(final Class clazz, final boolean includeSuperMethods) {
		return !getStaticMethods(clazz, includeSuperMethods).isEmpty();
	}
	
	public static List<Method> getStaticMethods(final Class clazz, final boolean includeSuperMethods) {
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
}
