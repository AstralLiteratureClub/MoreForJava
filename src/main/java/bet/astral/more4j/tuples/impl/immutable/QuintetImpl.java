package bet.astral.more4j.tuples.impl.immutable;

import bet.astral.more4j.tuples.Quintet;

public final class QuintetImpl<A, B, C, D, E> implements Quintet<A, B, C, D, E> {
	private final A first;
	private final B second;
	private final C third;
	private final D fourth;
	private final E fifth;

	public QuintetImpl(A first, B second, C third, D fourth, E fifth) {
		this.first = first;
		this.second = second;
		this.third = third;
		this.fourth = fourth;
		this.fifth = fifth;
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
	public E getFifth() {
		return fifth;
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
	public QuintetImpl<A, B, C, D, E> clone(){
		return new QuintetImpl<>(getFirst(), getSecond(), getThird(), getFourth(), getFifth());
	}
}
