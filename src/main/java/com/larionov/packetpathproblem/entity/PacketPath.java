package com.larionov.packetpathproblem.entity;

import java.util.Arrays;
import java.util.StringJoiner;

import com.larionov.packetpathproblem.geneticalgorithm.Matrix;

public class PacketPath {

    private int[] nodes;

    public long getDistance(Matrix matrix) {
        return matrix.getDistance(nodes);
    }

    public int getNumberOfNodes() {
        return nodes.length;
    }

    public int[] getNodes() {
        return nodes;
    }

    public void setNodes(int[] nodes) {
        this.nodes = nodes;
    }

    public int getSourceNode() {
        return nodes[0];
    }

    public int getDestinationNode() {
        return nodes[nodes.length - 1];
    }

    public int getNodeByIndex(int index) {
        return nodes[index];
    }

    public void setNodeByIndex(int index, int value) {
        nodes[index] = value;
    }

    public String getResultAsString() {
        int previous = -1;
        StringJoiner stringJoiner = new StringJoiner("-");
        for (int node : nodes) {
            if (node != previous) {
                stringJoiner.add(Integer.toString(node));
                previous = node;
            }
        }
        return stringJoiner.toString();
    }

    @Override
    public String toString() {
        return "PacketPath{ " + Arrays.toString(nodes) + " }";
    }
}