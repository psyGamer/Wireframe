package dev.psygamer.wireframe.item;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.BlockProperty;
import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.item.util.ClickResult;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.util.TagCompound;
import dev.psygamer.wireframe.util.math.BlockHitResult;
import dev.psygamer.wireframe.world.World;

public class BlockItem extends Item {
	
	protected final Block block;
	
	public BlockItem(final Identifier identifier, final ItemAttributes attributes, final Block block) {
		super(identifier, attributes);
		
		this.block = block;
	}
	
	@Override
	public ClickResult onItemUsedOnBlock(final ItemStack usedItemStack, final World world, final Player player,
										 final Hand hand, final BlockHitResult hitResult
	) {
		final BlockPosition targetPosition = world.isReplaceable(
				hitResult.getBlockPosition()
		)
				? hitResult.getBlockPosition()
				: hitResult.getBlockPosition()
						   .offset(hitResult.getDirection());
		
		if (!world.isReplaceable(targetPosition))
			return ClickResult.REJECTED;
		
		final BlockState oldBlockState = world.getBlockState(targetPosition);
		final BlockState placementState = this.block.getPlacementState(usedItemStack, world, player, hand, hitResult);
		
		if (!world.setBlockState(placementState, targetPosition, World.UpdateFlag.DEFAULT_AND_RERENDER))
			return ClickResult.REJECTED;
		
		world.notifyNeighbours(targetPosition, placementState.getBlock());
		
		if (block == world.getBlock(targetPosition)) {
			applyBlockStateTags(world, targetPosition, world.getBlockState(targetPosition), usedItemStack);
		}
		
		this.block.onBlockPlaced(oldBlockState, placementState, targetPosition, world);
		this.block.onBlockPlacedByPlayer(oldBlockState, placementState, targetPosition, world, player);
		
		if (player != null && !player.isCreative())
			usedItemStack.shrink(1);
		
		return ClickResult.ACCEPTED;
	}
	
	private void applyBlockStateTags(final World world, final BlockPosition position,
									 final BlockState blockState, final ItemStack itemStack
	) {
		final TagCompound itemTag = itemStack.getTag();
		
		if (itemTag == null)
			return;
		
		final TagCompound blockStateTag = itemTag.getCompound("BlockStateTag");
		
		for (final String key : blockStateTag.getKeys()) {
			final BlockProperty<?> property = blockState.getProperty(key);
			
			if (property != null) {
				blockState.setObjectProperty(property, property.getValue(key));
			}
		}
		
		world.setBlockState(blockState, position);
	}
	
	/* private void applyBlockEntityTags(final World world, BlockPosition position,
									  final Player player, final ItemStack itemStack
	) {
		final TagCompound itemTag = itemStack.getTag();
		
		if (itemTag == null)
			return;
		
		final TagCompound blockEntityTag = itemTag.getCompound("BlockEntityTag");
		
		if (blockEntityTag == null)
			return;
		
		BlockEntity blockEntity = world.getBlockEntity
	} */ // TODO Include feature once BlockEntities are more developed
}
