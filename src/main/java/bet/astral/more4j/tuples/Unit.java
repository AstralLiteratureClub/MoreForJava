package bet.astral.more4j.tuples;

import bet.astral.more4j.tuples.impl.immutable.UnitImpl;
import bet.astral.more4j.tuples.impl.mutable.MutableUnitImpl;
import bet.astral.more4j.tuples.mutable.MutableUnit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public interface Unit<A> extends Iterable<Object>, Cloneable {
	@Contract(value = "_ -> new", pure = true)
	static <A> @NotNull Unit<A> immutable(A first){
		return new UnitImpl<>(first);
	}
	@Contract(value = "_ -> new", pure = true)
	static <A> @NotNull MutableUnit<A> mutable(A first){
		return new MutableUnitImpl<>(first);
	}
	@Contract(value = "_ -> new", pure = true)
	static <A> @NotNull Unit<A> immutable(@NotNull Unit<A> unit){
		return new UnitImpl<>(unit.getFirst());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A> @NotNull MutableUnit<A> mutable(@NotNull Unit<A> unit){
		return new MutableUnitImpl<>(unit.getFirst());
	}
	A getFirst();

	@NotNull
	@Override
	default Iterator<Object> iterator() {
		//noinspection unchecked
		return (Iterator<Object>) List.of(getFirst()).iterator();
	}

	@NotNull
	default MutableUnit<A> cloneAsMutable(){
		return mutable(this);
	}
	@NotNull
	default Unit<A> cloneAsImmutable(){
		return immutable(this);
	}
}
