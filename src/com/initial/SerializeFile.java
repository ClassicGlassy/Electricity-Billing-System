package com.initial;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class SerializeFile {

    public SerializeFile(String user, String pwd, String port) {
        Configuration Object = new Configuration(user, pwd, port);

        //Serialization
        try {
//            Saving of object in a file.
            FileOutputStream file = new FileOutputStream("config.cfg");
            ObjectOutputStream out = new ObjectOutputStream(file);

            out.writeObject(Object);
            out.close();
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
