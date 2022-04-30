package dev.psygamer.wireframe

import net.minecraft.tileentity.TileEntityType
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import org.apache.logging.log4j.*
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import dev.psygamer.wireframe.api.registry.*
import dev.psygamer.wireframe.debug.WireframeDebugger
import dev.psygamer.wireframe.debug.logging.*
import dev.psygamer.wireframe.event.*
import dev.psygamer.wireframe.event.api.IEventBus
import dev.psygamer.wireframe.test.BlockTest
import dev.psygamer.wireframe.test.TestPacket
import dev.psygamer.wireframe.test.TestPacketDecoder
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import dev.psygamer.wireframe.api.registry.*
import dev.psygamer.wireframe.debug.WireframeDebugger
import dev.psygamer.wireframe.debug.logging.*
import dev.psygamer.wireframe.event.*
import dev.psygamer.wireframe.event.api.IEventBus
import dev.psygamer.wireframe.nativeapi.block.entity.NativeBlockEntity
import dev.psygamer.wireframe.nativeapi.registry.NativeBlockEntityRegistry
import dev.psygamer.wireframe.test.*

object Wireframe {
	
	const val NAME = "Wireframe"
	const val MODID = "wireframe"
	const val VERSION = "1.0"
	
	@JvmField
	val LOGGER: Logger = LogManager.getLogger(NAME)
	
	@JvmField
	val CHAT_LOGGER: SimpleLogger =
		if (WireframeDebugger.ENABLED)
			ChatLogger(name = NAME, consoleLogger = LOGGER)
		else // We only want to use the ChatLogger in a debug environment.
			VoidLogger
	
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
	
	lateinit var blockTest: BlockTest
	
	@net.minecraftforge.fml.common.Mod(MODID)
	object NativeMod {
		
		init {
			nativeModImplementation = this
			
			/* Force-init the internal implementation.
			 * This will probably cause issues with child mods which don't have this way of force-init-ing their extension class.
			 *
			 * TODO Do this automatically
			 */ WireframeMod
			
			MOD_BUS.addListener(::clientSetup)
			
			blockTest = BlockTest()
			EventBusRegistrator.register()
			PacketRegistry.register(TestPacket::class.java, TestPacketDecoder)
		}
		
		@SubscribeEvent
		fun clientSetup(event: FMLClientSetupEvent) {
			val def = BlockEntityRegistry.getValue(blockTest.identifier)
			
			@Suppress("UNCHECKED_CAST")
			ClientRegistry.bindTileEntityRenderer(
				NativeBlockEntityRegistry.getTileEntityType(def!!.identifier) as TileEntityType<NativeBlockEntity>,
				::TestTESR)
		}
	}
}