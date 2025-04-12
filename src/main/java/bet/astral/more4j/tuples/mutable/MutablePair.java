package bet.astral.more4j.tuples.mutable;

import bet.astral.more4j.tuples.Pair;
import org.jetbrains.annotations.Contract;

public interface MutablePair<A, B> extends Pair<A, B>, MutableUnit<A> {
	@Override
	@Contract("_ -> this")
	MutablePair<A, B> setFirst(A first);

	@Contract("_ -> this")
	MutablePair<A, B> setSecond(B second);
}
