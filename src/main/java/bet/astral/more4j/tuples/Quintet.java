package bet.astral.more4j.tuples;

import bet.astral.more4j.tuples.impl.immutable.QuintetImpl;
import bet.astral.more4j.tuples.impl.mutable.MutableQuintetImpl;
import bet.astral.more4j.tuples.mutable.MutableQuintet;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.List;

public interface Quintet<A, B, C, D, E> extends Quartet<A, B, C, D>{
	@Contract(value = "_, _, _, _, _ -> new", pure = true)
	static <A, B, C, D, E> @NotNull Quintet<A, B, C, D, E> immutable(A first, B second, C third, D fourth, E fifth){
		return new QuintetImpl<>(first, second, third, fourth, fifth);
	}
	@Contract(value = "_, _, _, _, _ -> new", pure = true)
	static <A, B, C, D, E> @NotNull MutableQuintet<A, B, C, D, E> mutable(A first, B second, C third, D fourth, E fifth){
		return new MutableQuintetImpl<>(first, second, third, fourth, fifth);
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B, C, D, E> @NotNull Quintet<A, B, C, D, E> immutable(@NotNull Quintet<A, B, C, D, E> quintet) {
		return new QuintetImpl<>(quintet.getFirst(), quintet.getSecond(), quintet.getThird(), quintet.getFourth(), quintet.getFifth());
	}
	@Contract(value = "_ -> new", pure = true)
	static <A, B, C, D, E> @NotNull MutableQuintet<A, B, C, D, E> mutable(@NotNull Quintet<A, B, C, D, E> quintet) {
		return new MutableQuintetImpl<>(quintet.getFirst(), quintet.getSecond(), quintet.getThird(), quintet.getFourth(), quintet.getFifth());
	}
	E getFifth();

	@NotNull
	@Override
	default Iterator<Object> iterator() {
		return List.of(getFirst(), getSecond(), getThird(), getFourth(), getFifth()).iterator();
	}

	@NotNull
	default MutableQuintet<A, B, C, D, E> cloneAsMutable(){
		return mutable(this);
	}
	@NotNull
	default Quintet<A, B, C, D, E> cloneAsImmutable(){
		return immutable(this);
	}
}
