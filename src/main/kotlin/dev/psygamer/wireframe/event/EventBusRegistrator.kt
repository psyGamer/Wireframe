package dev.psygamer.wireframe.event

import dev.psygamer.wireframe.Wireframe
import dev.psygamer.wireframe.Wireframe.mods
import dev.psygamer.wireframe.event.api.EventBusSubscriber
import dev.psygamer.wireframe.event.api.ModEventBusSubscriber
import dev.psygamer.wireframe.util.reflection.ClassUtil
import java.lang.reflect.InvocationTargetException

object EventBusRegistrator {
	
	private val eventClasses: List<Class<*>>
		get() = buildList {
			mods.forEach { addAll(ClassUtil.getClasses(it.rootPackage)) }
		}
	
	@JvmStatic
	fun registerModEventBusses() {
		eventClasses
			.filter { it.isAnnotationPresent(ModEventBusSubscriber::class.java) }
			.forEach {
				registerClassToNativeModEventBus(it)
				
				Wireframe.LOGGER.info("Added $it to the mod event bus.")
			}
	}
	
	@JvmStatic
	fun registerWireframeEventBusses() {
		eventClasses
			.filter { it.isAnnotationPresent(EventBusSubscriber::class.java) }
			.forEach {
				Wireframe.EVENT_BUS.register(it)
				Wireframe.LOGGER.info("Added $it to the Wireframe event bus.")
			}
	}
	
	private fun registerClassToNativeModEventBus(clazz: Class<*>) {
		mods
			.forEach {
				val modEventBus = it.nativeEventBus
				
				try {
					val createInstanceMethod = clazz.getDeclaredMethod("createInstance", String::class.java)
					createInstanceMethod.isAccessible = true
					
					val classInstance = createInstanceMethod(null, it.modID)
					modEventBus.register(classInstance)
				} catch (ex: Exception) {
					when (ex) {
						is NoSuchMethodException,
						is IllegalAccessException,
						is InvocationTargetException -> Unit // Ignore
						
						else -> throw ex
					}
				}
			}
	}
}