package dev.psygamer.wireframe.api.entity

import org.joml.Vector3d
import java.util.*
import dev.psygamer.wireframe.api.world.World
import dev.psygamer.wireframe.util.BlockPosition

interface Entity {

	val ticks: Long

	@Suppress("INAPPLICABLE_JVM_NAME") // Why Kotlin?
	@get:JvmName("getUUID")
	val uuid: UUID

	val world: World
	val position: Vector3d
	val previousPosition: Vector3d
	val eyePosition: Vector3d
	val blockPosition: BlockPosition
	val velocity: Vector3d

	val headRotationYaw: Float
	val rotationYaw: Float
	val rotationPitch: Float
	val previousRotationYaw: Float
	val previousRotationPitch: Float

	val passengerCount: Int
	val passengers: List<Entity>

	var riding: Entity?

	fun isPassenger(passenger: Entity): Boolean
	fun addPassenger(passenger: Entity)
	fun removePassenger(passenger: Entity)

	val isDead: Boolean

	fun damage(damage: Float, message: String)
	fun directDamage(damage: Float, message: String)

	fun kill()
}