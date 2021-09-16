package dev.psygamer.wireframe.entity;

import dev.psygamer.wireframe.internal.entity.InternalEntity;
import dev.psygamer.wireframe.util.BlockPosition;
import dev.psygamer.wireframe.util.math.vector.Vector3d;
import dev.psygamer.wireframe.world.World;

import java.util.List;
import java.util.UUID;

public interface Entity {
	
	/* General */
	
	long getTicks();
	
	UUID getUUID();
	
	/* Position */
	
	World getWorld();
	
	Vector3d getPosition();
	
	Vector3d getPreviousPosition();
	
	Vector3d getEyePosition();
	
	BlockPosition getBlockPosition();
	
	Vector3d getVelocity();
	
	/* Rotation */
	
	float getRotationYaw();
	
	float getHeadRotationYaw();
	
	float getPreviousRotationYaw();
	
	float getRotationPitch();
	
	float getPreviousRotationPitch();
	
	/* Passengers */
	
	int getPassengerCount();
	
	List<Entity> getPassengers();
	
	boolean isPassenger(Entity passenger);
	
	void addPassenger(Entity passenger);
	
	void removePassenger(Entity passenger);
	
	/* Riding */
	
	Entity getRiding();
	
	void setRiding(Entity riding);
	
	/* Other */
	
	boolean isDead();
	
	void damage(float damage, String message);
	
	void directDamage(float damage, String message);
	
	void kill();
	
	/* Internal */
	
	net.minecraft.entity.Entity getInternal();
}
