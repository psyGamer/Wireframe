<<<<<<<< HEAD:src/main/kotlin/dev/psygamer/wireframe/util/helper/Debug.kt
package dev.psygamer.wireframe.util.helper
========
package dev.psygamer.wireframe.debug
>>>>>>>> dev:src/main/kotlin/dev/psygamer/wireframe/debug/DebugBlocks.kt

/**
 * Can be used to run debug-only code.
 *
 * **IMPORTANT:** Whatever code is passed in here should have *NO* side effects!
 */
inline fun debug(block: () -> Unit) {
	// When compiling for a release this can just be commented out to avoid having debug tools in the release build.
	
	block()
}

inline fun nonDebug(block: () -> Unit) {
	block()
}