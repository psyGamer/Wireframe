package dev.psygamer.construct.core.dependant;

import dev.psygamer.construct.core.dependant.namespace.Namespace;
import dev.psygamer.construct.core.dependant.namespace.NamespaceUtil;


public final class DependantsUtil {
	
	public static Dependant<?> getDependant(final Namespace namespace) {
		for (final Dependant<?> dependant : DependantsHandler.getDependants()) {
			if (dependant.getRootPackage().startsWith(namespace.getPackagePath()) ||
					dependant.getNamespace().equalsIgnoreCase(namespace.getNamespace())
			) {
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
		return getDependant(NamespaceUtil.getCurrentNamespace());
	}
}
