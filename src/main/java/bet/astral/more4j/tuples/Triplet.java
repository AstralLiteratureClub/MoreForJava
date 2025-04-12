package bet.astral.more4j.tuples;

import bet.astral.more4j.tuples.impl.immutable.TripletImpl;
import bet.astral.more4j.tuples.impl.mutable.MutableTripletImpl;
import bet.astral.more4j.tuples.mutable.MutableTriplet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public interface Triplet<A, B, C> extends Pair<A, B>{
	@Contract(value = "_, _, _ -> new", pure = true)
	static <A, B, C> @NotNull Triplet<A, B, C> immutable(A first, B second, C third){
		return new TripletImpl<>(first, second, third);
	}
	@Contract(value = "_, _, _ -> new", pure = true)
	static <A, B, C> @NotNull MutableTriplet<A, B, C> mutable(A first, B second, C third){
		return new MutableTripletImpl<>(first, second, third);
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B, C> @NotNull Triplet<A, B, C> immutable(@NotNull Triplet<A, B, C> triplet) {
		return new TripletImpl<>(triplet.getFirst(), triplet.getSecond(), triplet.getThird());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B, C> @NotNull MutableTriplet<A, B, C> mutable(@NotNull Triplet<A, B, C> triplet) {
		return new MutableTripletImpl<>(triplet.getFirst(), triplet.getSecond(), triplet.getThird());
	}
	C getThird();

	@NotNull
	@Override
	default Iterator<Object> iterator() {
		return List.of(getFirst(), getSecond(), getThird()).iterator();
	}

	@NotNull
	default MutableTriplet<A, B, C> cloneAsMutable(){
		return mutable(this);
	}
	@NotNull
	default Triplet<A, B, C> cloneAsImmutable(){
		return immutable(this);
	}
}
