package dev.psygamer.wireframe.block;

import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.BlockStateDefinition;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.entity.Entity;
import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.entity.ProjectileEntity;
import dev.psygamer.wireframe.internal.block.InternalBlock;
import dev.psygamer.wireframe.item.BlockItem;
import dev.psygamer.wireframe.item.Item;
import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.item.ItemStack;
import dev.psygamer.wireframe.item.util.ClickResult;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.registry.BlockEntityRegistry;
import dev.psygamer.wireframe.registry.BlockRegistry;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.math.BlockHitResult;
import dev.psygamer.wireframe.world.BlockReader;
import dev.psygamer.wireframe.world.World;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

public class Block {
	
	private final net.minecraft.block.Block internal;
	private final Item blockItem;
	
	private final Identifier identifier;
	
	private final BlockAttributes blockAttributes;
	private final ItemAttributes itemAttributes;
	
	private BlockState defaultBlockState;
	private BlockStateDefinition stateDefinition;
	
	private BlockEntity.Definition blockEntityDefinition;
	
	private Block(final net.minecraft.block.Block internal) {
		this.identifier = Identifier.get(internal.getRegistryName());
		
		this.blockAttributes = null;
		this.itemAttributes = null;
		
		this.internal = internal;
		this.stateDefinition = BlockStateDefinition.get(getInternal().getStateDefinition());
		this.defaultBlockState = this.stateDefinition.getDefaultState();
		
		this.blockItem = null;
	}
	
	public Block(final Identifier identifier, final BlockAttributes blockAttributes) {
		this(identifier, blockAttributes, new ItemAttributes());
	}
	
	public Block(final Identifier identifier, final BlockAttributes blockAttributes, final ItemAttributes itemAttributes) {
		this(identifier, blockAttributes, itemAttributes, new BlockProperty[0]);
	}
	
	public Block(final Identifier identifier,
				 final BlockAttributes blockAttributes, final ItemAttributes itemAttributes,
				 final BlockProperty<?>... blockProperties
	) {
		this.identifier = identifier;
		
		this.blockAttributes = blockAttributes;
		this.itemAttributes = itemAttributes;
		
		this.internal = new InternalBlock(this, this.blockAttributes);
		
		if (blockProperties.length > 0)
			((InternalBlock) this.internal).registerBlockProperties(blockProperties);
		
		this.stateDefinition = BlockStateDefinition.get(getInternal().getStateDefinition());
		this.defaultBlockState = this.stateDefinition.getDefaultState();
		
		if (blockAttributes.hasItem())
			this.blockItem = new BlockItem(identifier, itemAttributes, this);
		else
			this.blockItem = null;
		
		BlockRegistry.register(this);
	}
	
	public static Block get(final net.minecraft.block.Block internal) {
		if (internal == null)
			return null;
		
		return new Block(internal);
	}
	
	protected void registerDefaultBlockState(final BlockState blockState) {
		if (this.internal instanceof InternalBlock) {
			((InternalBlock) this.internal).setDefaultBlockState(blockState);
		}
	}
	
	protected void registerBlockEntity(final Supplier<BlockEntity> blockEntityCreator) {
		if (this.blockEntityDefinition != null)
			return;
		
		this.blockEntityDefinition = new BlockEntity.Definition(this.identifier, blockEntityCreator, new Block[] { this });
		
		BlockEntityRegistry.register(this.blockEntityDefinition);
	}
	
	/* Placement State */
	
	public BlockState getPlacementState(
			final ItemStack usedItemStack,
			final World world, final Player player, final Hand hand, final BlockHitResult hitResult
	) {
		return getDefaultBlockState();
	}
	
	/* Block Events */
	
	public void onBlockPlaced(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world
	) {
	}
	
	public void onBlockPlacedByPlayer(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
	}
	
	public void onBlockRemoved(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world
	) {
	}
	
	public void onBlockRemovedByPlayer(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
	}
	
	public void onTick(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world
	) {
	}
	
	public void onRandomTick(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Random random
	) {
	}
	
	public void onEntityStepOnBlock(
			final BlockPosition blockPosition, final World world, final Entity entity
	) {
	}
	
	public void onEntityFallOnBlock(
			final BlockPosition blockPosition, final World world, final Entity entity, final float fallDistance
	) {
	}
	
	public ClickResult onUsedByPlayer(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
		return ClickResult.PASS;
	}
	
	public void onAttackedByPlayer(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
	}
	
	public void onHitByProjectile(
			final BlockState blockState,
			final World world, final ProjectileEntity projectile
	) {
	}
	
	/* Block Entity */
	
	public boolean hasBlockEntity() {
		return this.blockEntityDefinition != null;
	}
	
	public BlockEntity createBlockEntity() {
		if (this.blockEntityDefinition == null)
			return null;
		
		return this.blockEntityDefinition.getBlockEntitySupplier().get();
	}
	
	/* Pick Block */
	
	public ItemStack createPickBlockStack(
			final BlockState blockState, final BlockPosition pos, final BlockReader blockReader
	) {
		return null;
	}
	
	/* Drops */
	
	public List<ItemStack> createBlockDrops(
			final BlockState blockState
	) {
		return Lists.newArrayList();
	}
	
	/* Getters */
	
	public Identifier getIdentifier() {
		return this.identifier;
	}
	
	public BlockAttributes getBlockAttributes() {
		return this.blockAttributes;
	}
	
	public ItemAttributes getItemAttributes() {
		return this.itemAttributes;
	}
	
	public BlockState getDefaultBlockState() {
		return this.defaultBlockState;
	}
	
	public BlockStateDefinition getStateDefinition() {
		return this.stateDefinition;
	}
	
	public Item getBlockItem() {
		return this.blockItem;
	}
	
	public net.minecraft.block.Block getInternal() {
		return this.internal;
	}
}
