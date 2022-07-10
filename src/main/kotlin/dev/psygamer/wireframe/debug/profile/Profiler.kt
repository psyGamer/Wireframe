package dev.psygamer.wireframe.debug.profile

import net.minecraft.client.Minecraft
import net.minecraft.profiler.IProfiler
import java.io.Closeable

val PROFILER: Profiler
	get() = Profiler(Minecraft.getInstance().profiler)

inline fun <T> profile(name: String, function: () -> T): T {
	with(PROFILER) {
		push(name)
		val returnValue = function()
		pop()
		return returnValue
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