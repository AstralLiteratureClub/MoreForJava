package bet.astral.more4j.tuples;

import bet.astral.more4j.tuples.impl.immutable.PairImpl;
import bet.astral.more4j.tuples.impl.mutable.MutablePairImpl;
import bet.astral.more4j.tuples.mutable.MutablePair;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface Pair<A, B> extends Unit<A> {
	@Contract(value = "_, _ -> new", pure = true)
	static <A, B> @NotNull Pair<A, B> immutable(A first, B second){
		return new PairImpl<>(first, second);
	}
	@Contract(value = "_, _ -> new", pure = true)
	static <A, B> @NotNull MutablePair<A, B> mutable(A first, B second){
		return new MutablePairImpl<>(first, second);
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B> @NotNull Pair<A, B> immutable(@NotNull Pair<A, B> pair){
		return new PairImpl<>(pair.getFirst(), pair.getSecond());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B> @NotNull MutablePair<A, B> mutable(@NotNull Pair<A, B> pair){
		return new MutablePairImpl<>(pair.getFirst(), pair.getSecond());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B> @NotNull Pair<A, B> immutable(Map.Entry<A, B> entry){
		return new PairImpl<>(entry.getKey(), entry.getValue());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B> @NotNull MutablePair<A, B> mutable(Map.Entry<A, B> entry){
		return new MutablePairImpl<>(entry.getKey(), entry.getValue());
	}
	B getSecond();

	@NotNull
	@Override
	default Iterator<Object> iterator() {
		return List.of(getFirst(), getSecond()).iterator();
	}

	@NotNull
	default MutablePair<A, B> cloneAsMutable(){
		return mutable(this);
	}
	@NotNull
	default Pair<A, B> cloneAsImmutable(){
		return immutable(this);
	}
}


