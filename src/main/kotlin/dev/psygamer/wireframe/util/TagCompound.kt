package dev.psygamer.wireframe.util

import com.google.common.primitives.*
import java.util.*
import dev.psygamer.wireframe.nativeapi.wfWrapped

class TagCompound(
	internal val mcNative: net.minecraft.nbt.CompoundNBT = net.minecraft.nbt.CompoundNBT(),
) {

	val allKeys: Set<String> = this.mcNative.allKeys
	val isEmpty: Boolean = this.mcNative.isEmpty

	fun setByte(key: String, value: Byte) {
		this.mcNative.putByte(key, value)
	}

	fun getByte(key: String): Byte {
		return this.mcNative.getByte(key)
	}

	fun setByteArray(key: String, vararg value: Byte) {
		this.mcNative.putByteArray(key, value)
	}

	fun setByteArray(key: String, value: Collection<Byte>) {
		this.mcNative.putByteArray(key, Bytes.toArray(value))
	}

	fun getByteArray(key: String): ByteArray {
		return this.mcNative.getByteArray(key)
	}

	fun setShort(key: String, value: Short) {
		this.mcNative.putShort(key, value)
	}

	fun getShort(key: String): Short {
		return this.mcNative.getShort(key)
	}

	fun setInt(key: String, value: Int) {
		this.mcNative.putInt(key, value)
	}

	fun getInt(key: String): Int {
		return this.mcNative.getInt(key)
	}

	fun setIntArray(key: String, vararg value: Int) {
		this.mcNative.putIntArray(key, value)
	}

	fun setIntArray(key: String, value: Collection<Int>) {
		this.mcNative.putIntArray(key, Ints.toArray(value))
	}

	fun getIntArray(key: String): IntArray {
		return this.mcNative.getIntArray(key)
	}

	fun setLong(key: String, value: Long) {
		this.mcNative.putLong(key, value)
	}

	fun getLong(key: String): Long {
		return this.mcNative.getLong(key)
	}

	fun setLongArray(key: String, vararg value: Long) {
		this.mcNative.putLongArray(key, value)
	}

	fun setLongArray(key: String, value: Collection<Long>) {
		this.mcNative.putLongArray(key, Longs.toArray(value))
	}

	fun getLongArray(key: String): LongArray {
		return this.mcNative.getLongArray(key)
	}

	fun setFloat(key: String, value: Float) {
		this.mcNative.putFloat(key, value)
	}

	fun getFloat(key: String): Float {
		return this.mcNative.getFloat(key)
	}

	fun setDouble(key: String, value: Double) {
		this.mcNative.putDouble(key, value)
	}

	fun getDouble(key: String): Double {
		return this.mcNative.getDouble(key)
	}

	fun setBoolean(key: String, value: Boolean) {
		this.mcNative.putBoolean(key, value)
	}

	fun getBoolean(key: String): Boolean {
		return this.mcNative.getBoolean(key)
	}

	fun setString(key: String, value: String) {
		this.mcNative.putString(key, value)
	}

	fun getString(key: String): String {
		return this.mcNative.getString(key)
	}

	fun setUUID(key: String, value: UUID) {
		this.mcNative.putUUID(key, value)
	}

	fun getUUID(key: String): UUID {
		return this.mcNative.getUUID(key)
	}

	fun setCompound(key: String, compound: TagCompound) {
		this.mcNative.put(key, compound.mcNative)
	}

	fun getCompound(key: String): TagCompound {
		return this.mcNative.getCompound(key).wfWrapped
	}

	fun remove(key: String) {
		this.mcNative.remove(key)
	}

	operator fun contains(key: String): Boolean {
		return this.mcNative.contains(key)
	}
}
