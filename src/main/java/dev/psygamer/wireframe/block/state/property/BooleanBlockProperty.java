package dev.psygamer.wireframe.block.state.property;

public class BooleanBlockProperty extends BlockProperty<Boolean> {
	
	public BooleanBlockProperty(final String propertyName) {
		super(propertyName);
		
		addEntry("true", true);
		addEntry("false", false);
		
		setDefaultValue(false);
		
		freeze();
	}
}