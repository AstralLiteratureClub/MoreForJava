package bet.astral.more4j.tuples.mutable;

import bet.astral.more4j.tuples.Quartet;
import org.jetbrains.annotations.Contract;

public interface MutableQuartet<A, B, C, D> extends MutableTriplet<A, B, C>, Quartet<A, B, C, D> {
	@Contract("_ -> this")
	@Override
	MutableQuartet<A, B, C, D> setFirst(A first);
	@Contract("_ -> this")
	@Override
	MutableQuartet<A, B, C, D> setSecond(B second);
	@Contract("_ -> this")
	MutableQuartet<A, B, C, D> setThird(C third);
	@Contract("_ -> this")
	MutableQuartet<A, B, C, D> setFourth(D fourth);
}
