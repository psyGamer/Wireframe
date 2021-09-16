package dev.psygamer.wireframe.internal.item;

import dev.psygamer.wireframe.block.state.BlockState;
import dev.psygamer.wireframe.internal.block.InternalBlockFoundation;
import dev.psygamer.wireframe.item.ItemAttributes;
import dev.psygamer.wireframe.item.ItemFoundation;
import dev.psygamer.wireframe.util.BlockPosition;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.animation.TimeValues;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class InternalItemFoundation extends Item {
	private static final Map<Item, ItemFoundation> cachedItems = new HashMap<>();
	
	private static final Field hitResultField;
	
	static {
		Field tmp;
		
		try {
			tmp = ItemUseContext.class.getDeclaredField("hitResult");
		} catch (final NoSuchFieldException ignored) {
			tmp = null;
		}
		
		hitResultField = tmp;
	} // TODO Use AT or extend ItemUseContext
	
	private final ItemFoundation item;
	
	/* Item Events */
	
	public InternalItemFoundation(final ItemFoundation item, final ItemAttributes attributes) {
		super(attributes.getInternal().createProperties());
		
		this.item = item;
		this.setRegistryName(item.getIdentifier().getNamespace(), item.getIdentifier().getPath());
		
		cachedItems.put(this, item);
	}
	
	public static ItemFoundation convertItem(final Item item) {
		return cachedItems.get(item);
	}
	
	@Override
	public ActionResult<ItemStack> use(final World world, final PlayerEntity player, final Hand hand) {
		final ActionResult<dev.psygamer.wireframe.item.ItemStack> result = this.item.onItemUsed(
				dev.psygamer.wireframe.item.ItemStack.fromInternal(player.getItemInHand(hand)),
				dev.psygamer.wireframe.world.World.get(world), player,
				dev.psygamer.wireframe.item.util.Hand.fromInternal(hand)
		).toInternal();
		
		return new ActionResult<>(result.getResult(), result.getObject().toInternal());
	}
	
	@Override
	public ActionResultType useOn(final ItemUseContext context) {
		try {
			return this.item.onItemUsedOnBlock(
					dev.psygamer.wireframe.item.ItemStack.fromInternal(context.getItemInHand()),
					dev.psygamer.wireframe.world.World.get(context.getLevel()), context.getPlayer(),
					dev.psygamer.wireframe.item.util.Hand.fromInternal(context.getHand()),
					(BlockRayTraceResult) hitResultField.get(context)
			).getInternal();
		} catch (final IllegalAccessException e) {
			return ActionResultType.PASS;
		}
	} // TODO improve this mess
	
	@Override
	public ActionResultType interactLivingEntity(final ItemStack item, final PlayerEntity player, final LivingEntity entity, final Hand hand) {
		return this.item.onItemUsedOnEntity(
				dev.psygamer.wireframe.item.ItemStack.fromInternal(player.getItemInHand(hand)),
				dev.psygamer.wireframe.world.World.get(player.level), player, entity,
				dev.psygamer.wireframe.item.util.Hand.fromInternal(hand)
		).getInternal();
	}
	
	@Override
	public boolean mineBlock(final ItemStack itemStack, final World world, final net.minecraft.block.BlockState state, final BlockPos pos, final LivingEntity entity) {
		final BlockState propertyContainer = InternalBlockFoundation.convertBlockState(
				InternalBlockFoundation.convertBlock(state.getBlock()), state
		);
		
		return this.item.onBlockMined(
				dev.psygamer.wireframe.item.ItemStack.fromInternal(itemStack), propertyContainer, new BlockPosition(pos.getX(), pos.getY(), pos.getZ()),
				dev.psygamer.wireframe.world.World.get(world), entity
		);
	}
	
	@Override
	public void onCraftedBy(final ItemStack itemStack, final World world, final PlayerEntity player) {
		this.item.onItemCrafted(
				dev.psygamer.wireframe.item.ItemStack.fromInternal(itemStack),
				dev.psygamer.wireframe.world.World.get(world), player
		);
	}
}
