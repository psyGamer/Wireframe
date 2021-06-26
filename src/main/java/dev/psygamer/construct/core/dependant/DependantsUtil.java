package dev.psygamer.construct.core.dependant;

import dev.psygamer.construct.core.ConstructUtil;

import java.util.Objects;

public final class DependantsUtil {
	
	public static Dependant<?> getDependant(final String namespace) {
		for (final Dependant<?> dependant : DependantsHandler.getDependants()) {
			if (dependant.getNamespace().equalsIgnoreCase(namespace)) {
				return dependant;
			}
		}
		
		throw new Dependant.NotFoundException(namespace);
	}
	
	/**
	 * <p>Searches the StackTrace of the current Thread for the first non internal class, and gets the corresponding mod definition.</p>
	 * <p><strong>Note: </strong>Should only be used in methods that are directly call by the dependant.</p>
	 * <p>
	 *
	 * @return The mod definition of the current mod.
	 */
	public static Dependant<?> getCurrentDependant() {
		return getDependant(Objects.requireNonNull(ConstructUtil.getFirstExternalClass()).getName());
	}
}
