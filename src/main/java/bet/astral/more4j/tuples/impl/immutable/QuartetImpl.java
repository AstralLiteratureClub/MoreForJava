package bet.astral.more4j.tuples.impl.immutable;

import bet.astral.more4j.tuples.Quartet;

public final class QuartetImpl<A, B, C, D> implements Quartet<A, B, C, D> {
	private final A first;
	private final B second;
	private final C third;
	private final D fourth;

	public QuartetImpl(A first, B second, C third, D fourth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
	}


	@Override
	public B getSecond() {
		return second;
	}

	@Override
	public D getFourth() {
		return fourth;
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
	public QuartetImpl<A, B, C, D> clone(){
		return new QuartetImpl<>(getFirst(), getSecond(), getThird(),getFourth());
	}
}
