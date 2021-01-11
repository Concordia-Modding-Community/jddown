package ca.concordia.jddown.utils;

public class Tuple<T, U> {
    private T t;
    private U u;

    public Tuple(T t, U u) {
        this.t = t;
        this.u = u;
    }

    public T first() {
        return t;
    }

    public U second() {
        return u;
    }
}
