package dev.psygamer.wireframe.core.impl;

import dev.psygamer.wireframe.core.WireframeCore;
import dev.psygamer.wireframe.util.reflection.ClassUtil;
import dev.psygamer.wireframe.util.reflection.MethodUtil;
import dev.psygamer.wireframe.util.reflection.ObjectUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Implementor {
	
	private final Method unimplementedMethod;
	
	protected Implementor(final Method unimplementedMethod) {
		this.unimplementedMethod = unimplementedMethod;
	}
	
	public static <R> R execute(final Object... parameters) {
		return Implementor.find(ObjectUtil.getClassTypes(parameters)).run(parameters);
	}
	
	public static Implementor find(final Class<?>... parameterTypes) {
		final StackTraceElement invoker = Arrays.stream(Thread.currentThread().getStackTrace())
				.filter(WireframeCore.Packages::isLibraryClass)
				.findFirst()
				.orElseThrow(() -> new NoInvokerFoundException("Class part of the Wireframe library classes"));
		
		final Class<?> libraryClass = ClassUtil.getClassFromStackTraceElement(invoker);
		final Method libraryMethod = MethodUtil.getStaticMethod(libraryClass, invoker.getMethodName(), parameterTypes);
		
		return new Implementor(libraryMethod);
	}
	
	@SuppressWarnings("unchecked")
	public <R> R run(final Object... parameters) {
		try {
			return (R) this.unimplementedMethod.invoke(null, parameters);
		} catch (IllegalAccessException | InvocationTargetException ex) {
			throw new NoImplementorFoundException(this.unimplementedMethod.getName(), ex);
		}
	}
}
