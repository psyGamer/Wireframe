package dev.psygamer.wireframecore.impl;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public @interface MethodCache {
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@interface StartupOnly {
	}
	
	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	@interface FrequentlyUsed {
	}
}
