package dev.psygamer.wireframe

import dev.psygamer.wireframe.event.EventBus
import dev.psygamer.wireframe.event.EventBusRegistrator
import dev.psygamer.wireframe.event.api.IEventBus
import dev.psygamer.wireframe.test.BlockTest
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS

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
	
	@JvmStatic
	var nativeModImplementation: NativeMod? = null
		private set
	
	open class Mod(val modID: String, val modName: String, val modVersion: String) {
		
		val rootPackage: String = this.javaClass.`package`.name
		
		val eventBus: IEventBus = EventBus()
		val nativeEventBus: net.minecraftforge.eventbus.api.IEventBus = MOD_BUS
		
		init {
			(mods as MutableList).add(this)
		}
	}
	
	private object WireframeMod : Mod(MODID, NAME, VERSION)
	
	@net.minecraftforge.fml.common.Mod(MODID)
	object NativeMod {
		
		init {
			nativeModImplementation = this
			EventBusRegistrator.register()
			
			/* Force-init the internal implementation.
			 * This will probably cause issues with child mods which don't have this way of force-init-ing their extension class.
			 *
			 * TODO Do this automatically
			 */ WireframeMod
			
			BlockTest()
		}
	}
}