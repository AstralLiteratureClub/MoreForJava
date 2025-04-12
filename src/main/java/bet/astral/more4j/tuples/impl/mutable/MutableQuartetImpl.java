package bet.astral.more4j.tuples.impl.mutable;

import bet.astral.more4j.tuples.mutable.MutableQuartet;

public final class MutableQuartetImpl<A, B, C, D> implements MutableQuartet<A, B, C, D> {
	private A first;
	private B second;
	private C third;
	private D fourth;

	public MutableQuartetImpl(A first, B second, C third, D fourth) {
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

	@Override
	public MutableQuartet<A, B, C, D> setFirst(A first) {
		this.first = first;
		return this;
	}

	@Override
	public MutableQuartet<A, B, C, D> setSecond(B second) {
		this.second = second;
		return this;
	}

	@Override
	public MutableQuartet<A, B, C, D> setThird(C third) {
		this.third = third;
		return this;
	}

	@Override
	public MutableQuartet<A, B, C, D> setFourth(D fourth) {
		this.fourth = fourth;
		return this;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	@Override
	protected MutableQuartetImpl<A, B, C, D> clone() {
		return new MutableQuartetImpl<>(getFirst(), getSecond(), getThird(), getFourth());
	}
}
