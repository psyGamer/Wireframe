package dev.psygamer.wireframe.util;

public interface IFreezable <T> {
	
	/**
	 * Freezes the object.
	 *
	 * @version 1.0
	 * @since 1.0
	 */
	void freeze();
	
	
	/**
	 * @return Whether the object has been frozen or not.
	 * @version 1.0
	 * @since 1.0
	 */
	boolean isFrozen();
	
	/**
	 * @return A copy of the object. Frozen state should be copied too.
	 * @version 1.0
	 * @since 1.0
	 */
	T copy();
	
	final class ObjectFrozenException extends RuntimeException {
		
		public ObjectFrozenException(final Object frozenObject) {
			super("Unable to modify frozen object:" + frozenObject);
		}
		
	}
}
