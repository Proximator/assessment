package com.goeuro.view.impl;

import au.com.bytecode.opencsv.CSVWriter;
import com.goeuro.model.CSVPosition;
import com.goeuro.view.Generator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CSVGenerator implements Generator<CSVPosition> {

    private static final Logger logger = LoggerFactory.getLogger(CSVGenerator.class);
    private CSVWriter writer;

    public CSVGenerator(CSVWriter writer) throws IOException {
        this.writer = writer;
    }

    public CSVGenerator(String fileName) throws IOException {
        System.out.println("  " + fileName);
        writer = new CSVWriter(new FileWriter(fileName), ';', CSVWriter.NO_QUOTE_CHARACTER);
    }

    @Override
    public void generate(List<CSVPosition> positions) {

        try (CSVWriter writer = getWriter()) {

            writer.writeNext(CSVPosition.toHeader());
            for (CSVPosition position : positions) {
                writer.writeNext(position.toArray());
            }
        } catch (IOException e) {
            logger.error("Unable to generate CSV file", e);
        }
    }

    protected CSVWriter getWriter() throws IOException {
        return writer;
    }

}
