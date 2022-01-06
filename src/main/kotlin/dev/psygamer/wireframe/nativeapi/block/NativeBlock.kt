package dev.psygamer.wireframe.nativeapi.block

import com.google.common.collect.ImmutableMap
import com.mojang.serialization.MapCodec
import dev.psygamer.wireframe.api.block.BlockAttributes
import dev.psygamer.wireframe.api.block.BlockProperty
import dev.psygamer.wireframe.nativeapi.item.NativeItem
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.math.BlockHitResult
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.ProjectileEntity
import net.minecraft.fluid.FluidState
import net.minecraft.item.BlockItemUseContext
import net.minecraft.item.ItemStack
import net.minecraft.loot.LootContext
import net.minecraft.state.Property
import net.minecraft.state.StateContainer
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.util.math.RayTraceResult
import net.minecraft.world.IBlockReader
import net.minecraft.world.World
import net.minecraft.world.server.ServerWorld
import net.minecraftforge.fml.common.ObfuscationReflectionHelper
import java.util.*

@Suppress("DEPRECATION")
class NativeBlock(
	private val block: dev.psygamer.wireframe.api.block.Block,
	
	blockAttributes: BlockAttributes
) : Block(blockAttributes.mcNative.createProperties()) {
	
	companion object {
		
		// Get the Block#stateDefinition field
		val stateDefinitionField = ObfuscationReflectionHelper.findField(Block::class.java, "field_176227_L")
		
		init {
			stateDefinitionField.isAccessible = true
		}
	}
	
	init {
		this.setRegistryName(
			block.identifier.namespace,
			block.identifier.path
		)
	}
	
	fun setDefaultBlockState(blockState: dev.psygamer.wireframe.api.block.BlockState) {
		registerDefaultState(blockState.mcNative)
	}
	
	fun registerBlockProperties(blockProperties: Array<out BlockProperty<*>>) {
		val builder = StateContainer.Builder<Block, BlockState>(this)
		blockProperties.forEach { builder.add(it.mcNative) }
		
		val stateDefinition =
			builder.create(
				Block::defaultBlockState
			) { block: Block,
				properties: ImmutableMap<Property<*>, Comparable<*>>,
				codec: MapCodec<BlockState> ->
				
				BlockState(block, properties, codec)
			} // @Kotlin: Why can't I just use `Block::new`???
		
		var defaultState = stateDefinition.any()
		
		blockProperties.forEach {
			defaultState = setValue(it, defaultState)
		}
		
		stateDefinitionField.set(this, stateDefinition)
		registerDefaultState(defaultState)
	}
	
	private fun <T : Comparable<T>, S : T> setValue(blockProperty: BlockProperty<T>, blockState: BlockState): BlockState {
		return blockState.setValue(blockProperty.mcNative, blockProperty.defaultValue)
	}
	
	/* Block Events */
	
	override fun onPlace(
		newBlockState: BlockState, world: World, pos: BlockPos,
		oldBlockState: BlockState, isMoving: Boolean
	) {
		block.onBlockPlaced(world.wfWrapped, pos.wfWrapped, oldBlockState.wfWrapped, newBlockState.wfWrapped)
	}
	
	override fun onRemove(newBlockState: BlockState, world: World, pos: BlockPos, oldBlockState: BlockState, isMoving: Boolean) {
		block.onBlockRemoved(
			world.wfWrapped, pos.wfWrapped, oldBlockState.wfWrapped, newBlockState.wfWrapped
		)
		
		super.onRemove(newBlockState, world, pos, oldBlockState, isMoving)
	}
	
	override fun use(
		state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, hand: Hand, hitResult: BlockRayTraceResult
	): ActionResultType {
		return block.onUsedByPlayer(world.wfWrapped, pos.wfWrapped, state.wfWrapped, player.wfWrapped).mcNative
	}
	
	override fun getDrops(state: BlockState, builder: LootContext.Builder): List<ItemStack> {
		return block.createBlockDrops(state.wfWrapped).map { it.mcNative }
	}
	
	override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
		block.onRandomTick(world.wfWrapped, pos.wfWrapped, state.wfWrapped, random)
	}
	
	override fun tick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
		block.onTick(world.wfWrapped, pos.wfWrapped, state.wfWrapped)
	}
	
	override fun attack(state: BlockState, world: World, pos: BlockPos, player: PlayerEntity) {
		block.onAttackedByPlayer(world.wfWrapped, pos.wfWrapped, state.wfWrapped, player.wfWrapped)
	}
	
	override fun onProjectileHit(world: World, state: BlockState, hitResult: BlockRayTraceResult, projectile: ProjectileEntity) {
		block.onHitByProjectile(world.wfWrapped, hitResult.blockPos.wfWrapped, state.wfWrapped, projectile.wfWrapped)
	}
	
	override fun stepOn(world: World, pos: BlockPos, entity: Entity) {
		block.onEntityStepOnBlock(world.wfWrapped, pos.wfWrapped, world.getBlockState(pos).wfWrapped, entity.wfWrapped)
	}
	
	override fun getStateForPlacement(context: BlockItemUseContext): BlockState {
		return block.getPlacementState(
			context.level.wfWrapped,
			context.player?.wfWrapped,
			context.hand.wfWrapped,
			context.itemInHand.wfWrapped,
			
			BlockHitResult.get(NativeItem.hitResultField[context] as BlockRayTraceResult)
		).mcNative
	}
	
	override fun setPlacedBy(world: World, pos: BlockPos, blockState: BlockState, placer: LivingEntity?, itemStack: ItemStack) {
		if (placer !is PlayerEntity)
			return
		
		block.onBlockPlacedByPlayer(
			world.wfWrapped,
			pos.wfWrapped,
			world.getBlockState(pos).wfWrapped,
			blockState.wfWrapped,
			placer.wfWrapped
		)
	}
	
	override fun fallOn(world: World, pos: BlockPos, entity: Entity, distance: Float) {
		super.fallOn(world, pos, entity, distance)
		
		block.onEntityFallOnBlock(
			world.wfWrapped,
			pos.wfWrapped,
			world.getBlockState(pos).wfWrapped,
			entity.wfWrapped,
			distance
		)
	}
	
	override fun hasTileEntity(state: BlockState): Boolean {
		return block.hasBlockEntity
	}
	
	override fun createTileEntity(state: BlockState, blockReader: IBlockReader): TileEntity? {
		return block.createBlockEntity()?.mcNative
	}
	
	override fun removedByPlayer(
		state: BlockState, world: World, pos: BlockPos, player: PlayerEntity, willHarvest: Boolean, fluid: FluidState
	): Boolean {
		block.onBlockRemovedByPlayer(
			world.wfWrapped,
			pos.wfWrapped,
			world.getBlockState(pos).wfWrapped,
			state.wfWrapped,
			player.wfWrapped
		)
		
		return super.removedByPlayer(state, world, pos, player, willHarvest, fluid)
	}
	
	override fun getPickBlock(
		state: BlockState, target: RayTraceResult, blockReader: IBlockReader, pos: BlockPos, player: PlayerEntity
	): ItemStack {
		return block.createPickBlockStack(blockReader.wfWrapped, pos.wfWrapped, state.wfWrapped).mcNative
	}
}