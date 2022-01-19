package dev.psygamer.wireframe.event

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.Wireframe.mods
import dev.psygamer.wireframe.event.api.EventBusSubscriber
import dev.psygamer.wireframe.event.nativeapi.NativeEventBusRegistrator
import dev.psygamer.wireframe.util.reflection.ClassUtil

object EventBusRegistrator {
	
	internal val eventClasses: List<Class<*>>
		get() = buildList {
			mods.forEach { addAll(ClassUtil.getClasses(it.rootPackage, false, this@EventBusRegistrator.javaClass.classLoader)) }
		}
	
	fun register() {
		registerWireframeEventBusses()
		
		NativeEventBusRegistrator.register()
	}
	
	private fun registerWireframeEventBusses() {
		eventClasses
			.filter { it.isAnnotationPresent(EventBusSubscriber::class.java) }
			.forEach {
				Wireframe.EVENT_BUS.register(it)
				Wireframe.LOGGER.info("Added $it to the Wireframe event bus.")
			}
	}
}