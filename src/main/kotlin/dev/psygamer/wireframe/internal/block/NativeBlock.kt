package dev.psygamer.wireframe.internal.block

import com.google.common.collect.ImmutableMap
import com.mojang.serialization.MapCodec
import dev.psygamer.wireframe.block.Block
import dev.psygamer.wireframe.block.BlockAttributes
import dev.psygamer.wireframe.block.state.BlockState
import dev.psygamer.wireframe.block.state.property.BlockProperty
import dev.psygamer.wireframe.entity.Player
import dev.psygamer.wireframe.internal.item.InternalItem
import dev.psygamer.wireframe.util.math.BlockHitResult
import dev.psygamer.wireframe.wfWrapped
import dev.psygamer.wireframe.world.BlockReader
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
import java.lang.reflect.Field
import java.util.*

@Suppress("DEPRECATION")
class NativeBlock(
	private val block: Block,
	
	blockAttributes: BlockAttributes
) : net.minecraft.block.Block(blockAttributes.mcNative.createProperties()) {
	
	companion object {
		
		val stateDefinitionField: Field = Block::class.java.getDeclaredField("stateDefinition")
		
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
	
	fun setDefaultBlockState(blockState: BlockState) {
		registerDefaultState(blockState.mcNative)
	}
	
	fun registerBlockProperties(blockProperties: Array<out BlockProperty<*>>) {
		val builder = StateContainer.Builder<net.minecraft.block.Block, net.minecraft.block.BlockState>(this)
		blockProperties.forEach { builder.add(it.mcNative) }
		
		val stateDefinition =
			builder.create(
				net.minecraft.block.Block::defaultBlockState
			) { block: net.minecraft.block.Block,
				properties: ImmutableMap<Property<*>, Comparable<*>>,
				codec: MapCodec<net.minecraft.block.BlockState> ->
				
				net.minecraft.block.BlockState(block, properties, codec)
			} // @Kotlin: Why can't I just use `net.minecraft.block.Block::new`???
		
		var defaultState = stateDefinition.any()
		
		blockProperties.forEach {
			defaultState = setValue(it, defaultState)
		}
		
		stateDefinitionField.set(this, stateDefinition)
		registerDefaultState(defaultState)
	}
	
	private fun <T : Comparable<T>, S : T> setValue(
		blockProperty: BlockProperty<T>,
		blockState: net.minecraft.block.BlockState
	): net.minecraft.block.BlockState {
		return blockState.setValue(blockProperty.mcNative, blockProperty.defaultValue)
	}
	
	/* Block Events */
	
	override fun onPlace(
		newBlockState: net.minecraft.block.BlockState, world: World, pos: BlockPos,
		oldBlockState: net.minecraft.block.BlockState, isMoving: Boolean
	) {
		block.onBlockPlaced(
			dev.psygamer.wireframe.world.World.get(world),
			
			pos.wfWrapped,
			oldBlockState.wfWrapped,
			newBlockState.wfWrapped
		)
	}
	
	override fun onRemove(
		newBlockState: net.minecraft.block.BlockState, world: World, pos: BlockPos,
		oldBlockState: net.minecraft.block.BlockState, isMoving: Boolean
	) {
		block.onBlockRemoved(
			dev.psygamer.wireframe.world.World.get(world),
			
			pos.wfWrapped,
			oldBlockState.wfWrapped,
			newBlockState.wfWrapped
		)
		
		super.onRemove(newBlockState, world, pos, oldBlockState, isMoving)
	}
	
	override fun use(
		state: net.minecraft.block.BlockState, world: World, pos: BlockPos,
		player: PlayerEntity, hand: Hand, hitResult: BlockRayTraceResult
	): ActionResultType {
		return block.onUsedByPlayer(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			state.wfWrapped,
			Player.get(player)
		).internal
	}
	
	override fun getDrops(state: net.minecraft.block.BlockState, builder: LootContext.Builder): List<ItemStack> {
		return block.createBlockDrops(
			state.wfWrapped
		).map { it.internal }
	}
	
	override fun randomTick(
		state: net.minecraft.block.BlockState,
		world: ServerWorld, pos: BlockPos, random: Random
	) {
		block.onRandomTick(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			state.wfWrapped,
			random
		)
	}
	
	override fun tick(
		state: net.minecraft.block.BlockState,
		world: ServerWorld, pos: BlockPos, random: Random
	) {
		block.onTick(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			state.wfWrapped
		)
	}
	
	override fun attack(
		state: net.minecraft.block.BlockState,
		world: World, pos: BlockPos, player: PlayerEntity
	) {
		block.onAttackedByPlayer(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			state.wfWrapped,
			Player.get(player)
		)
	}
	
	override fun onProjectileHit(
		world: World, state: net.minecraft.block.BlockState,
		hitResult: BlockRayTraceResult, projectile: ProjectileEntity
	) {
		block.onHitByProjectile(
			dev.psygamer.wireframe.world.World.get(world),
			hitResult.blockPos.wfWrapped,
			state.wfWrapped, dev.psygamer.wireframe.entity.ProjectileEntity.get(projectile)
		)
	}
	
	override fun stepOn(world: World, pos: BlockPos, entity: Entity) {
		block.onEntityStepOnBlock(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			world.getBlockState(pos).wfWrapped, dev.psygamer.wireframe.entity.Entity.get(entity)
		)
	}
	
	override fun getStateForPlacement(context: BlockItemUseContext): net.minecraft.block.BlockState {
		return block.getPlacementState(
			dev.psygamer.wireframe.world.World.get(context.level),
			Player.get(context.player),
			dev.psygamer.wireframe.item.util.Hand.get(context.hand),
			dev.psygamer.wireframe.item.ItemStack.get(context.itemInHand),
			BlockHitResult.get(InternalItem.hitResultField[context] as BlockRayTraceResult)
		).mcNative
	}
	
	override fun setPlacedBy(
		world: World, pos: BlockPos, blockState: net.minecraft.block.BlockState, placer: LivingEntity?, itemStack: ItemStack
	) {
		if (placer is PlayerEntity) {
			block.onBlockPlacedByPlayer(
				dev.psygamer.wireframe.world.World.get(world),
				pos.wfWrapped,
				world.getBlockState(pos).wfWrapped,
				blockState.wfWrapped, Player.get(placer as PlayerEntity?)
			)
		}
	}
	
	override fun fallOn(world: World, pos: BlockPos, entity: Entity, distance: Float) {
		super.fallOn(world, pos, entity, distance)
		block.onEntityFallOnBlock(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			world.getBlockState(pos).wfWrapped, dev.psygamer.wireframe.entity.Entity.get(entity), distance
		)
	}
	
	override fun hasTileEntity(state: net.minecraft.block.BlockState): Boolean {
		return block.hasBlockEntity
	}
	
	override fun createTileEntity(state: net.minecraft.block.BlockState, blockReader: IBlockReader): TileEntity? {
		return block.createBlockEntity()?.mcNative
	}
	
	override fun removedByPlayer(
		state: net.minecraft.block.BlockState, world: World, pos: BlockPos,
		player: PlayerEntity, willHarvest: Boolean, fluid: FluidState
	): Boolean {
		block.onBlockRemovedByPlayer(
			dev.psygamer.wireframe.world.World.get(world),
			pos.wfWrapped,
			world.getBlockState(pos).wfWrapped,
			state.wfWrapped, Player.get(player)
		)
		return super.removedByPlayer(state, world, pos, player, willHarvest, fluid)
	}
	
	override fun getPickBlock(
		state: net.minecraft.block.BlockState, target: RayTraceResult,
		blockReader: IBlockReader, pos: BlockPos, player: PlayerEntity
	): ItemStack {
		return block.createPickBlockStack(
			BlockReader.get(blockReader),
			pos.wfWrapped,
			state.wfWrapped
		).internal
	}
}