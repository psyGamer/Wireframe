package dev.psygamer.wireframe.entity;

import dev.psygamer.wireframe.internal.entity.InternalPlayer;
import dev.psygamer.wireframe.item.ItemStack;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.util.math.vector.Vector3d;

public interface Player extends LivingEntity {
	
	static Player get(final net.minecraft.entity.player.PlayerEntity internalPlayer) {
		if (internalPlayer == null)
			return null;
		
		return new InternalPlayer(internalPlayer);
	}
	
	/* Food */
	
	int getFoodLevel();
	
	void setFoodLevel(int foodLevel);
	
	float getSaturationLevel();
	
	void setSaturationLevel(float saturationLevel);
	
	/* Held Item */
	
	ItemStack getHeldItem(Hand hand);
	
	void setHeldItem(ItemStack item, Hand hand);
	
	/* Miscellaneous */
	
	Vector3d getMovementVector();
	
	boolean isCrouching();
	
	boolean isCreative();
	
	/* Internal */
	
	@Override
	net.minecraft.entity.player.PlayerEntity getInternal();
}
