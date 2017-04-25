package com.larionov.packetpathproblem.entity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.larionov.packetpathproblem.geneticalgorithm.Matrix;
import com.larionov.packetpathproblem.util.Utils;

public class Dataset {

    private static final Logger LOGGER = LoggerFactory.getLogger(Dataset.class);

    private int sourceNode;

    private int destinationNode;

    private Matrix matrix;

    public Dataset(int numberOfNodes) {
        matrix = new Matrix(numberOfNodes);
    }

    public boolean isValid() {
        return isValidSource() && isValidDestination() && matrix.isValid();
    }

    private boolean isValidSource() {
        if (!isValidNode(sourceNode)) {
            LOGGER.warn("Incorrect source node = {}", sourceNode);
            return false;
        }
        return true;
    }

    private boolean isValidDestination() {
        if (!isValidNode(destinationNode)) {
            LOGGER.warn("Incorrect destination node = {}", destinationNode);
            return false;
        }
        return true;
    }

    private boolean isValidNode(int nodeIndex) {
        return nodeIndex >= 0 && nodeIndex < matrix.getNumberOfNodes();
    }

    public void setRow(int rowIndex, String[] row, int defaultValue) {
        for (int column = 0; column < row.length; ++column) {
            int value = Utils.tryParseInteger(row[column], defaultValue);
            matrix.setNode(rowIndex, column, value);
        }
    }

    public int getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(int sourceNode) {
        this.sourceNode = sourceNode;
    }

    public int getDestinationNode() {
        return destinationNode;
    }

    public void setDestinationNode(int destinationNode) {
        this.destinationNode = destinationNode;
    }

    public int getNumberOfNodes() {
        return matrix.getNumberOfNodes();
    }

    public Matrix getMatrix() {
        return matrix;
    }
}
