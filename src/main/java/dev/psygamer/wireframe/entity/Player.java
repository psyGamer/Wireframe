package dev.psygamer.wireframe.entity;

import dev.psygamer.wireframe.item.ItemStack;
import dev.psygamer.wireframe.item.util.Hand;
import dev.psygamer.wireframe.util.math.vector.Vector3d;

public interface Player extends LivingEntity {
	
	int getFoodLevel();
	
	void setFoodLevel(int foodLevel);
	
	ItemStack getHeldItem(Hand hand);
	
	void setHeldItem(ItemStack item, Hand hand);
	
	Vector3d getMovementVector();
	
	boolean isCrouching();
	
	boolean isCreative();
}
