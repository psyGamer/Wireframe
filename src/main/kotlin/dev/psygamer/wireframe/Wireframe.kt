package dev.psygamer.wireframe

import dev.psygamer.wireframe.event.EventBus
import dev.psygamer.wireframe.event.api.IEventBus

import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

import java.util.*

object Wireframe {
	
	const val NAME = "Wireframe"
	const val MODID = "wireframe"
	const val VERSION = "1.0"
	
	@JvmField
	val LOGGER: Logger = LogManager.getLogger(NAME)
	
	@JvmField
	val EVENT_BUS: IEventBus = EventBus()
	
	@JvmStatic
	val mods: List<Mod> = mutableListOf()
		get() = Collections.unmodifiableList(field)
	
	@JvmStatic
	var nativeModImplementation: Main? = null
		set(value) {
			if (nativeModImplementation == null)
				field = value
		}
	
	open class Mod(val modID: String, val modName: String, val modVersion: String) {
		
		val rootPackage: String
		
		val eventBus: IEventBus = EventBus()
		val nativeEventBus: net.minecraftforge.eventbus.api.IEventBus
		
		init {
			this.rootPackage = this.javaClass.`package`.name
			this.nativeEventBus = net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext.get().modEventBus
			
			(mods as MutableList).add(this)
		}
	}
	
	private class NativeMod : Mod(MODID, NAME, VERSION)
}