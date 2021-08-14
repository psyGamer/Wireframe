package dev.psygamer.wireframe.core.dependant;

import com.google.common.collect.ImmutableList;
import dev.psygamer.wireframe.core.WireframePackages;

import dev.psygamer.wireframe.util.collection.FreezableArrayList;
import dev.psygamer.wireframe.util.collection.FreezableList;
import dev.psygamer.wireframe.util.reflection.FieldUtil;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

import java.util.Objects;

public final class Dependant <T> {
	private static final FreezableList<Dependant<?>> dependants = new FreezableArrayList<>();
	
	private final String namespace;
	private final String rootPackage;
	
	private final FMLJavaModLoadingContext modLoadingContext;
	private final FMLModContainer modContainer;
	
	private final Class<T> modClass;
	private final T modInstance;
	
	public Dependant(final Class<T> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		this.modLoadingContext = modLoadingContext;
		this.modContainer = FieldUtil.getField(FMLJavaModLoadingContext.class, modLoadingContext, "container");
		
		this.namespace = this.modContainer.getModId();
		this.rootPackage = Objects.requireNonNull(WireframePackages.getFirstExternalClass()).getName();
		
		this.modClass = modClass;
		this.modInstance = (T) this.modContainer.getMod();
		
		dependants.add(this);
	}
	
	public static ImmutableList<Dependant<?>> getDependants() {
		return dependants.toImmutable();
	}
	
	public static Dependant<?> fromNamespace(final Namespace namespace) {
		for (final Dependant<?> dependant : dependants) {
			if (dependant.getRootPackage().startsWith(namespace.getPackagePath()) ||
					dependant.getNamespace().equalsIgnoreCase(namespace.getNamespace())
			) return dependant;
		}
		
		throw new NotFoundException(namespace);
	}
	
	/**
	 * <p>Searches the StackTrace of the current Thread for the first non internal class, and gets the corresponding mod definition.</p>
	 * <p><strong>Note: </strong>Should only be used in methods that are directly call by the dependant.</p>
	 * <p>
	 *
	 * @return The mod definition of the current mod.
	 */
	public static Dependant<?> getCurrent() {
		return fromNamespace(Namespace.getCurrent());
	}
	
	public String getNamespace() {
		return this.namespace;
	}
	
	public String getRootPackage() {
		return this.rootPackage;
	}
	
	public FMLJavaModLoadingContext getModLoadingContext() {
		return this.modLoadingContext;
	}
	
	public FMLModContainer getModContainer() {
		return this.modContainer;
	}
	
	public Class<T> getModClass() {
		return this.modClass;
	}
	
	public T getModInstance() {
		return this.modInstance;
	}
	
	public static final class NotFoundException extends RuntimeException {
		public NotFoundException(final Namespace namespace) {
			super("Could not find dependant with namespace: " + namespace.getNamespace() + " or root package: " + namespace.getPackagePath());
		}
	}
}
