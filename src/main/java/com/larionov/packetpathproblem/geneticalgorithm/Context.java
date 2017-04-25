package com.larionov.packetpathproblem.geneticalgorithm;

public class Context {

    /**
     * Вероятность мутации
     */
    private double mutationRate;

    private int maxEpochs;

    private int populationSize;

    private int nonExistingPathWeight;

    private int numberOfRandomCandidates;

    public double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public int getMaxEpochs() {
        return maxEpochs;
    }

    public void setMaxEpochs(int maxEpochs) {
        this.maxEpochs = maxEpochs;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(int populationSize) {
        this.populationSize = populationSize;
    }

    public int getNonExistingPathWeight() {
        return nonExistingPathWeight;
    }

    public void setNonExistingPathWeight(int nonExistingPathWeight) {
        this.nonExistingPathWeight = nonExistingPathWeight;
    }

    public int getNumberOfRandomCandidates() {
        return numberOfRandomCandidates;
    }

    public void setNumberOfRandomCandidates(int numberOfRandomCandidates) {
        this.numberOfRandomCandidates = numberOfRandomCandidates;
    }
}
