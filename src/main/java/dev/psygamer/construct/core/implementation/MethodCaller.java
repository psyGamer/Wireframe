package dev.psygamer.construct.core.implementation;

import java.util.Arrays;
import java.util.Objects;

public class MethodCaller {
	
	public final String className;
	public final String methodName;
	
	public final Class<?>[] parameterTypes;
	
	public MethodCaller(final StackTraceElement element, final Class<?>... parameterTypes) {
		this(
				element.getClassName(),
				element.getMethodName(),
				parameterTypes
		);
	}
	
	public MethodCaller(final String className, final String methodName, final Class<?>... parameterTypes) {
		this.className = className;
		this.methodName = methodName;
		
		this.parameterTypes = parameterTypes;
	}
	
	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		final MethodCaller that = (MethodCaller) o;
		return this.className.equals(that.className) && this.methodName.equals(that.methodName) && Arrays.equals(this.parameterTypes, that.parameterTypes);
	}
	
	@Override
	public int hashCode() {
		int result = Objects.hash(this.className, this.methodName);
		result = 31 * result + Arrays.hashCode(this.parameterTypes);
		return result;
	}
}
