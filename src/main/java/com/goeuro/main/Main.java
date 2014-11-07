package com.goeuro.main;

import com.goeuro.controller.CSVController;
import com.goeuro.model.CSVPosition;
import com.goeuro.service.PositionService;
import com.goeuro.view.Generator;
import com.goeuro.view.impl.CSVGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {


        if (args == null || args.length == 0) {
            logger.error("Invalid command line: missing input parameter \n Example: java -jar GoEuroTest.jar \"STRING\"");
            System.exit(-1);
        }

        try {
            PositionService positionService = new PositionService();
            Generator<CSVPosition> viewGenerator = new CSVGenerator("data.csv");
            CSVController controller = new CSVController(positionService, viewGenerator);
            controller.generateCSV(args[0]);

        } catch (IOException e) {
            logger.error("Unable to access csv file", e);
        }


    }
}
