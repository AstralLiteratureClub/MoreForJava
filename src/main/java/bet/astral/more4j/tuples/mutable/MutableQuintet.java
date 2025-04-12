package bet.astral.more4j.tuples.mutable;

import bet.astral.more4j.tuples.Quintet;
import org.jetbrains.annotations.Contract;

public interface MutableQuintet<A, B, C, D, E> extends MutableQuartet<A, B, C, D>, Quintet<A, B, C, D, E> {
	@Contract("_ -> this")
	@Override
	MutableQuintet<A, B, C, D, E> setFirst(A first);
	@Contract("_ -> this")
	@Override
	MutableQuintet<A, B, C, D, E> setSecond(B second);
	@Contract("_ -> this")
	MutableQuintet<A, B, C, D, E> setThird(C third);
	@Contract("_ -> this")
	MutableQuintet<A, B, C, D, E> setFourth(D fourth);
	@Contract("_ -> this")
	MutableQuintet<A, B, C, D, E> setFifth(E fifth);
}
