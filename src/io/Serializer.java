package io;

import logic.Selectable;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

/**
 * Created by jonas on 16.4.21.
 */
public class Serializer {

    public static void serialize(List<Selectable> list, String filename)
    {
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            for (Selectable selectable : list)
                out.writeObject(selectable);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in employee.ser");
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
