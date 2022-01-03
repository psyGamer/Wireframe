package dev.psygamer.wireframe.block

import dev.psygamer.wireframe.block.entity.BlockEntity
import dev.psygamer.wireframe.block.entity.BlockEntity.Definition
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.block.state.BlockStateDefinition
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.entity.Entity
import dev.psygamer.wireframe.entity.Player
import dev.psygamer.wireframe.entity.ProjectileEntity
import dev.psygamer.wireframe.internal.block.InternalBlock
import dev.psygamer.wireframe.item.BlockItem
import dev.psygamer.wireframe.item.Item
import dev.psygamer.wireframe.item.ItemAttributes
import dev.psygamer.wireframe.item.ItemStack
import dev.psygamer.wireframe.item.util.ClickResult
import dev.psygamer.wireframe.item.util.Hand
import dev.psygamer.wireframe.registry.BlockEntityRegistry
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.math.BlockHitResult
import dev.psygamer.wireframe.util.wfWrapped
import dev.psygamer.wireframe.world.BlockReader
import dev.psygamer.wireframe.world.World
import java.util.*

open class Block constructor(
	val identifier: Identifier?,
	
	val blockAttributes: BlockAttributes? = null,
	val itemAttributes: ItemAttributes? = null,
	
	vararg blockProperties: BlockProperty<*>,
	
	internal val mcNative: net.minecraft.block.Block
) {
	
	val blockItem: Item?
	
	val stateDefinition: BlockStateDefinition = BlockStateDefinition.get(mcNative.stateDefinition)
	val defaultBlockState: BlockState = this.stateDefinition.defaultState
	
	private var blockEntityDefinition: Definition? = null
	
	constructor(mcNative: net.minecraft.block.Block)
			: this(
		identifier = mcNative.registryName?.wfWrapped,
		mcNative = mcNative
	)
	
	init {
		if (mcNative is InternalBlock && blockProperties.isNotEmpty())
			mcNative.registerBlockProperties(blockProperties)
		
		if (blockAttributes?.hasItem() == true)
			this.blockItem = BlockItem(identifier, itemAttributes, this)
		else
			this.blockItem = null
	}
	
	protected fun registerDefaultBlockState(blockState: BlockState) {
		if (mcNative is InternalBlock)
			mcNative.setDefaultBlockState(blockState)
	}
	
	protected fun registerBlockEntity(blockEntityCreator: () -> BlockEntity) {
		if (blockEntityDefinition != null)
			return
		
		blockEntityDefinition = Definition(identifier, blockEntityCreator, arrayOf(this))
		BlockEntityRegistry.register(blockEntityDefinition)
	}
	
	/* Placement State */
	fun getPlacementState(
		world: World, player: Player, hand: Hand,
		usedItemStack: ItemStack, hitResult: BlockHitResult
	): BlockState {
		return defaultBlockState
	}
	
	/* Block Events */
	fun onBlockPlaced(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState,
	) {
	}
	
	fun onBlockPlacedByPlayer(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState, player: Player
	) {
	}
	
	fun onBlockRemoved(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState,
	) {
	}
	
	fun onBlockRemovedByPlayer(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState, player: Player
	) {
	}
	
	fun onTick(
		world: World, blockPosition: BlockPosition, blockState: BlockState
	) {
	}
	
	fun onRandomTick(
		world: World, blockPosition: BlockPosition, blockState: BlockState, random: Random
	) {
	}
	
	fun onEntityStepOnBlock(
		world: World, blockPosition: BlockPosition, blockState: BlockState, entity: Entity
	) {
	}
	
	fun onEntityFallOnBlock(
		world: World, blockPosition: BlockPosition, blockState: BlockState, entity: Entity, fallDistance: Float
	) {
	}
	
	fun onUsedByPlayer(
		world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player
	): ClickResult? {
		return ClickResult.PASS
	}
	
	fun onAttackedByPlayer(
		world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player
	) {
	}
	
	fun onHitByProjectile(
		world: World, blockPosition: BlockPosition, blockState: BlockState, projectile: ProjectileEntity
	) {
	}
	
	/* Block Entity */
	val hasBlockEntity: Boolean
		get() = blockEntityDefinition != null
	
	fun createBlockEntity(): BlockEntity? {
		return blockEntityDefinition?.blockEntitySupplier?.get()
	}
	
	fun createPickBlockStack(
		world: BlockReader, blockPosition: BlockPosition, blockState: BlockState
	): ItemStack {
		return ItemStack.get(net.minecraft.item.ItemStack.EMPTY)
	}
	
	fun createBlockDrops(
		world: World, blockPosition: BlockPosition, blockState: BlockState
	): List<ItemStack> {
		return emptyList()
	}
}

internal val net.minecraft.block.Block.wfWrapped: Block
	get() = Block(this)