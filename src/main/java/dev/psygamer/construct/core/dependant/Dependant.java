package dev.psygamer.construct.core.dependant;

import dev.psygamer.construct.core.ConstructUtil;
import dev.psygamer.construct.core.namespace.Namespace;
import dev.psygamer.construct.util.reflection.FieldUtil;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

import java.util.Objects;

public final class Dependant <T> {
	private final String namespace;
	private final String rootPackage;
	
	private final FMLJavaModLoadingContext modLoadingContext;
	private final FMLModContainer modContainer;
	
	private final Class<T> modClass;
	private final T modInstance;
	
	@SuppressWarnings("unchecked")
	public Dependant(final Class<T> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		this.modLoadingContext = modLoadingContext;
		this.modContainer = FieldUtil.getField(FMLJavaModLoadingContext.class, modLoadingContext, "container");
		
		this.namespace = this.modContainer.getModId();
		this.rootPackage = Objects.requireNonNull(ConstructUtil.getFirstExternalClass()).getName();
		
		this.modClass = modClass;
		this.modInstance = (T) this.modContainer.getMod();
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
