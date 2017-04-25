package com.larionov.packetpathproblem.util;

import java.io.FileReader;
import java.io.IOException;

import com.larionov.packetpathproblem.entity.Dataset;
import com.opencsv.CSVReader;

public class Reader {

    public Dataset read(String filePath, int defaultValue) {
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] fromAndToVertexes = csvReader.readNext();
            int fromVertex = Integer.parseInt(fromAndToVertexes[0]);
            int toVertex = Integer.parseInt(fromAndToVertexes[1]);

            String[] row = csvReader.readNext();
            int lengthSide = row.length;
            Dataset dataset = new Dataset(lengthSide);
            dataset.setRow(0, row, defaultValue);
            dataset.setSourceNode(fromVertex);
            dataset.setDestinationNode(toVertex);

            for (int i = 1; i < lengthSide; ++i) {
                row = csvReader.readNext();
                dataset.setRow(i, row, defaultValue);
            }

            if (!dataset.isValid()) {
                throw new IllegalStateException("Dataset is not valid");
            }

            return dataset;
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
