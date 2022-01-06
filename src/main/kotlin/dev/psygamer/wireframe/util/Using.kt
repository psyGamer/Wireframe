package dev.psygamer.wireframe.util

import java.io.Closeable

/*
 * NOTE: The reason why the JVM names are so 'odd',
 * is because when using Java you can just a try-with-resource block, which doesn't exist in Kotlin for some reason.
 */

/**
 * Automatically closes all provided [resources] after the execution of [block] finished,
 * even if the [block] throws an exception.
 *
 * Handling thrown inside the [block] must be handled outside of [using]
 *
 * Exceptions throw while closing are automatically caught, with their stacktrace printed.
 * If you want to specify a custom exception handler you may use the provided overload.
 */
@JvmName("usingWithVararg")
fun using(vararg resources: Closeable, block: () -> Unit) {
	using(resources, block) { it.printStackTrace() }
}

/**
 * Automatically closes all provided [resources] after the execution of [block] finished,
 * even if the [block] throws an exception.
 *
 * Handling thrown inside the [block] must be handled outside of [using]
 *
 * Exceptions throw while closing are automatically caught, with their stacktrace printed.
 * If you want to specify a custom exception handler you may use the provided overload.
 */
@JvmName("usingWithArray")
fun using(resources: Array<out Closeable>, block: () -> Unit) {
	using(resources, block) { it.printStackTrace() }
}

/**
 * Automatically closes all provided [resources] after the execution of [block] finished,
 * even if the [block] throws an exception.
 *
 * Handling thrown inside the [block] must be handled outside of [using]
 *
 * The [exceptionHandler] is executed when an exception is thrown during the closing of a resource.
 */
@JvmName("usingWithVararg")
fun using(vararg resources: Closeable, block: () -> Unit, exceptionHandler: (Exception) -> Unit) {
	using(resources, block, exceptionHandler)
}

/**
 * Automatically closes all provided [resources] after the execution of [block] finished,
 * even if the [block] throws an exception.
 *
 * Handling thrown inside the [block] must be handled outside of [using]
 *
 * The [exceptionHandler] is executed when an exception is thrown during the closing of a resource.
 */
@JvmName("usingWithArray")
fun using(resources: Array<out Closeable>, block: () -> Unit, exceptionHandler: (Exception) -> Unit) {
	try {
		block()
	} catch (ex: Exception) {
		resources.forEach {
			try {
				it.close()
			} catch (ex: Exception) {
				exceptionHandler(ex)
			}
		}
		
		throw ex
	}
}