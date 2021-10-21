package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import java.util.Arrays;
import java.util.Optional;

public class EnumBlockProperty <T extends Enum<T>> extends BlockProperty<T> {
	
	private final ImmutableMap<String, T> possibleValuesByName;
	
	@SafeVarargs
	public EnumBlockProperty(final String propertyName, final T... possibleValues) {
		super(propertyName, possibleValues[0]);
		
		if (possibleValues.length <= 0)
			throw new ArrayIndexOutOfBoundsException("Possible values may not be empty");
		
		final ImmutableMap.Builder<String, T> builder = ImmutableMap.builder();
		
		Arrays.stream(possibleValues)
			  .forEach(value -> builder.put(value.name(), value));
		
		this.possibleValuesByName = builder.build();
		
		setDefaultValue(possibleValues[0]);
	}
	
	@Override
	public Optional<T> getValue(final String valueName) {
		return Optional.ofNullable(this.possibleValuesByName.get(valueName));
	}
	
	@Override
	public String getValueName(final T value) {
		return value.name();
	}
	
	@Override
	public ImmutableSet<T> getPossibleValues() {
		return ImmutableSet.copyOf(this.possibleValuesByName.values());
	}
}
