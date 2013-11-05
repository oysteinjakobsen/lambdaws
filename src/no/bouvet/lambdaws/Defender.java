package no.bouvet.lambdaws;


public interface Defender {
    String x = "";

    void foo();

    default String getX() {
        return x;
    }
}
