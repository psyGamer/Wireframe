package dev.psygamer.wireframe.lib.block;

import net.minecraft.state.Property;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import java.util.Optional;

public abstract class BlockProperty <T extends Comparable<T>> extends Property<T> {
	
	protected BlockProperty(final String name, final Class<T> valueClass) {
		super(name, valueClass);
	}
	
	public abstract ConfiguredModel.Builder<?> applyBlockModelModification(Comparable<? extends Comparable<?>> value, ConfiguredModel.Builder<?> modelBuilder);
	
	@Override
	public abstract String getName(final T value);
	
	@Override
	public abstract Optional<T> getValue(final String name);
}
