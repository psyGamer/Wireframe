package dev.psygamer.wireframe.event;

import io.netty.util.internal.ConcurrentSet;
import net.jodah.typetools.TypeResolver;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public abstract class EventBus implements IEventBus {
	
	private final ConcurrentSet<Object> registeredListeners = new ConcurrentSet<>();
	private final ConcurrentHashMap<Class<? extends IEvent>, List<IEventListener>> listeners = new ConcurrentHashMap<>();
	
	@Override
	public void register(final Object target) {
		if (target == null || this.registeredListeners.contains(target))
			return;
		
		this.registeredListeners.add(target);
		
		if (target.getClass() == Class.class) {
			registerClass((Class<?>) target);
		} else {
			registerObject(target);
		}
	}
	
	private void registerClass(final Class<?> clazz) {
		Arrays.stream(clazz.getMethods())
				.filter(method -> Modifier.isStatic(method.getModifiers()))
				.filter(method -> method.isAnnotationPresent(EventSubscriber.class))
				.forEach(method -> addListener(null, method, method.getAnnotation(EventSubscriber.class).priority()));
	}
	
	private void registerObject(final Object object) {
		Arrays.stream(object.getClass().getMethods())
				.filter(method -> !Modifier.isStatic(method.getModifiers()))
				.filter(method -> method.isAnnotationPresent(EventSubscriber.class))
				.forEach(method -> addListener(object, method, method.getAnnotation(EventSubscriber.class).priority()));
	}
	
	@Override
	public <T extends IEvent> void addListener(final Consumer<T> consumer) {
		addListener(consumer, IEvent.Priority.NORMAL);
	}
	
	@Override
	public <T extends IEvent> void addListener(final Consumer<T> consumer, final IEvent.Priority priority) {
		final Class<T> eventClass = getEventClass(consumer);
		
		addListener(eventClass,
				new ConsumerEventListener<>(consumer, priority, this)
		);
	}
	
	@Override
	public <T extends IEvent> void addListener(final Object instance, final Method method) {
		addListener(instance, method, IEvent.Priority.NORMAL);
	}
	
	@Override
	public <T extends IEvent> void addListener(final Object instance, final Method method, final IEvent.Priority priority) {
		if (method.getParameterTypes().length != 1)
			throw new IllegalArgumentException("Event method must have 1 argument");
		if (!method.getParameterTypes()[0].isAssignableFrom(IEvent.class))
			throw new IllegalArgumentException("Event method's argument must inherit Event");
		
		final Class<T> eventClass = (Class<T>) method.getParameterTypes()[0];
		
		addListener(eventClass,
				new MethodEventListener(instance, method, priority, this)
		);
	}
	
	private void addListener(final Class<? extends IEvent> eventClass, final IEventListener listener) {
		if (eventClass == null || listener == null ||
				(this.listeners.contains(eventClass) && this.listeners.get(eventClass).contains(listener))
		) return;
		
		if (!this.listeners.contains(eventClass))
			this.listeners.put(eventClass, new ArrayList<>());
		
		this.listeners.get(eventClass).add(listener);
	}
	
	@Override
	public boolean post(final IEvent event) {
		final List<IEventListener> eventListeners = this.listeners.get(event.getClass());
		
		eventListeners.stream()
				.filter(listener -> listener.getEventBus() == this)
				.sorted(Comparator.comparing(IEventListener::getPriority))
				.forEach(listener -> listener.invoke(event));
		
		return event.isCancelable() & event.isCanceled();
	}
	
	private <T extends IEvent> Class<T> getEventClass(final Consumer<T> consumer) {
		final Class<T> eventClass = (Class<T>) TypeResolver.resolveRawArgument(Consumer.class, consumer.getClass());
		
		if ((Class<?>) eventClass == TypeResolver.Unknown.class) {
			throw new IllegalStateException("Failed to resolve consumer event type: " + consumer);
		}
		return eventClass;
	}
}
