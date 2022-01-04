package dev.psygamer.wireframe.block

import dev.psygamer.wireframe.block.entity.BlockEntity
import dev.psygamer.wireframe.block.entity.BlockEntity.Definition
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.block.state.BlockStateDefinition
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.entity.Entity
import dev.psygamer.wireframe.entity.Player
import dev.psygamer.wireframe.entity.ProjectileEntity
import dev.psygamer.wireframe.internal.block.NativeBlock
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
import dev.psygamer.wireframe.wfWrapped
import dev.psygamer.wireframe.world.BlockReader
import dev.psygamer.wireframe.world.World
import java.util.*

open class Block {
	
	internal val mcNative: net.minecraft.block.Block
	
	val identifier: Identifier
	
	val blockAttributes: BlockAttributes?
	val itemAttributes: ItemAttributes?
	
	val blockProperties: Array<out BlockProperty<*>>
	val blockItem: Item?
	
	val stateDefinition: BlockStateDefinition
	val defaultBlockState: BlockState
	
	private var blockEntityDefinition: Definition? = null
	
	constructor(mcNative: net.minecraft.block.Block) {
		this.mcNative = mcNative
		
		this.identifier = mcNative.registryName!!.wfWrapped
		
		this.blockAttributes = null
		this.itemAttributes = null
		
		this.blockProperties = emptyArray()
		
		this.stateDefinition = mcNative.stateDefinition.wfWrapped
		this.defaultBlockState = this.stateDefinition.defaultState
		
		this.blockItem = null
	}
	
	protected constructor(
		identifier: Identifier,
		
		blockAttributes: BlockAttributes,
		itemAttributes: ItemAttributes = ItemAttributes(),
		
		vararg blockProperties: BlockProperty<*>,
	) {
		this.mcNative = NativeBlock(this, blockAttributes)
		
		this.identifier = identifier
		
		this.blockAttributes = blockAttributes
		this.itemAttributes = itemAttributes
		
		this.blockProperties = blockProperties
		
		this.stateDefinition = mcNative.stateDefinition.wfWrapped
		this.defaultBlockState = this.stateDefinition.defaultState
		
		if (blockProperties.isNotEmpty())
			mcNative.registerBlockProperties(blockProperties)
		
		if (blockAttributes.hasItem)
			this.blockItem = BlockItem(identifier, itemAttributes, this)
		else
			this.blockItem = null
	}
	
	protected fun registerDefaultBlockState(blockState: BlockState) {
		if (mcNative is NativeBlock)
			mcNative.setDefaultBlockState(blockState)
	}
	
	protected fun registerBlockEntity(blockEntityCreator: () -> BlockEntity) {
		if (blockEntityDefinition != null)
			return
		
		blockEntityDefinition = Definition(identifier, blockEntityCreator, arrayOf(this))
		BlockEntityRegistry.register(blockEntityDefinition)
	}
	
	/* Placement State */
	open fun getPlacementState(
		world: World, player: Player, hand: Hand,
		usedItemStack: ItemStack, hitResult: BlockHitResult
	): BlockState {
		return defaultBlockState
	}
	
	/* Block Events */
	open fun onBlockPlaced(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState,
	) {
	}
	
	open fun onBlockPlacedByPlayer(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState, player: Player
	) {
	}
	
	open fun onBlockRemoved(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState,
	) {
	}
	
	open fun onBlockRemovedByPlayer(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState, player: Player
	) {
	}
	
	open fun onTick(
		world: World, blockPosition: BlockPosition, blockState: BlockState
	) {
	}
	
	open fun onRandomTick(
		world: World, blockPosition: BlockPosition, blockState: BlockState, random: Random
	) {
	}
	
	open fun onEntityStepOnBlock(
		world: World, blockPosition: BlockPosition, blockState: BlockState, entity: Entity
	) {
	}
	
	open fun onEntityFallOnBlock(
		world: World, blockPosition: BlockPosition, blockState: BlockState, entity: Entity, fallDistance: Float
	) {
	}
	
	open fun onUsedByPlayer(
		world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player
	): ClickResult {
		return ClickResult.PASS
	}
	
	open fun onAttackedByPlayer(
		world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player
	) {
	}
	
	open fun onHitByProjectile(
		world: World, blockPosition: BlockPosition, blockState: BlockState, projectile: ProjectileEntity
	) {
	}
	
	/* Block Entity */
	@get:JvmName("hasBlockEntity")
	val hasBlockEntity: Boolean
		get() = blockEntityDefinition != null
	
	open fun createBlockEntity(): BlockEntity? {
		return blockEntityDefinition?.blockEntitySupplier?.invoke()
	}
	
	open fun createPickBlockStack(
		world: BlockReader, blockPosition: BlockPosition, blockState: BlockState
	): ItemStack {
		return net.minecraft.item.ItemStack.EMPTY.wfWrapped
	}
	
	open fun createBlockDrops(
		blockState: BlockState
	): List<ItemStack> {
		return emptyList()
	}
}
