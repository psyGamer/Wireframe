package dev.psygamer.wireframe.core.namespace;

import dev.psygamer.wireframe.core.WireframeUtil;

import java.util.Objects;

public final class NamespaceUtil {
	
	public static Namespace getNamespace(final String packagePath) {
		for (final Namespace namespace : NamespaceHandler.getNamespaces()) {
			if (namespace.packagePath.equalsIgnoreCase(packagePath)) {
				return namespace;
			}
		}
		
		return new Namespace(packagePath);
	}
	
	public static Namespace getCurrentNamespace() {
		return getNamespace(Objects.requireNonNull(WireframeUtil.getFirstExternalClass()).getPackage().getName());
	}
}
