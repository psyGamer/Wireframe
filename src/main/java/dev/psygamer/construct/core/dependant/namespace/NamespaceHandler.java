package dev.psygamer.construct.core.dependant.namespace;

import java.util.ArrayList;
import java.util.List;

public final class NamespaceHandler {
	
	private static final List<Namespace> namespaces = new ArrayList<>();
	
	public static List<Namespace> getNamespaces() {
		return new ArrayList<>(namespaces);
	}
	
	public static <T> void registerNamespace(final String modid, final String packagePath) {
		namespaces.add(new Namespace(modid, packagePath));
	}
}
