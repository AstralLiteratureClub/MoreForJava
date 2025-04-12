package bet.astral.more4j.function.predicate;

import java.util.Objects;

@FunctionalInterface
public interface QuinPredicate<A, B, C, D, E> {
    /**
     * Evaluates this predicate on the given arguments.
     *
     * @param a the first input argument
     * @param b the second input argument
     * @param c the third input argument
     * @param d the fourth input argument
     * @param e the fifth input argument
     *
     * @return {@code true} if the input arguments match the predicate,
     * otherwise {@code false}
     */
    boolean test(A a, B b, C c, D d, E e);

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code false}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ANDed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * AND of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default QuinPredicate<A, B, C, D, E> and(QuinPredicate<? super A, ? super B, ? super C, ? super D, ? super E> other) {
        Objects.requireNonNull(other);
        return (A a, B b, C c, D d, E e) -> test(a, b, c, d, e) && other.test(a, b, c, d, e);
    }

    /**
     * Returns a predicate that represents the logical negation of this
     * predicate.
     *
     * @return a predicate that represents the logical negation of this
     * predicate
     */
    default QuinPredicate<A, B, C, D, E> negate() {
        return (A a, B b, C c, D d, E e) -> !test(a, b, c, d, e);
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * OR of this predicate and another.  When evaluating the composed
     * predicate, if this predicate is {@code true}, then the {@code other}
     * predicate is not evaluated.
     *
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be logically-ORed with this
     *              predicate
     * @return a composed predicate that represents the short-circuiting logical
     * OR of this predicate and the {@code other} predicate
     * @throws NullPointerException if other is null
     */
    default QuinPredicate<A, B, C, D, E> or(QuinPredicate<? super A, ? super B, ? super C, ? super D, ? super E> other) {
        Objects.requireNonNull(other);
        return (A a, B b, C c, D d, E e) -> test(a, b, c, d, e) || other.test(a, b, c, d, e);
    }
}
