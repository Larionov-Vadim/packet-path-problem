package com.larionov.packetpathproblem;

import com.larionov.packetpathproblem.entity.Dataset;
import com.larionov.packetpathproblem.entity.PacketPath;
import com.larionov.packetpathproblem.geneticalgorithm.Context;
import com.larionov.packetpathproblem.geneticalgorithm.Crossover;
import com.larionov.packetpathproblem.geneticalgorithm.CrossoverImpl;
import com.larionov.packetpathproblem.geneticalgorithm.GeneticAlgorithm;
import com.larionov.packetpathproblem.geneticalgorithm.Mutation;
import com.larionov.packetpathproblem.geneticalgorithm.SingleGenMutation;
import com.larionov.packetpathproblem.util.Reader;

public class Main {

    private static final String INPUT_FILE = "input.csv";

    public static void main(String[] args) {

        Context context = createContext();

        Reader reader = new Reader();
        Dataset dataset = reader.read(INPUT_FILE, context.getNonExistingPathWeight());

        Mutation<PacketPath> mutation = new SingleGenMutation(context.getMutationRate());
        Crossover<PacketPath> crossover = new CrossoverImpl();

        GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm(context, mutation, crossover);
        PacketPath solution = geneticAlgorithm.findPacketPath(dataset);

        long distance = dataset.getMatrix().getDistance(solution.getNodes());
        System.out.println("Solution: " + solution.getResultAsString());
        System.out.println("Distance = " + distance);
    }

    private static Context createContext() {
        Context context = new Context();
        context.setMutationRate(0.001);
        context.setMaxEpochs(100);
        context.setPopulationSize(500);
        context.setNonExistingPathWeight(1_000_000);
        context.setNumberOfRandomCandidates(10);

        return context;
    }
}
