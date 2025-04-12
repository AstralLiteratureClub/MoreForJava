package bet.astral.more4j.tuples.mutable;

import bet.astral.more4j.tuples.Triplet;
import org.jetbrains.annotations.Contract;

public interface MutableTriplet<A, B, C> extends MutablePair<A, B>, Triplet<A, B, C> {
	@Contract("_ -> this")
	@Override
	MutableTriplet<A, B, C> setFirst(A first);
	@Contract("_ -> this")
	@Override
	MutableTriplet<A, B, C> setSecond(B second);
	@Contract("_ -> this")
	MutableTriplet<A, B, C> setThird(C third);
}
