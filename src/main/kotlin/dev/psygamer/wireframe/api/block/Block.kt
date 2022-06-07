package dev.psygamer.wireframe.api.block

import java.util.*
import dev.psygamer.wireframe.api.block.entity.BlockEntity
import dev.psygamer.wireframe.api.block.entity.BlockEntity.Definition
import dev.psygamer.wireframe.api.entity.*
import dev.psygamer.wireframe.api.item.*
import dev.psygamer.wireframe.api.item.util.*
import dev.psygamer.wireframe.api.registry.*
import dev.psygamer.wireframe.api.world.*
import dev.psygamer.wireframe.nativeapi.block.NativeBlock
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.*
import dev.psygamer.wireframe.util.math.BlockHitResult

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

	protected fun <T : BlockEntity> registerBlockEntity(blockEntityCreator: () -> T) {
		if (blockEntityDefinition != null)
			return

		blockEntityDefinition = Definition(identifier, blockEntityCreator, arrayOf(this))
		BlockEntityRegistry.register(blockEntityDefinition!!)
	}

	open fun getPlacementState(
		world: World, player: Player?, hand: Hand,
		usedItemStack: ItemStack, hitResult: BlockHitResult,
	): BlockState {
		return defaultBlockState
	}

	open fun onBlockPlaced(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState,
	) {
	}

	open fun onBlockPlacedByPlayer(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState, player: Player,
	) {
	}

	open fun onBlockRemoved(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState,
	) {
	}

	open fun onBlockRemovedByPlayer(
		world: World, blockPosition: BlockPosition,
		oldBlockState: BlockState, newBlockState: BlockState, player: Player,
	) {
	}

	open fun onTick(
		world: World, blockPosition: BlockPosition, blockState: BlockState,
	) {
	}

	open fun onRandomTick(
		world: World, blockPosition: BlockPosition, blockState: BlockState, random: Random,
	) {
	}

	open fun onEntityStepOnBlock(
		world: World, blockPosition: BlockPosition, blockState: BlockState, entity: Entity,
	) {
	}

	open fun onEntityFallOnBlock(
		world: World, blockPosition: BlockPosition, blockState: BlockState, entity: Entity, fallDistance: Float,
	) {
	}

	open fun onUsedByPlayer(
		world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player,
	): ClickResult {
		return ClickResult.PASS
	}

	open fun onAttackedByPlayer(
		world: World, blockPosition: BlockPosition, blockState: BlockState, player: Player,
	) {
	}

	open fun onHitByProjectile(
		world: World, blockPosition: BlockPosition, blockState: BlockState, projectile: ProjectileEntity,
	) {
	}

	@get:JvmName("hasBlockEntity")
	val hasBlockEntity: Boolean
		get() = blockEntityDefinition != null

	open fun createBlockEntity(): BlockEntity? {
		return blockEntityDefinition?.blockEntitySupplier?.invoke()
	}

	open fun createPickBlockStack(
		world: BlockReader, blockPosition: BlockPosition, blockState: BlockState,
	): ItemStack {
		return net.minecraft.item.ItemStack.EMPTY.wfWrapped
	}

	open fun createBlockDrops(
		blockState: BlockState,
	): List<ItemStack> {
		return emptyList()
	}
}
