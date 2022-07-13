package dev.psygamer.wireframe.util.reflection

import javax.tools.*
import dev.psygamer.wireframe.Wireframe

object ClassUtil {

	fun getClasses(packageName: String, initializeClass: Boolean = true, classLoader: ClassLoader? = null): List<Class<*>> {
		return getClassesWithAnnotation(packageName, null, initializeClass, classLoader)
	}

	fun getClassesWithAnnotation(
		packageName: String, annotationClass: Class<out Annotation>?, initializeClass: Boolean = true,
		classLoader: ClassLoader? = null,
	): List<Class<*>> {
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

			if (fullName.contains("mixin")) {
				Wireframe.LOGGER.info("Skipping mixin class: $fullName")
				continue
			}

			val clazz =
				if (initializeClass)
					Class.forName(fullName)
				else
					Class.forName(fullName, false, classLoader)

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