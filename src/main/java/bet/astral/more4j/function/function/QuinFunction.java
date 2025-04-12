package bet.astral.more4j.function.function;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Function;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the three-arity specialization of {@link Function}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object, Object, Object, Object)}.
 *
 * @param <A> the type of the first argument to the function
 * @param <B> the type of the second argument to the function
 * @param <C> the type of the third argument to the function
 * @param <D> the type of the fourth argument to the function
 * @param <E> the type of the fifth argument to the function
 * @param <V> the type of the result of the function
 *
 * @see Function
 * @since 1.0.0
 */
@FunctionalInterface
public interface QuinFunction<A, B, C, D, E, V> {
    /**
     * Applies this function to the given arguments.
     *
     * @param first the first function argument
     * @param second the second function argument
     * @param third the third function argument
     * @param fourth the fourth function argument
     * @param fifth the fifth function argument
     * @return the function result
     */
    V apply(A first, B second, C third, D fourth, E fifth);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <W> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    @NotNull
    @Contract(pure = true)
    default <W> QuinFunction<A, B, C, D, E, W> andThen(final Function<? super V, ? extends W> after) {
        Objects.requireNonNull(after);
        return (final A a, final B b, final C c, final D d, final E e) -> after.apply(apply(a, b, c, d, e));
    }
}
