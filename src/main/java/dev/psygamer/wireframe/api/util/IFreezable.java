package dev.psygamer.wireframe.api.util;

public interface IFreezable {
	
	void freeze();
	
	boolean isFrozen();
	
	final class ObjectFrozenException extends RuntimeException {
		
		public ObjectFrozenException(final Object frozenObject) {
			super("Unable to modify frozen object:" + frozenObject);
		}
		
	}
}
