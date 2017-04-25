package com.larionov.packetpathproblem.geneticalgorithm;

@FunctionalInterface
public interface Mutation<T> {

    void mutate(T mutableObject);
}
