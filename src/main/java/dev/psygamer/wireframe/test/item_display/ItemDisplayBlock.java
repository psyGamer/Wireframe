package dev.psygamer.wireframe.test.item_display;

import dev.psygamer.wireframe.block.Block;
import dev.psygamer.wireframe.block.BlockAttributes;
import dev.psygamer.wireframe.block.attributes.HarvestLevel;
import dev.psygamer.wireframe.block.attributes.Material;
import dev.psygamer.wireframe.block.entity.BlockEntity;
import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.block.state.property.DirectionBlockProperty;
import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.item.util.ClickResult;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.Identifier;
import dev.psygamer.wireframe.world.World;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
