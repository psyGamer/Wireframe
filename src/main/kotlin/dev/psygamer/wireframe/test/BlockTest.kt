package dev.psygamer.wireframe.test

import dev.psygamer.wireframe.api.block.*
import dev.psygamer.wireframe.api.block.attributes.*
import dev.psygamer.wireframe.api.client.screen.ScreenManager.open
import dev.psygamer.wireframe.api.entity.Player
import dev.psygamer.wireframe.api.item.ItemAttributes
import dev.psygamer.wireframe.api.item.util.ClickResult
import dev.psygamer.wireframe.api.network.PacketHandler
import dev.psygamer.wireframe.api.world.World
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.helper.onlyOnLogicalClient

class BlockTest : Block(
	Identifier("wireframe", "block_test"),

	BlockAttributes(Material.WOOD)
		.harvestLevel(HarvestLevel.STONE),
	ItemAttributes()
		.maxStackSize(15),

	FACING
) {

	companion object {

		val FACING = DirectionBlockProperty("facing")
	}

	override fun onUsedByPlayer(world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player): ClickResult {
		runCatching {
			PacketHandler.sendToServer(
				TestPacket("Moin, Server, Moin!")
			)
		}

		runCatching {
			PacketHandler.sendToAllClients(
				TestPacket("Moin, Client, Moin!")
			)
		}

		runCatching {
			TestScreen.open()
		}

		return ClickResult.ACCEPTED
	}

	override fun onBlockRemovedByPlayer(
		world: World, blockPosition: BlockPosition, oldBlockState: BlockState, newBlockState: BlockState, player: Player,
	) {
		onlyOnLogicalClient(world) {
			runCatching { TestGUI.open() }
		}
	}
}