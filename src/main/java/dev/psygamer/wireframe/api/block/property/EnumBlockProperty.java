package dev.psygamer.wireframe.api.block.property;

public class EnumBlockProperty <T extends Enum<T>> extends BlockProperty<T> {
	
	@SafeVarargs
	public EnumBlockProperty(final String propertyName, final T... possibleValues) {
		super(propertyName);
		
		for (final T possibleValue : possibleValues) {
			addEntry(possibleValue.name(), possibleValue);
		}
		
		freeze();
	}
}
