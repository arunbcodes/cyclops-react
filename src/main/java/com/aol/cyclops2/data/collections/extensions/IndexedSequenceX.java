package com.aol.cyclops2.data.collections.extensions;

import com.aol.cyclops2.data.collections.extensions.standard.LazyCollectionX;
import com.aol.cyclops2.internal.stream.ReactiveStreamX;
import cyclops.collections.mutable.ListX;
import cyclops.stream.FutureStream;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Interface that represents a FluentSequence of data
 * <p>
 * Supports operations such as adding / removing elements via a Fluent API
 *
 * @param <T> the type of elements held in this toX
 * @author johnmcclean
 */
public interface IndexedSequenceX<T> extends FluentCollectionX<T> {

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.FluentCollectionX#plus(java.lang.Object)
     */
    @Override
    public IndexedSequenceX<T> plus(T e);

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.FluentCollectionX#plusAll(java.util.Collection)
     */
    @Override
    public IndexedSequenceX<T> plusAll(Collection<? extends T> list);

    /**
     * Replace the value at the specifed index with the supplied value
     *
     * @param i Index to one value at
     * @param e Value to use
     * @return FluentSequence with value replaced
     */
    public IndexedSequenceX<T> with(int i, T e);

    /**
     * Add the supplied element at the supplied index
     *
     * @param i Index to add element at
     * @param e Element to add
     * @return FluentSequence with element added
     */
    public IndexedSequenceX<T> plus(int i, T e);

    /**
     * Add all of the supplied elements at the supplied index
     *
     * @param i    Index to add element at
     * @param list Collection of elements to add
     * @return FluentSequence with elements added
     */
    public IndexedSequenceX<T> plusAll(int i, Collection<? extends T> list);

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.FluentCollectionX#minus(java.lang.Object)
     */
    @Override
    public IndexedSequenceX<T> minus(Object e);

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.FluentCollectionX#minusAll(java.util.Collection)
     */
    @Override
    public IndexedSequenceX<T> minusAll(Collection<?> list);

    /**
     * Remove the element at the supplied index
     *
     * @param i Index at which to remvoe element
     * @return FluentSequence with element removed
     */
    public IndexedSequenceX<T> minus(int i);

    /**
     * Create a sub sequence between the two supplied index
     *
     * @param start Index of our sub sequence (inclusive)
     * @param end   Index of our sub sequence (exclusive)
     * @return
     */
    public IndexedSequenceX<T> subList(int start, int end);

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.CollectionX#filter(java.util.function.Predicate)
     */
    @Override
    IndexedSequenceX<T> filter(Predicate<? super T> pred);

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.CollectionX#map(java.util.function.Function)
     */
    @Override
    <R> IndexedSequenceX<R> map(Function<? super T, ? extends R> mapper);

    /* (non-Javadoc)
     * @see com.aol.cyclops2.data.collections.extensions.CollectionX#flatMap(java.util.function.Function)
     */
    @Override
    <R> IndexedSequenceX<R> flatMap(Function<? super T, ? extends Iterable<? extends R>> mapper);

    /**
     * Intercalate
     *
     * @param listOfLists Index of our sub sequence (inclusive)
     * @return List with current IndexedSequenceX inserted between each List.
     */
    default ListX<T> intercalate(List<List<T>> listOfLists) {
        return ListX.fromIterable(listOfLists).intersperse(this.toListX()).flatMap(x -> x);
    }
}
