package io;

import logic.Selectable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Created by jonas on 16.4.20.
 */
public class CsvWriter {
    private String separator;
    private List<Selectable> list;

    public CsvWriter(List<Selectable> objects) {
        this.list = objects;
        this.separator = ",";
    }

    public CsvWriter(List<Selectable> objects, String separator) {
        this.list = objects;
        this.separator = separator;
    }

    public void write(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName);
        writeHeader(fileWriter);
        writeData(fileWriter);
    }

    private void writeHeader(FileWriter fileWriter) throws IOException{
        if (list.get(0) != null) {
            String[] headerNames = list.get(0).getColumnNames();
            for (int i = 0; i < headerNames.length; i++) {
                fileWriter.append(headerNames[i]);
                if (i < headerNames.length-1)
                    fileWriter.append(separator);
            }
            fileWriter.append("\n");
        }
    }

    private void writeData(FileWriter fileWriter) throws IOException {
        for (Selectable selectable : list) {
            String[] stream1 = selectable.getFields();
            for (int i = 0; i < stream1.length; i++) {
                String field = "\"" + stream1[i] + "\"";
                fileWriter.append(field);
                if (i < stream1.length - 1)
                    fileWriter.append(separator);
            }
            fileWriter.append("\n");
        }
        fileWriter.close();
    }
}
