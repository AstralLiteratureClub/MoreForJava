package bet.astral.more4j.tuples.impl.mutable;

import bet.astral.more4j.tuples.mutable.MutableTriplet;

public final class MutableTripletImpl<A, B, C> implements MutableTriplet<A, B, C> {
	private A first;
	private B second;
	private C third;

	public MutableTripletImpl(A first, B second, C third) {
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

	@Override
	public MutableTriplet<A, B, C> setFirst(A first) {
		this.first = first;
		return this;
	}

	@Override
	public MutableTriplet<A, B, C> setSecond(B second) {
		this.second = second;
		return this;
	}

	@Override
	public MutableTriplet<A, B, C> setThird(C third) {
		this.third = third;
		return this;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	@Override
	protected MutableTripletImpl<A, B, C> clone() {
		return new MutableTripletImpl<>(getFirst(), getSecond(), getThird());
	}

}
