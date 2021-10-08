package dev.psygamer.wireframe.internal.entity;

import dev.psygamer.wireframe.entity.LivingEntity;
import dev.psygamer.wireframe.entity.Player;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.player.PlayerEntity;

public class InternalLivingEntity extends InternalEntity implements LivingEntity {
	
	protected final net.minecraft.entity.LivingEntity internalLivingEntity;
	
	public InternalLivingEntity(final net.minecraft.entity.LivingEntity internalLivingEntity) {
		super(internalLivingEntity);
		
		this.internalLivingEntity = internalLivingEntity;
	}
	
	@Override
	public int getMaxHealthLevel() {
		return (int) (this.internalLivingEntity.getMaxHealth() * 2);
	}
	
	@Override
	public int getHealthLevel() {
		return (int) (this.internalLivingEntity.getHealth() * 2);
	}
	
	@Override
	public void setHealthLevel(final int healthLevel) {
		this.internalLivingEntity.setHealth(healthLevel / 2f);
	}
	
	@Override
	public boolean canBeLeashedTo(final Player player) {
		return this.internalLivingEntity instanceof MobEntity &&
				((MobEntity) this.internalLivingEntity).canBeLeashed(player.getInternal());
	}
	
	@Override
	public boolean isLeashedTo(final Player player) {
		return this.internalLivingEntity instanceof MobEntity &&
				((MobEntity) this.internalLivingEntity).isLeashed() &&
				((MobEntity) this.internalLivingEntity).getLeashHolder()
													   .getUUID()
													   .equals(player.getUUID());
	}
	
	@Override
	public Player getLeashHolder() {
		if (this.internalLivingEntity instanceof MobEntity) {
			return Player.get((PlayerEntity) ((MobEntity) this.internalLivingEntity).getLeashHolder());
		}
		
		return null;
	}
	
	@Override
	public void setLeashHolder(final Player player) {
		if (this.internalLivingEntity instanceof MobEntity) {
			((MobEntity) this.internalLivingEntity).setLeashedTo(player.getInternal(), true);
		}
	}
	
	@Override
	public void unleash() {
		if (this.internalLivingEntity instanceof MobEntity) {
			((MobEntity) this.internalLivingEntity).dropLeash(true, !getLeashHolder().isCreative());
		}
	}
	
	@Override
	public net.minecraft.entity.LivingEntity getInternal() {
		return this.internalLivingEntity;
	}
}
