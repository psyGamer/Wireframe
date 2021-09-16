package dev.psygamer.wireframe.internal.entity;

import dev.psygamer.wireframe.entity.Player;
import dev.psygamer.wireframe.item.ItemStack;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.util.math.vector.Vector3d;

import net.minecraft.entity.player.PlayerEntity;

public class InternalPlayer extends InternalLivingEntity implements Player {
	
	protected final PlayerEntity internalPlayer;
	
	public InternalPlayer(final PlayerEntity internalPlayer) {
		super(internalPlayer);
		
		this.internalPlayer = internalPlayer;
	}
	
	@Override
	public int getFoodLevel() {
		return this.internalPlayer.getFoodData().getFoodLevel();
	}
	
	@Override
	public void setFoodLevel(final int foodLevel) {
		this.internalPlayer.getFoodData().setFoodLevel(foodLevel);
	}
	
	@Override
	public ItemStack getHeldItem(final Hand hand) {
		return ItemStack.get(this.internalPlayer.getItemInHand(hand.getInternal()));
	}
	
	@Override
	public void setHeldItem(final ItemStack item, final Hand hand) {
		this.internalPlayer.setItemInHand(hand.getInternal(), item.toInternal());
	}
	
	@Override
	public Vector3d getMovementVector() {
		return new Vector3d(
				this.internalPlayer.xxa * (this.internalPlayer.isSprinting() ? 0.4 : 0.2),
				this.internalPlayer.yya * (this.internalPlayer.isSprinting() ? 0.4 : 0.2),
				this.internalPlayer.zza * (this.internalPlayer.isSprinting() ? 0.4 : 0.2)
		);
	}
	
	@Override
	public boolean isCrouching() {
		return this.internalPlayer.isShiftKeyDown();
	}
	
	@Override
	public boolean isCreative() {
		return this.internalPlayer.isCreative();
	}
	
	@Override
	public PlayerEntity getInternal() {
		return this.internalPlayer;
	}
}
