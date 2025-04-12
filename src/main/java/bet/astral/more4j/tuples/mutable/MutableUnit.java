package bet.astral.more4j.tuples.mutable;

import bet.astral.more4j.tuples.Unit;
import org.jetbrains.annotations.Contract;

public interface MutableUnit<A> extends Unit<A> {
	@Contract("_ -> this")
	MutableUnit<A> setFirst(A first);
}
