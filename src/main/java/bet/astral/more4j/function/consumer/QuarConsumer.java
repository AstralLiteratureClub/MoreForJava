package bet.astral.more4j.function.consumer;


import java.util.function.Consumer;

/**
 * Represents an operation that accepts two input arguments and returns no
 * result.  This is the two-arity specialization of {@link Consumer}.
 * Unlike most other functional interfaces, {@code QuarConsumer} is expected
 * to operate via side-effects.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #accept(Object, Object, Object, Object)}.
 *
 * @param <A> the type of the first argument to the operation
 * @param <B> the type of the second argument to the operation
 * @param <C> the type of the third argument to the operation
 * @param <D> the type of the fifth argument to the operation
 *
 * @see Consumer
 * @since 1.0.0
 */
@FunctionalInterface
public interface QuarConsumer<A, B, C, D> {
	/**
	 * Performs this operation on the given arguments.
	 *
	 * @param first the first input argument
	 * @param second the second input argument
	 * @param third the third input argument
	 * @param fourth the fourth input argument
	 */
	void accept(A first, B second, C third, D fourth);
}
