package dev.psygamer.wireframe.api.block.property;

public class IntegerRangeBlockProperty extends BlockProperty<Integer> {
	
	public IntegerRangeBlockProperty(final String propertyName, final int max) {
		this(propertyName, 0, max);
	}
	
	public IntegerRangeBlockProperty(final String propertyName, final int min, final int max) {
		super(propertyName);
		
		for (int i = min ; i <= max ; i++)
			addEntry(String.valueOf(i), i);
		
		setDefaultValue(min);
		
		freeze();
	}
}
