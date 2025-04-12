package bet.astral.more4j.event;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class EventManager {
	private final Map<Class<?>, Map<EventPriority, Set<EventListener<?>>>> events = new HashMap<>();

	public <E extends Event> void register(@NotNull EventListener<E> event) {
		events.putIfAbsent(event.getEventType(), new HashMap<>());
		events.get(event.getEventType()).putIfAbsent(event.getPriority(), new HashSet<>());
		events.get(event.getEventType()).get(event.getPriority()).add(event);
	}

	public <E extends Event> CompletableFuture<E> call(@NotNull E event) {
		return CompletableFuture.supplyAsync(() -> {
			Map<EventPriority, Set<EventListener<?>>> map = events.get(event.getClass());
			call(EventPriority.MONITOR_BEFORE_FIRST, event, map);
			call(EventPriority.FIRST, event, map);
			call(EventPriority.NORMAL, event, map);
			call(EventPriority.LAST, event, map);
			call(EventPriority.MONITOR_AFTER_LAST, event, map);
			return event;
		});
	}

	public <E extends Event> void call(@NotNull EventPriority priority, @NotNull E event, @NotNull Map<EventPriority, Set<EventListener<?>>> listeners) {
		for (EventListener<?> eventListener : listeners.get(priority)) {
			//noinspection unchecked
			EventListener<E> listener = (EventListener<E>) eventListener;
			listener.listen(event);
		}
	}
}