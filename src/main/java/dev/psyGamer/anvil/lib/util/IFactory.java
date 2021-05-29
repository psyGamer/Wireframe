package dev.psyGamer.anvil.lib.util;

import dev.psyGamer.anvil.core.version.LibraryOnly;

@LibraryOnly
public interface IFactory <T> {
	
	T build();
}
