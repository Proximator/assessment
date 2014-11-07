package com.goeuro.controller;


import com.goeuro.util.CSVTransformer;
import com.goeuro.model.CSVPosition;
import com.goeuro.model.Position;
import com.goeuro.service.PositionService;
import com.goeuro.view.Generator;
import com.goeuro.view.impl.CSVGenerator;

import java.util.ArrayList;
import java.util.List;

public class CSVController {

    private PositionService positionService;
    private Generator<CSVPosition> viewGenerator;

    public CSVController(PositionService positionService, Generator<CSVPosition> viewGenerator) {
        this.positionService = positionService;
        this.viewGenerator = viewGenerator;
    }

    public void generateCSV(String location){

        Position[] positions = positionService.query(location);
        List<CSVPosition> csvPositions = new ArrayList<>();

        for (Position position : positions) {
            csvPositions.add(CSVTransformer.process(position));
        }
        viewGenerator.generate(csvPositions);
    }


}
