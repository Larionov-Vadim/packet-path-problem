package com.larionov.packetpathproblem.geneticalgorithm;

import java.util.List;

public interface Crossover<T> {

    List<T> crossover(T father, T mother);
}
