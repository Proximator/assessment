package com.goeuro.controller;

import com.goeuro.model.CSVPosition;
import com.goeuro.model.GeoPosition;
import com.goeuro.model.Position;
import com.goeuro.service.PositionService;
import com.goeuro.view.Generator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CSVControllerTest {

    @Captor
    ArgumentCaptor<List<CSVPosition>> captor;
    @Mock
    private PositionService positionService;
    @Mock
    private Generator<CSVPosition> viewGenerator;
    @InjectMocks
    private CSVController csvController;

    @Test
    public void shouldGenerateCSV() throws Exception {


        when(positionService.query("London")).thenReturn(new Position[]{getLondonPosition()});
        csvController.generateCSV("London");

        verify(positionService).query(Mockito.eq("London"));
        verify(viewGenerator).generate(captor.capture());

        List<CSVPosition> csvPositions = captor.getValue();
        assertThat(csvPositions.size(), is(1));

        CSVPosition csvPosition = csvPositions.get(0);

        assertThat(csvPosition.get_id(), is(33));
        assertThat(csvPosition.getLatitude(), is(51.50853));
        assertThat(csvPosition.getLongitude(), is(-0.12574));

    }

    private Position getLondonPosition() {

        Position position = new Position();
        position.set_id(33);
        position.setName("London");
        position.setType("Location");
        GeoPosition geoPosition = new GeoPosition();
        geoPosition.setLatitude(51.50853);
        geoPosition.setLongitude(-0.12574);
        position.setGeo_position(geoPosition);

        return position;
    }
}