package dev.psygamer.wireframe.core.event;

import net.minecraftforge.api.distmarker.Dist;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface WireframeEventBusSubscriber {
	
	Dist[] value() default { Dist.CLIENT, Dist.DEDICATED_SERVER };
}
