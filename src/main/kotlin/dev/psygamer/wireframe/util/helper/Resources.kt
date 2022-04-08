package dev.psygamer.wireframe.util.helper

/*
 * NOTE: The reason why the JVM names are so 'odd',
 * is because when using Java you can just a try-with-resource block, which doesn't exist in Kotlin for some reason.
 */

/**
 * Automatically closes the provided [resource] after the execution of [block] finished,
 * even if [block] throws an exception.
 *
 * Exceptions thrown inside [block] must be handled outside of [use]!
 */
@JvmName("usingWithSingleResource")
fun <R : AutoCloseable?> use(resource: R, block: (R) -> Unit) {
	try {
		block(resource)
	} catch (ex: Exception) {
		throw ex
	} finally {
		runCatching { resource?.close() }
	}
}

/**
 * Automatically closes all provided [resources] after the execution of [block] finished,
 * even if [block] throws an exception.
 *
 * Exceptions thrown inside [block] must be handled outside of [using]!
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

/**
 * Automatically closes all provided [resources] after the execution of [block] finished,
 * even if [block] throws an exception.
 *
 * Exceptions thrown inside [block] must be handled outside of [using]!
 */
@JvmName("usingWithVararg")
fun using(vararg resources: AutoCloseable?, block: () -> Unit) {
	using(resources, block)
}