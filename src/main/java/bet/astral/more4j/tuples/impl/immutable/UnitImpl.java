package bet.astral.more4j.tuples.impl.immutable;

import bet.astral.more4j.tuples.Unit;

public final class UnitImpl<A> implements Unit<A> {
	private final A first;

	public UnitImpl(A first) {
		this.first = first;
	}

	@Override
	public A getFirst() {
		return first;
	}

	@SuppressWarnings("MethodDoesntCallSuperMethod")
	public UnitImpl<A> clone(){
		return new UnitImpl<>(getFirst());
	}
}
