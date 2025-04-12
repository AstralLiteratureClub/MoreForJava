package bet.astral.more4j.tuples.impl.mutable;

import bet.astral.more4j.tuples.mutable.MutableQuintet;

public final class MutableQuintetImpl<A, B, C, D, E> implements MutableQuintet<A, B, C, D, E> {
	private A first;
	private B second;
	private C third;
	private D fourth;
	private E fifth;

	public MutableQuintetImpl(A first, B second, C third, D fourth, E fifth) {
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

	@Override
	public MutableQuintet<A, B, C, D, E> setFirst(A first) {
		this.first = first;
		return this;
	}

	@Override
	public MutableQuintet<A, B, C, D, E> setSecond(B second) {
		this.second = second;
		return this;
	}

	@Override
	public MutableQuintet<A, B, C, D, E> setThird(C third) {
		this.third = third;
		return this;
	}

	@Override
	public MutableQuintet<A, B, C, D, E> setFourth(D fourth) {
		this.fourth = fourth;
		return this;
	}

	@Override
	public MutableQuintet<A, B, C, D, E> setFifth(E fifth) {
		this.fifth = fifth;
		return this;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	@Override
	protected MutableQuintet<A, B, C, D, E> clone() {
		return new MutableQuintetImpl<>(getFirst(), getSecond(), getThird(), getFourth(), getFifth());
	}

}
