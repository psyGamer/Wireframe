package dev.psygamer.ferrus.lib.util;

import dev.psygamer.ferrus.core.version.LibraryOnly;

@LibraryOnly
public interface IFactory <T> {
	
	T build();
}
