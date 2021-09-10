package dev.psygamer.wireframe.block.state.property;

import java.util.Objects;

public class EnumBlockProperty <T extends Enum<T>> extends BlockProperty<T> {
	
	@SafeVarargs
	public EnumBlockProperty(final String propertyName, final T... possibleValues) {
		super(propertyName);
		
		Objects.requireNonNull(possibleValues, "The possible enum values may not be null");
		
		if (possibleValues.length <= 0)
			throw new ArrayIndexOutOfBoundsException("Possible values may not be empty");
		
		for (final T possibleValue : possibleValues) {
			addEntry(possibleValue.name(), possibleValue);
		}
		
		setDefaultValue(possibleValues[0]);
		
		freeze();
	}
}
