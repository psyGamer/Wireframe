package dev.psygamer.wireframe.event.nativeapi

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.Wireframe.mods
import dev.psygamer.wireframe.util.reflection.ClassUtil
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import java.lang.reflect.InvocationTargetException

object NativeEventBusRegistrator {
	
	private val eventClasses: List<Class<*>>
		get() = buildList {
			mods.forEach { addAll(ClassUtil.getClasses(it.rootPackage)) }
		}
	
	fun register() {
		registerNativeModEventBusses()
		registerNativeForgeEventBusses()
	}
	
	private fun registerNativeModEventBusses() {
		eventClasses
			.filter { it.isAnnotationPresent(NativeModEventBusSubscriber::class.java) }
			.forEach {
				registerClassToNativeModEventBus(it)
				Wireframe.LOGGER.info("Added $it to the native mod event bus.")
			}
	}
	
	private fun registerNativeForgeEventBusses() {
		eventClasses
			.filter { it.isAnnotationPresent(NativeForgeEventBusSubscriber::class.java) }
			.forEach {
				FORGE_BUS.register(it) // Does this work with Java?
				Wireframe.LOGGER.info("Added $it to the native forge event bus.")
			}
	}
	
	private fun registerClassToNativeModEventBus(clazz: Class<*>) {
		try {
			val constructor = clazz.getDeclaredConstructor(String::class.java)
			constructor.isAccessible = true
			
			mods.forEach {
				it.nativeEventBus.register(constructor.newInstance(it.modID))
			}
		} catch (ex: Exception) {
			when (ex) {
				is NoSuchMethodException,
				is IllegalAccessException,
				is InvocationTargetException -> Unit
				
				else -> throw ex
			}
		}
	}
}