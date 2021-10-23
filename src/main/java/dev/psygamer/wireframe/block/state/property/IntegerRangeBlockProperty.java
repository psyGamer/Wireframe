package dev.psygamer.wireframe.block.state.property;

import com.google.common.collect.ImmutableSet;

import java.util.Optional;
import java.util.stream.IntStream;

/** An {@link BlockProperty} implementation for integer ranges. */
public class IntegerRangeBlockProperty extends BlockProperty<Integer> {
	
	private final int min, max;
	
	/**
	 * When the lower bound of the range isn't specified, it's 0 (inclusive).
	 *
	 * @param propertyName The {@link BlockProperty}'s name.
	 * @param max          The upper bound of the range (inclusive).
	 */
	public IntegerRangeBlockProperty(final String propertyName, final int max) {
		this(propertyName, 0, max);
	}
	
	/**
	 * @param propertyName The {@link BlockProperty}'s name.
	 * @param min          The lower bound of the range (inclusive).
	 * @param max          The upper bound of the range (inclusive).
	 */
	public IntegerRangeBlockProperty(final String propertyName, final int min, final int max) {
		super(propertyName, Math.min(min, max));
		
		this.min = Math.min(min, max);
		this.max = Math.max(min, max);
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
