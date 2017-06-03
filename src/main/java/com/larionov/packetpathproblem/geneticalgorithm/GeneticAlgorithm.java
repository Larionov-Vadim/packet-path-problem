package com.larionov.packetpathproblem.geneticalgorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.larionov.packetpathproblem.entity.Dataset;
import com.larionov.packetpathproblem.entity.PacketPath;

public class GeneticAlgorithm {

    private Context context;

    private Random random;

    private Mutation<PacketPath> mutation;

    private Crossover<PacketPath> crossover;

    public GeneticAlgorithm(Context context, Mutation<PacketPath> mutation, Crossover<PacketPath> crossover) {
        this.context = context;
        this.mutation = mutation;
        this.crossover = crossover;
        random = new Random();
    }

    public PacketPath findPacketPath(Dataset dataset) {
        int sourceNode = dataset.getSourceNode();
        int destinationNode = dataset.getDestinationNode();
        int populationSize = context.getPopulationSize();
        int numberOfNodes = dataset.getNumberOfNodes();
        Matrix matrix = dataset.getMatrix();

        List<PacketPath> population = createPopulation(sourceNode, destinationNode, populationSize, numberOfNodes);
        for (int i = 0; i < context.getMaxEpochs(); ++i) {
            population = createNewPopulation(population, matrix);
            population.forEach(mutation::mutate);
        }

        return getTheBestPacketPath(population, matrix);
    }

    private List<PacketPath> createNewPopulation(List<PacketPath> population, Matrix matrix) {
        int numberOfRandomCandidates = context.getNumberOfRandomCandidates();

        List<PacketPath> newPopulation = new ArrayList<>(population.size());
        while (newPopulation.size() < population.size()) {
            List<PacketPath> fathers = getRandomPacketPaths(population, numberOfRandomCandidates);
            PacketPath father = getTheBestPacketPath(fathers, matrix);

            List<PacketPath> mothers = getRandomPacketPaths(population, numberOfRandomCandidates);
            PacketPath mother = getTheBestPacketPath(mothers, matrix);

            List<PacketPath> children = crossover.crossover(father, mother);
            if (newPopulation.size() + children.size() <= population.size()) {
                newPopulation.addAll(children);
            } else {
                int count = population.size() - newPopulation.size();
                newPopulation.addAll(children.subList(0, count));
            }
        }

        return newPopulation;
    }

    private PacketPath getTheBestPacketPath(List<PacketPath> population, Matrix matrix) {
        PacketPath result = population.get(0);
        long minDistance = population.get(0).getDistance(matrix);
        for (PacketPath packetPath : population) {
            long distance = packetPath.getDistance(matrix);
            if (distance < minDistance) {
                minDistance = distance;
                result = packetPath;
            }
        }
        return result;
    }

    private List<PacketPath> getRandomPacketPaths(List<PacketPath> population, int amount) {
        List<PacketPath> result = new ArrayList<>(amount);
        for (int i = 0; i < amount; ++i) {
            int randomIndex = random.nextInt(population.size());
            result.add(population.get(randomIndex));
        }
        return result;
    }

    private List<PacketPath> createPopulation(int sourceNode, int destinationNode,
                                              int populationSize, int lengthChromosome) {
        List<PacketPath> population = new ArrayList<>(lengthChromosome);
        List<Integer> nodeVariants = IntStream.range(0, lengthChromosome)
                .filter(value -> value != sourceNode)
                .filter(value -> value != destinationNode)
                .boxed()
                .collect(Collectors.toList());

        for (int i = 0; i < populationSize; ++i) {
            int[] chromosome = new int[lengthChromosome];
            chromosome[0] = sourceNode;
            chromosome[lengthChromosome - 1] = destinationNode;

            Collections.shuffle(nodeVariants);
            for (int j = 1; j < lengthChromosome - 1; ++j) {
                chromosome[j] = nodeVariants.get(j - 1);
            }

            PacketPath packetPath = new PacketPath();
            packetPath.setNodes(chromosome);
            population.add(packetPath);
        }

        return population;
    }
}
