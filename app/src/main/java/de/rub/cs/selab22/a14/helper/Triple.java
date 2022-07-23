package de.rub.cs.selab22.a14.helper;

public class Triple<G,H,I> {

    private G first;
    private H second;
    private I third;

    public Triple(G f, H s, I t) {
        this.first = f;
        this.second = s;
        this.third = t;
    }

    public G getFirst() {
        return first;
    }

    public H getSecond() {
        return second;
    }

    public I getThird() {
        return third;
    }
}
