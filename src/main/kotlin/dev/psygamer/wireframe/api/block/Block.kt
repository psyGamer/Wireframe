package dev.psygamer.wireframe.api.block

import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.block.entity.BlockEntity.Definition

import dev.psygamer.wireframe.api.entity.Entity
import dev.psygamer.wireframe.api.entity.Player
import dev.psygamer.wireframe.api.entity.ProjectileEntity

import dev.psygamer.wireframe.api.item.BlockItem
import dev.psygamer.wireframe.api.item.Item
import dev.psygamer.wireframe.api.item.ItemAttributes
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.api.item.util.ClickResult
import dev.psygamer.wireframe.api.item.util.Hand

import dev.psygamer.wireframe.api.registry.BlockEntityRegistry
import dev.psygamer.wireframe.api.registry.BlockRegistry

import dev.psygamer.wireframe.api.world.BlockReader
import dev.psygamer.wireframe.api.world.World

import dev.psygamer.wireframe.nativeapi.block.NativeBlock
import dev.psygamer.wireframe.nativeapi.wfWrapped

import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.Identifier
import dev.psygamer.wireframe.util.math.BlockHitResult





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
	
	private var definition: Definition? = null
	
	constructor(mcNative: net.minecraft.block.Block) {
		this.identifier = mcNative.registryName!!.wfWrapped
		
		this.blockAttributes = null
		this.itemAttributes = null
		
		this.blockProperties = emptyArray()
		
		this.mcNative = mcNative
		
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
		this.identifier = identifier
		
		this.blockAttributes = blockAttributes
		this.itemAttributes = itemAttributes
		
		this.blockProperties = blockProperties
		
		this.mcNative = NativeBlock(this, blockAttributes)
		
		if (blockProperties.isNotEmpty())
			mcNative.registerBlockProperties(blockProperties)
		
		this.stateDefinition = mcNative.stateDefinition.wfWrapped
		this.defaultBlockState = this.stateDefinition.defaultState
		
		if (blockAttributes.hasItem)
			this.blockItem = BlockItem(identifier, itemAttributes, this)
		else
			this.blockItem = null
		
		BlockRegistry.register(this)
	}
	
	protected fun registerDefaultBlockState(blockState: BlockState) {
		if (mcNative is NativeBlock)
			mcNative.setDefaultBlockState(blockState)
	}
	
	protected fun registerBlockEntity(blockEntityCreator: () -> BlockEntity) {
		if (definition != null)
			return
		
		definition = Definition(identifier, blockEntityCreator, arrayOf(this))
		BlockEntityRegistry.register(definition!!)
	}
	
	/* Placement State */
	open fun getPlacementState(
		world: World, player: Player?, hand: Hand,
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
		get() = definition != null
	
	open fun createBlockEntity(): BlockEntity? {
		return definition?.blockEntitySupplier?.invoke()
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
