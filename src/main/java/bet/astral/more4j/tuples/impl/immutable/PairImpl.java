package bet.astral.more4j.tuples.impl.immutable;

import bet.astral.more4j.tuples.Pair;

public final class PairImpl<A, B> implements Pair<A, B> {
	private final A first;
	private final B second;

	public PairImpl(A first, B second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public B getSecond() {
		return second;
	}

	@Override
	public A getFirst() {
		return first;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	public PairImpl<A, B> clone(){
		return new PairImpl<>(getFirst(), getSecond());
	}
}
