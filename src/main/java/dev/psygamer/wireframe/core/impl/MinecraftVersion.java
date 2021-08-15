package dev.psygamer.wireframe.core.impl;

import java.lang.reflect.Field;
import java.util.Arrays;

public enum MinecraftVersion {
	
	COMMON(null),
	
	v16(Major.v16),
	v16_1(Major.v16, Minor.v1),
	v16_2(Major.v16, Minor.v2),
	v16_3(Major.v16, Minor.v3),
	v16_4(Major.v16, Minor.v4),
	v16_5(Major.v16, Minor.v5);
	
	private static MinecraftVersion currentVersion;
	
	static { // We need to use reflection, to be version agnostic //
		
		try {
			final Class<?> minecraftVersionClass = Class.forName("net.minecraft.util.MinecraftVersion");
			final Field minecraftVersionInstance = minecraftVersionClass.getDeclaredField("BUILT_IN");
			final Field currentVersionField = minecraftVersionClass.getDeclaredField("releaseTarget");
			
			currentVersion = fromString(
					(String) currentVersionField.get(minecraftVersionInstance.get(null))
			);
			
		} catch (ClassNotFoundException | ClassCastException | NoSuchFieldException | IllegalAccessException ignore) {
		}
	}
	
	private final Major majorVersion;
	private final Minor minorVersion;
	
	MinecraftVersion(final Major majorVersion) {
		this(majorVersion, Minor.v0);
	}
	
	MinecraftVersion(final Major majorVersion, final Minor minorVersion) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}
	
	enum Major {
		v16;
		
		public MinecraftVersion[] getSubVersions() {
			return Arrays.stream(MinecraftVersion.values())
					.filter(version -> version.getMajorVersion() == this)
					.toArray(MinecraftVersion[]::new);
		}
		
		public Major getPreviousMajorVersion() {
			return Major.values()[ordinal() <= 0 ? 0 : ordinal() - 1];
		}
		
		public Major getNextMinorVersion() {
			return Major.values()[ordinal() >= Major.values().length ? Major.values().length : ordinal() + 1];
		}
		
		@Override
		public String toString() {
			return name().replace("v", "");
		}
	}
	
	enum Minor {
		v0, v1, v2, v3, v4, v5;
		
		public Minor getPreviousMinorVersion() {
			return Minor.values()[ordinal() <= 0 ? 0 : ordinal() - 1];
		}
		
		public Minor getNextMinorVersion() {
			return Minor.values()[ordinal() >= Minor.values().length ? Minor.values().length : ordinal() + 1];
		}
		
		@Override
		public String toString() {
			return name().replace("v", "");
		}
	}
	
	public static MinecraftVersion getCurrent() {
		return currentVersion;
	}
	
	public static MinecraftVersion fromString(final String versionString) {
		return Arrays.stream(MinecraftVersion.values())
				.filter(version -> version.toString().equalsIgnoreCase(versionString))
				.findFirst()
				.orElse(null);
	}
	
	private static MinecraftVersion fromMajorAndMinor(final Major majorVersion, final Minor minorVersion) {
		return Arrays.stream(values())
				.filter(version -> version.getMajorVersion() == majorVersion && version.getMinorVersion() == minorVersion)
				.findFirst()
				.orElse(null);
	}
	
	public MinecraftVersion[] getVersionAbove() {
		return Arrays.stream(values())
				.filter(mcVersion -> mcVersion.ordinal() >= this.ordinal())
				.toArray(MinecraftVersion[]::new);
	}
	
	public MinecraftVersion[] getVersionBelow() {
		return Arrays.stream(values())
				.filter(mcVersion -> mcVersion.ordinal() <= this.ordinal())
				.toArray(MinecraftVersion[]::new);
	}
	
	public MinecraftVersion[] getVersionBetween(final MinecraftVersion newerVersion) {
		return Arrays.stream(getVersionAbove())
				.filter(version -> version.ordinal() < newerVersion.ordinal())
				.toArray(MinecraftVersion[]::new);
	}
	
	public Major getMajorVersion() {
		return this.majorVersion;
	}
	
	public Minor getMinorVersion() {
		return this.minorVersion;
	}
	
	public MinecraftVersion getNextVersion() {
		if (this.minorVersion != null) {
			final Minor nextMinorVersion = this.minorVersion.getNextMinorVersion();
			
			if (nextMinorVersion != null) {
				return fromMajorAndMinor(this.majorVersion, nextMinorVersion);
			}
		}
		
		if (this.majorVersion != null) {
			final Major nextMajorVersion = this.majorVersion.getNextMinorVersion();
			
			if (nextMajorVersion != null) {
				return fromMajorAndMinor(nextMajorVersion, Minor.v0);
			}
		}
		
		return this;
	}
	
	public MinecraftVersion getPreviousVersion() {
		if (this.minorVersion != null) {
			final Minor previousMinorVersion = this.minorVersion.getPreviousMinorVersion();
			
			if (previousMinorVersion != null) {
				return fromMajorAndMinor(this.majorVersion, previousMinorVersion);
			}
		}
		
		if (this.majorVersion != null) {
			final Major nextMajorVersion = this.majorVersion.getPreviousMajorVersion();
			
			if (nextMajorVersion != null) {
				return fromMajorAndMinor(nextMajorVersion, Minor.v0);
			}
		}
		
		return COMMON;
	}
	
	@Override
	public String toString() {
		return this.majorVersion == null
				? "Common"
				: this.minorVersion == Minor.v0
				? "1." + this.majorVersion
				: "1." + this.majorVersion + "." + this.minorVersion;
	}
}
