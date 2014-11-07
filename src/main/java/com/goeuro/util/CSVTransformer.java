package com.goeuro.util;


import com.goeuro.model.CSVPosition;
import com.goeuro.model.Position;

public class CSVTransformer {

    public static CSVPosition process(Position position) {
        return new CSVPosition(position.get_id(), position.getName(), position.getType(), position.getGeo_position().getLatitude(), position.getGeo_position().getLongitude());
    }
}
