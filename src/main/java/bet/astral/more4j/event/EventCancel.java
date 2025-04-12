package bet.astral.more4j.event;

public interface EventCancel {
	void canceled();
	void uncanceled();
	boolean isCanceled();
}
