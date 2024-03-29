package net.projektcontingency.titanium.internal;

public class Pair<F, S> {
    private final F first;

    private final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }
    public S getSecond() {
        return second;
    }

    public F getLeft() {
        return first;
    }
    public S getRight() {
        return second;
    }

    public static <T,R> Pair<T,R> pair(T first, R second) {
        return new Pair<>(first, second);
    }

    public static <T,R> Pair<T,R> of(T first, R second) {
        return new Pair<>(first, second);
    }
}