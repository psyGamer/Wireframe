package dev.psygamer.wireframe.internal.item;

import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.block.InternalBlock;
import dev.psygamer.wireframe.item.Item;
import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.util.math.BlockHitResult;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InternalItem extends net.minecraft.item.Item {
	
	public static final Field hitResultField;
	
	private static final Map<net.minecraft.item.Item, Item> cachedItems = new HashMap<>();
	
	static {
		Field tmp;
		
		try {
			tmp = ItemUseContext.class.getDeclaredField("hitResult");
		} catch (final NoSuchFieldException ignored) {
			tmp = null; // Should never happen
		}
		
		hitResultField = tmp;
		hitResultField.setAccessible(true);
	} // TODO Use AT or extend ItemUseContext
	
	private final Item item;
	
	/* Item Events */
	
	public InternalItem(final Item item, final ItemAttributes attributes) {
		super(attributes.getInternal()
						.createProperties()
		);
		
		this.item = item;
		
		this.setRegistryName(
				item.getIdentifier()
					.getNamespace(),
				item.getIdentifier()
					.getPath()
		);
		
		cachedItems.put(this, item);
	}
	
	public static Item convertItem(final net.minecraft.item.Item item) {
		return cachedItems.get(item);
	}
	
	@Override
	public ActionResultType useOn(final ItemUseContext context) {
		try {
			return this.item.onItemUsedOnBlock(
							   dev.psygamer.wireframe.item.ItemStack.get(context.getItemInHand()),
							   dev.psygamer.wireframe.world.World.get(context.getLevel()),
							   dev.psygamer.wireframe.entity.Player.get(context.getPlayer()),
							   dev.psygamer.wireframe.item.util.Hand.get(context.getHand()),
					
							   BlockHitResult.get((BlockRayTraceResult) hitResultField.get(context))
					   )
							.getInternal();
		} catch (final IllegalAccessException e) {
			return ActionResultType.PASS;
		}
	} // TODO improve this mess
	
	@Override
	public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
		final ActionResult<dev.psygamer.wireframe.item.ItemStack> result
				= this.item.onItemUsed(
							  dev.psygamer.wireframe.item.ItemStack.get(player.getItemInHand(hand)),
							  dev.psygamer.wireframe.world.World.get(world),
							  dev.psygamer.wireframe.entity.Player.get(player),
							  dev.psygamer.wireframe.item.util.Hand.get(hand)
					  )
						   .toInternal();
		
		return new ActionResult<>(
				result.getResult(),
				result.getObject()
					  .getInternal()
		);
	}
	
	@Override
	public boolean mineBlock(final ItemStack itemStack, final World world, final net.minecraft.block.BlockState state,
							 final BlockPos pos, final LivingEntity entity
	) {
		final BlockState blockState = InternalBlock.convertBlockState(state);
		
		return this.item.onBlockMined(
				dev.psygamer.wireframe.item.ItemStack.get(itemStack), blockState,
				dev.psygamer.wireframe.util.BlockPosition.get(pos),
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.entity.LivingEntity.get(entity)
		);
	}
	
	@Override
	public ActionResultType interactLivingEntity(final ItemStack item, final PlayerEntity player, final LivingEntity entity,
												 final Hand hand
	) {
		return this.item.onItemUsedOnEntity(
						   dev.psygamer.wireframe.item.ItemStack.get(player.getItemInHand(hand)),
						   dev.psygamer.wireframe.world.World.get(player.level),
						   dev.psygamer.wireframe.entity.Player.get(player),
						   dev.psygamer.wireframe.entity.LivingEntity.get(entity),
						   dev.psygamer.wireframe.item.util.Hand.get(hand)
				   )
						.getInternal();
	}
	
	@Override
	public void onCraftedBy(final ItemStack itemStack, final World world, final PlayerEntity player) {
		this.item.onItemCrafted(
				dev.psygamer.wireframe.item.ItemStack.get(itemStack),
				dev.psygamer.wireframe.world.World.get(world),
				dev.psygamer.wireframe.entity.Player.get(player)
		);
	}
}
