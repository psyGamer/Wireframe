package dev.psygamer.wireframe.nativeapi.item

import dev.psygamer.wireframe.api.item.ItemAttributes
import dev.psygamer.wireframe.nativeapi.getNative
import dev.psygamer.wireframe.nativeapi.mcNative
import dev.psygamer.wireframe.nativeapi.wfWrapped
import dev.psygamer.wireframe.util.math.BlockHitResult
import net.minecraft.block.BlockState
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUseContext
import net.minecraft.util.ActionResult
import net.minecraft.util.ActionResultType
import net.minecraft.util.Hand
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.BlockRayTraceResult
import net.minecraft.world.World
import java.lang.reflect.Field

class NativeItem(
	private val item: dev.psygamer.wireframe.api.item.Item,
	
	attributes: ItemAttributes
) : Item(attributes.mcNative.createProperties()) {
	
	companion object {
		
		val hitResultField: Field = ItemUseContext::class.java.getDeclaredField("hitResult")
		
		init {
			hitResultField.isAccessible = true
		}
	}
	
	/* Item Events */
	
	init {
		this.setRegistryName(
			item.identifier.namespace,
			item.identifier.path
		)
	}
	
	override fun useOn(context: ItemUseContext): ActionResultType {
		if (context.player == null)
			return ActionResultType.FAIL
		
		return item.onItemUsedOnBlock(
			context.itemInHand.wfWrapped,
			context.player!!.wfWrapped,
			context.hand.wfWrapped,
			context.level.wfWrapped,
			
			BlockHitResult.get(hitResultField[context] as BlockRayTraceResult)
		).mcNative
	}
	
	override fun use(world: World, player: PlayerEntity, hand: Hand): ActionResult<ItemStack> {
		val result = item.onItemUsed(
			player.getItemInHand(hand).wfWrapped,
			player.wfWrapped,
			hand.wfWrapped,
			world.wfWrapped
		).getNative(world.isClientSide())
		
		return ActionResult(
			result.result,
			result.getObject().mcNative
		)
	}
	
	override fun mineBlock(itemStack: ItemStack, world: World, state: BlockState, pos: BlockPos, entity: LivingEntity): Boolean {
		return item.onBlockMined(itemStack.wfWrapped, entity.wfWrapped, world.wfWrapped, pos.wfWrapped, state.wfWrapped)
	}
	
	override fun interactLivingEntity(item: ItemStack, player: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResultType {
		return this.item.onItemUsedOnEntity(
			player.getItemInHand(hand).wfWrapped, player.wfWrapped, hand.wfWrapped, player.level.wfWrapped, entity.wfWrapped
		).mcNative
	}
	
	override fun onCraftedBy(itemStack: ItemStack, world: World, player: PlayerEntity) {
		item.onItemCrafted(itemStack.wfWrapped, player.wfWrapped, world.wfWrapped)
	}
}