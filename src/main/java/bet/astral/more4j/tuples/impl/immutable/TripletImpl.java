package bet.astral.more4j.tuples.impl.immutable;

import bet.astral.more4j.tuples.Triplet;

public final class TripletImpl<A, B, C> implements Triplet<A, B, C> {
	private final A first;
	private final B second;
	private final C third;

	public TripletImpl(A first, B second, C third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	@Override
	public B getSecond() {
		return second;
	}

	@Override
	public C getThird() {
		return third;
	}

	@Override
	public A getFirst() {
		return first;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	public TripletImpl<A, B, C> clone(){
		return new TripletImpl<>(getFirst(), getSecond(), getThird());
	}
}
