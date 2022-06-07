package dev.psygamer.wireframe.api.item

import dev.psygamer.wireframe.api.block.*
import dev.psygamer.wireframe.api.entity.Player
import dev.psygamer.wireframe.api.item.util.*
import dev.psygamer.wireframe.api.world.World
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.BlockHitResult

class BlockItem(
	identifier: Identifier,
	attributes: ItemAttributes = ItemAttributes(),

	private val block: Block,
) : Item(identifier, attributes) {

	override fun onItemUsedOnBlock(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World, hitResult: BlockHitResult,
	): ClickResult {
		val targetPosition =
			if (world.isReplaceable(hitResult.blockPosition))
				hitResult.blockPosition
			else
				hitResult.blockPosition.offset(hitResult.direction)

		if (!world.isReplaceable(targetPosition))
			return ClickResult.REJECTED

		val oldBlockState = world.getBlockState(targetPosition)
		val placementState = block.getPlacementState(world, player, hand, itemStack, hitResult)

		if (!world.setBlockState(placementState, targetPosition, World.UpdateFlag.DEFAULT_AND_RENDER))
			return ClickResult.REJECTED

		world.notifyNeighbours(targetPosition, placementState.block)

		if (block == world.getBlock(targetPosition)) {
			applyBlockStateTags(world, targetPosition, world.getBlockState(targetPosition), itemStack)
		}

		block.onBlockPlaced(world, targetPosition, oldBlockState, placementState)
		block.onBlockPlacedByPlayer(world, targetPosition, oldBlockState, placementState, player)

		if (!player.isCreative)
			itemStack.shrink(1)

		return ClickResult.ACCEPTED
	}

	private fun applyBlockStateTags(
		world: World, position: BlockPosition,
		blockState: BlockState, itemStack: ItemStack,
	) {
		val itemTag = itemStack.tag ?: return
		val blockStateTag = itemTag.getCompound("BlockStateTag")

		for (key in blockStateTag.allKeys) {
			val property = blockState.block.stateDefinition.getProperty(key)

			if (property != null)
				updateBlockState(blockState, property, blockStateTag.getString(key))
		}

		world.setBlockState(blockState, position)
	}

	private fun <T : Comparable<T>> updateBlockState(
		blockState: BlockState,
		property: BlockProperty<T>,
		valueName: String,
	): BlockState {
		return property.getValue(valueName)
			.map { blockState.setValue(property, it) }
			.orElse(blockState)
	}
}