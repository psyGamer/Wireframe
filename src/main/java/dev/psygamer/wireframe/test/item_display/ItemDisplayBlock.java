package dev.psygamer.wireframe.test.item_display;

import dev.psygamer.wireframe.api.block.Block;
import dev.psygamer.wireframe.api.block.BlockAttributes;
import dev.psygamer.wireframe.api.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.api.block.attributes.Material;
import dev.psygamer.wireframe.api.block.entity.BlockEntity;
import dev.psygamer.wireframe.api.block.state.BlockState;
import dev.psygamer.wireframe.api.block.state.property.DirectionBlockProperty;
import dev.psygamer.wireframe.api.entity.Player;
import dev.psygamer.wireframe.api.item.ItemAttributes;
import dev.psygamer.wireframe.api.item.util.ClickResult;
import dev.psygamer.wireframe.api.world.World;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;

import org.jetbrains.annotations.NotNull;

/*
 * This class is an example of how to create blocks in Wireframe.
 * The goal is to use as few classes from net.minecraft or net.minecraftforge as possible.
 *
 * Once this block is finished it should be an item display.
 */

public class ItemDisplayBlock extends Block {
	
	public static final DirectionBlockProperty FACING = new DirectionBlockProperty("facing");
	
	public ItemDisplayBlock() {
		super(new Identifier("wireframe", "item_display"),
		
			  new BlockAttributes(Material.WOOD)
					  .harvestLevel(HarvestLevel.STONE),
			  new ItemAttributes()
					  .maxStackSize(15),
		
			  FACING
		);
		
		registerBlockEntity(ItemDisplayBlockEntity::new);
	}
	
	@NotNull
	@Override
	public ClickResult onUsedByPlayer(@NotNull final World world, @NotNull final BlockPosition blockPosition,
									  @NotNull final BlockState blockState,
									  @NotNull final Player player
	) {
		final BlockEntity blockEntity = world.getBlockEntity(blockPosition);
		
		if (blockEntity instanceof ItemDisplayBlockEntity)
			((ItemDisplayBlockEntity) blockEntity).addClick();
		
		return ClickResult.ACCEPTED;
	}
}
