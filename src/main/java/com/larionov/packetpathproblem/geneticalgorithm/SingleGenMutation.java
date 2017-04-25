package com.larionov.packetpathproblem.geneticalgorithm;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.larionov.packetpathproblem.entity.PacketPath;

public class SingleGenMutation implements Mutation<PacketPath> {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleGenMutation.class);

    private static final int MAX_ITERATIONS = 100;

    private Random random = new Random();

    private double mutationRate;

    public SingleGenMutation(double mutationRate) {
        this.mutationRate = mutationRate;
    }

    @Override
    public void mutate(PacketPath packetPath) {
        int sourceNode = packetPath.getSourceNode();
        int destinationNode = packetPath.getDestinationNode();

        if (random.nextDouble() <= mutationRate) {
            int[] nodes = packetPath.getNodes();
            int randomIndex = getRandomIndex(sourceNode, destinationNode, nodes.length);
            int randomValue = random.nextInt(nodes.length);
            nodes[randomIndex] = randomValue;
        }
    }

    private int getRandomIndex(int sourceNode, int destinationNode, int bound) {
        int value;
        for (int i = 0; i < MAX_ITERATIONS; ++i) {
            value = random.nextInt(bound);
            if (value != sourceNode && value != destinationNode) {
                return value;
            }
        }
        LOGGER.warn("Max iterations reached");
        return 0;
    }
}
