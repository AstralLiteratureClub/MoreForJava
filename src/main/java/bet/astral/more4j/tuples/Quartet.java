package bet.astral.more4j.tuples;

import bet.astral.more4j.tuples.impl.immutable.QuartetImpl;
import bet.astral.more4j.tuples.impl.mutable.MutableQuartetImpl;
import bet.astral.more4j.tuples.mutable.MutableQuartet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public interface Quartet<A, B, C, D> extends Triplet<A, B, C> {
	@Contract(value = "_, _, _, _ -> new", pure = true)
	static <A, B, C, D> @NotNull Quartet<A, B, C, D> immutable(A first, B second, C third, D fourth){
		return new QuartetImpl<>(first, second, third, fourth);
	}
	@Contract(value = "_, _, _, _ -> new", pure = true)
	static <A, B, C, D> @NotNull MutableQuartet<A, B, C, D> mutable(A first, B second, C third, D fourth){
		return new MutableQuartetImpl<>(first, second, third, fourth);
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B, C, D> @NotNull Quartet<A, B, C, D> immutable(@NotNull Quartet<A, B, C, D> quartet){
		return new QuartetImpl<>(quartet.getFirst(), quartet.getSecond(), quartet.getThird(), quartet.getFourth());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B, C, D> @NotNull MutableQuartet<A, B, C, D> mutable(@NotNull Quartet<A, B, C, D> quartet){
		return new MutableQuartetImpl<>(quartet.getFirst(), quartet.getSecond(), quartet.getThird(), quartet.getFourth());
	}
	D getFourth();

	@NotNull
	@Override
	default Iterator<Object> iterator() {
		return List.of(getFirst(), getSecond(), getThird(), getFourth()).iterator();
	}

	@NotNull
	default MutableQuartet<A, B, C, D> cloneAsMutable(){
		return mutable(this);
	}
	@NotNull
	default Quartet<A, B, C, D> cloneAsImmutable(){
		return immutable(this);
	}
}
