package dev.psygamer.wireframe.api.network

import io.netty.buffer.Unpooled
import io.netty.handler.codec.DecoderException
import net.minecraft.network.PacketBuffer
import org.joml.*
import java.time.Instant
import java.util.*
import dev.psygamer.wireframe.api.item.ItemStack
import dev.psygamer.wireframe.nativeapi.*
import dev.psygamer.wireframe.util.*

class PacketBuffer(val mcNative: PacketBuffer = PacketBuffer(Unpooled.buffer())) {

	val readable: Boolean = mcNative.isReadable
	val writable: Boolean = mcNative.isWritable

	fun skipBytes(count: Int) {
		mcNative.skipBytes(count)
	}

	fun clear() {
		mcNative.clear()
	}

	// Primitive types

	fun writeByte(byte: Byte) {
		mcNative.writeByte(byte.toInt())
	}

	fun readByte() =
		mcNative.readByte()

	fun writeInt(int: Int) {
		mcNative.writeInt(int)
	}

	fun readInt() =
		mcNative.readInt()

	fun writeLong(long: Long) {
		mcNative.writeLong(long)
	}

	fun readLong() =
		mcNative.readLong()

	fun writeFloat(float: Float) {
		mcNative.writeFloat(float)
	}

	fun readFloat() =
		mcNative.readFloat()

	fun writeDouble(double: Double) {
		mcNative.writeDouble(double)
	}

	fun readDouble() =
		mcNative.readDouble()

	fun writeByteArray(bytes: ByteArray) {
		mcNative.writeByteArray(bytes)
	}

	// Array types

	fun readByteArray(maxLength: Int = mcNative.readableBytes()): ByteArray {
		return mcNative.readByteArray(maxLength)
	}

	fun writeIntArray(ints: IntArray) =
		writeArray(ints.toTypedArray()) { mcNative.writeInt(it) }

	fun readIntArray(maxLength: Int = mcNative.readableBytes() / Int.SIZE_BYTES) =
		readArray(maxLength) { mcNative.readInt() }.toIntArray()

	fun writeLongArray(longs: LongArray) {
		mcNative.writeLongArray(longs)
	}

	fun readLongArray(maxLength: Int = mcNative.readableBytes() / Long.SIZE_BYTES): LongArray {
		return mcNative.readLongArray(null, maxLength)
	}

	fun writeFloatArray(floats: FloatArray) =
		writeArray(floats.toTypedArray()) { mcNative.writeFloat(it) }

	fun readFloatArray(maxLength: Int = mcNative.readableBytes() / Float.SIZE_BYTES) =
		readArray(maxLength) { mcNative.readFloat() }.toFloatArray()

	fun writeDoubleArray(floats: DoubleArray) =
		writeArray(floats.toTypedArray()) { mcNative.writeDouble(it) }

	fun readDoubleArray(maxLength: Int = mcNative.readableBytes() / Double.SIZE_BYTES) =
		readArray(maxLength) { mcNative.readDouble() }.toDoubleArray()

	fun writeString(string: String) =
		writeArray(string.toCharArray().toTypedArray()) { mcNative.writeChar(it.code) }

	fun readString() =
		readArray(mcNative.readableBytes() / Char.SIZE_BYTES) { mcNative.readChar() }.toCharArray().concatToString()

	// Vector types

	fun writeVector2f(vector: Vector2f) {
		mcNative.writeFloat(vector.x)
		mcNative.writeFloat(vector.y)
	}

	fun writeVector2d(vector: Vector2d) {
		mcNative.writeDouble(vector.x)
		mcNative.writeDouble(vector.y)
	}

	fun writeVector2i(vector: Vector2i) {
		mcNative.writeInt(vector.x)
		mcNative.writeInt(vector.y)
	}

	fun writeVector3f(vector: Vector3f) {
		mcNative.writeFloat(vector.x)
		mcNative.writeFloat(vector.y)
		mcNative.writeFloat(vector.z)
	}

	fun writeVector3d(vector: Vector3d) {
		mcNative.writeDouble(vector.x)
		mcNative.writeDouble(vector.y)
		mcNative.writeDouble(vector.z)
	}

	fun writeVector3i(vector: Vector3i) {
		mcNative.writeInt(vector.x)
		mcNative.writeInt(vector.y)
		mcNative.writeInt(vector.z)
	}

	fun readVector2f() = Vector2f(mcNative.readFloat(), mcNative.readFloat())
	fun readVector2d() = Vector2d(mcNative.readDouble(), mcNative.readDouble())
	fun readVector2i() = Vector2i(mcNative.readInt(), mcNative.readInt())

	fun readVector3f() = Vector3f(mcNative.readFloat(), mcNative.readFloat(), mcNative.readFloat())
	fun readVector3d() = Vector3d(mcNative.readDouble(), mcNative.readDouble(), mcNative.readDouble())
	fun readVector3i() = Vector3i(mcNative.readInt(), mcNative.readInt(), mcNative.readInt())

	fun readBlockPosition() = BlockPosition(mcNative.readInt(), mcNative.readInt(), mcNative.readInt())

	// Other types

	fun writeEnum(enum: Enum<*>) {
		mcNative.writeEnum(enum)
	}

	fun <T : Enum<T>> readEnum(enumClass: Class<T>): T =
		mcNative.readEnum(enumClass)

	fun writeUUID(uuid: UUID) {
		mcNative.writeUUID(uuid)
	}

	fun readUUID(): UUID =
		mcNative.readUUID()

	fun writeTagCompound(tagCompound: TagCompound) {
		mcNative.writeNbt(tagCompound.mcNative)
	}

	fun readTagCompound() =
		mcNative.readNbt()!!.wfWrapped

	fun writeItemStack(itemStack: ItemStack, limitedTag: Boolean = true) {
		mcNative.writeItemStack(itemStack.mcNative, limitedTag)
	}

	fun readItemStack() =
		mcNative.readItem().wfWrapped

	fun writeIdentifier(identifier: Identifier) {
		mcNative.writeResourceLocation(identifier.mcNative)
	}

	fun readIdentifier() =
		mcNative.readResourceLocation().wfWrapped

	fun writeDate(date: Date = Date.from(Instant.now())) {
		mcNative.writeDate(date)
	}

	fun readDate(): Date =
		mcNative.readDate()

	private inline fun <T> writeArray(array: Array<out T>, writeFunc: (T) -> Unit) {
		mcNative.writeVarInt(array.size)
		array.forEach { writeFunc(it) }
	}

	private inline fun <reified T> readArray(maxLength: Int, readFunc: () -> T): Array<out T> {
		val length = mcNative.readVarInt()
		if (length > maxLength)
			throw DecoderException("Array with size $length is bigger than allowed: $maxLength")

		return Array(length) { readFunc() }
	}
}