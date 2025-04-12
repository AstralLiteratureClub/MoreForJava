package bet.astral.more4j.tuples.impl.mutable;

import bet.astral.more4j.tuples.mutable.MutablePair;

public final class MutablePairImpl<A, B> implements MutablePair<A, B> {
	private A first;
	private B second;

	public MutablePairImpl(A first, B second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public MutablePair<A, B> setFirst(A first) {
		this.first = first;
		return this;
	}

	public MutablePair<A, B> setSecond(B second) {
		this.second = second;
		return this;
	}

	@Override
	public A getFirst() {
		return first;
	}

	@Override
	public B getSecond() {
		return second;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	public MutablePairImpl<A, B> clone(){
		return new MutablePairImpl<>(getFirst(), getSecond());
	}
}
