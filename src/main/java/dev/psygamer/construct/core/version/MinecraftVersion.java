package dev.psygamer.construct.core.version;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum MinecraftVersion {
	COMMON(null),
	
	v16(Major.v16),
	v16_1(Major.v16, Minor.v1),
	v16_2(Major.v16, Minor.v2),
	v16_3(Major.v16, Minor.v3),
	v16_4(Major.v16, Minor.v4),
	v16_5(Major.v16, Minor.v5);
	
	private final Major majorVersion;
	private final Minor minorVersion;
	
	MinecraftVersion(final Major majorVersion) {
		this(majorVersion, Minor.v0);
	}
	
	MinecraftVersion(final Major majorVersion, final Minor minorVersion) {
		this.majorVersion = majorVersion;
		this.minorVersion = minorVersion;
	}
	
	@Getter
	enum Major {
		COMMON,
		v16;
		
		private final Major previousMajorVersion;
		
		Major() {
			this(null);
		}
		
		Major(final Major previousVersion) {
			this.previousMajorVersion = previousVersion;
		}
		
		public Major getNextMinorVersion() {
			return Arrays.stream(Major.values())
					.filter(version -> version.getPreviousMajorVersion() == this)
					.findFirst()
					.orElse(null);
		}
		
		@Override
		public String toString() {
			return name().replace("v", "");
		}
	}
	
	@Getter
	enum Minor {
		v0,
		v1(v0),
		v2(v1),
		v3(v2),
		v4(v3),
		v5(v4);
		
		private final Minor previousMinorVersion;
		
		Minor() {
			this(null);
		}
		
		Minor(final Minor previousVersion) {
			this.previousMinorVersion = previousVersion;
		}
		
		public Minor getNextMinorVersion() {
			return Arrays.stream(Minor.values())
					.filter(version -> version.getPreviousMinorVersion() == this)
					.findFirst()
					.orElse(null);
		}
		
		@Override
		public String toString() {
			return name().replace("v", "");
		}
	}
	
	public static MinecraftVersion getCurrentVersion() {
		return getVersionFromString(net.minecraft.util.MinecraftVersion.tryDetectVersion().getReleaseTarget());
	}
	
	public static MinecraftVersion getVersionFromString(final String versionString) {
		return Arrays.stream(MinecraftVersion.values())
				.filter(version -> version.getVersionString().equalsIgnoreCase(versionString))
				.findFirst()
				.orElse(null);
	}
	
	public static MinecraftVersion getVersion(final Major majorVersion, final Minor minorVersion) {
		return Arrays.stream(values())
				.filter(version -> version.getMajorVersion() == majorVersion && version.getMinorVersion() == minorVersion)
				.findFirst()
				.orElse(null);
	}
	
	public static MinecraftVersion[] getVersions(final Major majorVersion) {
		return Arrays.stream(values())
				.filter(version -> version.getMajorVersion() == majorVersion)
				.toArray(MinecraftVersion[]::new);
	}
	
	public static MinecraftVersion[] getVersionAbove(final MinecraftVersion version) {
		return Arrays.stream(values())
				.filter(mcVersion -> mcVersion.ordinal() >= version.ordinal())
				.toArray(MinecraftVersion[]::new);
	}
	
	public static MinecraftVersion[] getVersionBelow(final MinecraftVersion version) {
		return Arrays.stream(values())
				.filter(mcVersion -> mcVersion.ordinal() <= version.ordinal())
				.toArray(MinecraftVersion[]::new);
	}
	
	public static MinecraftVersion[] getVersionBetween(final MinecraftVersion olderVersion, final MinecraftVersion newerVersion) {
		return Arrays.stream(getVersionAbove(olderVersion))
				.filter(version -> version.ordinal() < newerVersion.ordinal())
				.toArray(MinecraftVersion[]::new);
	}
	
	public MinecraftVersion getNextVersion() {
		if (this.minorVersion != null) {
			final Minor nextMinorVersion = this.minorVersion.getNextMinorVersion();
			
			if (nextMinorVersion != null) {
				return getVersion(this.majorVersion, nextMinorVersion);
			}
		}
		
		if (this.majorVersion != null) {
			final Major nextMajorVersion = this.majorVersion.getNextMinorVersion();
			
			if (nextMajorVersion != null) {
				return getVersion(nextMajorVersion, Minor.v0);
			}
		}
		
		return this;
	}
	
	public MinecraftVersion getPreviousVersion() {
		if (this.minorVersion != null) {
			final Minor previousMinorVersion = this.minorVersion.getPreviousMinorVersion();
			
			if (previousMinorVersion != null) {
				return getVersion(this.majorVersion, previousMinorVersion);
			}
		}
		
		if (this.majorVersion != null) {
			final Major nextMajorVersion = this.majorVersion.getPreviousMajorVersion();
			
			if (nextMajorVersion != null) {
				return getVersion(nextMajorVersion, Minor.v0);
			}
		}
		
		return COMMON;
	}
	
	public String getVersionString() {
		return toString();
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
