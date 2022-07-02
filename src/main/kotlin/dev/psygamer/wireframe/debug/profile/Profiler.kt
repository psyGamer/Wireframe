package dev.psygamer.wireframe.debug.profile

import net.minecraft.client.Minecraft
import net.minecraft.profiler.*
import java.io.Closeable

val PROFILER: Profiler
	get() {
		if (_profiler != null)
			_profiler
		if (Minecraft.getInstance().profiler != EmptyProfiler.INSTANCE) {
			_profiler = Profiler(Minecraft.getInstance().profiler)
			_profiler
		}
		throw UninitializedPropertyAccessException("Cannot use profiler before it is initialized!")
	}

private var _profiler: Profiler? = null

inline fun profile(name: String, function: () -> Unit) {
	with(PROFILER) {
		push(name)
		function()
		pop()
	}
}

class Profiler(internal val mcNative: IProfiler) {

	fun push(name: String): Closeable {
		mcNative.push(name)
		return Closeable { mcNative.pop() }
	}

	fun pop() {
		mcNative.pop()
	}

	fun popPush(name: String): Closeable {
		mcNative.pop()
		mcNative.push(name)
		return Closeable { mcNative.pop() }
	}
}