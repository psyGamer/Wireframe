package dev.psygamer.wireframe.util.helper;

public interface IFreezable {
	
	static void throwIfFrozen(final IFreezable freezable) {
		if (freezable.isFrozen())
			throw new IFreezable.ObjectFrozenException(freezable);
	}
	
	/**
	 * Freezes the object.
	 *
	 * @version 1.0
	 * @since 1.0
	 */
	void freeze();
	
	/**
	 * @return Whether the object has been frozen or not.
	 *
	 * @version 1.0
	 * @since 1.0
	 */
	boolean isFrozen();
	
	final class ObjectFrozenException extends RuntimeException {
		
		public ObjectFrozenException(final IFreezable frozenObject) {
			super("Unable to modify frozen object:" + frozenObject);
		}
		
	}
}
