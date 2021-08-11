package dev.psygamer.wireframe.core.util.reflection;

import javax.tools.*;

import java.lang.annotation.Annotation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public final class ClassUtil {
	
	public static List<Class<?>> getClasses(final String packageName) {
		return getClassesWithAnnotation(packageName, null);
	}
	
	public static List<Class<?>> getClassesWithAnnotation(final String packageName, final Class<? extends Annotation> annotationClass) {
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
					final String[] pathSpliced = raw.split(packageName.replace('.', '/'));
					String fullName = packageName.replace('.', '/') + pathSpliced[pathSpliced.length - 1];
					
					fullName = fullName.replaceAll("/", ".");
					fullName = fullName.substring(0, fullName.length() - ".class".length());
					
					final Class<?> clazz = Class.forName(fullName);
					
					if (annotationClass == null || clazz.isAnnotationPresent(annotationClass)) {
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
	
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getClassFromStackTraceElement(final StackTraceElement element) {
		try {
			return (Class<T>) Class.forName(element.getClassName());
		} catch (final ClassNotFoundException e) {
			return null;
		}
	}
}
