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

/** Extend this class to create your own blocks */
public class Block {
	
	private final net.minecraft.block.Block internal;
	private final Item blockItem;
	
	private final Identifier identifier;
	
	private final BlockAttributes blockAttributes;
	private final ItemAttributes itemAttributes;
	
	private final BlockState defaultBlockState;
	private final BlockStateDefinition stateDefinition;
	
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
	
	/**
	 * Creates a new {@link Block} with a {@link BlockItem} (unless disabled), and automatically registers it.
	 * The {@link BlockItem} uses default {@link ItemAttributes attributes} since no custom ones are supplied.
	 * <p>
	 * To disable the automatic creation of a {@link BlockItem}, set the {@link BlockAttributes#noItem()} flag.
	 *
	 * @param identifier      A unique {@link Identifier} for this block.
	 * @param blockAttributes A set of {@link BlockAttributes attributes} which define how the {@link Block} works.
	 */
	public Block(final Identifier identifier, final BlockAttributes blockAttributes) {
		this(identifier, blockAttributes, new ItemAttributes());
	}
	
	/**
	 * Creates a new {@link Block} with a {@link BlockItem} (unless disabled), and automatically registers it.
	 * The {@link BlockItem} uses default {@link ItemAttributes attributes} since no custom ones are supplied.
	 * <p>
	 * To disable the automatic creation of a {@link BlockItem}, set the {@link BlockAttributes#noItem()} flag.
	 *
	 * @param identifier      A unique {@link Identifier} for this block.
	 * @param blockAttributes A set of {@link BlockAttributes attributes} which define how the {@link Block} works.
	 * @param blockProperties An array of {@link BlockProperty BlockProperties} which are used for {@link BlockState BlockStates}.
	 */
	public Block(final Identifier identifier, final BlockAttributes blockAttributes, final BlockProperty<?>... blockProperties) {
		this(identifier, blockAttributes, new ItemAttributes(), blockProperties);
	}
	
	/**
	 * Creates a new {@link Block} with a {@link BlockItem} (unless disabled), and automatically registers it.
	 * <p>
	 * To disable the automatic creation of a {@link BlockItem}, set the {@link BlockAttributes#noItem()} flag.
	 *
	 * @param identifier      A unique {@link Identifier} for this block.
	 * @param blockAttributes A set of {@link BlockAttributes attributes} which define how the {@link Block} works.
	 * @param itemAttributes  A set of {@link ItemAttributes attributes} which define how the {@link Item Item} works.
	 */
	public Block(final Identifier identifier, final BlockAttributes blockAttributes, final ItemAttributes itemAttributes) {
		this(identifier, blockAttributes, itemAttributes, new BlockProperty[0]);
	}
	
	/**
	 * Creates a new {@link Block} with a {@link BlockItem} (unless disabled), and automatically registers it.
	 * <p>
	 * To disable the automatic creation of a {@link BlockItem}, set the {@link BlockAttributes#noItem()} flag.
	 *
	 * @param identifier      A unique {@link Identifier} for this block.
	 * @param blockAttributes A set of {@link BlockAttributes attributes} which define how the {@link Block} works.
	 * @param itemAttributes  A set of {@link ItemAttributes attributes} which define how the {@link Item Item} works.
	 * @param blockProperties An array of {@link BlockProperty BlockProperties} which are used for {@link BlockState BlockStates}.
	 */
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
	
	/** Internal, do not use! */
	public static Block get(final net.minecraft.block.Block internal) {
		if (internal == null)
			return null;
		
		return new Block(internal);
	}
	
	/**
	 * Sets a new default {@link BlockState}.
	 * Might be currently bugged.
	 *
	 * @param blockState The new default {@link BlockState}.
	 */
	protected void registerDefaultBlockState(final BlockState blockState) {
		if (this.internal instanceof InternalBlock) {
			((InternalBlock) this.internal).setDefaultBlockState(blockState);
		} // TODO Validate that this works properly
	}
	
