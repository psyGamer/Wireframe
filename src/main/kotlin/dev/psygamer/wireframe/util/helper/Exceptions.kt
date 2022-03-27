package dev.psygamer.wireframe.util.helper

inline fun <reified T : Throwable> catch(block: () -> Unit) {
	try {
		block()
	} catch (t: Throwable) {
		if (t !is T) throw t
	}
}