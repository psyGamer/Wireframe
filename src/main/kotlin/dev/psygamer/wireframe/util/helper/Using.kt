package dev.psygamer.wireframe.util.helper

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
fun using(vararg resources: AutoCloseable?, block: () -> Unit) {
	using(resources, block)
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
fun using(resources: Array<out AutoCloseable?>, block: () -> Unit) {
	try {
		block()
	} catch (ex: Exception) {
		throw ex
	} finally {
		resources.forEach { runCatching { it?.close() } }
	}
}