	/**
	 * Registers and links the specified {@link BlockEntity} to this {@link Block}.
	 *
	 * @param blockEntityCreator A supplier which creates a <strong>new</strong> instance.
	 */
	protected void registerBlockEntity(final Supplier<BlockEntity> blockEntityCreator) {
		if (this.blockEntityDefinition != null)
			return;
		
		this.blockEntityDefinition = new BlockEntity.Definition(this.identifier, blockEntityCreator, new Block[] { this });
		
		BlockEntityRegistry.register(this.blockEntityDefinition);
	}
	
	/**
	 * Determines in which {@link BlockState} the {@link Block} should be placed.
	 *
	 * @param usedItemStack The {@link ItemStack} which was used to place the {@link Block}.
	 * @param world         The {@link World} in which the {@link Block} was placed.
	 * @param player        The {@link Player} which placed the {@link Block}.
	 * @param hand          The {@link Hand} which was used to place the {@link Block}.
	 * @param hitResult     Additional information about the {@link Block} placement.
	 *
	 * @return The {@link BlockState} which the newly placed {@link Block} should have.
	 */
	public BlockState getPlacementState(
			final ItemStack usedItemStack,
			final World world, final Player player, final Hand hand, final BlockHitResult hitResult
	) {
		return getDefaultBlockState();
	}
	
	/**
	 * Called when this {@link Block} is placed.
	 *
	 * @param oldBlockState The {@link BlockState} <strong>before</strong> the {@link Block} was placed.
	 * @param newBlockState The {@link BlockState} <strong>after</strong> the {@link Block} was placed.
	 * @param blockPosition The {@link BlockPosition Position} where the {@link Block} was placed.
	 * @param world         The {@link World} in which the {@link Block} was placed.
	 */
	public void onBlockPlaced(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world
	) {
	}
	
	/**
	 * Called when this {@link Block} is placed by a {@link Player}.
	 *
	 * @param oldBlockState The {@link BlockState} <strong>before</strong> the {@link Block} was placed.
	 * @param newBlockState The {@link BlockState} <strong>after</strong> the {@link Block} was placed.
	 * @param blockPosition The {@link BlockPosition Position} where the {@link Block} was placed.
	 * @param world         The {@link World} in which the {@link Block} was placed.
	 * @param player        The {@link Player} which placed the {@link Block}.
	 */
	public void onBlockPlacedByPlayer(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
	}
	
	/**
	 * Called when this {@link Block} is destroyed.
	 *
	 * @param oldBlockState The {@link BlockState} <strong>before</strong> the {@link Block} was destroyed.
	 * @param newBlockState The {@link BlockState} <strong>after</strong> the {@link Block} was destroyed.
	 * @param blockPosition The {@link BlockPosition Position} where the {@link Block} was destroyed.
	 * @param world         The {@link World} in which the {@link Block} was destroyed.
	 */
	public void onBlockRemoved(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world
	) {
	}
	
	/**
	 * Called when this {@link Block} is destroyed by a {@link Player}.
	 *
	 * @param oldBlockState The {@link BlockState} <strong>before</strong> the {@link Block} was destroyed.
	 * @param newBlockState The {@link BlockState} <strong>after</strong> the {@link Block} was destroyed.
	 * @param blockPosition The {@link BlockPosition Position} where the {@link Block} was destroyed.
	 * @param world         The {@link World} in which the {@link Block} was destroyed.
	 * @param player        The {@link Player} which destroyed the {@link Block}.
	 */
	public void onBlockRemovedByPlayer(
			final BlockState oldBlockState,
			final BlockState newBlockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
	}
	
	/**
	 * Called every tick to update this {@link Block}.
	 * <p>
	 * <strong>Note:</strong> Every Second has 20 ticks.
	 *
	 * @param blockState    The {@link BlockState} of the {@link Block}
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param world         The {@link World} of the {@link Block}.
	 */
	public void onTick(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world
	) {
	}
	
	/**
	 * Called every random tick) to update this {@link Block}.
	 * <p>
	 * <strong>Note:</strong> The game rule <code>randomTickSpeed</code> (default: 3) determines how many blocks in a chunk will receive this tick.
	 *
	 * @param blockState    The {@link BlockState} of the {@link Block}.
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param world         The {@link World} of the {@link Block}.
	 */
	public void onRandomTick(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Random random
	) {
	}
	
