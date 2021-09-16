package dev.psygamer.wireframe.entity;

public interface LivingEntity extends Entity {
	
	boolean canBeLeashedTo(Entity entity);
	
	boolean isLeashedTo(Entity entity);
	
	Entity getLeashHolder();
	
	void setLeashHolder(Entity entity);
	
	void unleash();
}
