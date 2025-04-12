package bet.astral.more4j.tuples.impl.mutable;

import bet.astral.more4j.tuples.mutable.MutableUnit;

public final class MutableUnitImpl<A> implements MutableUnit<A> {
	private A first;

	public MutableUnitImpl(A first) {
		this.first = first;
	}

	@Override
	public A getFirst() {
		return first;
	}

	@Override
	public MutableUnit<A> setFirst(A first) {
		this.first = first;
		return this;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	@Override
	protected MutableUnit<A> clone() {
		return new MutableUnitImpl<>(getFirst());
	}
}