	/**
	 * Called when an {@link Entity} steps on a {@link Block}.
	 *
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param world         The {@link World} of the {@link Block}.
	 * @param entity        The {@link Entity} which stepped on the {@link Block}.
	 */
	public void onEntityStepOnBlock(
			final BlockPosition blockPosition, final World world, final Entity entity
	) {
	}
	
	/**
	 * Called when an {@link Entity} falls on a {@link Block}.
	 *
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param world         The {@link World} of the {@link Block}.
	 * @param entity        The {@link Entity} which fell on the {@link Block}.
	 */
	public void onEntityFallOnBlock(
			final BlockPosition blockPosition, final World world, final Entity entity, final float fallDistance
	) {
	}
	
	/**
	 * Called when a {@link Player} uses (right clicks) this {@link Block}.
	 *
	 * @param blockState    The {@link BlockState} of the {@link Block}.
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param world         The {@link World} of the {@link Block}.
	 * @param player        The {@link Player} which used the {@link Block}.
	 *
	 * @return Weather the click was successful ({@link ClickResult#SUCCESS}), <br>
	 * it failed ({@link ClickResult#FAIL}) <br>
	 * or was ignored ({@link ClickResult#IGNORE}).
	 */
	public ClickResult onUsedByPlayer(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
		return ClickResult.IGNORE;
	}
	
	/**
	 * Called when a {@link Player} attacks (left clicks) this {@link Block}.
	 *
	 * @param blockState    The {@link BlockState} of the {@link Block}.
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param world         The {@link World} of the {@link Block}.
	 * @param player        The {@link Player} which used the {@link Block}.
	 */
	public void onAttackedByPlayer(
			final BlockState blockState,
			final BlockPosition blockPosition, final World world, final Player player
	) {
	}
	
	/**
	 * Called when a {@link ProjectileEntity} hits this {@link Block}.
	 *
	 * @param blockState The {@link BlockState} of the {@link Block}.
	 * @param world      The {@link World} of the {@link Block}.
	 * @param projectile The {@link ProjectileEntity} which hit the {@link Block}.
	 */
	public void onHitByProjectile(
			final BlockState blockState,
			final World world, final ProjectileEntity projectile
	) {
	}
	
	/**
	 * Called to determine which {@link ItemStack} the {@link Player} gets when, picking this {@link Block} (middle click).
	 * <p>
	 * <strong>Note:</strong> By default, this returns the {@link BlockItem} if it exists, otherwise <code>null</code>.
	 *
	 * @param blockState    The {@link BlockState} of the {@link Block}.
	 * @param blockPosition The {@link BlockPosition Position} of the {@link Block}.
	 * @param blockReader   The {@link BlockReader} of the {@link Block}.
	 *
	 * @return The {@link ItemStack} the {@link Player} should get, or <code>null</code> if it can't be picked.
	 */
	public ItemStack createPickBlockStack(
			final BlockState blockState, final BlockPosition blockPosition, final BlockReader blockReader
	) {
		if (this.blockItem == null)
			return null;
		
		return new ItemStack(this.blockItem);
	}
	
	/**
	 * Called to determine which {@link ItemStack ItemStacks} this block should drop when being destroyed.
	 * <p>
	 * <strong>Note:</strong> By default, this drops 1 {@link BlockItem} if it exists, otherwise nothing.
	 *
	 * @param blockState The {@link BlockState} of the {@link Block}.
	 *
	 * @return The {@link ItemStack ItemStacks} the {@link Block} should drop when being destroyed.
	 */
	public List<ItemStack> createBlockDrops(
			final BlockState blockState
	) {
		if (this.blockItem == null)
			return Lists.newArrayList();
		
		return Lists.newArrayList(new ItemStack(this.blockItem));
	}
	
	/** @return Weather this {@link Block} has a {@link BlockEntity} */
	public boolean hasBlockEntity() {
		return this.blockEntityDefinition != null;
	}
	
	/** @return A new instance of the {@link Block}'s {@link BlockEntity} */
	public BlockEntity createBlockEntity() {
		if (this.blockEntityDefinition == null)
			return null;
		
		return this.blockEntityDefinition.getBlockEntitySupplier().get();
	}
	
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
