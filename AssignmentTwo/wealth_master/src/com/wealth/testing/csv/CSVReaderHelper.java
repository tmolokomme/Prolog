package com.wealth.testing.csv;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class CSVReaderHelper {
    
    public static List<String[]> readFileFromClasspath(String filename, char separatorChar, char quoteChar) throws IOException  {
        InputStream is = CSVReaderHelper.class.getResourceAsStream("/"+filename);
        CSVReader reader = new CSVReader(new InputStreamReader(is), separatorChar, quoteChar);
        List<String[]> myEntries = reader.readAll();
        reader.close();
        return myEntries;
    }
    
    public static List<String[]> readFileFromClasspath(String filename, char separatorChar) throws IOException  {
        InputStream is = CSVReaderHelper.class.getResourceAsStream("/"+filename);
        CSVReader reader = new CSVReader(new InputStreamReader(is), separatorChar);
        List<String[]> myEntries = reader.readAll();
        reader.close();
        return myEntries;
    }
}