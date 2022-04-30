package dev.psygamer.wireframe.util

interface IFreezable {
	
	/** Makes the object immutable */
	fun freeze()
	
	val frozen: Boolean
	
	class ObjectFrozenException(frozenObject: IFreezable) : RuntimeException("Unable to modify frozen object: $frozenObject")
	
	companion object {
		
		@JvmStatic
		fun throwIfFrozen(freezable: IFreezable) {
			if (freezable.frozen)
				throw ObjectFrozenException(freezable)
		}
	}
}