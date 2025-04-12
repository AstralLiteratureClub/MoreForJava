package bet.astral.more4j.event;

import org.jetbrains.annotations.NotNull;

public interface EventListener<E extends Event> {
	void listen(@NotNull E event);
	@NotNull
	Class<E> getEventType();
	@NotNull
	default EventPriority getPriority(){
		return EventPriority.NORMAL;
	}
}
