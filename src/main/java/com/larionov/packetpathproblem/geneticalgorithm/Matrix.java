package com.larionov.packetpathproblem.geneticalgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Matrix {

    private static final Logger LOGGER = LoggerFactory.getLogger(Matrix.class);

    private int[][] matrixAdjacency;

    public Matrix(int numberOfNodes) {
        matrixAdjacency = new int[numberOfNodes][];
        for (int i = 0; i < numberOfNodes; ++i) {
            matrixAdjacency[i] = new int[numberOfNodes];
        }
    }

    public long getDistance(int fromVertex, int toVertex) {
        return matrixAdjacency[fromVertex][toVertex];
    }

    public long getDistance(int[] nodeIndexes) {
        long sum = 0L;
        for (int i = 0; i < nodeIndexes.length - 1; ++i) {
            int fromNode = nodeIndexes[i];
            int toNode = nodeIndexes[i + 1];
            sum += matrixAdjacency[fromNode][toNode];
        }
        return sum;
    }

    public int getNumberOfNodes() {
        return matrixAdjacency.length;
    }

    public boolean isValid() {
        return isSquareMatrix() && isSymmetricMatrix() && isCorrectDiagonal();
    }

    private boolean isSquareMatrix() {
        int lengthSide = matrixAdjacency.length;
        int rowNumber = 1;
        for (int[] row : matrixAdjacency) {
            if (row.length != lengthSide) {
                LOGGER.info("Matrix is not square. Check {} row", rowNumber);
                return false;
            }
            ++rowNumber;
        }
        return true;
    }

    private boolean isSymmetricMatrix() {
        for (int i = 0; i < matrixAdjacency.length; ++i) {
            for (int j = i; j < matrixAdjacency.length; ++j) {
                if (matrixAdjacency[i][j] != matrixAdjacency[j][i]) {
                    LOGGER.warn("Matrix is not symmetric (i={}, j={})", i, j);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isCorrectDiagonal() {
        for (int i = 0; i < matrixAdjacency.length; ++i) {
            if (matrixAdjacency[i][i] != 0) {
                LOGGER.warn("Check element [{}][{}]", i, i);
                return false;
            }
        }
        return true;
    }

    public void setNode(int row, int column, int value) {
        matrixAdjacency[row][column] = value;
    }
}
