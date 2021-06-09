package dev.psyGamer.anvil.lib.block;

import net.minecraft.state.Property;
import net.minecraftforge.client.model.generators.ConfiguredModel;

import java.util.Optional;

public abstract class BlockProperty <T extends Comparable<T>> extends Property<T> {
	
	protected BlockProperty(final String name, final Class<T> valueClass) {
		super(name, valueClass);
	}
	
	public abstract <U> ConfiguredModel.Builder<U> applyBlockModelModification(T value, ConfiguredModel.Builder<U> modelBuilder);
	
	@Override
	public abstract String getName(final T value);
	
	@Override
	public abstract Optional<T> getValue(final String name);
}
