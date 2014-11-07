package com.goeuro.view.impl;

import au.com.bytecode.opencsv.CSVWriter;
import com.goeuro.model.CSVPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class CSVGeneratorTest {

    @Mock
    private CSVWriter writer;

    @InjectMocks
    private CSVGenerator csvGenerator;

    @Test
    public void shouldWriteHeaderOnlyIfEmptyLocation() throws Exception {

        csvGenerator.generate(new ArrayList<CSVPosition>());

        verify(writer, times(1)).writeNext(any(String[].class));
        verify(writer).writeNext(eq(CSVPosition.toHeader()));

    }

    @Test
    public void shouldWriteHeaderAndLineLocations() throws Exception {

        CSVPosition csvPosition1 = new CSVPosition(11, "Berlin", "location", 45.50298, 10.04366);
        CSVPosition csvPosition2 = new CSVPosition(22, "Paris", "location", 48.85341, 2.3488);

        csvGenerator.generate(Arrays.asList(csvPosition1, csvPosition2));

        verify(writer, times(3)).writeNext(any(String[].class));

        verify(writer).writeNext(eq(CSVPosition.toHeader()));
        verify(writer).writeNext(eq(csvPosition1.toArray()));
        verify(writer).writeNext(eq(csvPosition2.toArray()));
    }


}