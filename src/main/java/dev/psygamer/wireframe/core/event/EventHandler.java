package dev.psygamer.wireframe.core.event;

import com.mojang.datafixers.util.Pair;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventHandler {
	
	private static final Map<Class<? super IEvent>, List<Pair<Object, Method>>> eventMethodsMap = new HashMap<>();
	
	public static void registerEvent(final Class<? super IEvent> eventClass, final Object methodClassInstance, final Method eventMethod) {
		if (!eventMethodsMap.containsKey(eventClass)) {
			eventMethodsMap.put(eventClass, new ArrayList<>());
		}
		eventMethodsMap.get(eventClass).add(Pair.of(methodClassInstance, eventMethod));
	}
	
	public static void fireEvent(final IEvent event) {
		if (eventMethodsMap.containsKey(event)) {
			eventMethodsMap.get(event.getClass()).forEach(methodPair -> {
				try {
					methodPair.getSecond().invoke(methodPair.getFirst(), event);
				} catch (IllegalAccessException | InvocationTargetException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
