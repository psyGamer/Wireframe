package dev.psygamer.wireframe.item

import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.entity.Player
import dev.psygamer.wireframe.item.util.ClickResult
import dev.psygamer.wireframe.item.util.Hand
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.math.BlockHitResult
import dev.psygamer.wireframe.world.World

class BlockItem(
	identifier: Identifier,
	attributes: ItemAttributes = ItemAttributes(),
	
	private val block: Block
) : Item(identifier, attributes) {
	
	override fun onItemUsedOnBlock(
		itemStack: ItemStack, player: Player, hand: Hand,
		world: World, hitResult: BlockHitResult
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
		
		if (!world.setBlockState(placementState, targetPosition, World.UpdateFlag.DEFAULT_AND_RERENDER))
			return ClickResult.REJECTED
		
		world.notifyNeighbours(targetPosition, placementState.block)
		
		if (block == world.getBlock(targetPosition)) {
			applyBlockStateTags(world, targetPosition, world.getBlockState(targetPosition), itemStack)
		}
		
		block.onBlockPlaced(world, targetPosition, oldBlockState, placementState)
		if (player != null)
			block.onBlockPlacedByPlayer(world, targetPosition, oldBlockState, placementState, player)
		
		if (!player?.isCreative)
			itemStack.shrink(1)
		
		return ClickResult.ACCEPTED
	}
	
	private fun applyBlockStateTags(
		world: World, position: BlockPosition,
		blockState: BlockState, itemStack: ItemStack
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
		valueName: String
	): BlockState {
		return property.getValue(valueName)
			.map { blockState.setValue(property, it) }
			.orElse(blockState)
	}
}