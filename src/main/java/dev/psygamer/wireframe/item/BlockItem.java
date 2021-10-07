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
	public ClickResult onItemUsedOnBlock(final ItemStack usedItemStack, final World world, final Player player, final Hand hand, final BlockHitResult hitResult) {
		final BlockPosition targetPosition = world.isReplaceable(
				hitResult.getBlockPosition()
		) ? hitResult.getBlockPosition() : hitResult.getBlockPosition().offset(hitResult.getDirection());
		
		if (!world.isReplaceable(targetPosition))
			return ClickResult.REJECTED;
		
		final BlockState placementState = this.block.getPlacementState(usedItemStack, world, player, hand, hitResult);
		
		if (!world.setBlockState(placementState, targetPosition))
			return ClickResult.REJECTED;
		
		world.notifyNeighbours(targetPosition, placementState.getBlock());
		
		applyBlockStateTags(usedItemStack, world, world.getBlockState(targetPosition), targetPosition);
		
		this.block.onBlockPlaced(world.getBlockState(targetPosition), world.getBlockState(targetPosition), targetPosition, world);
		this.block.onBlockPlacedByPlayer(world.getBlockState(targetPosition), world.getBlockState(targetPosition), targetPosition, world, player);
		
		if (player != null && !player.isCreative()) {
			usedItemStack.shrink(1);
		}
		
		return ClickResult.ACCEPTED;
	}
	
	private void applyBlockStateTags(final ItemStack itemStack, final World world, final BlockState blockState, final BlockPosition position) {
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
		
		world.setBlockState(blockState,position);
	}
}
