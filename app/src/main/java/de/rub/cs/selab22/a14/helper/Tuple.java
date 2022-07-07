package de.rub.cs.selab22.a14.helper;

public class Tuple<G,H> {

    private G first;
    private H second;

    public Tuple(G f, H s) {
        this.first = f;
        this.second = s;
    }

    public G getFirst() {
        return first;
    }

    public H getSecond() {
        return second;
    }
}
