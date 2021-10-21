package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableSet;

import java.util.Optional;
import java.util.stream.IntStream;

public class IntegerRangeBlockProperty extends BlockProperty<Integer> {
	
	private final int min, max;
	
	public IntegerRangeBlockProperty(final String propertyName, final int max) {
		this(propertyName, 0, max);
	}
	
	public IntegerRangeBlockProperty(final String propertyName, final int min, final int max) {
		super(propertyName, min);
		
		this.min = min;
		this.max = max;
		
		setDefaultValue(min);
	}
	
	@Override
	public Optional<Integer> getValue(final String valueName) {
		try {
			final int value = Integer.parseInt(valueName);
			
			if (!getPossibleValues().contains(value))
				return Optional.empty();
			
			return Optional.of(value);
		} catch (final NumberFormatException ex) {
			return Optional.empty();
		}
	}
	
	@Override
	public String getValueName(final Integer value) {
		return String.valueOf(value);
	}
	
	@Override
	public ImmutableSet<Integer> getPossibleValues() {
		final ImmutableSet.Builder<Integer> builder = ImmutableSet.builder();
		
		IntStream.range(this.min, this.max + 1)
				 .forEach(builder::add);
		
		return builder.build();
	}
}
