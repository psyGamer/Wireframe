package dev.psygamer.wireframe.util.reflection

import javax.tools.JavaFileObject
import javax.tools.StandardLocation
import javax.tools.ToolProvider

object ClassUtil {
	
	fun getClasses(packageName: String): List<Class<*>> {
		return getClassesWithAnnotation(packageName, null)
	}
	
	fun getClassesWithAnnotation(packageName: String, annotationClass: Class<out Annotation>?): List<Class<*>> {
		val classes: MutableList<Class<*>> = ArrayList()
		val compiler = ToolProvider.getSystemJavaCompiler()
		val fileManager = compiler.getStandardFileManager(null, null, null)
		
		val kinds = buildSet { add(JavaFileObject.Kind.CLASS) }
		
		val list = fileManager.list(StandardLocation.CLASS_PATH, packageName, kinds, true)
		
		for (javaFileObject in list) {
			val raw = javaFileObject.toUri().toString()
			val index = raw.lastIndexOf(packageName.replace('.', '/'))
			
			if (index == 0)
				continue
			
			val pathSpliced = raw.split(packageName.replace('.', '/').toRegex()).toTypedArray()
			var fullName = packageName.replace('.', '/') + pathSpliced[pathSpliced.size - 1]
			
			fullName = fullName.replace("/".toRegex(), ".")
			fullName = fullName.substring(0, fullName.length - ".class".length)
			
			val clazz = Class.forName(fullName)
			
			if (annotationClass == null || clazz.isAnnotationPresent(annotationClass))
				classes.add(clazz)
		}
		
		return classes
	}
	
	fun getClassFromStackTraceElement(element: StackTraceElement): Class<*>? {
		return try {
			Class.forName(element.className)
		} catch (e: ClassNotFoundException) {
			null
		}
	}
}