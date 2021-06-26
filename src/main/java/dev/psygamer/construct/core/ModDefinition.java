package dev.psygamer.construct.core;

import dev.psygamer.construct.util.reflection.FieldUtil;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.javafmlmod.FMLModContainer;

public class ModDefinition <T> {
	private final String namespace;
	private final String rootPackage;
	
	private final FMLJavaModLoadingContext modLoadingContext;
	private final FMLModContainer modContainer;
	
	private final Class<T> modClass;
	private final T modInstance;
	
	@SuppressWarnings("unchecked")
	public ModDefinition(final Class<T> modClass, final FMLJavaModLoadingContext modLoadingContext) {
		this.modLoadingContext = modLoadingContext;
		this.modContainer = FieldUtil.getField(FMLJavaModLoadingContext.class, modLoadingContext, "container");
		
		this.namespace = this.modContainer.getModId();
		this.rootPackage = ConstructUtil.getFirstExternalClass();
		
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
}
