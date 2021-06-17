package dev.psygamer.construct.lib.util;

import dev.psygamer.construct.core.version.LibraryOnly;

@LibraryOnly
public interface IFactory <T> {
	
	T build();
}
