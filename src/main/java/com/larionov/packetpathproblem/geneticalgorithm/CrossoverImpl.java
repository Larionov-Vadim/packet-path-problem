package com.larionov.packetpathproblem.geneticalgorithm;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.larionov.packetpathproblem.entity.PacketPath;

public class CrossoverImpl implements Crossover<PacketPath> {

    private static final Logger LOGGER = LoggerFactory.getLogger(CrossoverImpl.class);

    private Random random = new Random();

    @Override
    public List<PacketPath> crossover(PacketPath father, PacketPath mother) {
        if (!isCorrectParents(father, mother)) {
            throw new IllegalStateException("Incorrect parents " + father + " " + mother);
        }

        int numberOfNodes = father.getNumberOfNodes();

        int[] firstChildNodes = new int[numberOfNodes];
        int[] secondChildNodes = new int[numberOfNodes];

        int randomIndex = random.nextInt(numberOfNodes - 2) + 1;
        for (int i = 0; i < numberOfNodes; ++i) {
            if (i <= randomIndex) {
                firstChildNodes[i] = father.getNodeByIndex(i);
                secondChildNodes[i] = mother.getNodeByIndex(i);
            } else {
                firstChildNodes[i] = mother.getNodeByIndex(i);
                secondChildNodes[i] = father.getNodeByIndex(i);
            }
        }

        PacketPath firstChild = new PacketPath();
        firstChild.setNodes(firstChildNodes);

        PacketPath secondChild = new PacketPath();
        secondChild.setNodes(secondChildNodes);

        return Arrays.asList(firstChild, secondChild);
    }

    private boolean isCorrectParents(PacketPath father, PacketPath mother) {
        if (father.getNumberOfNodes() != mother.getNumberOfNodes()) {
            LOGGER.warn("Chromosome lengths aren't equals: father = {}, mother = {}", father, mother);
            return false;
        }

        if (father.getSourceNode() != mother.getSourceNode()) {
            LOGGER.warn("Source nodes aren't equals: father = {}; mother = {}", father, mother);
            return false;
        }

        if (father.getDestinationNode() != mother.getDestinationNode()) {
            LOGGER.warn("Destionation nodes aren't equals: father = {}; mother = {}", father, mother);
            return false;
        }

        return true;
    }
}
