package dev.psygamer.wireframe.internal.entity

import dev.psygamer.wireframe.entity.Entity
import dev.psygamer.wireframe.mcNative
import dev.psygamer.wireframe.util.BlockPosition
import dev.psygamer.wireframe.util.math.vector.Vector3d
import dev.psygamer.wireframe.wfWrapped
import dev.psygamer.wireframe.world.World
import net.minecraft.util.DamageSource
import java.util.*

open class NativeEntity(protected val mcNative: net.minecraft.entity.Entity) : Entity {
	
	override val ticks: Long
		get() = mcNative.tickCount.toLong()
	override val uuid: UUID
		get() = mcNative.uuid
	
	override val world: World
		get() = World.get(mcNative.level)
	override val position: Vector3d
		get() = mcNative.position().wfWrapped
	override val previousPosition: Vector3d
		get() = Vector3d(mcNative.xOld, mcNative.yOld, mcNative.zOld)
	override val eyePosition: Vector3d
		get() = Vector3d(mcNative.x, mcNative.y + mcNative.eyeHeight, mcNative.z)
	override val blockPosition: BlockPosition
		get() = mcNative.blockPosition().wfWrapped
	override val velocity: Vector3d
		get() = mcNative.deltaMovement.wfWrapped
	
	override val headRotationYaw: Float
		get() = mcNative.yHeadRot
	override val rotationYaw: Float
		get() = mcNative.yRot
	override val rotationPitch: Float
		get() = mcNative.xRot
	override val previousRotationYaw: Float
		get() = mcNative.yRotO
	override val previousRotationPitch: Float
		get() = mcNative.xRotO
	
	override val passengerCount: Int
		get() = mcNative.passengers.size
	override val passengers: List<Entity>
		get() = mcNative.passengers.map { NativeEntity(it) }
	
	override fun isPassenger(passenger: Entity): Boolean {
		return mcNative.hasPassenger(passenger.mcNative)
	}
	
	override fun addPassenger(passenger: Entity) {
		passenger.mcNative.startRiding(mcNative)
	}
	
	override fun removePassenger(passenger: Entity) {
		passenger.mcNative.stopRiding()
	}
	
	override var riding: Entity?
		get() = mcNative.vehicle?.wfWrapped
		set(value) {
			if (value == null || mcNative.vehicle != null) {
				mcNative.stopRiding()
				value ?: return
			}
			
			mcNative.startRiding(value.mcNative)
		}
	
	override val isDead: Boolean
		get() = !mcNative.isAlive
	
	override fun damage(damage: Float, message: String) {
		mcNative.hurt(DamageSource(message), damage)
	}
	
	override fun directDamage(damage: Float, message: String) {
		mcNative.hurt(DamageSource(message).bypassArmor(), damage)
	}
	
	override fun kill() {
		mcNative.kill()
	}
}