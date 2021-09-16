package dev.psygamer.wireframe.util;

import com.google.common.primitives.Bytes;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import net.minecraft.nbt.CompoundNBT;

import java.util.Collection;
import java.util.UUID;

public class TagCompound {
	
	private final net.minecraft.nbt.CompoundNBT internal;
	
	public TagCompound() {
		this.internal = new net.minecraft.nbt.CompoundNBT();
	}
	
	private TagCompound(final net.minecraft.nbt.CompoundNBT internal) {
		this.internal = internal;
	}
	
	public static TagCompound get(final net.minecraft.nbt.CompoundNBT internal) {
		return new TagCompound(internal);
	}
	
	public void putByte(final String key, final byte value) {
		this.internal.putByte(key, value);
	}
	
	public byte getByte(final String key) {
		return this.internal.getByte(key);
	}
	
	public void putByteArray(final String key, final byte... value) {
		this.internal.putByteArray(key, value);
	}
	
	public void putByteArray(final String key, final Collection<Byte> value) {
		this.internal.putByteArray(key, Bytes.toArray(value));
	}
	
	public byte[] getByteArray(final String key) {
		return this.internal.getByteArray(key);
	}
	
	public void putShort(final String key, final short value) {
		this.internal.putShort(key, value);
	}
	
	public short getShort(final String key) {
		return this.internal.getShort(key);
	}
	
	public void putInt(final String key, final int value) {
		this.internal.putInt(key, value);
	}
	
	public int getInt(final String key) {
		return this.internal.getInt(key);
	}
	
	public void putIntArray(final String key, final int... value) {
		this.internal.putIntArray(key, value);
	}
	
	public void putIntArray(final String key, final Collection<Integer> value) {
		this.internal.putIntArray(key, Ints.toArray(value));
	}
	
	public int[] getIntArray(final String key) {
		return this.internal.getIntArray(key);
	}
	
	public void putLong(final String key, final long value) {
		this.internal.putLong(key, value);
	}
	
	public long getLong(final String key) {
		return this.internal.getLong(key);
	}
	
	public void putLongArray(final String key, final long... value) {
		this.internal.putLongArray(key, value);
	}
	
	public void putLongArray(final String key, final Collection<Long> value) {
		this.internal.putLongArray(key, Longs.toArray(value));
	}
	
	public long[] getLongArray(final String key) {
		return this.internal.getLongArray(key);
	}
	
	public void putFloat(final String key, final float value) {
		this.internal.putFloat(key, value);
	}
	
	public float getFloat(final String key) {
		return this.internal.getFloat(key);
	}
	
	public void putDouble(final String key, final double value) {
		this.internal.putDouble(key, value);
	}
	
	public double getDouble(final String key) {
		return this.internal.getDouble(key);
	}
	
	public void putBoolean(final String key, final boolean value) {
		this.internal.putBoolean(key, value);
	}
	
	public boolean getBoolean(final String key) {
		return this.internal.getBoolean(key);
	}
	
	public void putString(final String key, final String value) {
		this.internal.putString(key, value);
	}
	
	public String getString(final String key) {
		return this.internal.getString(key);
	}
	
	public void putUUID(final String key, final UUID value) {
		this.internal.putUUID(key, value);
	}
	
	public UUID getUUID(final String key) {
		return this.internal.getUUID(key);
	}
	
	public boolean contains(final String key) {
		return this.internal.contains(key);
	}
	
	public void remove(final String key) {
		this.internal.remove(key);
	}
	
	public boolean isEmpty() {
		return this.internal.isEmpty();
	}
	
	public CompoundNBT getInternal() {
		return this.internal;
	}
}
