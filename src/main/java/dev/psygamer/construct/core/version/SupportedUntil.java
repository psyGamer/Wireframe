package dev.psygamer.construct.core.version;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface SupportedUntil {
	MinecraftVersion value() default MinecraftVersion.COMMON;
	
	MinecraftVersion.Major major() default MinecraftVersion.Major.COMMON;
	
	MinecraftVersion.Minor minor() default MinecraftVersion.Minor.v0;
}
